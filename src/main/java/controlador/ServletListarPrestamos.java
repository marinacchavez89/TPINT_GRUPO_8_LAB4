package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Prestamo;
import excepciones.SinCuentasException;
import excepciones.SinPrestamosException;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletListarPrestamos
 */
@WebServlet("/ServletListarPrestamos")
public class ServletListarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Cliente cliente = (Cliente)session.getAttribute("clienteLogueado");
		
		
		List<Prestamo> listaMisPrestamos = prestamoNegocio.listarPrestamosPorCliente(cliente.getIdCliente());
		
		try {
		    if (listaMisPrestamos == null || listaMisPrestamos.isEmpty()) {
		        throw new SinPrestamosException();
		    }
		    request.setAttribute("misPrestamos", listaMisPrestamos);
		    request.getRequestDispatcher("misPrestamos.jsp").forward(request, response);
		} catch (SinPrestamosException e) {
			session.setAttribute("mensajeError", e.getMessage());
			response.sendRedirect("misPrestamos.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
