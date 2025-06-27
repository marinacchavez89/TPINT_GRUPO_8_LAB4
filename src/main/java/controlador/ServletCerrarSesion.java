package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet ("/CerrarSesion")
public class ServletCerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;


protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    HttpSession session = request.getSession(false); // No crea nueva si no existe
    if (session != null) {
        session.invalidate(); // Cierra la sesion
    }

    response.sendRedirect("login.jsp"); // Vuelve al login
}
}