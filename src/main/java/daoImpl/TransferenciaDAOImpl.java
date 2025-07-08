package daoImpl;


import java.sql.*;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import java.util.ArrayList;

import entidades.Cliente;
import entidades.Transferencia;
import entidades.Cuenta;

import dao.TransferenciaDAO;

import dominio.Conexion;

public class TransferenciaDAOImpl implements TransferenciaDAO {
	
	
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
