<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="validarSesion.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reportes</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<body>

<jsp:include page="navbarAdmin.jsp" />
<jsp:include page="bienvenidaUsuario.jsp"/>


<div class="container d-flex justify-content-center align-items-center" style="min-height: 70vh;">
   <div class="card shadow-lg p-4" style="width: 100%; max-width: 600px;">
      <h3 class="text-center mb-4">Seleccione un Reporte</h3>
      <div class="list-group">
        <a href="ServletReporteIngresosEgresos" class="list-group-item list-group-item-action d-flex align-items-center mb-2">
          <i class="bi bi-graph-up me-2 text-primary"></i> Reporte de Ingresos vs. Egresos
        </a>
        <a href="ServletReporteCuentas" class="list-group-item list-group-item-action d-flex align-items-center mb-2">
          <i class="bi bi-person-plus-fill me-2 text-success"></i> Reporte de Cuentas
        </a>
        <a href="ServletReportePrestamos" class="list-group-item list-group-item-action d-flex align-items-center mb-2">
          <i class="bi bi-cash-coin me-2 text-info"></i> Reporte de Préstamos Autorizados
        </a>
        <a href="#" class="list-group-item list-group-item-action d-flex align-items-center mb-2">
          <i class="bi bi-calendar-check me-2 text-warning"></i> Reporte de Pagos de Cuotas
        </a>
      </div>
      <div class="text-center mt-4">
        <a href="inicioAdmin.jsp" class="btn btn-outline-secondary"> Volver
        </a>
      </div>
    </div>
  </div>

<jsp:include page="footer.jsp" />
</body>
</html>