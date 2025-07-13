<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="validarSesion.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Préstamos Autorizados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
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

    <div class="mt-5">
        <%
	        Double totalSolicitado = (Double) request.getAttribute("totalSolicitado");
	        Integer cantidad = (Integer) request.getAttribute("cantidad");


            if (totalSolicitado != null && cantidad != null) {
        %>
            <div class="alert alert-info text-center">
                <strong>Total solicitado:</strong> $<%= totalSolicitado %><br>
                <strong>Cantidad de préstamos:</strong> <%= cantidad %>
            </div>
        <%
            }
        %>
    </div>

    <div class="text-center mt-4">
        <a href="menuReportes.jsp" class="btn-volver">Volver</a>
    </div>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
