<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis Cuentas</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container mt-5 mb-5">
        <div class="row mb-3">
            <div class="col bank-heading text-center">
                <h1>Movimientos de Cuentas </h1>
            </div>
        </div>     

        <form>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="fechaInicio">Fecha de Inicio</label>
                    <input type="date" class="form-control" id="fechaInicio" required>
                </div>
                <div class="col-md-6">
                    <label for="fechaFin">Fecha de Fin</label>
                    <input type="date" class="form-control" id="fechaFin" required>
                </div>
            </div>
            <div class="form-group">
                <label for="categoria">Seleccione Cuenta:</label>
                <select class="form-control" id="categoria" required>
                    <option value="">Seleccione la cuenta</option>
                    <option value="1">Caja de Ahorro</option>
                    <option value="2">Cuenta Corriente</option>
                </select>
            </div>
            <div class="row mt-3">
                <div class="col">
                    <button type="submit" class="btn btn-primary">Buscar</button>
                </div>
            </div>
        </form>

        <hr>

        <div class="row mb-3">
            <div class="col">
                <h2>Detalle de Cuentas</h2>
                <table class="table table-striped text-center">
                    <thead>
                        <tr>
                            <th>Número de Cuenta</th>
                            <th>Fecha de Creación</th>
                            <th>Tipo de Cuenta</th>
                            <th>CBU</th>
                            <th>Saldo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        	<td>Cuenta 001</td>
                        	<td>05/06/2016</td>
                        	<td>Caja de Ahorro</td>
                            <td>12345678</td>
                            <td>$10.000</td>
                        </tr>
                        <tr>
                       	    <td>Cuenta 002</td>
                       	    <td>06/06/2006</td>
                       	    <td>Cuenta Corriente</td>
                            <td>87654321</td>
                            <td>$7.500</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col">
                <a href="inicioCliente.jsp" class="btn btn-primary">Volver</a>
            </div>
        </div>
    </div>



<jsp:include page="footer.jsp" />
</body>
</html>