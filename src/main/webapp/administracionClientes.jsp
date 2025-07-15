<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="validarSesion.jsp" %>

<%@ page import="java.util.*, entidades.Cliente" %>
<%@ page import="entidades.PaisResidencia" %>
<%@ page import="entidades.Nacionalidad, entidades.Direccion, entidades.Localidad" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administracion Clientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https:cdn.datatables.net/2.3.2/css/dataTables.dataTables.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src ="https://cdn.datatables.net/2.3.2/js/dataTables.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> 
<script type="text/javascript">
    $(document).ready(function() {
        $('#tablaClientes').DataTable();
        
     	// --- CÓDIGO PARA MOSTRAR MENSAJES DE CONFIRMACIÓN ---
        <%
            String mensaje = (String) session.getAttribute("confirmacionMensaje");
            String tipo = (String) session.getAttribute("confirmacionTipo");
            if (mensaje != null && !mensaje.isEmpty()) {
        %>
            Swal.fire({
                icon: '<%= tipo %>', // success, error, warning, info, question
                title:'<%= tipo.equals("success") ? "Operación Exitosa" : tipo.equals("error") ? "Error" : tipo.equals("warning") ? "Advertencia" : "Información" %>',
                text: '<%= mensaje %>',
                showConfirmButton: false,
                timer: 2000 // El cartel se cierra automáticamente después de 2 segundos
            });
        <%
                // Limpiar los atributos de la sesión para que no se muestren de nuevo
                session.removeAttribute("confirmacionMensaje");
                session.removeAttribute("confirmacionTipo");
            }
        %>
           
    });
</script>
</head>
<body>

<jsp:include page="navbarAdmin.jsp"/>
<jsp:include page="bienvenidaUsuario.jsp" />

<form action="ServletCliente" method="post">
<div class="tabla-contenedor">
 <table id="tablaClientes" class="tabla">
 
<thead>
    <tr>
      <th></th> <!-- columna vacía para el radio -->
      <th class="d-none">ID</th>
      <th>DNI</th>
      <th>CUIL</th>
      <th>Nombre</th>
      <th>Apellido</th>
      <th>Sexo</th>
      <th>Nacionalidad</th>
      <th>Fecha Nacimiento</th>
      <th>Direccion</th>
      <th>Correo Electronico</th>
      <th>Telefono</th>
    </tr>
  </thead>
  <tbody>
  <% 
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
    if (clientes != null) {
        for (Cliente c : clientes) {
%>
   <tr>
  <td>
    <input type="radio" name="clienteSeleccionado" onclick="seleccionarFila(this)"
      data-id="<%= c.getIdCliente() %>"
      data-dni="<%= c.getDni() %>"
      data-cuil="<%= c.getCuil() %>"
      data-nombre="<%= c.getNombre() %>"
      data-apellido="<%= c.getApellido() %>"
      data-sexo="<%= c.getSexo() %>"
      data-nacionalidad="<%= c.getNacionalidad().getIdNacionalidad() %>"
      data-fecha="<%= c.getFechaNacimiento() %>"
      data-pais="<%= c.getDireccion().getLocalidad().getProvincia().getPaisResidencia().getIdPaisResidencia() %>"
	  data-provincia="<%= c.getDireccion().getLocalidad().getProvincia().getIdProvincia() %>"
	  data-localidad="<%= c.getDireccion().getLocalidad().getIdLocalidad() %>"
      data-direccion="<%= c.getDireccion().getIdDireccion() %>"
      data-direccionid="<%= c.getDireccion().getIdDireccion() %>"
      data-calle="<%= c.getDireccion().getCalle() %>"
      data-numero="<%= c.getDireccion().getNumero() %>"
      data-codigopostal="<%= c.getDireccion().getCodigoPostal() %>"
      data-email="<%= c.getCorreoElectronico() %>"
      data-telefono="<%= c.getTelefono() %>">
  </td>
  <td class="d-none"><%= c.getIdCliente() %></td>
  <td><%= c.getDni() %></td>
  <td><%= c.getCuil() %></td>
  <td><%= c.getNombre() %></td>
  <td><%= c.getApellido() %></td>
  <td><%= c.getSexo() %></td>
  <td><%= c.getNacionalidad().getDescripcion() %></td>
  <td><%= c.getFechaNacimiento() %></td>
  <td><%= c.getDireccion().getCalle() + " " + c.getDireccion().getNumero() %></td>
  <td><%= c.getCorreoElectronico() %></td>
  <td><%= c.getTelefono() %></td>
</tr>
<%
        }
    }
