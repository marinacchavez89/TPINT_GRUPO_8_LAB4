<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Banco - Perfil Administrador</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarAdmin" aria-controls="navbarAdmin" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarAdmin">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="inicioAdmin.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ServletCliente">Administración Clientes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ServletCuenta">Administración Cuentas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ServletAutorizarPrestamos">Administración Préstamos</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" href="menuReportes.jsp">Reportes</a>
                </li>
                <% 
        			if (session.getAttribute("usuarioLogueado") != null) {
			    %>
			        <li class="nav-item">
			            <a class="nav-link text-warning" href="CerrarSesion">Cerrar Sesión</a>
			        </li>
			    <%
			        }
			    %>
            </ul>
        </div>
    </div>
</nav>
