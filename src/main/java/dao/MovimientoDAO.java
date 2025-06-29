package dao;

import java.util.List;
import entidades.Movimiento;

public interface MovimientoDAO {

	public List<Movimiento> obtenerXId(int idCliente);
}
