package daoImpl;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import entidades.Cliente;
import entidades.Transferencia;
import entidades.Cuenta;

import dao.TransferenciaDAO;

import dominio.Conexion;

public class TransferenciaDAOImpl implements TransferenciaDAO {
	
	private TransferenciaDAO transferenciaDAO = new TransferenciaDAOImpl();
	
	@Override
	public boolean registrarTransferencia(Transferencia transferencia) {
		return transferenciaDAO.registrarTransferencia(transferencia);
	}
}
