<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="validarSesion.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="entidades.Cuenta" %>
<%@ page import="entidades.Cliente" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte Cuentas </title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>

<jsp:include page="navbarAdmin.jsp" />
<jsp:include page="bienvenidaUsuario.jsp" />

<div class="container mt-5">
    <h2 class="mb-4 text-center">Reporte de Cuentas </h2>
    <form action="ServletReporteCuentas" method="post" class="row g-3 mb-4">
        <div class="col-md-5">
            <label for="fechaDesde" class="form-label">Desde</label>
            <input type="date" name="fechaDesde" id="fechaDesde" class="form-control" required>
        </div>
        <div class="col-md-5">
            <label for="fechaHasta" class="form-label">Hasta</label>
            <input type="date" name="fechaHasta" id="fechaHasta" class="form-control" required>
        </div>
        <div class="col-md-2 d-flex align-items-end">
            <button type="submit" class="btn btn-primary w-100">Generar</button>
        </div>
    </form>

    <%
        List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
        Map<Integer, String> dniPorIdCliente = (Map<Integer, String>) request.getAttribute("dniPorIdCliente");
        if (cuentas != null && !cuentas.isEmpty()) {
    %>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Nro Cuenta</th>
                    <th>DNI Cliente</th>
                    <th>CBU</th>
                    <th>Fecha Creación</th>
                    <th>Tipo de Cuenta</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Cuenta c : cuentas) {
                %>
                    <tr>
                        <td><%= c.getNroCuenta() %></td>
                        <td><%= dniPorIdCliente.get(c.getIdCliente()) %></td>
                        <td><%= c.getCBU() %></td>
                        <td><%= c.getFechaCreación() %></td>
                        <td><%= c.getIdTipoCuenta().getDescripcion() %></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <% } else if (request.getParameter("fechaDesde") != null) { %>
    <script>
        Swal.fire({
            icon: 'info',
            title: 'Sin resultados',
            text: 'No se encontraron cuentas en el período indicado.',
            confirmButtonText: 'Aceptar'
        });
    </script>
	<% } %>
  
<c:if test="${not empty stats}">
    <div class="card mt-4 mb-4 shadow-sm">
        <div class="card-header bg-primary text-white">
            <h5 class="mb-0">Resumen de cuentas nuevas</h5>
        </div>
        <div class="card-body">
            <div class="row text-center">
                <div class="col-md-4 mb-3">
                    <div class="border p-3 rounded bg-light">
                        <h6>Total de cuentas</h6>
                        <p class="h4 text-primary">${stats.total}</p>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="border p-3 rounded bg-light">
                        <h6>Saldo total</h6>
                        <p class="h4 text-success">$${stats.saldoTotal}</p>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="border p-3 rounded bg-light">
                        <h6>Caja de ahorro</h6>
                        <p class="h4">${stats.ahorro}</p>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="border p-3 rounded bg-light">
                        <h6>Cuenta corriente</h6>
                        <p class="h4">${stats.corriente}</p>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="border p-3 rounded bg-light">
                        <h6>Activas</h6>
                        <p class="h4 text-success">${stats.activas}</p>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="border p-3 rounded bg-light">
                        <h6>Inactivas</h6>
                        <p class="h4 text-danger">${stats.inactivas}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
   
    

    <div class="text-center mt-4 mb-5">
        <a href="menuReportes.jsp" class="btn btn-secondary">Volver</a>
    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>