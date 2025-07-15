package negocio;

import java.util.List;
import java.util.Map;
import java.util.Date;

import entidades.Movimiento;

public interface MovimientoNegocio {

	public List<Movimiento> obtenerMovimientosXCuenta(int nroCuenta);
	public List<Movimiento> obtenerMovimientosXCliente(int idCliente);
	public boolean agregarMovimiento (Movimiento movimiento);
	public List<Movimiento> obtenerMovimientosXFecha(int nroCuenta, Date fechaDesde, Date fechaHasta);
	//reportes: 
	Map<String, Double> obtenerResumenIngresosEgresos(Date desde, Date hasta);
}
