package daoImpl;

import dao.ClienteDAO;
import dominio.Conexion;
import entidades.Cliente;
import entidades.Nacionalidad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
	
	@Override
	public boolean agregar(Cliente cliente) {
		PreparedStatement statement;
		Connection conn = Conexion.getSQLConexion();
		boolean agregado= false;		
		String sql = "INSERT INTO cliente (Dni, Cuil, Nombre, Apellido, Sexo, ID_Nacionalidad, Fecha_Nacimiento, ID_Direccion, Correo_Electronico, Telefono, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getDni());
            statement.setString(2, cliente.getCuil());
            statement.setString(3, cliente.getNombre());
            statement.setString(4, cliente.getApellido());
            statement.setString(5, String.valueOf(cliente.getSexo()));
            statement.setInt(6, cliente.getNacionalidad().getIdNacionalidad());
            statement.setDate(7, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            statement.setInt(8, cliente.getIdDireccion());
            statement.setString(9, cliente.getCorreoElectronico());
            statement.setString(10, cliente.getTelefono());
            statement.setBoolean(11, cliente.isEstado());	
		
            if (statement.executeUpdate() > 0) {
                conn.commit();
                agregado = true;
            }
		} catch (SQLException e) {
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
	public boolean modificar(Cliente cliente) {
		PreparedStatement statement;
		Connection conn = Conexion.getSQLConexion();
		boolean modificado= false;		
		String sql = "UPDATE cliente SET Cuil = ?, Nombre = ?, Apellido = ?, Sexo = ?, ID_Nacionalidad = ?, Fecha_Nacimiento = ?, ID_Direccion = ?, Correo_Electronico = ?, Telefono = ?, Estado = ? WHERE Dni = ?";
		try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getCuil());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getApellido());
            statement.setString(4, String.valueOf(cliente.getSexo()));
            statement.setInt(5, cliente.getNacionalidad().getIdNacionalidad());
            statement.setDate(6, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            statement.setInt(7, cliente.getIdDireccion());
            statement.setString(8, cliente.getCorreoElectronico());
            statement.setString(9, cliente.getTelefono());
            statement.setBoolean(10, cliente.isEstado());
            statement.setString(11, cliente.getDni());

            if (statement.executeUpdate() > 0) {
                conn.commit();
                modificado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return modificado;		        
	}
	
	@Override
	public boolean eliminar(int idCliente) {
		PreparedStatement statement;
        Connection conn = Conexion.getSQLConexion();
        boolean eliminado = false;
		String sql = "DELETE FROM cliente WHERE ID_Cliente = ?";
		try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idCliente);

            if (statement.executeUpdate() > 0) {
                conn.commit();
                eliminado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return eliminado;
	}
	
	@Override
	public Cliente obtenerXId(int idCliente) {
		return null;
		
	}
	
	@Override
	public List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<>();
		
		PreparedStatement statement;
        ResultSet rs;
        Connection conn = Conexion.getSQLConexion();
        String sql = "SELECT * FROM cliente";

        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_Cliente"));
                cliente.setDni(rs.getString("Dni"));
                cliente.setCuil(rs.getString("Cuil"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setSexo(rs.getString("Sexo").charAt(0));
                Nacionalidad nacionalidad= new Nacionalidad();
                nacionalidad.setIdNacionalidad(rs.getInt("ID_Nacionalidad"));
                cliente.setNacionalidad(nacionalidad);
                cliente.setFechaNacimiento(rs.getDate("Fecha_Nacimiento"));
                cliente.setIdDireccion(rs.getInt("ID_Direccion"));
                cliente.setCorreoElectronico(rs.getString("Correo_Electronico"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setEstado(rs.getBoolean("Estado"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
	}
 }
