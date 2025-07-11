<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="validarSesion.jsp" %>
    
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
<jsp:include page="bienvenidaUsuario.jsp"/>

<main>
<div class="container mt-5  mb-5 text-center">
   <h2 class="mb-4">Gestión de Préstamos</h2>
   <div class="row row-cols-1 row-cols-md-2 g-4 justify-content-center">
   
   <div class="col">
       <a href="ServletPrestamos" class="text-decoration-none text-dark">
          <div class="card h-100 shadow-sm">
            <div class="card-body">
              <i class="bi bi-file-earmark-plus fs-1 mb-2"></i>
              <h5 class="card-title">Solicitar Préstamo</h5>
            </div>
          </div>
       </a>
   </div>
   
    <div class="col">
       <a href="ServletListarPrestamos" class="text-decoration-none text-dark">
          <div class="card h-100 shadow-sm">
            <div class="card-body">
              <i class="bi bi-list-check fs-1 mb-2"></i>
              <h5 class="card-title">Mis Préstamos</h5>
            </div>
          </div>
       </a>
    </div>  

  </div>
</div>
</main>
      
 <div class="text-center mt-5 mb-5">
    <form action="inicioCliente.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
</div>


 <jsp:include page="footer.jsp" />

</body>
</html>