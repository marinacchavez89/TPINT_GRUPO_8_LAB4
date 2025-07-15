<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List, entidades.Prestamo,entidades.Cuenta, java.text.SimpleDateFormat, negocio.PrestamoNegocio" %>
    
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <jsp:include page="navbar.jsp" />
    <jsp:include page="bienvenidaUsuario.jsp" />

    <div class="container mt-5">
        <div class="row mb-3">
            <div class="col text-center">
                <h2>Préstamos Autorizados</h2>
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
            </div>
        </div>
        <!-- Selección de Cuenta -->
        <form method="get" action="ServletListarPrestamosAutorizados">
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="cuentaSel" class="form-label">Seleccione la Cuenta</label>
                    <select id="cuentaSel" name="cuentaSeleccionada" class="form-control" onchange="this.form.submit()">
                        <option value="">-- Cuenta --</option>
                        <% 
                        List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentasDelCliente");
                        String sel = (String) request.getParameter("cuentaSeleccionada");
                        if (cuentas != null) {
                            for (Cuenta c : cuentas) {
                                String selected = c.getNroCuenta() == (sel!=null?Integer.parseInt(sel):-1) ? "selected" : "";
                        %>
                        <option value="<%= c.getNroCuenta() %>" <%= selected %>>Cuenta #<%= c.getNroCuenta() %> - Saldo $<%= c.getSaldo() %></option>
                        <%  }
                        }
                        %>
                    </select>
                </div>
            </div>
        </form>
         <div class="table-responsive">
            <table id="tablaAutorizados" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Nro Préstamo</th>
                        <th>Importe</th>
                        <th>Fecha Alta</th>
                        <th>Cantidad Cuotas</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    List<Prestamo> lista = (List<Prestamo>) request.getAttribute("prestamosAutorizados");
                    if (lista != null) {
                        for (Prestamo p : lista) {
                    %>
                    <tr>
                        <td><%= p.getIdPrestamo() %></td>
                        <td>$<%= p.getImportePedido() %></td>
                        <td><%= p.getFechaAlta()!=null ? sdf.format(p.getFechaAlta()) : "-" %></td>
                        <td><%= p.getCantidadCuotas() %></td>
                        <td>
                            <a href="ServletPagarCuotas?cuentaSeleccionada=<%= request.getParameter("cuentaSeleccionada") %>&idPrestamo=<%= p.getIdPrestamo() %>" 
                               class="btn btn-success btn-sm">Pagar Cuotas</a>
                        </td>
                    </tr>
                    <%      }
                    } %>
                </tbody>
            </table>
        </div>
        
        <div class="mt-4">
            <a href="gestionPrestamos.jsp" class="btn btn-secondary">Volver</a>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>