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
import negocio.CuentaNegocio;
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
				
		List<Cuenta> listaCuentas = cuentaNegocio.listarCuentas();
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
				mensaje = resultado ? "Cuenta agregada correctamente." : "Error al agregar la cuenta.";
				break;
			case "Modificar":
				resultado = cuentaNegocio.modificarCuenta(cuenta);
				mensaje = resultado ? "Cuenta modificada correctamente." : "Error al modificar la cuenta.";
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