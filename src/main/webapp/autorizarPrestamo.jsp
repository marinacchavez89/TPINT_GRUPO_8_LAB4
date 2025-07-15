<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="validarSesion.jsp" %>
<%@ page import="java.util.List, entidades.Prestamo, java.text.SimpleDateFormat, negocio.PrestamoNegocio" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autorización de Préstamos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>
<jsp:include page="navbarAdmin.jsp" />
<jsp:include page="bienvenidaUsuario.jsp"/>

<div class="container mt-5">

  <% if (request.getAttribute("mensaje") != null) { %>
    <div class="alert alert-info text-center">
      <%= request.getAttribute("mensaje") %>
    </div>
  <% } %>

  <h2 class="mb-4 text-center">Solicitudes de Préstamos</h2>

  <%
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
  List<Prestamo> listaPendientes = (List<Prestamo>) request.getAttribute("prestamosPendientes");


    if (listaPendientes != null && !listaPendientes.isEmpty()) {
      for (Prestamo p : listaPendientes) {
  %>
    <div class="card mb-3">
      <div class="card-body">
        <!-- <h5 class="card-title">Cliente ID: <%= p.getIdCliente() %></h5>  -->
        <h5 class="card-title">Cliente DNI: <%= p.getCliente().getDni() %></h5>
        <p class="card-text"><strong>Fecha de solicitud:</strong><%= ((p.getFechaAlta() != null) ? sdf.format(p.getFechaAlta()) :"Sin fecha")%></p>
        <p class="card-text"><strong>Monto solicitado:</strong> $<%= p.getImportePedido() %></p>
        <p class="card-text"><strong>Plazo:</strong> <%= p.getCantidadCuotas() %> cuotas</p>

        <div class="d-flex gap-2">


	<form action="ServletAutorizarPrestamos" method="post" onsubmit="return validarImporte(this);">
	<input type="hidden" name="accion" value="autorizar">
	<input type="hidden" name="idPrestamo" value="<%= p.getIdPrestamo() %>">
	<div class="d-flex flex-column flex-sm-row gap-2 align-items-start align-items-sm-center">
	<input type="number" step="0.01" name="importeAPagar" class="form-control" placeholder="Importe a pagar" required>
	<button class="btn btn-success" type="submit">Autorizar</button>
	</div>
	</form>

          <form action="ServletAutorizarPrestamos" method="post">
            <input type="hidden" name="accion" value="rechazar">
            <input type="hidden" name="idPrestamo" value="<%= p.getIdPrestamo() %>">
            <button class="btn btn-danger">Rechazar</button>
          </form>
        </div>
      </div>
    </div>
  <%
      }
    } else {
  %>
    <p class="text-center">No hay solicitudes de préstamos pendientes.</p>
  <%
    }
  %>
</div>


<div class="text-center mt-5 mb-5">
    <form action="inicioAdmin.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
</div>


<jsp:include page="footer.jsp" />
</body>
</html>
