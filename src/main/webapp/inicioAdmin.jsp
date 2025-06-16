<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Panel de Administrador - Banco</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body class= "bg-light">

<jsp:include page="navbar.jsp"
/>

<div class="container mt-5 text-center">
        <h2 class="mb-4">Panel de Administración</h2>
        <p>Gestion de adminstrador:</p>
        <div class="d-grid gap-2 col-6 mx-auto mt-4">
            <a href="" class="btn btn-outline-primary">ABML Clientes</a>
             <a href="administracionUsuarios.jsp" class="btn btn-outline-primary">ABML Usuarios</a>
            <a href="administracionCuentas.jsp" class="btn btn-outline-primary">ABML Cuentas y asignacion de cuenta a cliente</a>
            <a href="autorizarPrestamo.jsp" class="btn btn-outline-primary">Autorizacion de prestamos</a>
            <a href="" class="btn btn-outline-primary">Reportes</a>
        </div>
    </div>

    <jsp:include page="footer.jsp" />

</body>
</html>