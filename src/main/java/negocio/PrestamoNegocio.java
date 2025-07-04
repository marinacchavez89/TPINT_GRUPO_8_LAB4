package negocio;

import java.util.List;

import entidades.Cliente;
import entidades.Cuota;
import entidades.Prestamo;

public interface PrestamoNegocio {
    boolean solicitarPrestamo(Prestamo prestamo);
    public boolean modificarEstadoPrestamo(Prestamo prestamo, int estado);
    boolean autorizarPrestamo(int idPrestamo);
    boolean rechazarPrestamo(int idPrestamo);
    List<Cuota> listarCuotas(int idPrestmao);
    List<Prestamo> listarPrestamosPendientes();
    boolean pagarCuota (int idCuota, int nroCuenta);
}
