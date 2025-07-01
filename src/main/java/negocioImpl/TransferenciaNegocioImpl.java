package negocioImpl;

import entidades.Transferencia;

import dao.ClienteDAO;
import dao.TransferenciaDAO;

import daoImpl.ClienteDAOImpl;
import daoImpl.TransferenciaDAOImpl;


import negocio.ClienteNegocio;
import negocio.TransferenciaNegocio;


public class TransferenciaNegocioImpl implements TransferenciaNegocio {

	private TransferenciaDAO transferenciaDAO = new TransferenciaDAOImpl();
	
}
