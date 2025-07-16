<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, entidades.Cliente" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Clientes Inactivos</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.3.2/css/dataTables.dataTables.min.css">
  <link href="css/estilos.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
  <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/2.3.2/js/dataTables.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <script>
    $(document).ready(function () {
      $('#tablaClientes').DataTable();
    });
  </script>
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<jsp:include page="bienvenidaUsuario.jsp"/>

<div class="container mt-4">

<script>
	<%
	String mensaje = (String) request.getAttribute("confirmacionMensaje");
	String tipo = (String) request.getAttribute("confirmacionTipo");
	if (mensaje != null && !mensaje.isEmpty()) {
	%>
	Swal.fire({
	    icon: '<%= tipo %>',
	    title: '<%= "success".equals(tipo) ? "Operación Exitosa" : "Error en la operación" %>',
	    text: '<%= mensaje %>',
	    showConfirmButton: false,
	    timer: 2500
	});
	<%
	}
	%>
</script>

  <h3>Clientes Inactivos</h3>
  <table id="tablaClientes" class="display">
    <thead>
      <tr>
        <th>ID</th>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Correo</th>
        <th>Teléfono</th>
        <th>Acción</th>
      </tr>
    </thead>
    <tbody>
      <%
        List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientesInactivos");
        if (clientes != null) {
          for (Cliente c : clientes) {
      %>
      <tr>
        <td><%= c.getIdCliente() %></td>
        <td><%= c.getDni() %></td>
        <td><%= c.getNombre() %></td>
        <td><%= c.getApellido() %></td>
        <td><%= c.getCorreoElectronico() %></td>
        <td><%= c.getTelefono() %></td>
        <td>
          <form action="ServletActivarClientes" method="post">
            <input type="hidden" name="accion" value="ActivarCliente">
            <input type="hidden" name="idCliente" value="<%= c.getIdCliente() %>">
            <button type="submit" class="btn btn-success btn-sm">Activar</button>
          </form>
        </td>
      </tr>
      <%
          }
        }
      %>
    </tbody>
  </table>
  <div class="mt-3">
    <a href="ServletCliente" class="btn btn-secondary">Volver</a>
  </div>
</div>

</body>
</html>