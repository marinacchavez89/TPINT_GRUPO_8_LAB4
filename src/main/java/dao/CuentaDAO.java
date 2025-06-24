package dao;

import entidades.Cuenta;
import entidades.TipoCuenta;
import java.util.List;

public interface CuentaDAO {
	public boolean agregarCuenta(Cuenta cuenta, int idCliente, TipoCuenta idTipoCuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(int nroCuenta);
	public List<Cuenta> listarCuentas();
}
