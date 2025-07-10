package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Prestamo;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletAutorizarPrestamos
 */
@WebServlet("/ServletAutorizarPrestamos")
public class ServletAutorizarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAutorizarPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Prestamo> listaPrestamosPendientes = prestamoNegocio.listarPrestamosPendientes();
		request.setAttribute("prestamosPendientes", listaPrestamosPendientes);
		
		
		request.getRequestDispatcher("autorizarPrestamo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
