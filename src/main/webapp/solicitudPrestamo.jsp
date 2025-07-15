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

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>

<body>

<jsp:include page="navbar.jsp"/>

<div class="perfil-container">
 <div class="perfil-card">
  <h2 class="text-center mb-4">Solicitar Préstamo</h2>
  <%
	String mensajeError = (String) session.getAttribute("mensajeError");
	if(mensajeError != null) {
	%>
		<div class="alert alert-danger text-center"> 
		<%= mensajeError %>
		</div>
<%
	session.removeAttribute("mensajeError");
	}
%>
  <form action="ServletPrestamos" method="post" class="perfil-form" id="formPrestamo">
    <div class="row">
      <div class="mb-3">
        <label for="monto" class="form-label">Monto Solicitado</label>
        <input type="number" name="monto" id="monto" class="form-control" required min="1" step="1" pattern="\d+">
      </div>
       <div class="mb-3">
        <label for="cuotas" class="form-label">Cantidad de Cuotas</label>
        <input type="number" name="cuotas" id="cuotas" class="form-control" required min="1" step="1" pattern="\d+">
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
      	<input type="submit" id="btnSolicitar" class="btn-solicitar" value="Solicitar">	
      <a href="gestionPrestamos.jsp" class="btn-volver">Volver</a>
      </div>
    </form>
  </div> <!-- fin perfil-card -->
</div> <!-- fin perfil-container -->

<!-- Footer afuera de los contenedores -->
<jsp:include page="footer.jsp" />

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const btnSolicitar = document.getElementById("btnSolicitar");
    const formPrestamo = document.getElementById("formPrestamo");

    if (btnSolicitar && formPrestamo) {
      btnSolicitar.addEventListener("click", function (e) {
        e.preventDefault();

        // Obtener valores de los campos
        const monto = document.getElementById("monto").value.trim();
        const cuotas = document.getElementById("cuotas").value.trim();
        const cuenta = document.getElementById("cuenta").value;

        // Validar que monto y cuotas sean números positivos
        const montoValido = /^\d+$/.test(monto) && parseInt(monto) > 0;
        const cuotasValidas = /^\d+$/.test(cuotas) && parseInt(cuotas) > 0;
        const cuentaSeleccionada = cuenta !== "";

        if (!montoValido || !cuotasValidas || !cuentaSeleccionada) {
          Swal.fire({
            icon: 'error',
            title: 'Campos inválidos',
            html: `
              <ul style="text-align: left;">
                ${!montoValido ? "<li>El monto debe ser un número entero mayor a cero.</li>" : ""}
                ${!cuotasValidas ? "<li>Las cuotas deben ser un número entero mayor a cero.</li>" : ""}
                ${!cuentaSeleccionada ? "<li>Debe seleccionar una cuenta para el depósito.</li>" : ""}
              </ul>
            `
          });
          return;
        }

        // Si todo es válido, mostrar confirmación
        Swal.fire({
          title: '¿Deseas solicitar el préstamo?',
          text: "Una vez solicitado será procesado.",
          icon: 'question',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Sí, solicitar',
          cancelButtonText: 'Cancelar'
        }).then((result) => {
          if (result.isConfirmed) {
            Swal.fire({
              title: '¡Solicitud enviada!',
              text: 'Tu solicitud de préstamo fue registrada.',
              icon: 'success',
              showConfirmButton: false,
              timer: 2000
            });
            setTimeout(() => {
              formPrestamo.submit();
            }, 2000);
          } else {
            Swal.fire('Cancelado', 'La solicitud fue cancelada.', 'info');
          }
        });
      });
    }
  });
</script>

</body>
</html>