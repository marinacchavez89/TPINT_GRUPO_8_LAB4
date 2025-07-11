<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List, entidades.Prestamo, java.text.SimpleDateFormat, negocio.PrestamoNegocio" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Historial de Préstamos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container mt-5">
  <h2 class="mb-4 text-center">Mis Préstamos</h2>
	
	<%
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
  List<Prestamo> listaMisPrestamos = (List<Prestamo>) request.getAttribute("misPrestamos");


    if (listaMisPrestamos != null && !listaMisPrestamos.isEmpty()) {
      for (Prestamo p : listaMisPrestamos) {
  %>
	
    <div class="card mb-3">
    <div class="card-body">
      <h5 class="card-title">Préstamo nro: <%= p.getIdPrestamo() %></h5>
      <p class="card-text"><strong>Fecha de solicitud:</strong> <%= (p.getFechaAlta() != null) ? sdf.format(p.getFechaAlta()) : "Sin fecha" %></p>
      <p class="card-text"><strong>Monto solicitado:</strong> $<%= p.getImportePedido() %></p>
      <p class="card-text"><strong>Plazo:</strong> <%= p.getCantidadCuotas() %> cuotas</p>
      <p class="card-text"><strong>Estado:</strong> <%= 
          (p.getEstado() == 1) ? "Pendiente" :
          (p.getEstado() == 2) ? "Aprobado" :
          (p.getEstado() == 0) ? "Rechazado" : "Desconocido" 
        %></p>
    </div>
  </div>
<%
      }
    
    } else {
  %>
</div>
    
    <p class="text-center">No hay solicitudes de préstamos pendientes.</p>
  
  <%  
    }
  %>
  <div class="text-center mt-5 mb-5">
    <form action="gestionPrestamos.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
  </div>

<jsp:include page="footer.jsp" />

</body>
</html>