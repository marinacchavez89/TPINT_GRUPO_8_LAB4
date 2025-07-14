package negocio;
import entidades.Usuario;

public interface UsuarioNegocio {
	public boolean agregarUsuario(Usuario usuario);
	public Usuario autenticarUsuario(String nombreUsuario, String contrasena);
	public int obtenerIdPorCliente(int idCliente);
	public Integer obtenerIdClientePorDni(String dni);
	public boolean actualizarPasswordPorDni(String dni, String nuevaPassword);
}
