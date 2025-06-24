package negocio;

import entidades.Cuenta;
import entidades.TipoCuenta;
import java.util.List;

public interface CuentaNegocio {
	public boolean agregarCuenta(int nroCuenta, TipoCuenta idTipoCuenta);
	public boolean eliminarCuenta(int nroCuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public List<Cuenta> listarCuentas();
}