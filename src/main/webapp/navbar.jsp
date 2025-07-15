<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Banco</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="inicioCliente.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ServletMisCuentas">Cuentas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ServletTransferencias">Transferencias</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="perfilCliente.jsp">Perfil</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" href="ServletListarPrestamos">Prestamos</a>
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
