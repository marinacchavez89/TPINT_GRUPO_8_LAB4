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
<link rel="stylesheet" type="text/css" href="https:cdn.datatables.net/2.3.2/css/dataTables.dataTables.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src ="https://cdn.datatables.net/2.3.2/js/dataTables.min.js"></script>

   <script type="text/javascript">
    $(document).ready(function() {
        $('#tablaMisPrestamos').DataTable();
           
    });
</script>
</head>
<body>
<jsp:include page="navbar.jsp" />


<h1 class="text-center my-4">Mis Préstamos</h1>
<div class="tabla-contenedor">
    <table id="tablaMisPrestamos" class="tabla">
      <thead>
        <tr>
          <th>Nro Prestamo</th>
          <th>ID Cliente</th>
          <th>Fecha Creación</th>
          <th>Importe Pedido</th>
          <th>Plazo de pago</th>
          <th>Estado</th>         
        </tr>
      </thead>
      <tbody>
        <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
        List<Prestamo> listaMisPrestamos = (List<Prestamo>) request.getAttribute("misPrestamos");


          if (listaMisPrestamos != null && !listaMisPrestamos.isEmpty()) {
            for (Prestamo p : listaMisPrestamos) {
        %>
        <tr>
  <td><%= p.getIdPrestamo()%></td>
  <td><%= p.getIdCliente() %></td>
  <td><%= (p.getFechaAlta() != null) ? sdf.format(p.getFechaAlta()) : "Sin fecha" %></td>
  <td><%= p.getImportePedido() %></td>
  <td><%= p.getCantidadCuotas() + " cuotas" %></td>
  <td>
    <%
      String estadoStr = "Desconocido";
      if (p.getEstado() == 1) {
        estadoStr = "Pendiente";
      } else if (p.getEstado() == 2) {
        estadoStr = "Aprobado";
      } else if (p.getEstado() == 3) {
          estadoStr = "Pagado";
      } else if (p.getEstado() == 0) {
        estadoStr = "Rechazado";
      } 
    %>
    <%= estadoStr %>
  </td>
</tr>

        <% }
          }
        %>
      </tbody>
    </table>
  </div>
  
  <div class="text-center mt-5 mb-5">
    <form action="gestionPrestamos.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
  </div>

<jsp:include page="footer.jsp" />

</body>
</html>