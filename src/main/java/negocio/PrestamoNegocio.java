package negocio;

import entidades.Cliente;
import entidades.Prestamo;

public interface PrestamoNegocio {
    boolean solicitarPrestamo(Prestamo prestamo);
    public boolean modificarEstadoPrestamo(Prestamo prestamo, int estado);
}
