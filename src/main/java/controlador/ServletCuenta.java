package controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cuenta;
import entidades.TipoCuenta;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocio.TipoCuentaNegocio;
import negocioImpl.TipoCuentaNegocioImpl;

import entidades.Cliente;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;



@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	private TipoCuentaNegocio tipoCuentaNegocio = new TipoCuentaNegocioImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtroEstado = request.getParameter("filtroEstado");
	    String idClienteStr = request.getParameter("idCliente");
	    
	    List<Cuenta> listaCuentas;

	    if ((filtroEstado != null && !filtroEstado.isEmpty()) || (idClienteStr != null && !idClienteStr.isEmpty())) {
	        Boolean estado = (filtroEstado != null && !filtroEstado.isEmpty()) ? Boolean.parseBoolean(filtroEstado) : null;
	        int idCliente = (idClienteStr != null && !idClienteStr.isEmpty()) ? Integer.parseInt(idClienteStr) : 0;

	        listaCuentas = cuentaNegocio.listarCuentasFiltradas(estado, idCliente);
	    } else {
	        listaCuentas = cuentaNegocio.listarCuentas();
	    }

	    request.setAttribute("cuentas", listaCuentas);
	    
	    List<TipoCuenta> tiposCuenta = tipoCuentaNegocio.listar();
	    request.setAttribute("tiposCuenta", tiposCuenta);
	    
	    int proximoNroCuenta = cuentaNegocio.obtenerProximoNumeroCuenta();
		request.setAttribute("proximoNroCuenta", proximoNroCuenta);
	    
		ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
		List<Cliente> listaClientes = clienteNegocio.listarClientes(); 
		
		// Mapeo de ID y DNI
		Map<Integer, String> dniPorIdCliente = new HashMap<>();
		for (Cliente cli : listaClientes) {
		    dniPorIdCliente.put(cli.getIdCliente(), cli.getDni());
		}

		request.setAttribute("dniPorIdCliente", dniPorIdCliente);

	    request.getRequestDispatcher("administracionCuentas.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		HttpSession session = request.getSession();

		if (accion == null) {
			response.sendRedirect("ServletCuenta");
			return;
		}

		if ("CambiarEstado".equals(accion)) {
			int nroCuenta = parseInt(request.getParameter("nroCuenta"));
			boolean exito = cuentaNegocio.cambiarEstadoCuenta(nroCuenta);

			if (exito) {
				session.setAttribute("confirmacionMensaje", "Estado de cuenta actualizado correctamente.");
				session.setAttribute("confirmacionTipo", "success");
			} else {
				session.setAttribute("confirmacionMensaje", "Error al actualizar el estado de la cuenta. Verifique si ya tiene 3 cuentas activas.");
				session.setAttribute("confirmacionTipo", "error");
			}
			response.sendRedirect("ServletCuenta");
			return;
		}

		Cuenta cuenta = new Cuenta();

		if (!"Agregar".equals(accion)) {
			cuenta.setNroCuenta(parseInt(request.getParameter("numeroCuenta")));
		}

		ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
		String dni = request.getParameter("dni");

		int idCliente = clienteNegocio.obtenerIdXDni(dni);
		if (idCliente == 0) {
		    session.setAttribute("confirmacionMensaje", "DNI no encontrado en el sistema.");
		    session.setAttribute("confirmacionTipo", "error");
		    response.sendRedirect("ServletCuenta");
		    return;
		}
		cuenta.setIdCliente(idCliente);

		String cbu = request.getParameter("cbu");

		//verificacion cbu
		if (cbu == null || !cbu.matches("^\\d+$")) {
			session.setAttribute("confirmacionMensaje", "CBU inválido. Debe ser un número positivo.");
			session.setAttribute("confirmacionTipo", "error");
			response.sendRedirect("ServletCuenta");
			return;
		}
		cuenta.setCBU(cbu);

		//verificacion saldo
		float saldo = 0;
		try {
			saldo = Float.parseFloat(request.getParameter("saldo"));
			if (saldo < 0) throw new Exception("Saldo negativo");
			cuenta.setSaldo(saldo);
		} catch (Exception e) {
			session.setAttribute("confirmacionMensaje", "Saldo inválido. Debe ser un número positivo.");
			session.setAttribute("confirmacionTipo", "error");
			response.sendRedirect("ServletCuenta");
			return;
		}

		//verificacion fecha
		try {
			cuenta.setFechaCreación(Date.valueOf(request.getParameter("fechaCreacion")));
		} catch (Exception e) {
			session.setAttribute("confirmacionMensaje", "Fecha inválida.");
			session.setAttribute("confirmacionTipo", "error");
			response.sendRedirect("ServletCuenta");
			return;
		}

		TipoCuenta tipo = new TipoCuenta();
		tipo.setIdTipoCuenta(parseInt(request.getParameter("tipoCuenta")));
		cuenta.setTipoCuenta(tipo);

		boolean resultado = false;
		String mensaje = "";

		switch (accion) {
			case "Agregar":
				cuenta.setEstado(true); // Solo al agregar
				resultado = cuentaNegocio.agregarCuenta(cuenta);
				if (!resultado) {
					Cuenta existente = cuentaNegocio.obtenerPorCBU(cuenta.getCBU());
					if (existente != null) {
						mensaje = "Error: El CBU ingresado ya existe.";
					} else {
						mensaje = "Error: El cliente ya tiene 3 cuentas activas.";
					}
				} else {
					mensaje = "Cuenta agregada correctamente.";
					Cuenta cuentaInsertada = cuentaNegocio.obtenerPorCBU(cuenta.getCBU());

					TipoCuentaNegocio tipoCuentaNegocio = new TipoCuentaNegocioImpl();
					TipoCuenta tipoCuenta = tipoCuentaNegocio.obtenerTipoCuentaXId(cuenta.getIdTipoCuenta().getIdTipoCuenta());

				}
				break;

			case "Modificar":
				Cuenta cuentaExistente = cuentaNegocio.obtenerPorCBU(cuenta.getCBU());
				if (cuentaExistente != null && cuentaExistente.getNroCuenta() != cuenta.getNroCuenta()) {
					resultado = false;
					mensaje = "Error al modificar: el CBU ya está en uso por otra cuenta.";
				} else {
					resultado = cuentaNegocio.modificarCuenta(cuenta);
					mensaje = resultado ? "Cuenta modificada correctamente." : "Error al modificar la cuenta.";
				}
				break;

			case "Eliminar":
				resultado = cuentaNegocio.eliminarCuenta(cuenta.getNroCuenta());
				mensaje = resultado ? "Cuenta eliminada correctamente." : "Error al eliminar la cuenta.";
				break;
		}

		session.setAttribute("confirmacionMensaje", mensaje);
		session.setAttribute("confirmacionTipo", resultado ? "success" : "error");
		response.sendRedirect("ServletCuenta");
	}

	private int parseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
	}
}