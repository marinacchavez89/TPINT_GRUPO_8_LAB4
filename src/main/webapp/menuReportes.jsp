<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>
<body class="bg-light">

<jsp:include page="navbar.jsp" />

<div class="container mt-5">
    <h2 class="mb-4 text-center">Seleccione un Reporte</h2>
    <div class="list-group col-6 mx-auto">
        <a href="" class="list-group-item list-group-item-action">Reporte de Ingresos vs. Egresos</a>
        <a href="" class="list-group-item list-group-item-action">Reporte de Cuentas Nuevas</a>
        <a href="" class="list-group-item list-group-item-action">Reporte de Préstamos Autorizados</a>
        <a href="" class="list-group-item list-group-item-action">Reporte de Pagos de Cuotas</a>
    </div>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>