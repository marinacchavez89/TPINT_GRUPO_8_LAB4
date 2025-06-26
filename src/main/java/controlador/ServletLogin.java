package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;


/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();

    public ServletLogin() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nombreUsuario = request.getParameter("usuario");
        String contrasena = request.getParameter("clave");
        
        Usuario usuarioLogueado = usuarioNegocio.autenticarUsuario(nombreUsuario, contrasena);

        if (usuarioLogueado != null && usuarioLogueado.getEstado()) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuarioLogueado);

            if ("admin".equalsIgnoreCase(usuarioLogueado.getTipoUsuario())) {
                response.sendRedirect("inicioAdmin.jsp");
            } else {
                response.sendRedirect("inicioCliente.jsp");
            }
        } else {
            request.setAttribute("mensajeError", "Usuario o contraseña incorrectos, o usuario inactivo.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    // Opcional: Manejar peticiones GET si alguien intenta acceder al servlet directamente
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

}
