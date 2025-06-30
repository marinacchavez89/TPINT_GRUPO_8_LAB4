package negocioImpl;

import java.util.List;

import dao.ClienteDAO;
import daoImpl.ClienteDAOImpl;
import entidades.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {
	private ClienteDAO clienteDAO= new ClienteDAOImpl();
	
	@Override
	public boolean agregarCliente(Cliente cliente) {
		return clienteDAO.agregar(cliente);
	}
	
	@Override
	public boolean eliminarCliente(int idCliente) {
		return clienteDAO.eliminar(idCliente);
	}
	
	@Override
	public boolean modificarCliente(Cliente cliente) {
		return clienteDAO.modificar(cliente);
	}
	
	@Override
	public Cliente obtenerClienteXId(int idCliente) {
		return clienteDAO.obtenerXId(idCliente);
	}
	
	@Override
	public int obtenerIdXDni(String dni) {
		return clienteDAO.obtenerIdXDni(dni);
	}
	
	@Override
	public List<Cliente> listarClientes(){
		return clienteDAO.listar();
	}
	
	@Override
	public boolean setearUsuarioEnCliente(int idCliente, int idUsuario) {
	    return clienteDAO.setearUsuarioEnCliente(idCliente, idUsuario);
	}
}
