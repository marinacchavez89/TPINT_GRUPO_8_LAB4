package dao;

import entidades.Cliente;
import java.util.List;

public interface ClienteDAO {
	public boolean agregar(Cliente cliente);
	public boolean eliminar(int idCliente);
	public boolean modificar(Cliente cliente);
	public Cliente obtenerXId(int idCliente);
	public List<Cliente> listar();
}
