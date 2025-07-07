package negocioImpl;

import entidades.Transferencia;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import dao.ClienteDAO;
import dao.TransferenciaDAO;

import daoImpl.ClienteDAOImpl;
import daoImpl.TransferenciaDAOImpl;
import dominio.Conexion;
import negocio.ClienteNegocio;
import negocio.TransferenciaNegocio;


public class TransferenciaNegocioImpl implements TransferenciaNegocio {

	private TransferenciaDAO transferenciaDAO = new TransferenciaDAOImpl();
	
	
	@Override 
	public boolean registrarTransferencia(Transferencia transferencia) {
		return transferenciaDAO.registrarTransferencia(transferencia);

	}
	
}
