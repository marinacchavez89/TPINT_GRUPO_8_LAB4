<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<div class="perfil-container">
 <div class="perfil-card">
  <h2 class="text-center mb-4">Mi Perfil</h2>
  <form class="perfil-form">
    <div class="row">
     
      <div class="col-md-6">
        <label for="nombre">Nombre</label>
        <input type="text" id="nombre">

        <label for="dni">DNI</label>
        <input type="text" id="dni">
        
        <label for="usuario">Usuario</label>
        <input type="text" id="usuario">
        
        <label for="direccion">Dirección</label>
        <input type="text" id="direccion">
        
        <label for="nacionalidad">Nacionalidad</label>
		<select id="nacionalidad" class="form-select">
		  <option value="argentina">Argentina</option>
		  <option value="uruguay">Uruguay</option>
		  <option value="chile">Chile</option>
		</select>	    

        <label for="provincia">Provincia</label>
		<select id="provincia" class="form-select">
		  <option value="buenos_aires">Buenos Aires</option>
		  <option value="cordoba">Córdoba</option>
		  <option value="santafe">Santa Fe</option>
		</select>
		
		<label for="sexo">Género</label>
		<select id="sexo" class="form-select">
		  <option value="femenino">Femenino</option>
		  <option value="masculino">Masculino</option>
		  <option value="otro">Otro</option>
		</select>
                
      </div>


      <div class="col-md-6">
        <label for="apellido">Apellido</label>
        <input type="text" id="apellido">
        
        <label for="cuil">CUIL</label>
        <input type="text" id="cuil">
        
        <label for="email">Email</label>
        <input type="email" id="email">
        
        <label for="telefono">Teléfono</label>
        <input type="tel" id="telefono">
        
        <label for="nacionalidad">Pais de Residencia</label>
		<select id="nacionalidad" class="form-select">
		  <option value="argentina">Argentina</option>
		  <option value="uruguay">Uruguay</option>
		  <option value="chile">Chile</option>
		</select>
		
		<label for="localidad">Localidad</label>
		<select id="localidad" class="form-select">
		  <option value="la_plata">La Plata</option>
		  <option value="cordoba_capital">Córdoba Capital</option>
		  <option value="rosario">Rosario</option>
		</select> 
		
        <label for="fechaNacimiento">Fecha de Nacimiento</label>
        <input type="date" id="fechaNacimiento">
        
      </div>
    </div>
  </form>

  
  <div class="text-center mt-4">
    <form action="inicioCliente.jsp" method="get">
      <input type="submit" class="btn-volver" value="Volver">
    </form>
  </div>
 </div>
</div>



<jsp:include page="footer.jsp" />

</body>
</html>