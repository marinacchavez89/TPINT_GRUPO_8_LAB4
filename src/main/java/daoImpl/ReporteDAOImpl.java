package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import dao.ReporteDAO;
import dominio.Conexion;

public class ReporteDAOImpl implements ReporteDAO {

	@Override
	public double obtenerTotalPrestamosAutorizados(Date desde, Date hasta) {
		double total = 0;
        String query = "SELECT SUM(importe_pedido) FROM prestamo WHERE estado = 2 AND fecha_alta BETWEEN ? AND ?";

        try (Connection conn = Conexion.getSQLConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, new java.sql.Date(desde.getTime()));
            stmt.setDate(2, new java.sql.Date(hasta.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getDouble(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
	}

	@Override
	public int contarPrestamosAutorizados(Date desde, Date hasta) {
		int cantidad = 0;
        String query = "SELECT COUNT(*) FROM prestamo WHERE estado = 2 AND fecha_alta BETWEEN ? AND ?";

        try (Connection conn = Conexion.getSQLConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, new java.sql.Date(desde.getTime()));
            stmt.setDate(2, new java.sql.Date(hasta.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cantidad = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cantidad;
	}

}
