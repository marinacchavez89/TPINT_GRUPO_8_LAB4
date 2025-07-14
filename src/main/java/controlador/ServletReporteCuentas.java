package controlador;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Cuenta;
import entidades.Cliente;
import negocio.CuentaNegocio;
import negocio.ClienteNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.ClienteNegocioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletReporteCuentas")
public class ServletReporteCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirige al JSP vacío con formulario
		request.getRequestDispatcher("reporteCuentas.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String fechaDesdeStr = request.getParameter("fechaDesde");
	    String fechaHastaStr = request.getParameter("fechaHasta");
	    
	    try {
	        Date fechaDesde = Date.valueOf(fechaDesdeStr);
	        Date fechaHasta = Date.valueOf(fechaHastaStr);
	        
	        List<Cuenta> cuentas = cuentaNegocio.listarCuentasFiltradasPorFecha(fechaDesde, fechaHasta);
	        List<Cuenta> lista = cuentaNegocio.listarCuentasFiltradasPorFecha(fechaDesde, fechaHasta);
	        Map<String, Object> stats = cuentaNegocio.generarEstadisticas(lista);

	        request.setAttribute("listaCuentas", lista);
	        request.setAttribute("stats", stats);

	        // Obtener mapa de idCliente -> dni
	        List<Cliente> clientes = clienteNegocio.listarClientes();
	        Map<Integer, String> dniPorIdCliente = new HashMap<>();
	        for (Cliente c : clientes) {
	            dniPorIdCliente.put(c.getIdCliente(), c.getDni());
	        }

	        request.setAttribute("cuentas", cuentas);
	        request.setAttribute("dniPorIdCliente", dniPorIdCliente);
	        request.getRequestDispatcher("reporteCuentas.jsp").forward(request, response);

	    } catch (Exception e) {
	    	e.printStackTrace();
	        request.setAttribute("error", "Fechas inválidas.");
	        request.getRequestDispatcher("reporteCuentas.jsp").forward(request, response);
	    }
	}


	
}