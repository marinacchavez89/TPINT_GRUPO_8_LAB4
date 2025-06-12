<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error - Banco </title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body class="bg-light">
   <div class="container text-center mt-5">
        <div class="alert alert-danger p-5 rounded shadow">
            <h1 class="display-4">Error</h1>
            <p class="lead">
                <%= exception != null ? exception.getMessage() : "Ha ocurrido un problema inesperado." %>
            </p>
            <hr>
            <a href="login.jsp" class="btn btn-primary mt-3">Volver al inicio</a>
        </div>
    </div>
</body>
</html>