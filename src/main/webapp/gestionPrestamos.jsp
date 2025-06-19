<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Préstamos</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container mt-5  mb-5">
   <h2 class="mb-4 text-center">Gestión de Préstamos</h2>
    <div class="list-group col-6 mx-auto">
        <a href="solicitudPrestamo.jsp" class="list-group-item list-group-item-action">Solicitar Préstamo</a>
        <a href="" class="list-group-item list-group-item-action">Mis Préstamos</a>
    </div>
</div>
 
 <div class="text-center mt-5 mb-5">
    <form action="inicioCliente.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
</div>


 <jsp:include page="footer.jsp" />

</body>
</html>