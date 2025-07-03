package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoMovimientoDAO;
import dominio.Conexion;
import entidades.TipoMovimiento;

public class TipoMovimientoDAOImpl implements TipoMovimientoDAO {

	@Override
	public List<TipoMovimiento> obtenerTodos() {
		List<TipoMovimiento> lista = new ArrayList<>();
	    PreparedStatement statement;
	    ResultSet rs;
	    Connection conn = Conexion.getSQLConexion();

	    String sql = "SELECT id_tipo_movimiento, desc_tipo_movimiento FROM tipo_movimiento";

	    try {
	        statement = conn.prepareStatement(sql);
	        rs = statement.executeQuery();

	        while (rs.next()) {
	            TipoMovimiento tm = new TipoMovimiento();
	            tm.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
	            tm.setDescripcion(rs.getString("desc_tipo_movimiento"));
	            lista.add(tm);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return lista;
	}

	@Override
	public TipoMovimiento obtenerXId(int id) {
		TipoMovimiento tipoMovimiento = null;
		PreparedStatement statement;
        Connection conn = Conexion.getSQLConexion();
	    String sql = "SELECT id_tipo_movimiento, desc_tipo_movimiento"
	    		+ "FROM tipo_movimiento"
	    		+ "WHERE id_tipo_movimiento = ?";
	    try{
	    	statement = conn.prepareStatement(sql);
	        statement.setInt(1, id);	        
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	        	tipoMovimiento = new TipoMovimiento();
	            tipoMovimiento.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
	            tipoMovimiento.setDescripcion(rs.getString("desc_tipo_movimiento"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tipoMovimiento;
	}
	
}
