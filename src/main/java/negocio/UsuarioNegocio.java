package negocio;
import entidades.Usuario;

public interface UsuarioNegocio {
	public boolean agregarUsuario(Usuario usuario);
	public Usuario autenticarUsuario(String nombreUsuario, String contrasena);
	public int obtenerIdPorCliente(int idCliente);
}
