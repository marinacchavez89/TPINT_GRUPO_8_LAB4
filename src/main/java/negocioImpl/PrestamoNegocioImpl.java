package negocioImpl;

import entidades.Prestamo;

import java.util.List;

import dao.PrestamoDAO;
import daoImpl.PrestamoDAOImpl;
import negocio.PrestamoNegocio;

public abstract class PrestamoNegocioImpl implements PrestamoNegocio {
    private PrestamoDAO prestamoDao = new PrestamoDAOImpl();

    @Override
    public boolean solicitarPrestamo(Prestamo prestamo) {
        return prestamoDao.insert(prestamo);
    }

	@Override
	public boolean modificarEstadoPrestamo(Prestamo prestamo, int estado) {
		return prestamoDao.modificarEstado(prestamo, estado);
	}
	@Override
public List<Prestamo> listarPrestamosPendientes()
{
		return prestamoDao.listarPendientes(); // solo lista los -1
		
}
	@Override
	public List <Prestamo> listarPrestamosPorCliente(int idCliente)
	{
		return prestamoDao.listarPorCliente(idCliente);
	}
}
//falta desarrollar autorizar prestamo. Pero hay que hacer movimientos

//Instanciar movimientos