package daoImpl;

import dao.ClienteDAO;
import dominio.Conexion;
import entidades.Cliente;
import entidades.Direccion;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.PaisResidencia;
import entidades.Provincia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
	
	@Override
	public boolean agregar(Cliente cliente) {
		
		String sql = "INSERT INTO cliente (Dni, Cuil, Nombre, Apellido, Sexo, ID_Nacionalidad, Fecha_Nacimiento, ID_Direccion, Correo_Electronico, Telefono, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
		PreparedStatement statement = null;
		boolean agregado= false;		
		Connection conn = null;
		ResultSet rsKKeys = null;
		try {
			conn = Conexion.getSQLConexion();
			conn.setAutoCommit(false);
			
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cliente.getDni());
            statement.setString(2, cliente.getCuil());
            statement.setString(3, cliente.getNombre());
            statement.setString(4, cliente.getApellido());
            statement.setString(5, String.valueOf(cliente.getSexo()));
            statement.setInt(6, cliente.getNacionalidad().getIdNacionalidad());
            statement.setDate(7, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            statement.setInt(8, cliente.getDireccion().getIdDireccion());
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
	    boolean modificado = false;

	    // Modificar: La condición WHERE debe ser por ID_Cliente para asegurar la unicidad y permitir cambio de DNI.
	    String sql = "UPDATE cliente SET Cuil = ?, Nombre = ?, Apellido = ?, Sexo = ?, ID_Nacionalidad = ?, Fecha_Nacimiento = ?, ID_Direccion = ?, Correo_Electronico = ?, Telefono = ?, Estado = ?, Dni = ? WHERE ID_Cliente = ?";
	    try {
	        statement = conn.prepareStatement(sql);
	        statement.setString(1, cliente.getCuil());
	        statement.setString(2, cliente.getNombre());
	        statement.setString(3, cliente.getApellido());
	        statement.setString(4, String.valueOf(cliente.getSexo()));
	        statement.setInt(5, cliente.getNacionalidad().getIdNacionalidad());
	        statement.setDate(6, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
	        statement.setInt(7, cliente.getDireccion().getIdDireccion());
	        statement.setString(8, cliente.getCorreoElectronico());
	        statement.setString(9, cliente.getTelefono());
	        statement.setBoolean(10, cliente.isEstado());
	        statement.setString(11, cliente.getDni()); // DNI ahora se actualiza como un campo más
	        statement.setInt(12, cliente.getIdCliente()); // Condición WHERE por ID_Cliente

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
	    PreparedStatement statement = null;
	    Connection conn = Conexion.getSQLConexion();
	    boolean actualizado = false; // Renamed for clarity, as it's an update, not a delete

	    // SQL to logically delete the client by setting their 'estado' to FALSE
	    String sql = "UPDATE cliente SET estado = FALSE WHERE ID_Cliente = ?";

	    try {
	        statement = conn.prepareStatement(sql);
	        statement.setInt(1, idCliente);

	        // Execute the update. If more than 0 rows are affected, it was successful.
	        if (statement.executeUpdate() > 0) {
	            conn.commit();
	            actualizado = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            // Rollback the transaction in case of an error
	            conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        // It's good practice to close the statement and connection
	        try {
	            if (statement != null) statement.close();
	            if (conn != null) conn.close(); // Only if you manage connections here
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return actualizado;
	}
	
	@Override
	public int obtenerIdXDni(String dni) {
		PreparedStatement statement;
        Connection conn = Conexion.getSQLConexion();
	    String sql = "SELECT id_cliente FROM cliente WHERE dni = ?";
	    try{
	    	statement = conn.prepareStatement(sql);
	        statement.setString(1, dni);	        
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("id_cliente");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	@Override
	public Cliente obtenerXId(int idCliente) {
		Cliente cliente = null;
	    try {
	        Connection conn = Conexion.getSQLConexion();
	        String sql = "SELECT "
	                + "c.*, "
	                + "n.desc_nacionalidad, "
	                + "d.calle, d.numero, d.codigo_postal, "
	                + "l.id_localidad, l.nombre_localidad, "
	                + "p.id_provincia, p.nombre_pcia, "
	                + "pr.id_pais_residencia, pr.desc_pais_residencia "
	                + "FROM cliente c "
	                + "INNER JOIN nacionalidad n ON c.id_nacionalidad = n.id_nacionalidad "
	                + "INNER JOIN direccion d ON c.id_direccion = d.id_direccion "
	                + "INNER JOIN localidad l ON d.id_localidad = l.id_localidad "
	                + "INNER JOIN provincia p ON l.id_provincia = p.id_provincia "
	                + "INNER JOIN pais_residencia pr ON p.id_pais_residencia = pr.id_pais_residencia "
	                + "WHERE c.estado = 1 AND c.id_cliente = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, idCliente);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            cliente = new Cliente();
	            cliente.setIdCliente(rs.getInt("id_cliente"));
	            cliente.setIdUsuario(rs.getInt("id_usuario"));
	            cliente.setDni(rs.getString("dni"));
	            cliente.setCuil(rs.getString("cuil"));
	            cliente.setNombre(rs.getString("nombre"));
	            cliente.setApellido(rs.getString("apellido"));
	            cliente.setCorreoElectronico(rs.getString("correo_electronico"));
	            cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
	            cliente.setTelefono(rs.getString("telefono"));
	            cliente.setSexo(rs.getString("sexo").charAt(0));
	          
	            Direccion direccion = new Direccion();
	            direccion.setIdDireccion(rs.getInt("id_direccion"));
	            direccion.setCalle(rs.getString("calle"));
	            direccion.setNumero(rs.getString("numero"));
	            direccion.setCodigoPostal(rs.getString("codigo_postal"));
	            
	            Nacionalidad nacionalidad = new Nacionalidad();
                nacionalidad.setIdNacionalidad(rs.getInt("id_nacionalidad"));
                nacionalidad.setDescripcion(rs.getString("desc_nacionalidad"));
                cliente.setNacionalidad(nacionalidad);

	            Localidad localidad = new Localidad();
	            localidad.setIdLocalidad(rs.getInt("id_localidad"));
	            localidad.setNombreLocalidad(rs.getString("nombre_localidad"));

	            Provincia provincia = new Provincia();
	            provincia.setIdProvincia(rs.getInt("id_provincia"));
	            provincia.setNombreProvincia(rs.getString("nombre_pcia"));

	            PaisResidencia pais = new PaisResidencia();
	            pais.setIdPaisResidencia(rs.getInt("id_pais_residencia"));
	            pais.setDescripcion(rs.getString("desc_pais_residencia"));

	            provincia.setPaisResidencia(pais);
	            localidad.setProvincia(provincia);
	            direccion.setLocalidad(localidad);
	            cliente.setDireccion(direccion);
	            
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return cliente;
		
	}
	
	@Override
	public List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<>();
		
		PreparedStatement statement;
        ResultSet rs;
        Connection conn = Conexion.getSQLConexion();
        /*String sql = "SELECT c.*, n.desc_nacionalidad, d.calle, d.numero " +
                "FROM cliente c " +
                "INNER JOIN nacionalidad n ON c.id_nacionalidad = n.id_nacionalidad " +
                "INNER JOIN direccion d ON c.id_direccion = d.id_direccion";*/
        
        String sql = "SELECT "
                + "c.*, "
                + "n.desc_nacionalidad, "
                + "d.calle, d.numero, d.codigo_postal, "
                + "l.id_localidad, l.nombre_localidad, "
                + "p.id_provincia, p.nombre_pcia, "
                + "pr.id_pais_residencia, pr.desc_pais_residencia "
                + "FROM cliente c "
                + "INNER JOIN nacionalidad n ON c.id_nacionalidad = n.id_nacionalidad "
                + "INNER JOIN direccion d ON c.id_direccion = d.id_direccion "
                + "INNER JOIN localidad l ON d.id_localidad = l.id_localidad "
                + "INNER JOIN provincia p ON l.id_provincia = p.id_provincia "
                + "INNER JOIN pais_residencia pr ON p.id_pais_residencia = pr.id_pais_residencia "
                + "WHERE c.estado = 1";
        
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
            	Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setIdUsuario(rs.getInt("id_usuario"));
                cliente.setDni(rs.getString("dni"));
                cliente.setCuil(rs.getString("cuil"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setSexo(rs.getString("sexo").charAt(0)); //convierte String a char
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                cliente.setCorreoElectronico(rs.getString("correo_electronico"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEstado(rs.getBoolean("estado"));
                
                Nacionalidad nacionalidad = new Nacionalidad();
                nacionalidad.setIdNacionalidad(rs.getInt("id_nacionalidad"));
                nacionalidad.setDescripcion(rs.getString("desc_nacionalidad"));
                cliente.setNacionalidad(nacionalidad);
                
                PaisResidencia paisResidencia = new PaisResidencia();
                paisResidencia.setIdPaisResidencia(rs.getInt("id_pais_residencia"));
                paisResidencia.setDescripcion(rs.getString("desc_pais_residencia"));

                Provincia provincia = new Provincia();
                provincia.setIdProvincia(rs.getInt("id_provincia"));
                provincia.setNombreProvincia(rs.getString("nombre_pcia"));
                provincia.setPaisResidencia(paisResidencia);

                Localidad localidad = new Localidad();
                localidad.setIdLocalidad(rs.getInt("id_localidad"));
                localidad.setNombreLocalidad(rs.getString("nombre_localidad"));
                localidad.setProvincia(provincia);

                Direccion direccion = new Direccion();
                direccion.setIdDireccion(rs.getInt("id_direccion"));
                direccion.setCalle(rs.getString("calle"));
                direccion.setNumero(rs.getString("numero"));
                direccion.setCodigoPostal(rs.getString("codigo_postal"));
                direccion.setLocalidad(localidad);

                cliente.setDireccion(direccion);

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
	}
	
	@Override
	public boolean setearUsuarioEnCliente(int idCliente, int idUsuario) {
	    Connection conn = Conexion.getSQLConexion();
	    String sql = "UPDATE cliente SET id_usuario = ? WHERE id_cliente = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idUsuario);
	        stmt.setInt(2, idCliente);
	        if (stmt.executeUpdate() > 0) {
	            conn.commit();
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
	    }
	    return false;
	}
 }