%>
  </tbody>
  </table>
</div>

<div class="contenedor-botones">
  <!--<button type="submit" name="accion" value="Agregar"  class="btn btn-success btn-sm">Agregar</button>-->
  <button type="button" onclick="mostrarFormularioAgregar()" class="btn btn-success btn-sm">Agregar</button>  
  <a href="inicioAdmin.jsp" class="btn btn-volver btn-sm">Volver</a>
</div>
</form>

<form action="ServletCliente" method="post" id="formModificar" style="display: none;">

<div class="tabla-contenedor mt-3">

 <!-- Título -->
 <h4 class="mb-3">Modificar o Eliminar cliente</h4>

  <div class="row">

    <!-- Columna izquierda -->
    <div class="col-md-6">
      <div class="mb-3">
        <input type="text" name="id" class="form-control" placeholder="ID" readonly>
      </div>
      <div class="mb-3">
        <input type="text" name="dni" class="form-control" placeholder="DNI" required>
      </div>
      <div class="mb-3">
        <input type="text" name="cuil" class="form-control" placeholder="CUIL" required>
      </div>
      <div class="mb-3">
        <input type="text" name="nombre" class="form-control" placeholder="Nombre" required>
      </div>
      <div class="mb-3">
        <input type="text" name="apellido" class="form-control" placeholder="Apellido" required>
      </div>
      <div class="mb-3">
	  <select name="paisResidencia" class="form-select")">
	    <option value="">Seleccione país de residencia</option>
	    <%
	        List<PaisResidencia> paisesResidencia = (List<PaisResidencia>) request.getAttribute("listaPaises");
	        if (paisesResidencia != null) {
	            for (PaisResidencia p : paisesResidencia) {
	    %>
	                <option value="<%= p.getIdPaisResidencia() %>"><%= p.getDescripcion() %></option>
	    <%
	            }
	        }
	    %>
	</select>
	</div>
	
	<div class="mb-3">
	  <select name="provincia" class="form-select">
	    <option value="">Seleccione provincia</option>
	  </select>
	</div>
	
	<div class="mb-3">
	  <select name="localidad" class="form-select">
	    <option value="">Seleccione localidad</option>
	  </select>
	</div>
	  
    </div>

    <!-- Columna derecha -->
    <div class="col-md-6">
      <div class="mb-3">
        <input type="text" name="sexo" class="form-control" placeholder="Sexo">
      </div>
      <div class="mb-3">
	  <select name="nacionalidad" class="form-select" required>
	    <option value="">Seleccione nacionalidad</option>
	    <%
	      List<Nacionalidad> listaNacionalidadesModif = (List<Nacionalidad>) request.getAttribute("listaNacionalidades");
	      if (listaNacionalidadesModif != null) {
	        for (Nacionalidad n : listaNacionalidadesModif) {
	    %>
	        <option value="<%= n.getIdNacionalidad() %>"><%= n.getDescripcion() %></option>
	    <%
	        }
	      }
	    %>
	  </select>
	  </div>
      <div class="mb-3">
        <input type="date" name="fechaNacimiento" class="form-control" placeholder="Fecha Nacimiento">
      </div>
      <!--<div class="mb-3">
        <input type="text" name="direccion" class="form-control" placeholder="Dirección">
      </div>-->
      <input type="hidden" name="direccionId" class="form-control">
		<div class="mb-3">
		  <input type="text" name="calle" class="form-control" placeholder="Calle">
		</div>
		<div class="mb-3">
		  <input type="text" name="numero" class="form-control" placeholder="Número">
		</div>
		<div class="mb-3">
		  <input type="text" name="codigoPostal" class="form-control" placeholder="Código Postal">
		</div>
      <div class="mb-3">
        <input type="email" name="email" class="form-control" placeholder="Correo Electrónico">
      </div>
      <div class="mb-3">
        <input type="tel" name="telefono" class="form-control" placeholder="Teléfono">
      </div>
    </div>
  </div>
  
