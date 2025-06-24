package negocioImpl;

import java.util.List;

import dao.CuentaDAO;
import daoImpl.CuentaDAOImpl;
import entidades.Cuenta;
import entidades.TipoCuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {
	private CuentaDAO cuentaDAO= new CuentaDAOImpl();
	
	@Override
	public boolean agregarCuenta(Cuenta cuenta, int idCliente, TipoCuenta idTipoCuenta) {
		return cuentaDAO.agregarCuenta(cuenta, idCliente, idTipoCuenta);
	}

	@Override
	public boolean eliminarCuenta(int nroCuenta) {
		return cuentaDAO.eliminarCuenta(nroCuenta);
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		return cuentaDAO.modificarCuenta(cuenta);
	}

	@Override
	public List<Cuenta> listarCuentas() {
		return cuentaDAO.listarCuentas();
	}

}