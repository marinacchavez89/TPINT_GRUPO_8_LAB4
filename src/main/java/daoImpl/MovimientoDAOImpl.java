package daoImpl;

import dominio.Conexion;
import dao.MovimientoDAO;
import entidades.Movimiento;
import entidades.TipoMovimiento;
import java.util.Date;
import java.util.HashMap;

import entidades.Cuenta;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class MovimientoDAOImpl implements MovimientoDAO {

	public List<Movimiento> obtenerMovimientosXCuenta(int nroCuenta){
		
	List<Movimiento> movimientos = new ArrayList<>();
	
	PreparedStatement statement;
	ResultSet rs;
	Connection conn= Conexion.getSQLConexion();
	
	String sql = "SELECT m.id_movimiento, m.fecha, m.detalle, m.importe, m.nro_cuenta, " +
				"tm.id_tipo_movimiento, tm.desc_tipo_movimiento "+ 
				"FROM movimiento m " +
				"JOIN tipo_movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
				"WHERE m.nro_cuenta = ? ORDER BY m.fecha DESC";
	
		try {
			statement= conn.prepareStatement(sql);
			statement.setInt(1,nroCuenta); // faltaba enviar el parametro nroCuenta al '?'
			rs= statement.executeQuery();
			
			while(rs.next()) {
				Movimiento movimiento = new Movimiento();
				movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
				movimiento.setFecha(rs.getDate("fecha"));
	            movimiento.setDetalle(rs.getString("detalle"));
	            movimiento.setImporte(rs.getFloat("importe"));
	            
	            TipoMovimiento tipoMovimiento = new TipoMovimiento();
	            tipoMovimiento.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
	            tipoMovimiento.setDescripcion(rs.getString("desc_tipo_movimiento"));
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
	
	public List<Movimiento> obtenerMovimientosXCliente(int idCliente) {
		List<Movimiento> movimientos = new ArrayList<>();
		
		PreparedStatement statement;
		ResultSet rs;
		Connection conn= Conexion.getSQLConexion();
		
		String sql = "SELECT m.id_movimiento, m.fecha, m.detalle, m.importe, m.nro_cuenta, " +
					"tm.id_tipo_movimiento, tm.desc_tipo_movimiento "+ 
					"FROM movimiento m " +
					"JOIN tipo_movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
					"JOIN cuenta c ON m.nro_cuenta = c.nro_cuenta " +
					"WHERE c.id_cliente = ? ORDER BY m.fecha DESC";
		
			try {
				statement= conn.prepareStatement(sql);
				
				statement.setInt(1, idCliente); // idem anterior. Faltaba enviar parametro idCliente al '?'
				rs= statement.executeQuery();
				
				while(rs.next()) {
					Movimiento movimiento = new Movimiento();
					movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
					movimiento.setFecha(rs.getDate("fecha"));
		            movimiento.setDetalle(rs.getString("detalle"));
		            movimiento.setImporte(rs.getFloat("importe"));
		            
		            TipoMovimiento tipoMovimiento = new TipoMovimiento();
		            tipoMovimiento.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
		            tipoMovimiento.setDescripcion(rs.getString("desc_tipo_movimiento"));
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
	
	public boolean agregarMovimiento (Movimiento movimiento) {
		String sql = "INSERT INTO movimiento (fecha,detalle,importe,id_tipo_movimiento,nro_cuenta) VALUES (?,?,?,?,?)";
		
		PreparedStatement statement;
		Connection conn= Conexion.getSQLConexion();
		boolean agregado = false;
		
		
		try {
			statement = (PreparedStatement) conn.prepareStatement(sql);
			
			statement.setDate(1, new java.sql.Date(movimiento.getFecha().getTime())); // revisar esta linea si falla la fecha
			statement.setString(2, movimiento.getDetalle());
			statement.setFloat(3, movimiento.getImporte());
			statement.setInt(4, movimiento.getTipoMovimiento().getIdTipoMovimiento());
			statement.setInt(5, movimiento.getCuenta().getNroCuenta());
			
		
			if(statement.executeUpdate() > 0) {
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
	
	public List<Movimiento> obtenerMovimientosXFecha(int nroCuenta, Date fechaDesde, Date fechaHasta) {
		List<Movimiento> movimientos = new ArrayList<>();
		
		PreparedStatement statement;
		ResultSet rs;
		Connection conn= Conexion.getSQLConexion();
		
		String sql =  "SELECT m.id_movimiento, m.fecha, m.detalle, m.importe, m.nro_cuenta, " +
				"tm.id_tipo_movimiento, tm.desc_tipo_movimiento "+ 
				"FROM movimiento m " +
				"JOIN tipo_movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
				"WHERE m.nro_cuenta = ? AND m.fecha BETWEEN ? AND ? "+
				"ORDER BY m.fecha DESC";
		
			try {
				statement= conn.prepareStatement(sql);
				
				statement.setInt(1,nroCuenta); 
				statement.setDate(2,new java.sql.Date(fechaDesde.getTime()));
				statement.setDate(3,new java.sql.Date(fechaHasta.getTime()));
				
				rs= statement.executeQuery();
				
				while(rs.next()) {
					Movimiento movimiento = new Movimiento();
					movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
					movimiento.setFecha(rs.getDate("fecha"));
		            movimiento.setDetalle(rs.getString("detalle"));
		            movimiento.setImporte(rs.getFloat("importe"));
		            
		            Cuenta cuenta = new Cuenta();
		            cuenta.setNroCuenta(rs.getInt("nro_cuenta"));
		            movimiento.setCuenta(cuenta);
		            
		            TipoMovimiento tipoMovimiento = new TipoMovimiento();
		            tipoMovimiento.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
		            tipoMovimiento.setDescripcion(rs.getString("desc_tipo_movimiento"));
		            movimiento.setTipoMovimiento(tipoMovimiento);

		            movimientos.add(movimiento);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return movimientos; 	
	}
	
	//reportes
	public Map<String, Double> obtenerResumenIngresosEgresos(Date desde, Date hasta) {
        Map<String, Double> resumen = new HashMap<>();
        String sql = "SELECT id_tipo_movimiento, SUM(ABS(importe)) AS total " +
                     "FROM movimiento WHERE fecha BETWEEN ? AND ? " +
                     "AND id_tipo_movimiento IN (2, 3, 4, 5) GROUP BY id_tipo_movimiento";

        Connection conn = Conexion.getSQLConexion();
        PreparedStatement stmt;
        ResultSet rs;

        double totalIngresos = 0, totalEgresos = 0;
        int cantIngresos = 0, cantEgresos = 0;
        Map<Integer, Double> totalesPorTipo = new HashMap<>();

        try {
        	System.out.println("Ejecutando consulta con fechas: " + desde + " hasta " + hasta);

            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(desde.getTime()));
            stmt.setDate(2, new java.sql.Date(hasta.getTime()));
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
                int tipo = rs.getInt("id_tipo_movimiento");
                double total = rs.getDouble("total");
                totalesPorTipo.put(tipo, total);
                System.out.println("Tipo: " + tipo + " Total: " + total);

                if (tipo == 3 || tipo == 5) { // ingresos
                    totalIngresos += total;
                    cantIngresos++;
                } else if (tipo == 2 || tipo == 4) { // egresos
                    totalEgresos += total;
                    cantEgresos++;
                }
            }

            resumen.put("totalIngresos", totalIngresos);
            resumen.put("totalEgresos", totalEgresos);
            resumen.put("promedioIngresos", cantIngresos > 0 ? totalIngresos / cantIngresos : 0);
            resumen.put("promedioEgresos", cantEgresos > 0 ? totalEgresos / cantEgresos : 0);

            resumen.put("pagosPrestamo", totalesPorTipo.getOrDefault(3, 0.0));
            resumen.put("transferenciasDebito", totalesPorTipo.getOrDefault(4, 0.0));
            resumen.put("transferenciasCredito", totalesPorTipo.getOrDefault(5, 0.0));
            resumen.put("prestamosOtorgados", totalesPorTipo.getOrDefault(2, 0.0));
            
            System.out.println("Totales finales:");
            System.out.println("Total ingresos: " + totalIngresos);
            System.out.println("Total egresos: " + totalEgresos);
            System.out.println("Promedio ingresos: " + (cantIngresos > 0 ? totalIngresos / cantIngresos : 0));
            System.out.println("Promedio egresos: " + (cantEgresos > 0 ? totalEgresos / cantEgresos : 0));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resumen;
    }
}