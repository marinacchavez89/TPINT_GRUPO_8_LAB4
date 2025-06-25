package dao;

import entidades.TipoCuenta;
import java.util.List;

public interface TipoCuentaDAO {
	public List<TipoCuenta> listar();
	public TipoCuenta obtenerTipoCuentaXId(int id);
}

