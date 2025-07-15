<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="validarSesion.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Préstamos Autorizados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<jsp:include page="navbarAdmin.jsp" />
<jsp:include page="bienvenidaUsuario.jsp"/>

<div class="container mt-5 mb-5">
    <h3 class="mb-4 text-center">Reporte de Préstamos Autorizados</h3>

    <form method="get" action="ServletReportePrestamos" class="row g-3 justify-content-center">
        <div class="col-md-4">
            <label for="fechaInicio" class="form-label">Fecha desde:</label>
            <input type="date" class="form-control" name="fechaInicio" required>
        </div>
        <div class="col-md-4">
            <label for="fechaFin" class="form-label">Fecha hasta:</label>
            <input type="date" class="form-control" name="fechaFin" required>
        </div>
        <div class="col-12 text-center">
            <button type="submit" class="btn btn-primary mt-3">Generar Reporte</button>
        </div>
    </form>

    <%
        Double totalSolicitado = (Double) request.getAttribute("totalSolicitado");
        Integer cantidad = (Integer) request.getAttribute("cantidad");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        if (totalSolicitado != null && cantidad != null) {
    %>
        <div class="card mt-5 mb-5 shadow-sm">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0">Resumen de préstamos autorizados</h5>
            </div>
            <div class="card-body">
                <div class="row text-center">
                    <div class="col-md-6 mb-3">
                        <div class="border p-3 rounded bg-light">
                            <h6>Total solicitado</h6>
                            <p class="h4 text-success">$<%= totalSolicitado %></p>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <div class="border p-3 rounded bg-light">
                            <h6>Cantidad de préstamos</h6>
                            <p class="h4 text-primary"><%= cantidad %></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <%
        } else if (fechaInicio != null && fechaFin != null) {
    %>
        <script>
            Swal.fire({
                icon: 'info',
                title: 'Sin resultados',
                text: 'No se encontraron préstamos aprobados en el período indicado.',
                confirmButtonText: 'Aceptar'
            });
        </script>
    <%
        }
    %>

    <div class="text-center mt-4">
        <a href="menuReportes.jsp" class="btn btn-secondary">Volver</a>
    </div>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
