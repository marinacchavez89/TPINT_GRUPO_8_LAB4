<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="validarSesion.jsp" %>
    
    <%@ page import="entidades.Cliente" %>
    <%@ page import="entidades.Usuario" %>
    
<%
    Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
    if (cliente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
%>

<%   
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    	if (usuario == null) {
        	response.sendRedirect("login.jsp");
        	return;
    }
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mi Perfil</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>

<jsp:include page="navbar.jsp"/>
<jsp:include page="bienvenidaUsuario.jsp"/>

<main class="flex-grow-1 d-flex justify-content-center align-items-center py-5">
 <div class="perfil-card">
  <h2 class="text-center mb-4">Mi Perfil</h2>
  <form class="perfil-form">
    <div class="row">
     
      <div class="col-md-6">
        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" value="<%= cliente.getNombre() %>" disabled>

        <label for="dni">DNI</label>
        <input type="text" id="dni" value="<%= cliente.getDni() %>" disabled>
        
        <label for="usuario">Usuario</label>
        <input type="text" id="usuario" value= "<%= usuario.getNombreUsuario() %>" disabled>
        
        <label for="direccion">Dirección</label>
        <input type="text" id="direccion" value= "<%= cliente.getDireccion().getCalle() + " " + cliente.getDireccion().getNumero() %>" disabled>
        
        <label for="nacionalidad">Nacionalidad</label>
		<input type="text" id="nacionalidad" value= "<%= cliente.getNacionalidad().getDescripcion() %>" disabled>	    

        <label for="provincia">Provincia</label>
		<input type="text" id="provincia" value= "<%= cliente.getDireccion().getLocalidad().getProvincia().getNombreProvincia() %>" disabled>
		
		<%
    		String sexo = String.valueOf(cliente.getSexo());
		%>
		<label for="sexo">Género</label>
		<select id="sexo" class="form-select" disabled>
  			<option value="F" <%= sexo.equals("F") ? "selected" : "" %>>Femenino</option>
  			<option value="M" <%= sexo.equals("M") ? "selected" : "" %>>Masculino</option>
  			<option value="O" <%= sexo.equals("O") ? "selected" : "" %>>Otro</option>
		</select>
                
      </div>


      <div class="col-md-6">
        <label for="apellido">Apellido</label>
        <input type="text" id="apellido" value="<%= cliente.getApellido() %>" disabled>
        
        <label for="cuil">CUIL</label>
        <input type="text" id="cuil" value="<%= cliente.getCuil() %>" disabled>
        
        <label for="email">Email</label>
        <input type="email" id="email" value="<%= cliente.getCorreoElectronico() %>" disabled>
        
        <label for="telefono">Teléfono</label>
        <input type="tel" id="telefono" value= "<%= cliente.getTelefono() %>" disabled>
        
        <label for="nacionalidad">Pais de Residencia</label>
		<input type="text" id="paisResidencia" value= "<%= cliente.getDireccion().getLocalidad().getProvincia().getPaisResidencia().getDescripcion() %>" disabled>
		
		<label for="localidad">Localidad</label>
		<input type="text" id="localidad" value= "<%= cliente.getDireccion().getLocalidad().getNombreLocalidad() %>" disabled> 
		
        <label for="fechaNacimiento">Fecha de Nacimiento</label>
        <input type="date" id="fechaNacimiento" value= "<%= cliente.getFechaNacimiento() %>" disabled>
        
      </div>
    </div>
  </form>

  
  <div class="text-center mt-4">
    <form action="inicioCliente.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
  </div>
 </div>
</main>



<jsp:include page="footer.jsp" />

</body>
</html>