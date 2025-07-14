package negocio;

import entidades.Cuenta;
import entidades.TipoCuenta;

import java.sql.Date;
import java.util.List;
import java.util.Map;

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
	public Cuenta obtenerPorCBU(String cbu);
	public Cuenta obtenerPorNroCuenta(int nroCuenta);
	public boolean incrementarSaldo (int nroCuenta, double importe);
	public boolean decrementarSaldo(int nroCuenta, double importe);
	//Reportes
	public List<Cuenta> listarCuentasFiltradasPorFecha(Date fechaDesde, Date fechaHasta);
	public Map<String, Object> generarEstadisticas(List<Cuenta> lista);

}
