package daoImpl;

import java.util.ArrayList;
import java.util.List;

import dao.ProvinciaDAO;
import entidades.PaisResidencia;
import entidades.Provincia;
import com.mysql.jdbc.PreparedStatement;
import dominio.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinciaDAOImpl implements ProvinciaDAO {
	public List<Provincia> listarProvinciasPorPaisesResidencia(int idPais) {
	  List<Provincia> provincias = new ArrayList<>();
	
	    PreparedStatement statement;
	    ResultSet rs;
	    Connection conn = Conexion.getSQLConexion();
	
	    String sql = "SELECT p.id_provincia, p.nombre_pcia, pr.id_pais_residencia, pr.desc_pais_residencia " +
	                 "FROM provincia p " +
	                 "JOIN pais_residencia pr ON p.id_pais_residencia = pr.id_pais_residencia " +
	                 "WHERE p.id_pais_residencia = ?";
	
	    try {
	    	statement = (PreparedStatement) conn.prepareStatement(sql);
	        statement.setInt(1, idPais);
	        rs = statement.executeQuery();
	
	        while (rs.next()) {
	            Provincia provincia = new Provincia();
	            provincia.setIdProvincia(rs.getInt("id_provincia"));
	            provincia.setNombreProvincia(rs.getString("nombre_pcia"));
	
	            PaisResidencia pais = new PaisResidencia();
	            pais.setIdPaisResidencia(rs.getInt("id_pais_residencia"));
	            pais.setDescripcion(rs.getString("desc_pais_residencia"));
	
	            provincia.setPaisResidencia(pais);
	
	            provincias.add(provincia);
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
		return provincias;
	}
}
