<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autorizacion de Prestamos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container mt-5">
  <h2 class="mb-4 text-center">Solicitudes de Préstamos</h2>

    <div class="card mb-3">
    <div class="card-body">
      <h5 class="card-title">Cliente:</h5>
      <p class="card-text"><strong>Fecha de solicitud:</strong> dd/mm/aaaa</p>
      <p class="card-text"><strong>Monto solicitado:</strong> $</p>
      <p class="card-text"><strong>Plazo:</strong> x</p>

      <div class="d-flex gap-2">
        <button class="btn btn-success">Autorizar</button>
        <button class="btn btn-danger">Rechazar</button>
      </div>
    </div>
  </div>
</div>

  <div class="text-center mt-5 mb-5">
    <form action="inicioAdmin.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
  </div>

<jsp:include page="footer.jsp" />

</body>
</html>