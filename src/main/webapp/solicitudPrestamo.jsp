<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Cuenta" %>
<%@ include file="validarSesion.jsp" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solicitar Préstamo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
<style> 
  .perfil-form input {
    cursor: text !important;
    background-color: #fff !important;
  }
</style>

</head>

<body>

<jsp:include page="navbar.jsp"/>

<div class="perfil-container">
 <div class="perfil-card">
  <h2 class="text-center mb-4">Solicitar Préstamo</h2>
  
  <form action="ServletPrestamos" method="post" class="perfil-form">
    <div class="row">
      <div class="mb-3">
        <label for="monto" class="form-label">Monto Solicitado</label>
        <input type="number" name="monto" id="monto" class="form-control" required>
      </div>
       <div class="mb-3">
        <label for="cuotas" class="form-label">Cantidad de Cuotas</label>
        <input type="number" name="cuotas" id="cuotas" class="form-control" required>
      </div>
      <div class="mb-3">
        <label for="cuenta" class="form-label">Cuenta de depósito</label>
        <select name="cuenta" id="cuenta" class="form-control" required>
        <option value="">Seleccione una cuenta</option>
    	<%
      		List<entidades.Cuenta> cuentas = (List<entidades.Cuenta>) request.getAttribute("cuentasDelCliente");
      		if (cuentas != null) {
        		for (entidades.Cuenta c : cuentas) {
    	%>
        <option value="<%= c.getNroCuenta() %>"> Nº <%= c.getNroCuenta() %> - CBU: <%= c.getCBU() %>
        </option>
       <%
        		}
     		 }
  	    %>
 	   </select>
      </div>
    </div>

    <!-- Botones -->
    <div class="d-flex gap-2 justify-content-center mt-4 mb-5">
      	<input type="submit" class="btn-solicitar" value="Solicitar">	
      <a href="gestionPrestamos.jsp" class="btn-volver">Volver</a>
      </div>
    </form>
  </div> <!-- fin perfil-card -->
</div> <!-- fin perfil-container -->

<!-- Footer afuera de los contenedores -->
<jsp:include page="footer.jsp" />

</body>
</html>