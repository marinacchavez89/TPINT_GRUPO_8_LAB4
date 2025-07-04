package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PrestamoDAO;
import entidades.Prestamo;
import dominio.Conexion;

public class PrestamoDAOImpl implements PrestamoDAO {

    @Override
    public boolean insert(Prestamo prestamo) {
        boolean estado = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getSQLConexion();
/**CORREGIR ESTE INSERT. !!!!!!!!!!!!!!!!!!!!*/
            String query = "INSERT INTO prestamos (id_cliente, nro_cuenta, importe_pedido, cantidad_cuotas, fecha_alta, importe_a_pagar, estado) VALUES (?, ?, ?, ?, ?, NOW(), 1)";
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, prestamo.getIdCliente());
            stmt.setInt(2, prestamo.getNroCuenta());
            stmt.setDouble(3, prestamo.getImportePedido());
            stmt.setInt(4, prestamo.getCantidadCuotas());
            stmt.setDate(5, new Date(prestamo.getFechaAlta().getTime()));
            stmt.setDouble(6, prestamo.getImporteAPagar());
            // dejamos el estado inicial en 1 = pendiente
            stmt.setInt(7, prestamo.getEstado());
            int filas = stmt.executeUpdate();
            estado = filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }

	@Override
	public boolean modificarEstado(Prestamo prestamo, int estado) { //El estado viene según se apruebe/rechaze el préstamo
		PreparedStatement statement;
	    Connection conn = Conexion.getSQLConexion();
	    boolean modificado = false;

	    // Modificar: La condición WHERE debe ser por ID_Cliente para asegurar la unicidad y permitir cambio de DNI.
	    String sql = "UPDATE prestamo SET estado = ? WHERE id_prestamo = ?";
	    try {
	        statement = conn.prepareStatement(sql);
	        statement.setInt(1, estado);
	        statement.setInt(2, prestamo.getIdPrestamo());
	        
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
    public Prestamo obtenerPorId(int idPrestamo) {
        Prestamo prestamo = null;
        String sql = "SELECT * FROM prestamos WHERE id_prestamo = ?";
        try (Connection conn = Conexion.getSQLConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    prestamo = new Prestamo();
                    prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
                    prestamo.setIdCliente(rs.getInt("id_cliente"));
                    prestamo.setNroCuenta(rs.getInt("nro_cuenta"));
                    prestamo.setImportePedido(rs.getDouble("importe_pedido"));
                    prestamo.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
                    prestamo.setFechaAlta(rs.getDate("fecha_alta"));
                    prestamo.setImporteAPagar(rs.getDouble("importe_a_pagar"));
                    prestamo.setEstado(rs.getInt("estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamo;
    }
}
//FALTA LISTAR PENDIENTES Y LISTAR POR CLIENTE
