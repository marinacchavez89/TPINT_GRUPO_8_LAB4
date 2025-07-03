package negocioImpl;

import entidades.Prestamo;
import dao.PrestamoDAO;
import daoImpl.PrestamoDAOImpl;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {
    private PrestamoDAO prestamoDao = new PrestamoDAOImpl();

    @Override
    public boolean solicitarPrestamo(Prestamo prestamo) {
        return prestamoDao.insert(prestamo);
    }

	@Override
	public boolean modificarEstadoPrestamo(Prestamo prestamo, int estado) {
		return prestamoDao.modificarEstado(prestamo, estado);
	}
}
