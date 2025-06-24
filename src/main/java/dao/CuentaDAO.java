package dao;

import entidades.Cuenta;
import entidades.TipoCuenta;
import java.util.List;

public interface CuentaDAO {
	public boolean agregarCuenta(int idCliente, TipoCuenta idTipoCuenta);
	public boolean eliminarCuenta(int nroCuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public List<Cuenta> listarCuentas();
}
