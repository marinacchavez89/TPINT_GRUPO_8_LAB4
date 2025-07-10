package dao;

import java.util.List;
import java.util.Date;

import entidades.Movimiento;


public interface MovimientoDAO {

	public List<Movimiento> obtenerMovimientosXCuenta(int nroCuenta);
	public List<Movimiento> obtenerMovimientosXCliente(int idCliente);
	public boolean agregarMovimiento (Movimiento movimiento);
	public List<Movimiento> obtenerMovimientosXFecha(int nroCuenta, Date fechaDesde, Date fechaHasta);
}
