package controlador;

import java.io.IOException;
import java.util.Map;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.ReporteNegocio;
import negocioImpl.ReporteNegocioImpl;


@WebServlet("/ServletReportePrestamos")
public class ServletReportePrestamos extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private ReporteNegocio reporteNegocio = new ReporteNegocioImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fechaDesdeStr = request.getParameter("fechaInicio");
		String fechaHastaStr = request.getParameter("fechaFin");


		 if (fechaDesdeStr != null && fechaHastaStr != null) {
	            try {
	                Date fechaDesde = Date.valueOf(fechaDesdeStr);
	                Date fechaHasta = Date.valueOf(fechaHastaStr);

	                double totalSolicitado = reporteNegocio.obtenerTotalPrestamosAutorizados(fechaDesde, fechaHasta);
	                int cantidad = reporteNegocio.contarPrestamosAutorizados(fechaDesde, fechaHasta);

	                request.setAttribute("totalSolicitado", totalSolicitado);
	                request.setAttribute("cantidad", cantidad);
	            } catch (IllegalArgumentException e) {
	                request.setAttribute("mensajeError", "Formato de fecha inválido.");
	            }
	        }
		request.getRequestDispatcher("reportePrestamos.jsp").forward(request, response);
	}
}