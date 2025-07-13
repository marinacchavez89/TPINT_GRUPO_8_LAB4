package negocio;

import java.util.Date;

public interface ReporteNegocio {
    double obtenerTotalPrestamosAutorizados(Date desde, Date hasta);
    int contarPrestamosAutorizados(Date desde, Date hasta);
}
