package negocioImpl;

import java.util.List;
import dao.MovimientoDAO;
import daoImpl.MovimientoDAOImpl;
import entidades.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {

	private MovimientoDAO movimientoDAO = new MovimientoDAOImpl();
	
	@Override
	public List<Movimiento> obtenerMovimientosXCuenta(int nroCuenta) {
		return movimientoDAO.obtenerMovimientosXCuenta(nroCuenta);
	}
	
	@Override
	public List<Movimiento> obtenerMovimientosXCliente(int idCliente){
		return movimientoDAO.obtenerMovimientosXCliente(idCliente);
	}
	
	@Override
	public boolean agregarMovimiento (Movimiento movimiento) {
		return movimientoDAO.agregarMovimiento(movimiento);
	}
}
