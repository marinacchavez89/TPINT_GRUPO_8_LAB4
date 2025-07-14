package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.ClienteNegocio;
import negocio.EmailSenderNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.EmailSenderImpl;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/ServletRecuperarClave")
public class ServletRecuperarClave extends HttpServlet {
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String correo = request.getParameter("correo");
	        String dni = request.getParameter("dni");
	        
	        ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
	        if (!clienteNegocio.existeClientePorDniYCorreo(dni, correo)) {
	            request.setAttribute("error", "El correo y DNI no coinciden o no existen.");
	            request.getRequestDispatcher("recuperarClave.jsp").forward(request, response);
	            return;
	        }
	        
	        String nuevaClave = generarPasswordAleatoria();
	        
	        EmailSenderNegocio emailNegocio = new EmailSenderImpl();
	        
	        emailNegocio.enviarNuevaPassword(correo, nuevaClave);
	        
	        UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
	        boolean actualizoPass = usuarioNegocio.actualizarPasswordPorDni(dni, nuevaClave);
	        
	        if(actualizoPass) {
	        	request.setAttribute("mensaje", "Se envió una nueva clave a tu correo. Inicia sesión nuevamente con la password recibida: ");
	 	        request.getRequestDispatcher("login.jsp").forward(request, response);
	        }
	        else {
	        	request.setAttribute("error", "Ocurrió un error al generar nueva password.");
	 	        request.getRequestDispatcher("login.jsp").forward(request, response);
	        }	        
	       
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
