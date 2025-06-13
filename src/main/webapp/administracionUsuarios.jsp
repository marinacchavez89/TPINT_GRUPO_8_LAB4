<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Administración Usuarios</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">	

<link rel="stylesheet" type="text/css" href="https:cdn.datatables.net/2.3.2/css/dataTables.dataTables.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src ="https://cdn.datatables.net/2.3.2/js/dataTables.min.js"></script>

   <script type="text/javascript">
    $(document).ready(function() {
        $('#table').DataTable();
           
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
    <th>Contraseña</th>
    <th>Tipo</th>
  </tr>
 </thead>
                   
  <tr>
 	 <td><input type="text" name="id" class="form-control" readonly></td>
    <td><input type="number" name="dni" class="form-control" required></td>
    <td><input type="number" name="cuil" class="form-control" required></td>
    <td><input type="text" name="nombre" class="form-control" required></td>
    <td><input type="password" name="contraseña" class="form-control" required></td>
    <td><input type="text" name="tipo" class="form-control" readonly></td>
 </tr>
                   
  </table>
 </div>
 
<div class="contenedor-botones">
  <button type="submit" name="accion" value="Agregar" class="btn btn-success btn-sm">Agregar</button>
  <button type="submit" name="accion" value="Modificar" class="btn btn-primary btn-sm">Modificar</button>
  <button type="submit" name="accion" value="Eliminar" class="btn btn-danger btn-sm">Eliminar</button>
  <button type="submit" name="accion" value="Listar" class="btn btn-secondary btn-sm">Listar</button>
</div>
  <jsp:include page="footer.jsp" />

</body>
</html>