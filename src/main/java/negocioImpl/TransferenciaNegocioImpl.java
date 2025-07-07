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
		PreparedStatement statement;
		Connection conn = Conexion.getSQLConexion();
		boolean registrado = false;
		
		String sql= "INSERT INTO transferencia (nro_cuenta_origen, nro_cuenta_destino, importe, fecha) VALUES (?,?,?,?)";
		
		try {
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setInt(1, transferencia.getNroCuentaOrigen());
			statement.setInt(2, transferencia.getNroCuentaDestino());
			statement.setFloat(3, transferencia.getImporte());
			statement.setDate(4, new java.sql.Date(transferencia.getFecha().getTime()));
		
			if(statement.executeUpdate() > 0) {
				conn.commit();
				registrado = true;
		}
		
		} catch (SQLException e) {
		    e.printStackTrace();
		    try {
		        conn.rollback();
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		}
		
		
		return registrado;
	}
	
}
