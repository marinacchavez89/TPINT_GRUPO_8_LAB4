package daoImpl;

import dominio.Conexion;
import dao.MovimientoDAO;
import entidades.Movimiento;
import entidades.TipoMovimiento;
import entidades.Cuenta;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class MovimientoDAOImpl implements MovimientoDAO {

	public List<Movimiento> obtenerXId (int idCliente) {
		
	List<Movimiento> movimientos = new ArrayList<>();
	
	PreparedStatement statement;
	ResultSet rs;
	Connection conn= Conexion.getSQLConexion();
	
	String sql = "SELECT m.id_movimiento AS idMovimiento, " +
            "m.fecha AS fechaMovimiento, " +
            "m.detalle AS detalle, " +
            "m.importe AS importe, " +
            "tm.desc_tipo_movimiento AS tipoMovimiento, " +
            "m.nro_cuenta AS nroCuenta " +
            "FROM movimiento m " +
            "JOIN tipo_movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
            "JOIN cuenta c ON m.nro_cuenta = c.nro_cuenta " +
            "WHERE c.id_cliente = ? " +
            "ORDER BY m.fecha DESC";
	
		try {
			statement= conn.prepareStatement(sql);
			rs= statement.executeQuery();
			
			while(rs.next()) {
				Movimiento movimiento = new Movimiento();
				movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
				movimiento.setFecha(rs.getDate("fecha"));
	            movimiento.setImporte(rs.getFloat("importe"));
	            movimiento.setDetalle(rs.getString("descripcion"));

	            TipoMovimiento tipoMovimiento = new TipoMovimiento();
	            tipoMovimiento.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
	            tipoMovimiento.setDescripcion(rs.getString("tipo_desc"));
	            movimiento.setTipoMovimiento(tipoMovimiento);

	            Cuenta cuenta = new Cuenta();
	            cuenta.setNroCuenta(rs.getInt("nro_cuenta"));
	            movimiento.setCuenta(cuenta);

	            movimientos.add(movimiento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	return movimientos; 
	}
	
}