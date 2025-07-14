package controlador;

import negocio.MovimientoNegocio;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import entidades.Usuario;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Movimiento;

import java.util.List;
import java.util.Date;

import excepciones.SinCuentasException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletMisCuentas
 */
@WebServlet("/ServletMisCuentas")
public class ServletMisCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl(); 
	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMisCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session = request.getSession();
		 	
	        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
       
	        
	        if (cliente == null) {
	            response.sendRedirect("login.jsp"); // o página de error
	            return;
	        } 

		
		try {
			int idCliente = cliente.getIdCliente();
	   
	        List<Cuenta> cuentas = cuentaNegocio.obtenerXIdCliente(idCliente);
	        
	        if(cuentas==null || cuentas.isEmpty()  ) {
	        	throw new SinCuentasException();
	        }
	       
	        for (Cuenta c : cuentas) {
	            System.out.println("Cuenta: " + c.getNroCuenta() + " - Saldo: " + c.getSaldo());
	        }

	        request.setAttribute("cuentasDelCliente", cuentas);
	        
	        
	        String nroCuentaParam = request.getParameter("cuentaSeleccionada");
	        
	        
	        Date fechaDesde = null;
	        String fechaDesdeStr = request.getParameter("fechaDesde");
	        
	        
	        Date fechaHasta = null;
	        String fechaHastaStr = request.getParameter("fechaHasta");
  
	         if (fechaDesdeStr != null && !fechaDesdeStr.isEmpty()) {
	                fechaDesde = java.sql.Date.valueOf(fechaDesdeStr);
	            }
	            if (fechaHastaStr != null && !fechaHastaStr.isEmpty()) {
	                fechaHasta = java.sql.Date.valueOf(fechaHastaStr);
	            }


	        
	        List<Movimiento> movimientos;

	        if (nroCuentaParam != null && !nroCuentaParam.isEmpty()) {
	            try {
	                int nroCuenta = Integer.parseInt(nroCuentaParam);
	               // movimientos = movimientoNegocio.obtenerMovimientosXCuenta(nroCuenta);
	                request.setAttribute("cuentaSeleccionada", nroCuentaParam);
	                
	                if(fechaDesde != null && fechaHasta != null) {
	                	movimientos = movimientoNegocio.obtenerMovimientosXFecha(nroCuenta,fechaDesde, fechaHasta);
	                } else {
	                	movimientos = movimientoNegocio.obtenerMovimientosXCuenta(nroCuenta);
	                }
	                
	                for (Cuenta c : cuentas) {
	                    if (c.getNroCuenta() == nroCuenta) {
	                        request.setAttribute("cuentaDetalle", c);
	                        break;
	                    }
	                }

	            } catch (NumberFormatException e) {
	                movimientos = List.of(); 
	            }
	        } else {
	            movimientos = movimientoNegocio.obtenerMovimientosXCliente(idCliente);
	        }

	        request.setAttribute("movimientos", movimientos);

	        request.getRequestDispatcher("misCuentas.jsp").forward(request, response);
	        
	    } catch (SinCuentasException e) {
	    	session.setAttribute("mensajeError", e.getMessage());
	    	response.sendRedirect("misCuentas.jsp");
	    	return;
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("mensajeError", "Error inesperado al cargar sus cuentas.");
			response.sendRedirect("misCuentas.jsp");
			return;
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
