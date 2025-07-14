package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/ServletActualizarClave")
public class ServletActualizarClave extends HttpServlet {
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        // Obtenemos el usuario logueado desde la sesión
	        Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuarioLogueado");

	        if (usuarioSesion == null) {
	            response.sendRedirect("login.jsp");
	            return;
	        }

	        // Parámetros que recibimos del formulario
	        String claveActual = request.getParameter("claveActual");
	        String nuevaClave = request.getParameter("nuevaClave");
	        String confirmarClave = request.getParameter("confirmarClave");

	        // Validación de campos
	        if (!nuevaClave.equals(confirmarClave)) {
	            request.setAttribute("error", "La nueva clave y su confirmación no coinciden.");
	            request.getRequestDispatcher("recuperarClaveCliente.jsp").forward(request, response);
	            return;
	        }

	        UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();

	        // Verifica si la clave actual es válida
	        Usuario usuarioBD = usuarioNegocio.autenticarUsuario(usuarioSesion.getNombreUsuario(), claveActual);

	        if (usuarioBD == null) {
	            request.setAttribute("error", "La clave actual ingresada es incorrecta.");
	            request.getRequestDispatcher("recuperarClaveCliente.jsp").forward(request, response);
	            return;
	        }

	        // Actualizar la clave
	        boolean actualizada = usuarioNegocio.actualizarPasswordPorIdUsuario(usuarioSesion.getIdUsuario(), nuevaClave);

	        if (actualizada) {
	            request.setAttribute("mensaje", "¡La contraseña fue actualizada correctamente!");
	        } else {
	            request.setAttribute("error", "Ocurrió un error al intentar actualizar la contraseña.");
	        }

	        request.getRequestDispatcher("recuperarClaveCliente.jsp").forward(request, response);
	    }
}
