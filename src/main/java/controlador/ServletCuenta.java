package controlador;

import java.io.IOException;
import java.util.List;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cuenta;
import entidades.TipoCuenta;
import entidades.Movimiento;
import entidades.TipoMovimiento;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocio.TipoCuentaNegocio;
import negocioImpl.TipoCuentaNegocioImpl;

/**
 * Servlet implementation class ServletCuenta
 */


@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	private TipoCuentaNegocio tipoCuentaNegocio = new TipoCuentaNegocioImpl();
	private MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    //public ServletCuenta() {
        //super();
        // TODO Auto-generated constructor stub
    //}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
	    
	    request.getRequestDispatcher("administracionCuentas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		HttpSession session = request.getSession();
		if (accion == null) {
			response.sendRedirect("ServletCuenta");
			return;
		}
		
		 //  Evitamos todo el procesamiento para cambiar estado
	    if ("CambiarEstado".equals(accion)) {
	        int nroCuenta = parseInt(request.getParameter("nroCuenta"));
	        boolean exito = cuentaNegocio.cambiarEstadoCuenta(nroCuenta);

	        if (exito) {
	            session.setAttribute("confirmacionMensaje", "Estado de cuenta actualizado correctamente.");
	            session.setAttribute("confirmacionTipo", "success");
	        } else {
	            session.setAttribute("confirmacionMensaje", "Error al actualizar el estado de la cuenta.");
	            session.setAttribute("confirmacionTipo", "error");
	        }

	        response.sendRedirect("ServletCuenta");
	        return; 
	    }

		Cuenta cuenta = new Cuenta();
		
		if (!"Agregar".equals(accion)) {
	        cuenta.setNroCuenta(parseInt(request.getParameter("numeroCuenta")));
	    }

		cuenta.setIdCliente(parseInt(request.getParameter("idCliente")));		
		cuenta.setCBU(request.getParameter("cbu"));
		cuenta.setSaldo(Float.parseFloat(request.getParameter("saldo")));
		//cuenta.setEstado(true);
		//Eliminamos el seteo del estado, solo será true cuando se agregue una cuenta...
		
		try {
			cuenta.setFechaCreación(Date.valueOf(request.getParameter("fechaCreacion")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		TipoCuenta tipo = new TipoCuenta();
		tipo.setIdTipoCuenta(parseInt(request.getParameter("tipoCuenta")));
		cuenta.setTipoCuenta(tipo);

		boolean resultado = false;
		String mensaje = "";

		switch (accion) {
			case "Agregar":
				cuenta.setEstado(true); // Solo al agregar.
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
				       
				        Movimiento movimiento = new Movimiento();
				        movimiento.setFecha(new java.util.Date());
				        movimiento.setDetalle("Alta de cuenta -  " + 
				            (tipoCuenta != null ? tipoCuenta.getDescripcion() : "desconocida"));
				        movimiento.setImporte(cuentaInsertada.getSaldo());
				        movimiento.setCuenta(cuentaInsertada);
				        
				        TipoMovimiento tipoMovimiento = new TipoMovimiento();
				        tipoMovimiento.setIdTipoMovimiento(1);         
				        movimiento.setTipoMovimiento(tipoMovimiento);
				        
				        MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
				        boolean exitoMovimiento = movimientoNegocio.agregarMovimiento(movimiento);
				        
				        if(!exitoMovimiento) {
				        	System.out.println("Movimiento no agregado");
				        }
				   }
				break;
			case "Modificar":
				resultado = cuentaNegocio.modificarCuenta(cuenta);
				if (!resultado) {
			        mensaje = "Error al modificar la cuenta: el CBU ingresado ya existe.";
			    } else {
			        mensaje = "Cuenta modificada correctamente.";
			    }
				break;
			case "Eliminar":
				resultado = cuentaNegocio.eliminarCuenta(cuenta.getNroCuenta());
				mensaje = resultado ? "Cuenta eliminada correctamente." : "Error al eliminar la cuenta.";
				break;
		}
		
		if (resultado) {
            session.setAttribute("confirmacionMensaje", mensaje);
            session.setAttribute("confirmacionTipo", "success");
        } else {
            session.setAttribute("confirmacionMensaje", mensaje);
            session.setAttribute("confirmacionTipo", "error");
        }

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