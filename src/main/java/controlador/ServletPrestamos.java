package controlador;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Prestamo;
import excepciones.SinCuentasException;
import negocio.CuentaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");

        if (cliente == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
        	List<Cuenta> cuentas = cuentaNegocio.obtenerXIdCliente(cliente.getIdCliente());
        	if (cuentas == null || cuentas.isEmpty()){
        		throw new SinCuentasException();
        	}
        	  request.setAttribute("cuentasDelCliente", cuentas);
              request.getRequestDispatcher("solicitudPrestamo.jsp").forward(request, response);
        }
        catch (SinCuentasException e) {
        	session.setAttribute("mensajeError", e.getMessage());
			response.sendRedirect("solicitudPrestamo.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");

        if (cliente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            double monto = Double.parseDouble(request.getParameter("monto"));
            int cuotas = Integer.parseInt(request.getParameter("cuotas"));
            int nroCuenta = Integer.parseInt(request.getParameter("cuenta"));

            Prestamo prestamo = new Prestamo();
            prestamo.setImportePedido(monto);
            prestamo.setCantidadCuotas(cuotas);
            prestamo.setIdCliente(cliente.getIdCliente());
            prestamo.setNroCuenta(nroCuenta);
            prestamo.setFechaAlta(new java.util.Date());

            prestamoNegocio.solicitarPrestamo(prestamo);
            request.setAttribute("mensajeExito", "Préstamo solicitado correctamente.");
        } catch (Exception e) {
            request.setAttribute("mensajeError", "Error al solicitar el préstamo.");
            e.printStackTrace();
        }

        // Cargar cuentas nuevamente
        List<Cuenta> cuentas = cuentaNegocio.obtenerXIdCliente(cliente.getIdCliente());
        request.setAttribute("cuentasDelCliente", cuentas);
        request.getRequestDispatcher("solicitudPrestamo.jsp").forward(request, response);
    }
}