<!-- Botones centrados -->
 <div class="contenedor-botones">
	 <button type="submit" name="accion" value="Modificar" class="btn btn-primary btn-sm me-2">Modificar</button>
	 <button type="button" onclick="confirmarEliminar()" class="btn btn-danger btn-sm">Eliminar</button>
 </div> 
</div>
</form>

<form action="ServletCliente" method="post" id="formAgregar" style="display: none;">

<div class="tabla-contenedor mt-3">
  <h4 class="mb-3">Agregar nuevo cliente</h4>

  <div class="row">
    <!-- Columna izquierda -->
    <div class="col-md-6">
      <div class="mb-3">
        <input type="text" name="dni" class="form-control" placeholder="DNI" required>
      </div>
      <div class="mb-3">
        <input type="text" name="cuil" class="form-control" placeholder="CUIL" required>
      </div>
      <div class="mb-3">
        <input type="text" name="nombre" class="form-control" placeholder="Nombre" required>
      </div>
      <div class="mb-3">
        <input type="text" name="apellido" class="form-control" placeholder="Apellido" required>
      </div>
      <div class="mb-3">
	  <select name="paisResidencia" class="form-select">
	    <option value="">Seleccione país de residencia</option>
	    <%
	      List<PaisResidencia> paisesResidenciaAgregar = (List<PaisResidencia>) request.getAttribute("listaPaises");
	      for (PaisResidencia p : paisesResidenciaAgregar) {
	    %>
	      <option value="<%= p.getIdPaisResidencia() %>"><%= p.getDescripcion() %></option>
	    <%
	      }
	    %>
	  </select>
	</div>
	
	<div class="mb-3">
	  <select name="provincia" class="form-select">
	    <option value="">Seleccione provincia</option>
	  </select>
	</div>
	
	<div class="mb-3">
	  <select name="localidad" class="form-select">
	    <option value="">Seleccione localidad</option>
	  </select>
	</div>
</div>

    <!-- Columna derecha -->
    <div class="col-md-6">
      <div class="mb-3">
        <input type="text" name="sexo" class="form-control" placeholder="Sexo">
      </div>
     <div class="mb-3">
	  <select name="nacionalidad" class="form-select" required>
	    <option value="">Seleccione nacionalidad</option>
	    <%
	      List<Nacionalidad> nacionalidades = (List<Nacionalidad>) request.getAttribute("listaNacionalidades");
	      if (nacionalidades != null) {
	        for (Nacionalidad n : nacionalidades) {
	    %>
	        <option value="<%= n.getIdNacionalidad() %>"><%= n.getDescripcion() %></option>
	    <%
	        }
	      }
	    %>
	  </select>
	</div>
      <div class="mb-3">
        <input type="date" name="fechaNacimiento" class="form-control" placeholder="Fecha Nacimiento">
      </div>
      <!--<div class="mb-3">
        <input type="text" name="direccion" class="form-control" placeholder="Dirección">
      </div>-->
      <div class="mb-3">
	  	<input type="text" name="calle" class="form-control" placeholder="Calle" required>
	  </div>
	  <div class="mb-3">
	  	<input type="text" name="numero" class="form-control" placeholder="Número" required>
	  </div>
	  <div class="mb-3">
	  	<input type="text" name="codigoPostal" class="form-control" placeholder="Código Postal" required>
	  </div>
      <div class="mb-3">
        <input type="email" name="email" class="form-control" placeholder="Correo Electrónico">
      </div>
      <div class="mb-3">
        <input type="tel" name="telefono" class="form-control" placeholder="Teléfono">
      </div>
    </div>
  </div>

  <!-- Botón Agregar -->
  <div class="contenedor-botones">
    <button type="submit" name="accion" value="Agregar" class="btn btn-success btn-sm">Guardar</button>
  </div>
