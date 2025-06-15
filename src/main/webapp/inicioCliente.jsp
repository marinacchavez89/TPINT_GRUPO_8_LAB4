<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Panel Cliente</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container mt-5  mb-5 text-center">
        <h2 class="mb-4">Home</h2>
        <div class="d-grid gap-2 col-6 mx-auto mt-4">
            <a href="perfilCliente.jsp" class="btn-cliente">Mi Perfil</a>
             <a href="" class="btn-cliente">Trasnferencias</a>
            <a href="" class="btn-cliente">Préstamos</a>
            <a href="" class="btn-cliente">Cuentas</a>
           
        </div>
 </div>

 <jsp:include page="footer.jsp" />

</body>
</html>