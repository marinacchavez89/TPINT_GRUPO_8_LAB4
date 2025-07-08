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
	public boolean agregarCuenta(Cuenta cuenta) {
	    int cuentasActivas = cuentaDAO.contarCuentasActivasPorCliente(cuenta.getIdCliente());
	    
	    if (cuentasActivas >= 3) {
	        return false; //no deja mas de 3 cuentas
	    }
	    
	    Cuenta existente = cuentaDAO.obtenerPorCBU(cuenta.getCBU());
	    if (existente != null) {
	        return false; // ya existe una cuenta con ese CBU
	    }

	    cuenta.setSaldo(10000);// fuerza el monto inicial 

	    return cuentaDAO.agregarCuenta(cuenta);
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

	@Override
	public int obtenerProximoNumeroCuenta() {
	    return cuentaDAO.obtenerProximoNumeroCuenta();
	}

	@Override
	public int obtenerCantidadCuentasActivas(int idCliente) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<Cuenta> obtenerXIdCliente (int idCliente) {
		return cuentaDAO.obtenerXIdCliente(idCliente);
	}

	@Override
	public boolean cambiarEstadoCuenta(int nroCuenta) {
		Cuenta cuenta = cuentaDAO.obtenerPorNroCuenta(nroCuenta);
        if (cuenta != null) {
            cuenta.setEstado(!cuenta.isEstado());
            return cuentaDAO.actualizarEstadoCuenta(cuenta);
        }
		return false;
	}

	@Override
	public List<Cuenta> listarCuentasFiltradas(Boolean estado, int idCliente) {
		return cuentaDAO.listarCuentasFiltradas(estado, idCliente);
	}
	
	@Override
	public Cuenta obtenerPorCBU(String cbu) {
	    return cuentaDAO.obtenerPorCBU(cbu);
	}

}