package negocioImpl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.CuentaDAO;
import daoImpl.CuentaDAOImpl;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.TipoCuenta;
import entidades.TipoMovimiento;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocio.TipoCuentaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;


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

	    boolean cuentaAgregada= cuentaDAO.agregarCuenta(cuenta);
	    
	    if(cuentaAgregada) {
		      Cuenta cuentaInsertada = cuentaDAO.obtenerPorCBU(cuenta.getCBU());
		      
		      TipoCuentaNegocio tipoCuentaNegocio = new TipoCuentaNegocioImpl();
		       TipoCuenta tipoCuenta = tipoCuentaNegocio.obtenerTipoCuentaXId(cuenta.getIdTipoCuenta().getIdTipoCuenta());
		       
		        Movimiento movimiento = new Movimiento();
		        movimiento.setFecha(new java.util.Date());
		        movimiento.setDetalle("Alta de cuenta -  " + 
		            (tipoCuenta != null ? tipoCuenta.getDescripcion() : "desconocida"));
		        movimiento.setImporte(cuentaInsertada.getSaldo());
		        movimiento.setCuenta(cuentaInsertada);
		        
		        TipoMovimiento tipoMovimiento = new TipoMovimiento();
		        tipoMovimiento.setIdTipoMovimiento(1);         
		        movimiento.setTipoMovimiento(tipoMovimiento);
		        
		        MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
		        boolean exitoMovimiento = movimientoNegocio.agregarMovimiento(movimiento);
		        
		        if(!exitoMovimiento) {
		        	System.out.println("Movimiento no agregado");
		        }
	    }
	    
	    return cuentaAgregada;
	}

	@Override
	public boolean eliminarCuenta(int nroCuenta) {
		return cuentaDAO.eliminarCuenta(nroCuenta);
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		Cuenta existente = cuentaDAO.obtenerPorCBU(cuenta.getCBU());
		if (existente != null && existente.getNroCuenta() != cuenta.getNroCuenta()) {
		     return false; //existe otro CBU igual
	    }
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
		
		 if (cuenta == null) {
		        return false;
		    }
		 
		 if (!cuenta.isEstado()) {
		        int activas = cuentaDAO.contarCuentasActivasPorCliente(cuenta.getIdCliente());
		        if (activas >= 3) {
		            return false; //tiene 3 cuentas activas
		        }
		    }
		 cuenta.setEstado(!cuenta.isEstado());
		 return  cuentaDAO.actualizarEstadoCuenta(cuenta);
	}

	@Override
	public List<Cuenta> listarCuentasFiltradas(Boolean estado, int idCliente) {
		return cuentaDAO.listarCuentasFiltradas(estado, idCliente);
	}
	
	@Override
	public Cuenta obtenerPorCBU(String cbu) {
		System.out.println("[NEGOCIO] Buscando cuenta por CBU: " + cbu);
	    return cuentaDAO.obtenerPorCBU(cbu);
	}
	
	@Override
	public Cuenta obtenerPorNroCuenta(int nroCuenta) {
	    return cuentaDAO.obtenerPorNroCuenta(nroCuenta);
	}
	
	@Override
	public boolean incrementarSaldo (int nroCuenta, double importe) {
		return cuentaDAO.incrementarSaldo(nroCuenta, importe);
	}
	public boolean decrementarSaldo(int nroCuenta, double importe)
	{
		return cuentaDAO.decrementarSaldo(nroCuenta, importe);
	}

	@Override
	public List<Cuenta> listarCuentasFiltradasPorFecha(Date fechaDesde, Date fechaHasta) {
		 return cuentaDAO.listarCuentasFiltradasPorFecha(fechaDesde, fechaHasta);
	}
	
	@Override
	public Map<String, Object> generarEstadisticas(List<Cuenta> cuentas) {
	    Map<String, Object> stats = new HashMap<>();

	    int total = cuentas.size();
	    float saldoTotal = 0;
	    int ahorro = 0, corriente = 0;
	    int activas = 0, inactivas = 0;

	    for (Cuenta c : cuentas) {
	        saldoTotal += c.getSaldo();

	        if (c.getIdTipoCuenta().getIdTipoCuenta() == 1) {
	            ahorro++;
	        } else if (c.getIdTipoCuenta().getIdTipoCuenta() == 2) {
	            corriente++;
	        }

	        if (c.isEstado()) {
	            activas++;
	        } else {
	            inactivas++;
	        }
	    }

	    stats.put("total", total);
	    stats.put("saldoTotal", saldoTotal);
	    stats.put("ahorro", ahorro);
	    stats.put("corriente", corriente);
	    stats.put("activas", activas);
	    stats.put("inactivas", inactivas);

	    return stats;
	}

	@Override
	public boolean cambiarEstadoCuenta(Cuenta cuenta) {
		 if (!cuenta.isEstado()) {
		        // Quiere activar la cuenta, no hace falta validación
		        return cuentaDAO.actualizarEstadoCuenta(cuenta);
		    }

		    // Si quiere desactivarla, hay que validar que no tenga préstamos activos
		    PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
		    boolean tienePrestamosActivos = prestamoNegocio.tienePrestamosActivosPorCuenta(cuenta.getNroCuenta());

		    if (tienePrestamosActivos) {
		        return false; // No se puede desactivar
		    }

		    return cuentaDAO.actualizarEstadoCuenta(cuenta);
	}

	
}