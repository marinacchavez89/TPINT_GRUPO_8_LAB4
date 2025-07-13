package dao;

import java.util.Date;

public interface ReporteDAO {
    double obtenerTotalPrestamosAutorizados(Date desde, Date hasta);
    int contarPrestamosAutorizados(Date desde, Date hasta);
}
