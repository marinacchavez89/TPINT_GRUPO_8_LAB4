<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="validarSesion.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Panel de Administrador - Banco</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body class= "bg-light">

<jsp:include page="navbarAdmin.jsp"/>
<jsp:include page="bienvenidaUsuario.jsp"/>

<div class="container mt-5 mb-5 text-center">
        <h2 class="mb-4">Panel de Administración</h2>
        <div class="row row-cols-1 row-cols-md-2 g-4">
        
         <div class="col">
      	  <a href="ServletCliente" class="text-decoration-none text-dark">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <i class="bi bi-people-fill fs-1 mb-2"></i>
            <h5 class="card-title">Gestión de Clientes</h5>
          </div>
        </div>
      </a>
    </div>

    <div class="col">
      <a href="ServletCuenta" class="text-decoration-none text-dark">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <i class="bi bi-bank fs-1 mb-2"></i>
            <h5 class="card-title">Gestión de Cuentas</h5>
          </div>
        </div>
      </a>
    </div>

    <div class="col">
      <a href="ServletAutorizarPrestamos" class="text-decoration-none text-dark">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <i class="bi bi-cash-stack fs-1 mb-2"></i>
            <h5 class="card-title">Autorizar Préstamos</h5>
          </div>
        </div>
      </a>
    </div>
        
      <div class="col">
      <a href="menuReportes.jsp" class="text-decoration-none text-dark">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <i class="bi bi-graph-up-arrow fs-1 mb-2"></i>
            <h5 class="card-title">Reportes</h5>
          </div>
        </div>
      </a>
    </div>
   </div>        
   </div>

    <jsp:include page="footer.jsp" />

</body>
</html>