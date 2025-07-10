package negocio;

import java.util.List;

import entidades.Cliente;
import entidades.Cuota;
import entidades.Prestamo;

public interface PrestamoNegocio {
    boolean solicitarPrestamo(Prestamo prestamo);
    public boolean modificarEstadoPrestamo(Prestamo prestamo, int estado);
	List<Prestamo> listarPrestamosPorCliente(int idCliente);
	List<Prestamo> listarPrestamosPendientes();
	boolean autorizarPrestamo(int idPrestamo);
	boolean rechazarPrestamo (int idPrestamo);
}
