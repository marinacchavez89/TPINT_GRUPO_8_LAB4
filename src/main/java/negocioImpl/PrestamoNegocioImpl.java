package negocioImpl;

import entidades.Cuenta;
import entidades.Cuota;
import entidades.Movimiento;
import entidades.Prestamo;
import entidades.TipoMovimiento;

import java.util.Date;
import java.util.List;

import dao.CuotaDAO;
import dao.MovimientoDAO;
import dao.PrestamoDAO;
import daoImpl.CuotaDAOImpl;
import daoImpl.MovimientoDAOImpl;
import daoImpl.PrestamoDAOImpl;
import dominio.Conexion;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {
    private PrestamoDAO prestamoDao = new PrestamoDAOImpl();
    private MovimientoDAO movDao = new MovimientoDAOImpl();
    private CuotaDAO cuotaDao = new CuotaDAOImpl();

    
    
    @Override
    public boolean solicitarPrestamo(Prestamo prestamo) {
        return prestamoDao.insert(prestamo);
    }

	@Override
	public boolean modificarEstadoPrestamo(Prestamo prestamo, int estado) {
		return prestamoDao.modificarEstado(prestamo, estado);
	}
	@Override
	public List<Prestamo> listarPrestamosPendientes()
	{
		return prestamoDao.listarPendientes(); // solo lista los -1
		
	}
	@Override
	public List <Prestamo> listarPrestamosPorCliente(int idCliente)
	{
		return prestamoDao.listarPorCliente(idCliente);
	}
	@Override
	public boolean autorizarPrestamo(int idPrestamo, double importeAPagar){
		// en primera instancia obtenemos el prestamo
		Prestamo p = prestamoDao.obtenerPorId(idPrestamo);
		if(p == null) return false; // el prestamo no existe o no nos retornan nada
		if(!prestamoDao.modificarEstado(p, 2)) return false; // suponemos al prestamo como APROBADO
		// registramos un deposito como movimiento
		
		Movimiento movimiento = new Movimiento();
		
		movimiento.setFecha(new Date());// HOY y AHORA. Si falla fecha revisar
		movimiento.setCuenta(p.getNroCuenta());
		movimiento.setDetalle("Deposito prestamo " + idPrestamo);
		movimiento.setImporte((float)p.getImportePedido());
		movimiento.setTipoMovimiento(new TipoMovimiento(2, "Alta de Prestamo"));//
		movDao.agregarMovimiento(movimiento);
		if(!movDao.agregarMovimiento(movimiento)) return false;
		
		if (!prestamoDao.actualizarImporteAPagar(idPrestamo, importeAPagar)) return false;
		
		int cant = p.getCantidadCuotas();
		float porCuota = (float) (importeAPagar / cant);
		
		// generacion de las cuotas
		for(int x = 1; x <= p.getCantidadCuotas(); x++) {
			Cuota cuota = new Cuota();
			cuota.setIdPrestamo(idPrestamo);
			cuota.setNumeroCuota(x);
			cuota.setMonto(porCuota);
			cuota.setFechaPago(null);
			cuota.setEstado(1);// SUPONGMAOS QUE ES PENDIENTE. no es string
			if(!cuotaDao.agregar(cuota)) return false;
		}
		
		//DEPOSITO EN CUENTA HACER!! JUAN
		
		return true;
	}
	
	 public boolean rechazarPrestamo(int idPrestamo){
		 Prestamo p = prestamoDao.obtenerPorId(idPrestamo);
		 if (p == null) return false;
		 return prestamoDao.modificarEstado(p,0); // 0: Rechazado
	 }
	 	
	 public boolean pagarCuota (int idCuota, int nroCuenta){

		 return false;// FALTA !!
	 }
	
	
}
//falta desarrollar autorizar prestamo. Pero hay que hacer movimientos

//Instanciar movimientos