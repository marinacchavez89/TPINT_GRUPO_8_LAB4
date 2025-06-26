package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import dominio.Conexion;

import dao.DireccionDAO;
import entidades.Direccion;

public class DireccionDAOImpl implements DireccionDAO {
	public int agregarDireccion(Direccion direccion) {
	    PreparedStatement statement;
	    ResultSet rs;
	    Connection conn = Conexion.getSQLConexion();
	    int idGenerado = -1;

	    String sql = "INSERT INTO direccion (calle, numero, codigo_postal, id_localidad) VALUES (?, ?, ?, ?)";
	    
	    try {
	    	statement = (PreparedStatement) conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
	    	statement.setString(1, direccion.getCalle());
            statement.setString(2, direccion.getNumero());
            statement.setString(3, direccion.getCodigoPostal());
            statement.setInt(4, direccion.getLocalidad().getIdLocalidad());

	        if (statement.executeUpdate() > 0) {
	            rs = statement.getGeneratedKeys();
	            if (rs.next()) {
	                idGenerado = rs.getInt(1);
	                conn.commit();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return idGenerado;
	}
	
	public boolean modificarDireccion(Direccion direccion) {
	    Connection conn = Conexion.getSQLConexion();
	    String sql = "UPDATE direccion SET calle=?, numero=?, codigo_postal=?, id_localidad=? WHERE id_direccion=?";
	    try {
	        PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
	        stmt.setString(1, direccion.getCalle());
	        stmt.setString(2, direccion.getNumero());
	        stmt.setString(3, direccion.getCodigoPostal());
	        stmt.setInt(4, direccion.getLocalidad().getIdLocalidad());
	        stmt.setInt(5, direccion.getIdDireccion());

	        int rows = stmt.executeUpdate();
	        conn.commit();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
	        return false;
	    }
	}
}
