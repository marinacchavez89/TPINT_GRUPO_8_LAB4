<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="java.util.List, entidades.Prestamo,entidades.Cuenta,entidades.Cuota, java.text.SimpleDateFormat, negocio.PrestamoNegocio" %>
    
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Pagar Prestamos</title>
</head>
<body>
    <jsp:include page="navbar.jsp" />
    <jsp:include page="bienvenidaUsuario.jsp" />

    <div class="container mt-5">
        <div class="row mb-3">
            <div class="col text-center">
                <h2>Cuotas Pendientes</h2>
            </div>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/ServletPagarCuotas">
            <input type="hidden" name="cuentaSeleccionada" value="<%= request.getParameter("cuentaSeleccionada") %>" />
            <input type="hidden" name="idPrestamo" value="<%= request.getParameter("idPrestamo") %>" />
            <% String error = (String) request.getAttribute("errorPago");
               if (error != null) { %>
                <div class="alert alert-danger"><%= error %></div>
            <% } %>
            
            <% String exito = (String) request.getAttribute("exitoPago");
   				if (exito != null) { %>
    			<div class="alert alert-success alert-dismissible fade show" role="alert">
        	<%= exito %>
        	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    		</div>
			<% } %>
            <ul class="list-group">
                <%
                List<Cuota> cuotas = (List<Cuota>) request.getAttribute("cuotasPendientes");
                if (cuotas != null && !cuotas.isEmpty()) {
                    for (Cuota c : cuotas) {
                %>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    Cuota #<%= c.getNumeroCuota() %> - Monto: $<%= String.format("%,.2f", c.getMonto()) %>
                    <button type="submit" name="idCuota" value="<%= c.getIdCuota() %>" class="btn btn-primary btn-sm">Pagar</button>
                </li>
                <%   }
                } else { %>
                <li class="list-group-item">No hay cuotas pendientes.</li>
                <% } %>
            </ul>
        </form>
        <div class="mt-4">
            <a href="ServletListarPrestamosAutorizados" class="btn btn-secondary">Volver</a>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>