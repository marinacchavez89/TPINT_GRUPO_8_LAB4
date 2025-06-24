<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, entidades.Cliente" %>
<%@ page import="entidades.Nacionalidad, entidades.Direccion, entidades.Localidad" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Administracion Clientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">	

<link rel="stylesheet" type="text/css" href="https:cdn.datatables.net/2.3.2/css/dataTables.dataTables.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src ="https://cdn.datatables.net/2.3.2/js/dataTables.min.js"></script>

   <script type="text/javascript">
    $(document).ready(function() {
        $('#tablaClientes').DataTable();
           
    });
</script>

</head>
<body>

<jsp:include page="navbar.jsp"/>  

<form action="ServletCliente" method="post">
<div class="tabla-contenedor">
 <table id="tablaClientes" class="tabla">
 
<thead>
    <tr>
      <th>ID</th>
      <th>DNI</th>
      <th>CUIL</th>
      <th>Nombre</th>
      <th>Apellido</th>
      <th>Sexo</th>
      <th>Nacionalidad</th>
      <th>Fecha Nacimiento</th>
      <th>Direccion</th>
      <th>Correo Electronico</th>
      <th>Telefono</th>
    </tr>
  </thead>
  <tbody>
  <% 
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
    if (clientes != null) {
        for (Cliente c : clientes) {
%>
    <tr onclick="seleccionarFila(this)">
        <td><%= c.getIdCliente() %></td>
        <td><%= c.getDni() %></td>
        <td><%= c.getCuil() %></td>
        <td><%= c.getNombre() %></td>
        <td><%= c.getApellido() %></td>
        <td><%= c.getSexo() %></td>
		<td><%= c.getNacionalidad().getDescripcion() %></td>        
		<td><%= c.getFechaNacimiento() %></td>
		<td><%= c.getDireccion().getCalle() + " " + c.getDireccion().getNumero() %></td>
        <td><%= c.getCorreoElectronico() %></td>
        <td><%= c.getTelefono() %></td>
    </tr>
<%
        }
    }
%>
  </tbody>
  </table>
</div>

<!-- Inputs debajo de la tabla -->
<div class="tabla-contenedor mt-3">
  <table class="tabla">

    

                   
  <tr>
	<td><input type="text" name="id" class="form-control" readonly></td>
    <td><input type="number" name="dni" class="form-control" required></td>
    <td><input type="number" name="cuil" class="form-control" required></td>
    <td><input type="text" name="nombre" class="form-control" required></td>
    <td><input type="text" name="apellido" class="form-control" required></td>
    <td><input type="text" name="sexo" class="form-control"></td>
    <td><input type="text" name="nacionalidad" class="form-control"></td>
	<td><input type="date" name="fechaNacimiento" class="form-control"></td>
	<td><input type="text" name="direccion" class="form-control"></td>
	<td><input type="email" name="email" class="form-control"></td>
 	<td><input type="tel" name="telefono" class="form-control"></td>
 </tr>
</table>
</div>


<div class="contenedor-botones">
  <button type="submit" name="accion" value="Agregar" class="btn btn-success btn-sm">Agregar</button>
  <button type="submit" name="accion" value="Modificar" class="btn btn-primary btn-sm">Modificar</button>
  <button type="submit" name="accion" value="Eliminar" class="btn btn-danger btn-sm">Eliminar</button>
  <a href="inicioAdmin.jsp" class="btn btn-volver btn-sm">Volver</a>
</div>

</form>

  <jsp:include page="footer.jsp" />
  
<!-- Hacer que al seleccionar una fila, los datos se copien a los inputs :: -->
  <script>
	function seleccionarFila(fila) {
    var celdas = fila.getElementsByTagName("td");
    var inputs = document.querySelectorAll("input");

    inputs[0].value = celdas[0].innerText; // ID
    inputs[1].value = celdas[1].innerText; // DNI
    inputs[2].value = celdas[2].innerText; // CUIL
    inputs[3].value = celdas[3].innerText; // Nombre
    inputs[4].value = celdas[4].innerText; // Apellido
    inputs[5].value = celdas[5].innerText; // Sexo
    inputs[6].value = celdas[6].innerText; // Nacionalidad
    inputs[7].value = celdas[7].innerText; // Fecha Nacimiento
    inputs[8].value = celdas[8].innerText; // Direccion
    inputs[9].value = celdas[9].innerText; //Correo
    inputs[10].value = celdas[10].innerText; // Telefono
}
</script>
  
</body>
</html>