package negocioImpl;

import java.util.List;

import dao.NacionalidadDAO;
import daoImpl.NacionalidadDAOImpl;
import entidades.Nacionalidad;
import negocio.NacionalidadNegocio;

public class NacionalidadNegocioImpl implements NacionalidadNegocio {
	  private NacionalidadDAO nacionalidadDAO = new NacionalidadDAOImpl();

	    @Override
	    public List<Nacionalidad> obtenerTodas() {
	        return nacionalidadDAO.obtenerTodas();
	    }
}
