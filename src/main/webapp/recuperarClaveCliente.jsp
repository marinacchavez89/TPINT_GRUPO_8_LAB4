<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recuperar clave - Cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header text-center">
                    <h3>Actualizá tu clave</h3>
                </div>
                <div class="card-body">                
                
                <%-- MOSTRAR ERROR SI EXISTE --%>
			    <%
			        String error = (String) request.getAttribute("error");
			        if (error != null) {
			    %>
			        <div class="alert alert-danger text-center mb-3">
			            <%= error %>
			        </div>
			    <%
			        }
			    %>
			    
			    <%-- MOSTRAR ERROR SI EXISTE --%>
			    <%
			        String mensaje = (String) request.getAttribute("mensaje");
			        if (mensaje != null) {
			    %>
			        <div class="alert alert-success text-center mb-3">
			            <%= mensaje %>
			        </div>
			    <%
			        }
			    %>
                
                    <form action="ServletActualizarClave" method="post">                    	
					    <div class="mb-3">
					        <label for="claveActual" class="form-label">Ingrese su clave actual:</label>
					        <input type="password" class="form-control" id="claveActual" name="claveActual" required>
					    </div>
					    <div class="mb-3">
					        <label for="nuevaClave" class="form-label">Ingrese su nueva clave:</label>
					        <input type="password" class="form-control" id="nuevaClave" name="nuevaClave" required>
					    </div>
					    <div class="mb-3">
					        <label for="confirmarClave" class="form-label">Confirme su nueva clave:</label>
					        <input type="password" class="form-control" id="confirmarClave" name="confirmarClave" required>
					    </div>
					    <div class="d-grid gap-2">
					        <button type="submit" class="btn btn-primary">Actualizar clave</button>
					        <a href="perfilCliente.jsp" class="btn btn-secondary">Volver</a>
					    </div>
					</form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>