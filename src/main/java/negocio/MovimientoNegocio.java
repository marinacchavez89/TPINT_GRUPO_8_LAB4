package negocio;

import java.util.List;

import entidades.Movimiento;

public interface MovimientoNegocio {

	public List<Movimiento> obtenerMovimientosXCuenta(int nroCuenta);
	public List<Movimiento> obtenerMovimientosXCliente(int idCliente);
	public boolean agregarMovimiento (Movimiento movimiento);
}
