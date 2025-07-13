package negocio;

import java.util.Date;
import java.util.List;
import java.util.Map;

import entidades.Cliente;
import entidades.Cuota;
import entidades.Prestamo;

public interface PrestamoNegocio {
    boolean solicitarPrestamo(Prestamo prestamo);
    public boolean modificarEstadoPrestamo(Prestamo prestamo, int estado);
	List<Prestamo> listarPrestamosPorCliente(int idCliente);
	List<Prestamo> listarPrestamosPendientes();
	List<Prestamo> listarPrestamosAutorizados();
	boolean autorizarPrestamo(int idPrestamo, double importeAPagar);
	boolean rechazarPrestamo (int idPrestamo);
	boolean pagarCuota(int idCuota , int nroCuenta);
}
