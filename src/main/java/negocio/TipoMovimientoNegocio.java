package negocio;

import java.util.List;

import entidades.TipoMovimiento;

public interface TipoMovimientoNegocio {
	public List<TipoMovimiento> obtenerTipoMovimientos();
	public TipoMovimiento obtenerXId(int id);
}
