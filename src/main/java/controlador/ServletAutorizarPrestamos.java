package controlador;

import java.io.IOException;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Prestamo> listaPrestamosPendientes = prestamoNegocio.listarPrestamosPendientes();
		request.setAttribute("prestamosPendientes", listaPrestamosPendientes);
		request.getSession().setAttribute("prestamosPendientes", listaPrestamosPendientes);
		
		request.getRequestDispatcher("autorizarPrestamo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		String idParam = request.getParameter("idPrestamo");
		String impParam = request.getParameter("importeAPagar");
		
		if (accion != null && idParam != null) {
			
try {
	int idPrestamo = Integer.parseInt(idParam);
	boolean b;
	
	if("autorizar".equals(accion)) {
		Double importeAPagar = Double.parseDouble(impParam);
		
		List <Prestamo> listaPrestamosPendientes = (List<Prestamo>) request.getSession().getAttribute("prestamosPendientes");
		Prestamo prestamo = null;
		
		if(listaPrestamosPendientes != null) {
			for (Prestamo p: listaPrestamosPendientes) {
				if(p.getIdPrestamo() == idPrestamo) {
					prestamo = p;
					break;
				}
			}
			
		}
	
		if (prestamo != null && importeAPagar < prestamo.getImportePedido() ) {
			request.setAttribute("mensaje", "El importe autorizado no puede ser menor al solicitado ($" + prestamo.getImportePedido() + ").");
			doGet(request, response); // volver a mostrar la vista
			return;
		}
		
		b = prestamoNegocio.autorizarPrestamo(idPrestamo, importeAPagar);
		request.setAttribute("mensaje", b ? "Prestamo autorizado." : "error al autorizar el prestamo.");
	
	} else if ("rechazar".equals(accion))
	{
		b = prestamoNegocio.rechazarPrestamo(idPrestamo);
		request.setAttribute("mensaje" , b ? "prestamo rechazado." : "error al rechazar el prestamo.");
		
	}
} catch (Exception e) { // cambiar la excepcion y manejarla
	e.printStackTrace();
	request.setAttribute("mensaje", "Ocurrio un error procesando la solicitud.");
}			
			
		}
		doGet(request, response); // volvemos a listar y el forward al jsp
	}

}
