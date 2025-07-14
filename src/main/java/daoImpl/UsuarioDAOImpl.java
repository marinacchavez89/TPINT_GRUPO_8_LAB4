package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UsuarioDAO; 
import dominio.Conexion;
import entidades.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public boolean agregarUsuario(Usuario usuario) {
		Connection conn = Conexion.getSQLConexion();
        boolean agregado = false;

        String sql = "INSERT INTO usuario (id_cliente, nombre_usuario, contrasena, tipo_usuario) VALUES (?,?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
  

            if (usuario.getIdCliente() != null) {
                stmt.setInt(1, usuario.getIdCliente());
            } else {
                stmt.setNull(1, java.sql.Types.INTEGER);
            }
            stmt.setString(2, usuario.getNombreUsuario() + ".Banco" );
            stmt.setString(3, usuario.getContrasena());
            stmt.setString(4, usuario.getTipoUsuario());
            
            if (stmt.executeUpdate() > 0) {
                conn.commit();
                agregado = true;
            }
        } catch (SQLException e) {
        	System.out.println("❌ Error al insertar usuario:");
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		
		return agregado;
	}
	
	@Override
    public Usuario obtenerUsuarioPorCredenciales(String nombreUsuario, String contrasena) {
		Usuario usuario = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "SELECT id_usuario, id_cliente, nombre_usuario, contrasena, estado, tipo_usuario FROM usuario WHERE nombre_usuario = ? AND contrasena = ?";

        try {
            conn = Conexion.getSQLConexion();
            statement = conn.prepareStatement(sql);
            statement.setString(1, nombreUsuario);
            statement.setString(2, contrasena);
            rs = statement.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));

                Object idClienteObj = rs.getObject("id_cliente");
                if (idClienteObj != null) {
                    usuario.setIdCliente((Integer) idClienteObj);
                } else {
                    usuario.setIdCliente(null);
                }

                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setEstado(rs.getBoolean("estado"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }
	
	@Override
	public int obtenerIdPorCliente(int idCliente) {
	    int idUsuario = 0;
	    Connection conn = Conexion.getSQLConexion();
	    String sql = "SELECT id_usuario FROM usuario WHERE id_cliente = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idCliente);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            idUsuario = rs.getInt("id_usuario");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return idUsuario;
	}
	
	@Override
	public Integer obtenerIdClientePorDni(String dni) {
	    Integer idCliente = null;
	    Connection conn = Conexion.getSQLConexion();
	    String sql = "SELECT id_cliente FROM cliente WHERE dni = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, dni);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            idCliente = rs.getInt("id_cliente");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return idCliente;
	}
	
	@Override
	public boolean actualizarPasswordPorDni(String dni, String nuevaPassword) {
	    boolean passEditada = false;
	    Integer idCliente = obtenerIdClientePorDni(dni);

	    System.out.println("ID Cliente encontrado para DNI " + dni + ": " + idCliente);

	    if (idCliente != null) {
	        int idUsuario = obtenerIdPorCliente(idCliente);
	        System.out.println("ID Usuario encontrado para ID Cliente " + idCliente + ": " + idUsuario);

	        if (idUsuario > 0) {
	            Connection conn = null;
	            PreparedStatement stmt = null;

	            try {
	                conn = Conexion.getSQLConexion();
	                conn.setAutoCommit(false); // para controlar commit manualmente
	                
	                String query = "UPDATE usuario SET contrasena = ? WHERE id_usuario = ?";
	                stmt = conn.prepareStatement(query);
	                stmt.setString(1, nuevaPassword);
	                stmt.setInt(2, idUsuario);

	                int filasAfectadas = stmt.executeUpdate();
	                System.out.println("Filas afectadas al actualizar password: " + filasAfectadas);
	                
	                if (filasAfectadas > 0) {
	                    conn.commit();
	                    passEditada = true;
	                } else {
	                    conn.rollback();
	                }

	            } catch (Exception e) {
	                e.printStackTrace();
	                try { if (conn != null) conn.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
	            } finally {
	                try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	                try { if (conn != null) conn.close(); } catch (Exception e) {}
	            }
	        }
	    }

	    return passEditada;
	}
}

