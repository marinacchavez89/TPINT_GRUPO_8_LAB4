package dao;

import entidades.Cuenta;

import java.sql.Date;
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
	public boolean decrementarSaldo(int nroCuenta, double importe);
	//public boolean tienePrestamosActivosPorCuenta(int nroCuenta);
	//Reporte
	public List<Cuenta> listarCuentasFiltradasPorFecha(Date fechaDesde, Date fechaHasta);
	
}
