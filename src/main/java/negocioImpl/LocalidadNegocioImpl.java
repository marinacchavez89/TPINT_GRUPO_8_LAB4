package negocioImpl;

import java.util.List;

import dao.LocalidadDAO;
import daoImpl.LocalidadDAOImpl;
import entidades.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio {
	private LocalidadDAO localidadDAO = new LocalidadDAOImpl();
	 @Override
	    public List<Localidad> obtenerPorIdProvincia(int idProvincia) {
	        return localidadDAO.listarLocalidadesPorProvincia(idProvincia);
	    }
}
