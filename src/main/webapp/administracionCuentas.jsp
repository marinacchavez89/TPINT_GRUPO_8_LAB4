<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="validarSesion.jsp" %>

<%@ page import="java.util.*, entidades.Cuenta, entidades.TipoCuenta" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administración Cuentas</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/estilos.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.3.2/css/dataTables.dataTables.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
  <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/2.3.2/js/dataTables.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <script type="text/javascript">
    $(document).ready(function () {
      $('#tablaCuentas').DataTable();
    });
  </script>
</head>

<body>
<jsp:include page="navbarAdmin.jsp"/>
<jsp:include page="bienvenidaUsuario.jsp" />

<form action="ServletCuenta" method="post">
  <div class="tabla-contenedor">
    <table id="tablaCuentas" class="tabla">
      <thead>
        <tr>
          <th></th>
          <th>Nro Cuenta</th>
          <th>Cliente</th>
          <th>Fecha Creación</th>
          <th>Tipo de Cuenta</th>
          <th>CBU</th>
          <th>Saldo</th>
        </tr>
      </thead>
      <tbody>
        <%
          List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
          if (cuentas != null) {
            for (Cuenta c : cuentas) {
        %>
        <tr>
          <td>
            <input type="radio" name="cuentaSeleccionada" onclick="seleccionarCuenta(this)"
              data-nro="<%= c.getNroCuenta() %>"
              data-idCliente="<%= c.getIdCliente() %>"
              data-fecha="<%= c.getFechaCreación() %>"
              data-tipo="<%= c.getIdTipoCuenta().getIdTipoCuenta() %>"
              data-cbu="<%= c.getCBU() %>"
              data-saldo="<%= c.getSaldo() %>">
          </td>
          <td><%= c.getNroCuenta() %></td>
          <td><%= c.getIdCliente() %></td>
          <td><%= c.getFechaCreación() %></td>
          <td><%= c.getIdTipoCuenta().getDescripcion() %></td>
          <td><%= c.getCBU() %></td>
          <td><%= c.getSaldo() %></td>
        </tr>
        <% }
          }
        %>
      </tbody>
    </table>
  </div>

  <div class="contenedor-botones">
    <button type="button" onClick="mostrarFormularioAgregarCuenta()" class="btn btn-success btn-sm">Agregar</button>
    <a href="inicioAdmin.jsp" class="btn btn-volver btn-sm">Volver</a>
  </div>

  <div class="tabla-contenedor mt-3" id="formModificarCuenta" style="display: none;">
    <h4 class="mb-3">Modificar o Eliminar cuenta</h4>
    <div class="row">
      <div class="col-md-6">
        <div class="mb-3">
          <input type="number" name="numeroCuenta" class="form-control" placeholder="Nro Cuenta" readonly>
        </div>
        <div class="mb-3">
          <input type="number" name="idCliente" class="form-control" placeholder="ID Cliente" required>
        </div>
        <div class="mb-3">
          <input type="date" name="fechaCreacion" class="form-control" placeholder="Fecha Creación" required>
        </div>
      </div>

      <div class="col-md-6">
        <div class="mb-3">
          <select name="tipoCuenta" class="form-control" required>
            <option value="">Seleccione un tipo</option>
            <%
              List<TipoCuenta> tiposCuenta = (List<TipoCuenta>) request.getAttribute("tiposCuenta");
              if (tiposCuenta != null) {
                for (TipoCuenta tipo : tiposCuenta) {
            %>
            <option value="<%= tipo.getIdTipoCuenta() %>"><%= tipo.getDescripcion() %></option>
            <%
                }
              }
            %>
          </select>
        </div>
        <div class="mb-3">
          <input type="text" name="cbu" class="form-control" placeholder="CBU" required>
        </div>
        <div class="mb-3">
          <input type="number" name="saldo" class="form-control" placeholder="Saldo" readonly>
        </div>
      </div>
    </div>

    <div class="contenedor-botones">
      <button type="submit" name="accion" value="Modificar" class="btn btn-primary btn-sm me-2">Modificar</button>
      <button type="submit" name="accion" value="Eliminar" class="btn btn-danger btn-sm">Eliminar</button>
    </div>
  </div>
</form>

