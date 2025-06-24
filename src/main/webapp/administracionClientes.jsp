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

<div class="tabla-contenedor">
 <table id="table" class="tabla">
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
  <button type="submit" name="accion" value="Listar" class="btn btn-secondary btn-sm">Listar</button>
  <a href="inicioAdmin.jsp" class="btn btn-volver btn-sm">Volver</a>
</div>

  <jsp:include page="footer.jsp" />
</body>
</html>