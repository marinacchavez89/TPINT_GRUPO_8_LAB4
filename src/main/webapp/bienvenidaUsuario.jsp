<%@ page import="entidades.Usuario" %>
<%@ page import="entidades.Cliente" %>
<%
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
    Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");

    if (usuarioLogueado == null) {
        return;
    }

    String tipo = usuarioLogueado.getTipoUsuario().equalsIgnoreCase("admin") ? "Administrador" : "Cliente";

    String nombreMostrar = usuarioLogueado.getTipoUsuario().equalsIgnoreCase("cliente") && clienteLogueado != null
        ? clienteLogueado.getNombre() + " " + clienteLogueado.getApellido()
        : usuarioLogueado.getNombreUsuario();
%>

<div class="bg-light text-dark px-4 py-2 border-bottom shadow-sm fw-semibold">
    Bienvenido: <%= nombreMostrar %> (<%= tipo %>)
</div>