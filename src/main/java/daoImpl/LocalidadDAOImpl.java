package daoImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import dao.LocalidadDAO;
import dominio.Conexion;
import entidades.Localidad;
import entidades.Provincia;

public class LocalidadDAOImpl implements LocalidadDAO  {
	public List<Localidad> listarLocalidadesPorProvincia(int idProvincia){
		List<Localidad> localidades = new ArrayList<>();
	    PreparedStatement statement;
	    ResultSet rs;
	    Connection conn = Conexion.getSQLConexion();
	    
	    String sql = """
    SELECT l.id_localidad, l.nombre_localidad, p.id_provincia, p.nombre_pcia
	            FROM localidad l
	            JOIN provincia p ON l.id_provincia = p.id_provincia
	            WHERE p.id_provincia = ?
	            """;
	    
	    try {
	    	statement = (PreparedStatement) conn.prepareStatement(sql);
	        statement.setInt(1, idProvincia);
	        rs = statement.executeQuery();
	        
	        while (rs.next()) {
	            Localidad  localidad = new Localidad();
	            localidad.setIdLocalidad(rs.getInt("id_localidad"));
	            localidad.setNombreLocalidad(rs.getString("nombre_localidad"));
	
	            Provincia provincia = new Provincia();
	            provincia.setIdProvincia(rs.getInt("id_provincia"));
	            provincia.setNombreProvincia(rs.getString("nombre_pcia"));
	
	            localidad.setProvincia(provincia);
	
	            localidades.add(localidad);
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		      	        
		return localidades;
	}
}
