package negocioImpl;

import entidades.Direccion;
import negocio.DireccionNegocio;
import dao.DireccionDAO;
import daoImpl.DireccionDAOImpl;

public class DireccionNegocioImpl implements DireccionNegocio {
	
	private DireccionDAO direccionDao = new DireccionDAOImpl();
	
	@Override
	public int agregarDireccion(Direccion direccion) {
		return direccionDao.agregarDireccion(direccion);
	}
}
