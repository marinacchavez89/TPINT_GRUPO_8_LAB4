package negocioImpl;

import java.util.List;
import java.util.Map;
import java.util.Date;

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
	
	@Override
	public List<Movimiento> obtenerMovimientosXFecha(int nroCuenta, Date fechaDesde, Date fechaHasta) {
		return movimientoDAO.obtenerMovimientosXFecha(nroCuenta, fechaDesde, fechaHasta);
	}
	
	//reportes

	    @Override
	    public Map<String, Double> obtenerResumenIngresosEgresos(Date desde, Date hasta) {
	        return movimientoDAO.obtenerResumenIngresosEgresos(desde, hasta);
	    }
	
}
