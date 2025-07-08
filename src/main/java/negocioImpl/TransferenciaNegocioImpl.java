package negocioImpl;

import entidades.Cuenta;
import entidades.Movimiento;
import entidades.TipoMovimiento;

import entidades.Transferencia;
import excepciones.SaldoInsuficienteException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

import dao.CuentaDAO;
import dao.MovimientoDAO;
import dao.TransferenciaDAO;
import dominio.Conexion;

import daoImpl.CuentaDAOImpl;
import daoImpl.MovimientoDAOImpl;
import daoImpl.TransferenciaDAOImpl;

import negocio.ClienteNegocio;
import negocio.TransferenciaNegocio;


public class TransferenciaNegocioImpl implements TransferenciaNegocio {

	private TransferenciaDAO transferenciaDAO = new TransferenciaDAOImpl();
	private CuentaDAO cuentaDAO = new CuentaDAOImpl();
    private MovimientoDAO movimientoDAO = new MovimientoDAOImpl();
	
	
	@Override 
	public boolean registrarTransferencia(Transferencia transferencia) throws SaldoInsuficienteException {
		Cuenta cuentaOrigen = cuentaDAO.obtenerPorNroCuenta(transferencia.getNroCuentaOrigen());
		Cuenta cuentaDestino = cuentaDAO.obtenerPorNroCuenta(transferencia.getNroCuentaDestino());
        float importe = transferencia.getImporte();
        
        if (cuentaDestino == null) {
            throw new RuntimeException("CBU destino no encontrado");
        }
        
        // 1. validar saldo
        if (cuentaOrigen.getSaldo() < importe) {
            throw new SaldoInsuficienteException();
        }

        // 2. actualizar saldos
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - importe);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + importe);       
        cuentaDAO.actualizarSaldo(cuentaOrigen);
        cuentaDAO.actualizarSaldo(cuentaDestino);

		
		// 3. Registrar movimientos

	    Movimiento movDebito = new Movimiento();
	    movDebito.setFecha(new Date());
	    movDebito.setDetalle("Transferencia a CBU " + cuentaDestino.getCBU());
	    movDebito.setImporte(-importe); // negativo
	    TipoMovimiento tmDebito = new TipoMovimiento();
	    tmDebito.setIdTipoMovimiento(4);
	    movDebito.setTipoMovimiento(tmDebito);
	    movDebito.setCuenta(cuentaOrigen.getNroCuenta());
	    movimientoDAO.agregarMovimiento(movDebito);

	    Movimiento movCredito = new Movimiento();
	    movCredito.setFecha(new Date());
	    movCredito.setCuenta(cuentaDestino.getNroCuenta());
	    movCredito.setDetalle("Transferencia desde CBU " + cuentaOrigen.getCBU());
	    movCredito.setImporte(importe); // positivo
	    TipoMovimiento tmCredito = new TipoMovimiento();
	    tmCredito.setIdTipoMovimiento(3);
	    movCredito.setTipoMovimiento(tmCredito);
	    movimientoDAO.agregarMovimiento(movCredito);

	    // 4. Registrar la transferencia
	    transferencia.setFecha(new Date());
	    boolean resultado = transferenciaDAO.registrarTransferencia(transferencia);

	    return resultado;
	}
	
}
