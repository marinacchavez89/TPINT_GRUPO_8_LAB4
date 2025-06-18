<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Administración Cuentas</title>
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
  	<th>Cliente</th>
    <th>Fecha creación</th>
    <th>Tipo de cuenta</th>
    <th>Número de cuenta</th>
    <th>CBU</th>
    <th>Saldo</th>
  </tr>
 </thead>
                   
  <tr>
 	 <td><input type="number" name="cliente" class="form-control" required></td>
    <td><input type="date" name="fechaCreacion" class="form-control" required></td>
    <td><input type="text" name="tipoCuenta" class="form-control" required></td>
    <td><input type="number" name="numeroCuenta" class="form-control" required></td>
    <td><input type="number" name="cbu" class="form-control" required></td>
    <td><input type="number" name="saldo" class="form-control" readonly></td>
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