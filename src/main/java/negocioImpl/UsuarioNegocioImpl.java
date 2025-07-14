package negocioImpl;

import entidades.Usuario;
import negocio.UsuarioNegocio;
import dao.UsuarioDAO;
import daoImpl.UsuarioDAOImpl;

public class UsuarioNegocioImpl implements UsuarioNegocio{
	private UsuarioDAO usuarioDAO= new UsuarioDAOImpl();
	
	@Override
	public boolean agregarUsuario(Usuario usuario) {
		return usuarioDAO.agregarUsuario(usuario);
	}
	
	@Override
    public Usuario autenticarUsuario(String nombreUsuario, String contrasena) {
		 return usuarioDAO.obtenerUsuarioPorCredenciales(nombreUsuario, contrasena);
    }

	@Override
	public int obtenerIdPorCliente(int idCliente) {
	    return usuarioDAO.obtenerIdPorCliente(idCliente);
	}
	
	@Override
	public Integer obtenerIdClientePorDni(String dni) {
		return usuarioDAO.obtenerIdClientePorDni(dni);
	}
	
	@Override
	public boolean actualizarPasswordPorDni(String dni, String nuevaPassword) {
	    return usuarioDAO.actualizarPasswordPorDni(dni, nuevaPassword);
	}
	
	@Override
	public boolean actualizarPasswordPorIdUsuario(int idUsuario, String nuevaPassword) {
		return usuarioDAO.actualizarPasswordPorIdUsuario(idUsuario, nuevaPassword);
	}
}