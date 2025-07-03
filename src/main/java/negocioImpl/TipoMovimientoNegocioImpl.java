package negocioImpl;

import java.util.List;

import dao.TipoMovimientoDAO;
import daoImpl.TipoMovimientoDAOImpl;
import entidades.TipoMovimiento;
import negocio.TipoMovimientoNegocio;

public class TipoMovimientoNegocioImpl implements TipoMovimientoNegocio {
	private TipoMovimientoDAO tipoMovimientoDao = new TipoMovimientoDAOImpl();
	
	public List<TipoMovimiento> obtenerTipoMovimientos(){
		return tipoMovimientoDao.obtenerTodos();
	}
	public TipoMovimiento obtenerXId(int id) {
		return tipoMovimientoDao.obtenerXId(id);
	}
}
