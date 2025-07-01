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

<body>

<jsp:include page="navbar.jsp"/>

<div class="perfil-container">
 <div class="perfil-card">
  <h2 class="text-center mb-4">Solicitar Préstamo</h2>
  <form action="ServletPrestamo" method="post" class="perfil-form">
    <div class="row">
      <div class="mb-3">
        <label for="monto" class="form-label">Monto</label>
        <input type="number" name="monto" id="monto" class="form-control" required>
      </div>
       <div class="mb-3">
        <label for="cuotas" class="form-label">Cuotas</label>
        <input type="number" name="cuotas" id="cuotas" class="form-control" required>
      </div>
      <div class="mb-3">
        <label for="cuenta" class="form-label">Cuenta de depósito</label>
        <input type="text" name="cuenta" id="cuenta" class="form-control" required>
      </div>
      </div>
    </div>
  </form>

  <!-- Botones -->
  <div class="d-flex gap-2 justify-content-center mt-4 mb-5">
    <form action="" method="get">
      <input type="submit" class="btn-aceptar" value="Aceptar">
    </form>
    <form action="gestionPrestamos.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
  </div>
 </div> <!-- fin perfil-card -->
</div> <!-- fin perfil-container -->

<!-- Footer afuera de los contenedores -->
<jsp:include page="footer.jsp" />

</body>
</html>