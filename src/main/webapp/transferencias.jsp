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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
    <%
	String mensajeErrorSinCuentas = (String) session.getAttribute("mensajeErrorSinCuentas");
	if(mensajeErrorSinCuentas != null) {
	%>
		<div class="alert alert-danger text-center"> 
		<%= mensajeErrorSinCuentas %>
		</div>
<%
	session.removeAttribute("mensajeErrorSinCuentas");
	}
%>
      <h2 class="text-center mb-4">Transferencia</h2>
      <form id="formTransferencia" method="post" action= "ServletTransferencias">
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
          <button type="submit" id="btnTransferir" class="btn btn-primary">Transferir</button>
        </div>
        
        <div class="row mt-3">
            <div class="col">
                <a href="inicioCliente.jsp" class="btn btn-primary">Volver</a>
            </div>
        </div>
        
      </form>
    </div>
  </div>
  
<script>
  document.addEventListener("DOMContentLoaded", function () {
    const btnTransferir = document.getElementById("btnTransferir");
    const formTransferencia = document.getElementById("formTransferencia");

    if (btnTransferir && formTransferencia) {
      btnTransferir.addEventListener("click", function (e) {
        e.preventDefault();

        const cbu = document.getElementById("cbuDestino").value.trim();
        const monto = parseFloat(document.getElementById("monto").value);
        const cuenta = document.getElementById("cuentaOrigen").value;

        const cbuValido = cbu !== "" && /^\d+$/.test(cbu);
        const montoValido = !isNaN(monto) && monto > 0;
        const cuentaSeleccionada = cuenta !== "";

        if (!cbuValido || !montoValido || !cuentaSeleccionada) {
          Swal.fire({
            icon: 'error',
            title: 'Campos inválidos',
            html: `
              <ul style="text-align: left;">
                ${!cbuValido ? "<li>El CBU debe ser dígitos numéricos.</li>" : ""}
                ${!montoValido ? "<li>El monto debe ser un número mayor a cero.</li>" : ""}
                ${!cuentaSeleccionada ? "<li>Debe seleccionar una cuenta de origen.</li>" : ""}
              </ul>
            `
          });
          return;
        }

        Swal.fire({
          title: '¿Deseas realizar la transferencia?',
          text: "Una vez realizada no se puede deshacer.",
          icon: 'question',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Sí, transferir',
          cancelButtonText: 'Cancelar'
        }).then((result) => {
          if (result.isConfirmed) {
            formTransferencia.submit();
          } else {
            Swal.fire(
              'Cancelado',
              'La transferencia fue cancelada.',
              'info'
            );
          }
        });
      });
    }
  });
</script>
  
<jsp:include page="footer.jsp" />
</body>
</html>