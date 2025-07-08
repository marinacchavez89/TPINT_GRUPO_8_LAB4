<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import = "entidades.Cuenta" %>
    <%@ page import = "java.util.List" %>
    <%@ include file="validarSesion.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transferencias</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">

</head>
<body>

<jsp:include page="navbar.jsp"/>
<jsp:include page="bienvenidaUsuario.jsp"/>

<%
    String mensajeExito = (String) request.getAttribute("mensajeExito");
    String mensajeError = (String) request.getAttribute("mensajeError");
%>

<% if (mensajeExito != null) { %>
    <div class="alert alert-success text-center"><%= mensajeExito %></div>
<% } %>

<% if (mensajeError != null) { %>
    <div class="alert alert-danger text-center"><%= mensajeError %></div>
<% } %>

 <div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="form-box p-4 shadow rounded">
      <h2 class="text-center mb-4">Transferencia</h2>
      <form method="post" action= "ServletTransferencias">
        <div class="mb-3">
          <label for="cbuDestino" class="form-label">CBU destino:</label>
          <input type="text" class="form-control" id="cbuDestino" name="cbuDestino" required>    
        </div>

        <div class="mb-3">
          <label for="monto" class="form-label">Monto:</label>
          <input type="number" name="monto" class="form-control" id="monto" step="0.01" min="1" required>
        </div>

        <div class="mb-3">
          <label for="cuentaOrigen" class="form-label">Cuenta origen:</label>
          <select id="cuentaOrigen" name="cuentaOrigen" class="form-select" required>
            <option value="">Seleccione cuenta</option>
           <%
           List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentasDelCliente");
           if(cuentas!=null) {
        	   for(Cuenta c: cuentas) {
        	%>
        	<option value="<%= c.getNroCuenta() %>">Cuenta <%= c.getNroCuenta() %> - $<%= c.getSaldo() %></option>
        	<%  
        	   }
           }
           %>
          </select>
        </div>

        <div class="d-grid mt-5 mb-5">
          <button type="submit" class="btn btn-primary">Transferir</button>
        </div>
        
        <div class="row mt-3">
            <div class="col">
                <a href="inicioCliente.jsp" class="btn btn-primary">Volver</a>
            </div>
        </div>
        
      </form>
    </div>
  </div>
  
<jsp:include page="footer.jsp" />
</body>
</html>