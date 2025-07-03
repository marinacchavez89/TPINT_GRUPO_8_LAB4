package dao;

import java.util.List;

import entidades.TipoMovimiento;

public interface TipoMovimientoDAO {
	public List<TipoMovimiento> obtenerTodos();
	public TipoMovimiento obtenerXId(int id);
}
