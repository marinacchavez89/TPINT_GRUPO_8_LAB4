package negocioImpl;

import java.util.List;

import dao.ClienteDAO;
import dao.PrestamoDAO;
import daoImpl.ClienteDAOImpl;
import daoImpl.PrestamoDAOImpl;
import entidades.Prestamo;
import negocio.ClienteNegocio;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {
	private PrestamoDAO prestamoDAO= new PrestamoDAOImpl();

	@Override
	public boolean agregarPrestamo(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}
}