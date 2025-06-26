package dao;
import entidades.Usuario;

public interface UsuarioDAO {
	public boolean agregarUsuario(Usuario usuario);
	//public boolean eliminarUsuario (int idUsuario);
	public Usuario obtenerUsuarioPorCredenciales(String nombreUsuario, String contrasena);
}
