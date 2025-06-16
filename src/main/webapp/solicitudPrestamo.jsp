<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solicitar Préstamo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="perfil-container">
 <div class="perfil-card">
  <h2 class="text-center mb-4">Solicitar Préstamo</h2>
  <form class="perfil-form">
    <div class="row">
     
      <div class="col-md-12">
        <label for="nombre">Monto</label>
        <input type="number" id="nombre">

        <label for="dni">Cuotas</label>
        <input type="number" id="dni">

        <label for="cuil">Cuenta de depósito</label>
        <input type="text" id="cuil">
      </div>
    </div>
  </form>

  <!-- Botón -->
  <div class="d-flex gap-2 justify-content-center mt-4">
  <form action="" method="get">
    <input type="submit" class="btn-aceptar" value="Aceptar">
  </form>
  <form action="gestionPrestamos.jsp" method="get">
    <input type="submit" class="btn-volver" value="Volver">
  </form>
</div>




<jsp:include page="footer.jsp" />

</body>
</html>