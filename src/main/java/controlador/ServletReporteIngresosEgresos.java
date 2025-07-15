package controlador;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.MovimientoNegocio;
import negocioImpl.MovimientoNegocioImpl;


@WebServlet("/ServletReporteIngresosEgresos")
public class ServletReporteIngresosEgresos extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ServletReporteIngresosEgresos() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.getRequestDispatcher("reporteIngresosEgresos.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date fechaDesde = Date.valueOf(request.getParameter("fechaDesde"));
        Date fechaHasta = Date.valueOf(request.getParameter("fechaHasta"));
        
        System.out.println("Fecha desde: " + fechaDesde);
        System.out.println("Fecha hasta: " + fechaHasta);

        MovimientoNegocio movNegocio = new MovimientoNegocioImpl();
        Map<String, Double> resumen = movNegocio.obtenerResumenIngresosEgresos(fechaDesde, fechaHasta);
        System.out.println("Resumen obtenido:");
        /*for (Map.Entry<String, Double> entry : resumen.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }*/
        request.setAttribute("resumen", resumen);
        request.getRequestDispatcher("reporteIngresosEgresos.jsp").forward(request, response);
    }

}
