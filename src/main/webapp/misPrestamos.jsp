<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Historial de Préstamos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container mt-5">
  <h2 class="mb-4 text-center">Mis Préstamos</h2>

    <div class="card mb-3">
    <div class="card-body">
      <h5 class="card-title">Préstamo nro: </h5>
      <p class="card-text"><strong>Fecha de solicitud:</strong> dd/mm/aaaa</p>
      <p class="card-text"><strong>Monto solicitado:</strong> $</p>
      <p class="card-text"><strong>Plazo:</strong> x</p>
      <p class="card-text"><strong>Estado:</strong> Aprobado/Rechazado</p>
    </div>
  </div>
</div>

  <div class="text-center mt-5 mb-5">
    <form action="gestionPrestamos.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
  </div>

<jsp:include page="footer.jsp" />

</body>
</html>