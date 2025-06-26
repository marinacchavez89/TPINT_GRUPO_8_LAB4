package dao;

import entidades.Cuenta;
import entidades.TipoCuenta;
import java.util.List;

public interface CuentaDAO {
	public boolean agregarCuenta(Cuenta cuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(int nroCuenta);
	public List<Cuenta> listarCuentas();
	public int obtenerProximoNumeroCuenta();
	public int contarCuentasActivasPorCliente(int idCliente);
}
