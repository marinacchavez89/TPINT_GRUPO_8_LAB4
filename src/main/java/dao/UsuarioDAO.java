package dao;
import entidades.Usuario;

public interface UsuarioDAO {
	public boolean agregarUsuario(Usuario usuario);
	//public boolean eliminarUsuario (int idUsuario);
	public Usuario obtenerUsuarioPorCredenciales(String nombreUsuario, String contrasena);
	public int obtenerIdPorCliente(int idCliente);
	public Integer obtenerIdClientePorDni(String dni);
	public boolean actualizarPasswordPorDni(String dni, String nuevaPassword);
	public boolean actualizarPasswordPorIdUsuario(int idUsuario, String nuevaPassword);
}
