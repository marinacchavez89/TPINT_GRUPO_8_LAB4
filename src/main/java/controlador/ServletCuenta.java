package controlador;

import java.io.IOException;
import java.util.List;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		cuenta.setEstado(true);

		try {
			cuenta.setFechaCreación(Date.valueOf(request.getParameter("fechaCreacion")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		TipoCuenta tipo = new TipoCuenta();
		tipo.setIdTipoCuenta(parseInt(request.getParameter("tipoCuenta")));
		cuenta.setTipoCuenta(tipo);

		boolean resultado = false;

		switch (accion) {
			case "Agregar":
				cuentaNegocio.agregarCuenta(cuenta);
				resultado = cuentaNegocio.agregarCuenta(cuenta);
				break;
			case "Modificar":
				resultado = cuentaNegocio.modificarCuenta(cuenta);
				break;
			case "Eliminar":
				resultado = cuentaNegocio.eliminarCuenta(cuenta.getNroCuenta());
				break;
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


