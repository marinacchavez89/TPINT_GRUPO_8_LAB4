package negocio;

import entidades.Cuenta;
import entidades.TipoCuenta;
import java.util.List;

public interface CuentaNegocio {
	public boolean agregarCuenta(Cuenta cuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(int nroCuenta);
	public List<Cuenta> listarCuentas();
	public int obtenerProximoNumeroCuenta();
	public int obtenerCantidadCuentasActivas(int idCliente);
	public List<Cuenta> obtenerXIdCliente (int idCliente);
	public boolean cambiarEstadoCuenta(int nroCuenta);
	public List<Cuenta> listarCuentasFiltradas(Boolean estado, int idCliente);
}
