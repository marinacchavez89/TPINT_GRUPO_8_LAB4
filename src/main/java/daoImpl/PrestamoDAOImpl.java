package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

            String query = "INSERT INTO prestamos (IdCliente, NroCuenta, ImportePedido, CantidadCuotas, FechaSolicitud, Estado) VALUES (?, ?, ?, ?, GETDATE(), 1)";
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, prestamo.getIdCliente());
            stmt.setInt(2, prestamo.getNroCuenta());
            stmt.setDouble(3, prestamo.getImportePedido());
            stmt.setInt(4, prestamo.getCantidadCuotas());

            int filas = stmt.executeUpdate();
            estado = filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }
}
