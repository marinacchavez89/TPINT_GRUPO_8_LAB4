<%@ page import="entidades.Cuenta" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="validarSesion.jsp" %>
<%@ page import="entidades.Movimiento" %>   
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
<jsp:include page="bienvenidaUsuario.jsp"/>

<div class="container mt-5 mb-5">

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
        <div class="row mb-3">
            <div class="col bank-heading text-center">
                <h1>Mis Cuentas </h1>
            </div>
        </div>     

        <form method="get" action="ServletMisCuentas">
            <div class="row">
                <div class="col-md-6">
                    <label for="fechaInicio">Fecha de Inicio</label>
                    <input type="date" class="form-control" id="fechaInicio" name="fechaDesde" value="<%= request.getParameter("fechaDesde") != null ? request.getParameter("fechaDesde") : "" %>">
                </div>
                <div class="col-md-6">
                    <label for="fechaFin">Fecha de Fin</label>
                    <input type="date" class="form-control" id="fechaFin" name="fechaHasta" value="<%= request.getParameter("fechaHasta") != null ? request.getParameter("fechaHasta") : "" %>">
                </div>
            </div>
  
            <div class="col-md-6">        
                <label for="categoria">Seleccione Cuenta:</label>
                <select class="form-control" id="categoria" name="cuentaSeleccionada">
                    <option value="">Seleccione la cuenta</option>
      				<%
                        List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentasDelCliente");
                        String cuentaSeleccionada = (String) request.getAttribute("cuentaSeleccionada");

                        if (cuentas != null) {
                            for (Cuenta c : cuentas) {
                               String selected = (cuentaSeleccionada != null && cuentaSeleccionada.equals(String.valueOf(c.getNroCuenta()))) ? "selected" : "";
                    %>
                        <option value="<%= c.getNroCuenta() %>" <%= selected %>>Cuenta Nº <%= c.getNroCuenta() %> - Saldo: $<%= c.getSaldo() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            
            <div class="row mt-3">
                <div class="col">
                    <button type="submit" class="btn btn-primary">Buscar</button>
                </div>
            </div>
        </form>

        <hr>
        <%
        Cuenta cuentaDetalle = (entidades.Cuenta) request.getAttribute("cuentaDetalle");
        if (cuentaDetalle != null) {
    %>
    <div class="card mb-4">
        <div class="card-body">
            <h5>Detalle de Cuenta Nº <%= cuentaDetalle.getNroCuenta() %></h5>
            <p><strong>Tipo de Cuenta:</strong> <%= cuentaDetalle.getIdTipoCuenta().getDescripcion() %></p>
            <p><strong>CBU:</strong> <%= cuentaDetalle.getCBU() %></p>
            <p><strong>Saldo:</strong> $<%= cuentaDetalle.getSaldo() %></p>
            <p><strong>Fecha de Creación:</strong> <%= cuentaDetalle.getFechaCreación() %></p>
        </div>
    </div>
    <%
        }
    %>

        
            <div class="table-responsive">
                <h2>Movimientos</h2>
                <table class="table table-bordered text-center">
            <thead class="table-light">
                <tr>
                    <th>Fecha</th>
                    <th>Detalle</th>
                    <th>Importe</th>
                    <th>Tipo</th>
                    <th>Nro. Cuenta</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<entidades.Movimiento> movimientos = (List<entidades.Movimiento>) request.getAttribute("movimientos");
                    if (movimientos != null && !movimientos.isEmpty()) {
                        for (entidades.Movimiento m : movimientos) {
                %>
                <tr>
                    <td><%= m.getFecha() %></td>
                    <td><%= m.getDetalle() %></td>
                    <td>$<%= m.getImporte() %></td>
                    <td><%= m.getTipoMovimiento().getDescripcion() %></td>
                    <td><%= m.getCuenta().getNroCuenta() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">No hay movimientos para mostrar.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
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