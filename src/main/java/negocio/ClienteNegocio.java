package negocio;

import entidades.Cliente;
import java.util.List;

public interface ClienteNegocio {
	public boolean agregarCliente(Cliente cliente);
	public boolean eliminarCliente(int idCliente);
	public boolean modificarCliente(Cliente cliente);
	public Cliente obtenerClienteXId(int idCliente);
	public List<Cliente> listarClientes();
}
