package dao;

import java.util.List;
import entidades.Movimiento;

public interface MovimientoDAO {

	public List<Movimiento> obtenerMovimientosXCuenta(int nroCuenta);
	public List<Movimiento> obtenerMovimientosXCliente(int idCliente);
}
