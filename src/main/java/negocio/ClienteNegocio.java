package negocio;

import entidades.Cliente;
import java.util.List;

public interface ClienteNegocio {
	public boolean agregarCliente(Cliente cliente);
	public boolean eliminarCliente(int idCliente);
	public boolean modificarCliente(Cliente cliente);
	public Cliente obtenerClienteXId(int idCliente);
	public int obtenerIdXDni(String dni);
	public List<Cliente> listarClientes();
	boolean setearUsuarioEnCliente(int idCliente, int idUsuario);
	public boolean existeClientePorDniYCorreo(String dni, String correo);
	
	public boolean existeDni(String dni, int idClienteExcluir);
	public boolean existeCuil(String cuil, int idClienteExcluir);
	
	public List<Cliente> listarInactivos();
	public boolean activarCliente(int idCliente);
}
