package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CuotaDAO;
import dominio.Conexion;
import entidades.Cuota;

public class CuotaDAOImpl implements CuotaDAO {

	@Override
	public boolean agregar(Cuota cuota) {
		PreparedStatement statement =null;
		Connection conn = Conexion.getSQLConexion();
		boolean agregado= false;		
		
		   String sql = "INSERT INTO cuotas (id_prestamo, numero_cuota, monto, fecha_pago, estado) VALUES (?,?,?,?,?)";	
		   
		try {
			statement = conn.prepareStatement(sql);
			// 1 id prestamo
            statement.setInt(1, cuota.getIdPrestamo());
            // 2 numero_cuota
            statement.setInt(2, cuota.getNumeroCuota());
            // 3 monto
            statement.setFloat(3, cuota.getMonto());
            // 4 fecha_pago o null
            statement.setDate(4, new java.sql.Date(cuota.getFechaPago().getTime()));
            // 5 estado
            statement.setInt(5, cuota.getEstado());
		
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
	public Cuota obtenerPorId(int idCuota) {
	    Cuota cuota = null;
	    String query = "SELECT id_cuota, id_prestamo, numero_cuota, monto, fecha_pago, estado FROM cuota WHERE id_cuota = ?";
	    
	    try (Connection conn = Conexion.getSQLConexion();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setInt(1, idCuota);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            cuota = new Cuota();
	            cuota.setIdCuota(rs.getInt("id_cuota"));
	            cuota.setIdPrestamo(rs.getInt("id_prestamo"));
	            cuota.setNumeroCuota(rs.getInt("numero_cuota"));
	            cuota.setMonto(rs.getFloat("monto"));
	            cuota.setFechaPago(rs.getDate("fecha_pago"));
	            cuota.setEstado(rs.getInt("estado"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cuota;
	}

}
