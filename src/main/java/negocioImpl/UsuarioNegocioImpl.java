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
}
