<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recuperar clave - Banco </title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header text-center">
                    <h3>Recuperar clave</h3>
                </div>
                <div class="card-body">
                    <form action="ServletRecuperarClave" method="post">
                        <div class="mb-3">
                            <label for="correo" class="form-label">Correo electrónico registrado</label>
                            <input type="email" class="form-control" id="correo" name="correo" required placeholder="ejemplo@correo.com">
                        </div>
                        <div class="mb-3">
                            <label for="dni" class="form-label">DNI</label>
                            <input type="text" class="form-control" id="dni" name="dni" required placeholder="Ingresá tu DNI">
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Recuperar clave</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <a href="login.jsp">Volver al inicio</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
	<%@ include file="footer.jsp" %>
</body>
</html>