</div>

</form>

  


  <jsp:include page="footer.jsp" />
  
<!-- Hacer que al seleccionar una fila, los datos se copien a los inputs: -->
<script>

	function seleccionarFila(radio) {
		document.getElementById("formAgregar").style.display = "none";
	    const form = document.getElementById("formModificar");
	    const datos = radio.dataset;
		
	 	// Para depuración, SIEMPRE es útil ver los datos que se leen:
        console.log("Datos del radio seleccionado:", datos);
	    
	    form.style.display = "block";
	
	    // Asignación de campos directos (usan form.querySelector para asegurar el formulario correcto)
	    form.querySelector('input[name="id"]').value = datos.id;
	    form.querySelector('input[name="dni"]').value = datos.dni;
	    form.querySelector('input[name="cuil"]').value = datos.cuil;
	    form.querySelector('input[name="nombre"]').value = datos.nombre;
	    form.querySelector('input[name="apellido"]').value = datos.apellido;
	    form.querySelector('input[name="sexo"]').value = datos.sexo;
	    form.querySelector('select[name="nacionalidad"]').value = datos.nacionalidad; // Es un select, pero se setea directo si la opción ya existe
	    form.querySelector('input[name="fechaNacimiento"]').value = datos.fecha;

        // Campos de Dirección y Contacto (ahora usan form.querySelector)
	    form.querySelector('input[name="direccionId"]').value = datos.direccionid;
		form.querySelector('input[name="calle"]').value = datos.calle;
		form.querySelector('input[name="numero"]').value = datos.numero;
		form.querySelector('input[name="codigoPostal"]').value = datos.codigopostal;
	    form.querySelector('input[name="email"]').value = datos.email;
	    form.querySelector('input[name="telefono"]').value = datos.telefono;

	 	// --- Lógica para cargar y seleccionar País, Provincia y Localidad (esta es la lógica ÚNICA y correcta) ---
	    const paisSelect = form.querySelector('select[name="paisResidencia"]');
	    const provinciaSelect = form.querySelector('select[name="provincia"]');
	    const localidadSelect = form.querySelector('select[name="localidad"]');

	    // 1. Setear el país de residencia.
        // Esto solo lo setea, no dispara el evento 'change'. Por eso necesitamos cargar manualmente.
	    paisSelect.value = datos.pais;

	    // 2. Limpiar opciones anteriores en provincias y localidades
	    provinciaSelect.innerHTML = '<option value="">-- Seleccione provincia --</option>';
	    localidadSelect.innerHTML = '<option value="">-- Seleccione localidad --</option>';

	    // Si hay un país seleccionado, cargar las provincias para ese país
	    if (datos.pais) {
	        $.get('<%= request.getContextPath() %>/ServletCliente', { idPais: datos.pais })
	            .done(function (htmlProvincias) {
                    // Añadir las provincias al select
	                provinciaSelect.innerHTML += htmlProvincias;
	                // 3. Setear la provincia seleccionada (una vez que las opciones estén cargadas)
	                provinciaSelect.value = datos.provincia;

	                // 4. Si hay una provincia seleccionada, cargar las localidades para esa provincia
	                if (datos.provincia) {
	                    $.get('<%= request.getContextPath() %>/ServletCliente', { idProvincia: datos.provincia })
	                        .done(function (htmlLocalidades) {
                                // Añadir las localidades al select
	                            localidadSelect.innerHTML += htmlLocalidades;
	                            // 5. Setear la localidad seleccionada (una vez que las opciones estén cargadas)
	                            localidadSelect.value = datos.localidad;
	                        })
	                        .fail(function () {
	                            console.error('Error cargando localidades');
	                        });
	                }
	            })
	            .fail(function () {
	                console.error('Error cargando provincias');
	            });
	    }
	}
	
	function mostrarFormularioAgregar() {
	   // Oculta el de modificar si estaba abierto
	   document.getElementById("formModificar").style.display = "none";
	
	   // Limpia todos los campos del formulario de agregar
	   const formAgregar = document.getElementById("formAgregar");
	   formAgregar.style.display = "block";
	
	   // Limpieza de campos (puede mejorarse si ponés un `id` al form o inputs)
	   const inputs = formAgregar.querySelectorAll('input');
	   inputs.forEach(input => input.value = '');
	   // También limpia los selects
	   formAgregar.querySelectorAll('select').forEach(select => select.value = '');
	 }


