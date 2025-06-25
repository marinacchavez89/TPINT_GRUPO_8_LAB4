package negocio;

import java.util.List;
import entidades.TipoCuenta;

public interface TipoCuentaNegocio {
	public List<TipoCuenta> listar();
	public TipoCuenta obtenerTipoCuentaXId(int id);
}
