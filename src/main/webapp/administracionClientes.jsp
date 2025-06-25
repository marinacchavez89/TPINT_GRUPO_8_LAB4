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

<jsp:include page="navbarAdmin.jsp"/>

<form action="ServletCliente" method="post">
<div class="tabla-contenedor">
 <table id="tablaClientes" class="tabla">
 
<thead>
    <tr>
      <th></th> <!-- columna vacía para el radio -->
      <th class="d-none">ID</th>
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
   <tr>
  <td>
    <input type="radio" name="clienteSeleccionado" onclick="seleccionarFila(this)"
      data-id="<%= c.getIdCliente() %>"
      data-dni="<%= c.getDni() %>"
      data-cuil="<%= c.getCuil() %>"
      data-nombre="<%= c.getNombre() %>"
      data-apellido="<%= c.getApellido() %>"
      data-sexo="<%= c.getSexo() %>"
      data-nacionalidad="<%= c.getNacionalidad().getIdNacionalidad() %>"
      data-fecha="<%= c.getFechaNacimiento() %>"
      data-direccion="<%= c.getDireccion().getIdDireccion() %>"
      data-email="<%= c.getCorreoElectronico() %>"
      data-telefono="<%= c.getTelefono() %>">
  </td>
  <td class="d-none"><%= c.getIdCliente() %></td>
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

<div class="contenedor-botones">
  <!--<button type="submit" name="accion" value="Agregar"  class="btn btn-success btn-sm">Agregar</button>-->
  <button type="button" onclick="mostrarFormularioAgregar()" class="btn btn-success btn-sm">Agregar</button>  
  <a href="inicioAdmin.jsp" class="btn btn-volver btn-sm">Volver</a>
</div>
</form>

<form action="ServletCliente" method="post" id="formModificar" style="display: none;">

<div class="tabla-contenedor mt-3">

 <!-- Título -->
 <h4 class="mb-3">Modificar o Eliminar cliente</h4>

  <div class="row">

    <!-- Columna izquierda -->
    <div class="col-md-6">
      <div class="mb-3">
        <input type="text" name="id" class="form-control" placeholder="ID" readonly>
      </div>
      <div class="mb-3">
        <input type="text" name="dni" class="form-control" placeholder="DNI" required>
      </div>
      <div class="mb-3">
        <input type="text" name="cuil" class="form-control" placeholder="CUIL" required>
      </div>
      <div class="mb-3">
        <input type="text" name="nombre" class="form-control" placeholder="Nombre" required>
      </div>
      <div class="mb-3">
        <input type="text" name="apellido" class="form-control" placeholder="Apellido" required>
      </div>
    </div>

    <!-- Columna derecha -->
    <div class="col-md-6">
      <div class="mb-3">
        <input type="text" name="sexo" class="form-control" placeholder="Sexo">
      </div>
      <div class="mb-3">
        <input type="text" name="nacionalidad" class="form-control" placeholder="Nacionalidad">
      </div>
      <div class="mb-3">
        <input type="date" name="fechaNacimiento" class="form-control" placeholder="Fecha Nacimiento">
      </div>
      <div class="mb-3">
        <input type="text" name="direccion" class="form-control" placeholder="Dirección">
      </div>
      <div class="mb-3">
        <input type="email" name="email" class="form-control" placeholder="Correo Electrónico">
      </div>
      <div class="mb-3">
        <input type="tel" name="telefono" class="form-control" placeholder="Teléfono">
      </div>
    </div>
  </div>
  
<!-- Botones centrados -->
 <div class="contenedor-botones">
	 <button type="submit" name="accion" value="Modificar" class="btn btn-primary btn-sm me-2">Modificar</button>
	 <button type="submit" name="accion" value="Eliminar" class="btn btn-danger btn-sm">Eliminar</button>
 </div> 
</div>
</form>

<form action="ServletCliente" method="post" id="formAgregar" style="display: none;">

	<div class="tabla-contenedor mt-3">
  <h4 class="mb-3">Agregar nuevo cliente</h4>

  <div class="row">
    <!-- Columna izquierda -->
    <div class="col-md-6">
      <div class="mb-3">
        <input type="text" name="dni" class="form-control" placeholder="DNI" required>
      </div>
      <div class="mb-3">
        <input type="text" name="cuil" class="form-control" placeholder="CUIL" required>
      </div>
      <div class="mb-3">
        <input type="text" name="nombre" class="form-control" placeholder="Nombre" required>
      </div>
      <div class="mb-3">
        <input type="text" name="apellido" class="form-control" placeholder="Apellido" required>
      </div>
    </div>

    <!-- Columna derecha -->
    <div class="col-md-6">
      <div class="mb-3">
        <input type="text" name="sexo" class="form-control" placeholder="Sexo">
      </div>
      <div class="mb-3">
        <input type="text" name="nacionalidad" class="form-control" placeholder="Nacionalidad">
      </div>
      <div class="mb-3">
        <input type="date" name="fechaNacimiento" class="form-control" placeholder="Fecha Nacimiento">
      </div>
      <div class="mb-3">
        <input type="text" name="direccion" class="form-control" placeholder="Dirección">
      </div>
      <div class="mb-3">
        <input type="email" name="email" class="form-control" placeholder="Correo Electrónico">
      </div>
      <div class="mb-3">
        <input type="tel" name="telefono" class="form-control" placeholder="Teléfono">
      </div>
    </div>
  </div>

  <!-- Botón Agregar -->
  <div class="contenedor-botones">
    <button type="submit" name="accion" value="Agregar" class="btn btn-success btn-sm">Guardar</button>
  </div>
</div>

</form>

  


  <jsp:include page="footer.jsp" />
  
<!-- Hacer que al seleccionar una fila, los datos se copien a los inputs :: -->
<script>

	function seleccionarFila(radio) {
		document.getElementById("formAgregar").style.display = "none";
	    const form = document.getElementById("formModificar");
	    const datos = radio.dataset;
	
	    form.style.display = "block";
	
	    document.querySelector('input[name="id"]').value = datos.id;
	    document.querySelector('input[name="dni"]').value = datos.dni;
	    document.querySelector('input[name="cuil"]').value = datos.cuil;
	    document.querySelector('input[name="nombre"]').value = datos.nombre;
	    document.querySelector('input[name="apellido"]').value = datos.apellido;
	    document.querySelector('input[name="sexo"]').value = datos.sexo;
	    document.querySelector('input[name="nacionalidad"]').value = datos.nacionalidad;
	    document.querySelector('input[name="fechaNacimiento"]').value = datos.fecha;
	    document.querySelector('input[name="direccion"]').value = datos.direccion;
	    document.querySelector('input[name="email"]').value = datos.email;
	    document.querySelector('input[name="telefono"]').value = datos.telefono;
	}
	
	function mostrarFormularioAgregar() {
	   // Oculta el de modificar si estaba abierto
	   document.getElementById("formModificar").style.display = "none";
	
	   // Limpia todos los campos del formulario de agregar
	   const formAgregar = document.getElementById("formAgregar");
	   formAgregar.style.display = "block";
	
	   // Limpieza de campos (puede mejorarse si ponés un `id` al form o inputs)
	   const inputs = formAgregar.querySelectorAll('input');
	   inputs.forEach(input => input.value = '');
	 }

</script>

</body>
</html>