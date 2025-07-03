package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.CuotaDAO;
import dominio.Conexion;
import entidades.Cuota;

public class CuotaDAOImpl implements CuotaDAO {

	@Override
	public boolean agregar(Cuota cuota) {
		PreparedStatement statement;
		Connection conn = Conexion.getSQLConexion();
		boolean agregado= false;		
		
		String sql = "INSERT INTO cuota (id_cuota, id_prestamo, numero_cuota, monto, fecha_pago, estado) VALUES (?, ?, ?, ?, ?, 1)";
				
		try {
			statement = conn.prepareStatement(sql);
            statement.setInt(1, cuota.getIdCuota());
            statement.setInt(2, cuota.getIdPrestamo());
            statement.setInt(3, cuota.getNumeroCuota());
            statement.setFloat(4, cuota.getMonto());
            statement.setDate(5, new java.sql.Date(cuota.getFechaPago().getTime()));
            statement.setBoolean(6, cuota.isEstado());
		
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

}