<form action="ServletCuenta" method="post" id="formAgregarCuenta" style="display: none;">
  <div class="tabla-contenedor mt-3">
    <h4 class="mb-3">Agregar Cuenta</h4>
    <div class="row">
      <div class="col-md-6">
        <div class="mb-3">
 		 <input type="number" name="proximoNroCuenta" class="form-control" id="proximoNro" 
        	 value="<%= request.getAttribute("proximoNroCuenta") %>" readonly>
		</div>
        <div class="mb-3">
          <input type="number" min="0" name="idCliente" class="form-control" placeholder="ID Cliente" required>
        </div>
        <div class="mb-3">
          <input type="date" name="fechaCreacion" class="form-control" placeholder="Fecha Creación" required>
        </div>
      </div>
      <div class="col-md-6">
        <div class="mb-3">
          <select name="tipoCuenta" class="form-select" required>
            <option value="">Seleccione tipo de cuenta</option>
            <%
              if (tiposCuenta != null) {
                for (TipoCuenta tipo : tiposCuenta) {
            %>
              <option value="<%= tipo.getIdTipoCuenta() %>"><%= tipo.getDescripcion() %></option>
            <%
                }
              }
            %>
          </select>
        </div>
        <div class="mb-3">
          <input type="text" name="cbu" class="form-control" placeholder="CBU" required>
        </div>
        <div class="mb-3">
          <input type="number" name="saldo" class="form-control" value="10000.0" readonly>
        </div>
      </div>
    </div>

    <div class="contenedor-botones">
      <button type="submit" name="accion" value="Agregar" class="btn btn-success btn-sm">Guardar</button>
    </div>
  </div>
</form>

<jsp:include page="footer.jsp" />

<script>
  function seleccionarCuenta(radio) {
    const form = document.getElementById("formModificarCuenta");
    const datos = radio.dataset;

    form.style.display = "block";

    document.querySelector('input[name="numeroCuenta"]').value = datos.nro;
    document.querySelector('input[name="idCliente"]').value = datos.idcliente;
    document.querySelector('input[name="fechaCreacion"]').value = datos.fecha;
    document.querySelector('select[name="tipoCuenta"]').value = datos.tipo;
    document.querySelector('input[name="cbu"]').value = datos.cbu;
    document.querySelector('input[name="saldo"]').value = datos.saldo;
  }
  
  function mostrarFormularioAgregarCuenta() {
	   document.getElementById("formModificarCuenta").style.display = "none";
	   const formAgregarCuenta = document.getElementById("formAgregarCuenta");
	   formAgregarCuenta.style.display = "block";
	
	   // Limpieza de campos menos el numero de cuenta y saldo
	  const inputs = formAgregarCuenta.querySelectorAll('input');
     inputs.forEach(input => {
   	  if (input.name === "saldo") {
   	      input.value = "10000.0"; // monto fijo
   	  } else if (input.name !== "proximoNroCuenta") {
   	      input.value = '';
   	  }
     });
 }
  
  ///// VALIDACIONES EN LOS FORM CON SWEET ALERT //////////
   document.addEventListener("DOMContentLoaded", function () {
    // para MODIFICAR cuenta
    const formModificar = document.querySelector('form[action="ServletCuenta"]:not(#formAgregarCuenta)');
    const btnModificar = formModificar.querySelector('button[name="accion"][value="Modificar"]');

    if (btnModificar) {
      btnModificar.addEventListener("click", function (e) {
        const cbu = formModificar.querySelector('input[name="cbu"]').value.trim();
        const fechaStr = formModificar.querySelector('input[name="fechaCreacion"]').value;

        if (!/^\d+$/.test(cbu) || parseInt(cbu) <= 0) {
          e.preventDefault();
          Swal.fire({ icon: 'error', title: 'CBU inválido',
            text: 'El CBU debe ser un número mayor a 0.'
          });
          return;
        }

        const fecha = new Date(fechaStr);
        const hoy = new Date();
        hoy.setHours(0, 0, 0, 0);

        if (fecha > hoy) {
          e.preventDefault();
          Swal.fire({ icon: 'error', title: 'Fecha no válida',
            text: 'La fecha de creación no puede ser futura.'
          });
          return;
        }
      });
    }

    // para AGREGAR cuenta
    const formAgregar = document.getElementById("formAgregarCuenta");
    const btnAgregar = formAgregar.querySelector('button[name="accion"][value="Agregar"]');

    if (btnAgregar) {
      btnAgregar.addEventListener("click", function (e) {
        const cbu = formAgregar.querySelector('input[name="cbu"]').value.trim();
        const fechaStr = formAgregar.querySelector('input[name="fechaCreacion"]').value;

        if (!/^\d+$/.test(cbu) || parseInt(cbu) <= 0) {
          e.preventDefault();
          Swal.fire({ icon: 'error', title: 'CBU inválido',
            text: 'El CBU debe ser un número mayor a 0.'
          });
          return;
        }

        const fecha = new Date(fechaStr);
        const hoy = new Date();
        hoy.setHours(0, 0, 0, 0);

        if (fecha > hoy) {
          e.preventDefault();
          Swal.fire({ icon: 'error',title: 'Fecha no válida',
        	  text: 'La fecha de creación no puede ser futura.'
          });
          return;
        }
      });
    }
  });
  
</script>

</body>
</html>