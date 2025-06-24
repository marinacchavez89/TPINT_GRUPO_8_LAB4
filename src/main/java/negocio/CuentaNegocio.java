package negocio;

import entidades.Cuenta;
import entidades.TipoCuenta;
import java.util.List;

public interface CuentaNegocio {
	public boolean agregarCuenta(Cuenta cuenta, int nroCuenta, TipoCuenta idTipoCuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(int nroCuenta);
	public List<Cuenta> listarCuentas();
}