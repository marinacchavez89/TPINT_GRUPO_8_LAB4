package negocioImpl;

import java.util.List;
import dao.MovimientoDAO;
import daoImpl.MovimientoDAOImpl;
import entidades.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {

	private MovimientoDAO movimientoDAO = new MovimientoDAOImpl();
	
	@Override
	public List<Movimiento> obtenerXId(int idCliente) {
		return movimientoDAO.obtenerXId(idCliente);
	}
}
