package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            stmt.setString(2, usuario.getNombreUsuario());
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
}
