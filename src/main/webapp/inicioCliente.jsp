<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="validarSesion.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Panel Cliente</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>


<jsp:include page="navbar.jsp"/>
<jsp:include page="bienvenidaUsuario.jsp"/>

<main>
<div class="container mt-5  mb-5 text-center">
        <h2 class="mb-4">Home</h2>
        <div class="row row-cols-1 row-cols-md-2 g-4">

    <div class="col">
      <a href="perfilCliente.jsp" class="text-decoration-none text-dark">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <i class="bi bi-person-fill fs-1 mb-2"></i>
            <h5 class="card-title">Mi Perfil</h5>
          </div>
        </div>
      </a>
    </div>

    <div class="col">
      <a href="misCuentas.jsp" class="text-decoration-none text-dark">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <i class="bi bi-wallet2 fs-1 mb-2"></i>
            <h5 class="card-title">Mis Cuentas</h5>
          </div>
        </div>
      </a>
    </div>

    <div class="col">
      <a href="transferencias.jsp" class="text-decoration-none text-dark">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <i class="bi bi-arrow-left-right fs-1 mb-2"></i>
            <h5 class="card-title">Transferencias</h5>
          </div>
        </div>
      </a>
    </div>

    <div class="col">
      <a href="gestionPrestamos.jsp" class="text-decoration-none text-dark">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <i class="bi bi-cash-coin fs-1 mb-2"></i>
            <h5 class="card-title">Préstamos</h5>
          </div>
        </div>
      </a>
    </div>

  </div>
</div>
</main>

 <jsp:include page="footer.jsp" />

</body>
</html>