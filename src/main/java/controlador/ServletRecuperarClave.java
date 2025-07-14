package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.EmailSenderNegocio;
import negocioImpl.EmailSenderImpl;

@WebServlet("/ServletRecuperarClave")
public class ServletRecuperarClave extends HttpServlet {
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String correo = request.getParameter("correo");
	        String dni = request.getParameter("dni");
	        
	        String nuevaClave = generarPasswordAleatoria();
	        
	        EmailSenderNegocio emailNegocio = new EmailSenderImpl();
	        
	        emailNegocio.enviarNuevaPassword(correo, nuevaClave);
	        
	        request.setAttribute("mensaje", "Se envió una nueva clave a tu correo. Inicia sesión nuevamente: ");
	        request.getRequestDispatcher("login.jsp").forward(request, response);
	 }
	 
	  private String generarPasswordAleatoria() {
	        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < 8; i++) {
	            int index = (int) (Math.random() * caracteres.length());
	            sb.append(caracteres.charAt(index));
	        }
	        return sb.toString();
	    }
	
}