</script>

<script>
  const servletBase = '<%= request.getContextPath() %>/ServletCliente';

  // Cuando cambie el país, recargo el listado de provincias y limpio las localidades
  $('select[name="paisResidencia"]').on('change', function() {
    const idPais = $(this).val();
    const $provSelect = $(this).closest('form').find('select[name="provincia"]');
    const $locSelect  = $(this).closest('form').find('select[name="localidad"]');

    // Reset
    $provSelect.html('<option value="">-- Seleccione provincia --</option>');
    $locSelect.html('<option value="">-- Seleccione localidad --</option>');

    if (!idPais) return;

    $.get(servletBase, { idPais: idPais })
      .done(html => $provSelect.append(html))
      .fail(err => console.error('Error cargando provincias:', err));
  });
</script>
<script>
  const servletBases = '<%= request.getContextPath() %>/ServletCliente';

  // Cuando cambie la provincia, recargo el listado de localidades
  $('select[name="provincia"]').on('change', function() {
    const idProv = $(this).val();
    const $locSelect = $(this).closest('form').find('select[name="localidad"]');

    // Reset
    $locSelect.html('<option value="">-- Seleccione localidad --</option>');

    if (!idProv) return;

    $.get(servletBases, { idProvincia: idProv })
      .done(html => $locSelect.append(html))
      .fail(err => console.error('Error cargando localidades:', err));
  });
  
  function confirmarEliminar() {
      // Obtenemos el formulario de modificar
      const formModificar = document.getElementById("formModificar");
      // Capturamos el ID del cliente que está en el campo oculto o readonly del formulario de modificar
      const idClienteAEliminar = formModificar.querySelector('input[name="id"]').value;

      if (!idClienteAEliminar) {
          Swal.fire({
              icon: 'warning',
              title: 'Seleccione un cliente',
              text: 'Por favor, seleccione un cliente de la tabla para eliminar.',
              confirmButtonText: 'Entendido'
          });
          return;
      }

      Swal.fire({
          title: '¿Estás seguro?',
          text: `Vas a eliminar el cliente seleccionado. ¡Esta acción es irreversible!`,
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#d33',
          cancelButtonColor: '#3085d6',
          confirmButtonText: 'Sí, ¡eliminar!',
          cancelButtonText: 'Cancelar'
      }).then((result) => {
          if (result.isConfirmed) {
              // Si el usuario confirma, se envía el formulario con la acción "Eliminar".
              const hiddenInput = document.createElement('input');
              hiddenInput.type = 'hidden';
              hiddenInput.name = 'accion';
              hiddenInput.value = 'Eliminar';
              formModificar.appendChild(hiddenInput); // Añade el input oculto al formulario

              formModificar.submit();
          } else {
              // Si el usuario cancela, no hacemos nada o mostramos un mensaje opcional
              Swal.fire(
                  'Cancelado',
                  'La eliminación ha sido cancelada.',
                  'info'
              );
          }
      });
  }
  
</script>




</body>
</html>