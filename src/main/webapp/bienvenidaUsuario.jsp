<%@ page import="entidades.Usuario" %>
<%
	//Intenta recuperar el usuario de la sesión
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

	//Por si esta página se incluye sin pasar por validarSesion.jsp
	if (usuarioLogueado == null) {
	    return; // No mostramos nada si no hay sesión
	}
	
	// Definimos si es admin o cliente	
       String tipo = usuarioLogueado.getTipoUsuario().equalsIgnoreCase("admin") ? "Administrador" : "Cliente";
%>
    <div class="bg-light text-dark px-4 py-2 border-bottom shadow-sm fw-semibold">
        Bienvenido: <%= usuarioLogueado.getNombreUsuario() %> (<%= tipo %>)
    </div>

