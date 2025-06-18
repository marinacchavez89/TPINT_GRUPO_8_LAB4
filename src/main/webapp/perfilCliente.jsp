<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mi Perfil</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="perfil-container">
 <div class="perfil-card">
  <h2 class="text-center mb-4">Mi Perfil</h2>
  <form class="perfil-form">
    <div class="row">
     
      <div class="col-md-6">
        <label for="nombre">Nombre</label>
        <input type="text" id="nombre">

        <label for="dni">DNI</label>
        <input type="text" id="dni">

        <label for="cuil">CUIL</label>
        <input type="text" id="cuil">

        <label for="nacionalidad">Nacionalidad</label>
        <input type="text" id="nacionalidad">

        <label for="direccion">Dirección</label>
        <input type="text" id="direccion">

        <label for="provincia">Provincia</label>
        <input type="text" id="provincia">

        <label for="email">Email</label>
        <input type="email" id="email">
      </div>


      <div class="col-md-6">
        <label for="apellido">Apellido</label>
        <input type="text" id="apellido">

        <label for="usuario">Usuario</label>
        <input type="text" id="usuario">

        <label for="sexo">Sexo</label>
        <input type="text" id="sexo">

        <label for="fechaNacimiento">Fecha de Nacimiento</label>
        <input type="date" id="fechaNacimiento">

        <label for="localidad">Localidad</label>
        <input type="text" id="localidad">

        <label for="telefono">Teléfono</label>
        <input type="tel" id="telefono">
      </div>
    </div>
  </form>

  
  <div class="text-center mt-4">
    <form action="inicioCliente.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
  </div>
 </div>
</div>



<jsp:include page="footer.jsp" />

</body>
</html>