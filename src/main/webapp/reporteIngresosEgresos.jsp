<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="validarSesion.jsp" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte Ingresos vs Egresos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<jsp:include page="navbarAdmin.jsp" />
<jsp:include page="bienvenidaUsuario.jsp" />

<div class="container mt-5">
    <h2 class="mb-4 text-center">Reporte de Ingresos vs Egresos</h2>
    <form action="ServletReporteIngresosEgresos" method="post" class="row g-3 mb-4">
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
    Map<String, Double> resumen = (Map<String, Double>) request.getAttribute("resumen");
    if (resumen != null) {
%>

    <div class="alert alert-info text-center">
        <strong>Total Ingresos:</strong> $<%= String.format("%.2f", resumen.getOrDefault("totalIngresos", 0.0)) %> <br>
        <strong>Promedio Ingresos:</strong> $<%= String.format("%.2f", resumen.getOrDefault("promedioIngresos", 0.0)) %> <br>
        <strong>Total Egresos:</strong> $<%= String.format("%.2f", resumen.getOrDefault("totalEgresos", 0.0)) %> <br>
        <strong>Promedio Egresos:</strong> $<%= String.format("%.2f", resumen.getOrDefault("promedioEgresos", 0.0)) %>
    </div>

    <div class="card mt-4 mb-4 shadow-sm">
        <div class="card-header bg-primary text-white">
            <h5 class="mb-0">Resumen discriminado</h5>
        </div>
        <div class="card-body">
            <div class="row text-center">
                <div class="col-md-3 mb-3">
                    <div class="border p-3 rounded bg-light">
                        <h6>Pagos de cuotas</h6>
                        <p class="h4 text-success">$<%= String.format("%.2f", resumen.getOrDefault("pagosPrestamo", 0.0)) %></p>
                    </div>
                </div>
                <div class="col-md-3 mb-3">
                    <div class="border p-3 rounded bg-light">
                        <h6>Transferencias debitadas</h6>
                        <p class="h4 text-danger">$<%= String.format("%.2f", resumen.getOrDefault("transferenciasDebito", 0.0)) %></p>
                    </div>
                </div>
                <div class="col-md-3 mb-3">
                    <div class="border p-3 rounded bg-light">
                        <h6>Transferencias acreditadas</h6>
                        <p class="h4 text-success">$<%= String.format("%.2f", resumen.getOrDefault("transferenciasCredito", 0.0)) %></p>
                    </div>
                </div>
                <div class="col-md-3 mb-3">
                    <div class="border p-3 rounded bg-light">
                        <h6>Préstamos otorgados</h6>
                        <p class="h4 text-success">$<%= String.format("%.2f", resumen.getOrDefault("prestamosOtorgados", 0.0)) %></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%
    } else if (request.getParameter("fechaDesde") != null) {
%>
    <script>
        Swal.fire({
            icon: 'info',
            title: 'Sin resultados',
            text: 'No se encontraron movimientos en el período indicado.',
            confirmButtonText: 'Aceptar'
        });
    </script>
<%
    }
%>

    <div class="text-center mt-4 mb-5">
        <a href="menuReportes.jsp" class="btn btn-secondary">Volver</a>
    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>