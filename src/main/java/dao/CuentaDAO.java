package dao;

import entidades.Cuenta;
import java.util.List;

public interface CuentaDAO {
	public boolean agregarCuenta(Cuenta cuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(int nroCuenta);
	public List<Cuenta> listarCuentas();
	public int obtenerProximoNumeroCuenta();
	public int contarCuentasActivasPorCliente(int idCliente);
	public List<Cuenta> obtenerXIdCliente (int idCliente);
	Cuenta obtenerPorNroCuenta(int nroCuenta);
    boolean actualizarEstadoCuenta(Cuenta cuenta);
	public List<Cuenta> listarCuentasFiltradas(Boolean estado, int idCliente);
	public boolean actualizarSaldo(Cuenta cuenta);
	public Cuenta obtenerPorCBU(String cbu);
	public boolean incrementarSaldo(int nroCuenta, double importe);

}
