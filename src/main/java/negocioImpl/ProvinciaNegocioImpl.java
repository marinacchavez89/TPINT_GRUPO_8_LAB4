package negocioImpl;

import java.util.List;

import entidades.Provincia;
import negocio.ProvinciaNegocio;

import dao.ProvinciaDAO;
import daoImpl.ProvinciaDAOImpl;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {
	private ProvinciaDAO provinciaDAO = new ProvinciaDAOImpl();
	 @Override
	    public List<Provincia> obtenerPorIdPaisResidencia(int idPaisResidencia) {
	        return provinciaDAO.listarProvinciasPorPaisesResidencia(idPaisResidencia);
	    }
}
