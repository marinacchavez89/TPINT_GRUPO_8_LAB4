<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Banco</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body class="bg-light">
	
	<jsp:include page="navbar.jsp" />
	
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow">
					<div class="card-header text-center">
						<h3>Inicio de Sesión</h3>
					</div>
					<div class="card-body">
					
					<%-- MOSTRAR MENSAJE SI EXISTE --%>
				    <%
				        String mensaje = (String) request.getAttribute("mensaje");
				        if (mensaje != null) {
				    %>
				        <div class="alert alert-danger text-center mb-3">
				            <%= mensaje %>
				        </div>
				    <%
				        }
				    %>		
						<form action="ServletLogin" method="post">
							<div class="mb-3">
								<label for="usuario" class="form-label">Usuario:</label> <input
									type="text" name="usuario" class="form-control" required>
							</div>
							<div class="mb-3">
								<label for="clave" class="form-label">Contraseña:</label> <input
									type="password" name="clave" class="form-control" required>
							</div>
							<div class="d-grid">
								<button type="submit" class="btn btn-primary">Ingresar</button>
							</div>
							<div class="text-center mt-3"><a href="recuperarClave.jsp">¿Olvidaste tu clave?</a> </div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="footer.jsp" />
	
	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
