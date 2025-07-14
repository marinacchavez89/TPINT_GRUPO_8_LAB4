package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CuotaDAO;
import daoImpl.CuotaDAOImpl;
import dao.CuotaDAO;
import entidades.Cliente;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

import entidades.Cuota;
/**
 * Servlet implementation class ServletPagarCuotas
 */
@WebServlet("/ServletPagarCuotas")
public class ServletPagarCuotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
       private CuotaDAO cuotaDao = new CuotaDAOImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPagarCuotas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
	    Cliente cliente = session!=null 
	      ? (Cliente)session.getAttribute("clienteLogueado") 
	      : null;
	    if (cliente==null) {
	      response.sendRedirect(request.getContextPath()+"/login.jsp");
	      return;
	    }

	    String error = (String) session.getAttribute("errorPago");
	    String exito = (String) session.getAttribute("exitoPago");

	    if (error != null) {
	        request.setAttribute("errorPago", error);
	        session.removeAttribute("errorPago");
	    }
	    if (exito != null) {
	        request.setAttribute("exitoPago", exito);
	        session.removeAttribute("exitoPago");
	    }
	    
	    // 1) Leer parámetros
	    String cuentaParam  = request.getParameter("cuentaSeleccionada");
	    String prestamoParam= request.getParameter("idPrestamo");
	    // 2) Obtener las cuotas pendientes
	    List<Cuota> pendientes = new ArrayList<>();
	    if (prestamoParam!=null) {
	      int idPrestamo = Integer.parseInt(prestamoParam);
	      List<Cuota> todas = cuotaDao.listarPorPrestamo(idPrestamo);
	      for (Cuota c : todas) {
	        if (c.getEstado()==1) pendientes.add(c);
	      }
	    }
	    request.setAttribute("cuotasPendientes", pendientes);
	    request.setAttribute("cuentaSeleccionada", cuentaParam);
	    request.setAttribute("idPrestamo", prestamoParam);
	    request.getRequestDispatcher("/pagarPrestamos.jsp")
	       .forward(request, response);
	  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
        Cliente cliente = session != null ? (Cliente) session.getAttribute("clienteLogueado") : null;
        if (cliente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String cuentaParam = request.getParameter("cuentaSeleccionada");
        String prestamoParam = request.getParameter("idPrestamo");
        String cuotaParam = request.getParameter("idCuota");
		
        boolean pagado = false;
        if (cuentaParam != null && prestamoParam != null && cuotaParam != null) {
            try {
                int nroCuenta = Integer.parseInt(cuentaParam);
                int idCuota = Integer.parseInt(cuotaParam);
                pagado = prestamoNegocio.pagarCuota(idCuota, nroCuenta);
            } catch (NumberFormatException e) {
                // parámetros inválidos
            }
        }

        if (!pagado) {
            // Guardar mensaje de error para mostrarlo tras redirección
            session.setAttribute("errorPago", "No se pudo pagar la cuota. Verifique sus fondos.");
        } else {
        	session.setAttribute("exitoPago", "La cuota fue pagada exitosamente");
        }
		
		
		doGet(request, response);
	}

}
