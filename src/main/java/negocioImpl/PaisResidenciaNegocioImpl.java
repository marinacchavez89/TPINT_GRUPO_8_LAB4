package negocioImpl;

import java.util.List;

import entidades.PaisResidencia;
import negocio.PaisResidenciaNegocio;
import dao.PaisResidenciaDAO;
import daoImpl.PaisResidenciaDAOImpl;

public class PaisResidenciaNegocioImpl implements PaisResidenciaNegocio {
	private PaisResidenciaDAO paisResidenciaDAO = new PaisResidenciaDAOImpl();

	@Override
    public List<PaisResidencia> obtenerTodos() {
        return paisResidenciaDAO.obtenerTodos();
    }
}
