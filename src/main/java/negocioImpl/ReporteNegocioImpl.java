package negocioImpl;

import java.util.Date;

import dao.ReporteDAO;
import daoImpl.ReporteDAOImpl;
import negocio.ReporteNegocio;

public class ReporteNegocioImpl implements ReporteNegocio {

    private ReporteDAO reporteDAO = new ReporteDAOImpl();

    @Override
    public double obtenerTotalPrestamosAutorizados(Date desde, Date hasta) {
        return reporteDAO.obtenerTotalPrestamosAutorizados(desde, hasta);
    }

    @Override
    public int contarPrestamosAutorizados(Date desde, Date hasta) {
        return reporteDAO.contarPrestamosAutorizados(desde, hasta);
    }
}
