package controlador;

import entidades.*;
import negocio.*;
import negocioImpl.*;
import excepciones.SaldoInsuficienteException;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTransferencias
 */
@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	private MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
	private TransferenciaNegocio transferenciaNegocio = new TransferenciaNegocioImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencias() {
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
	    	
	        response.sendRedirect("login.jsp");
	        return;
	    }

	    int idCliente = cliente.getIdCliente();
	    System.out.println("Cliente recuperado: " + cliente.getIdCliente());
	    
	    List<Cuenta> cuentas = cuentaNegocio.obtenerXIdCliente(idCliente);
	    request.setAttribute("cuentasDelCliente", cuentas);


	    request.getRequestDispatcher("transferencias.jsp").forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[SERVLET] Entró al doPost de ServletTransferencias");
		HttpSession session = request.getSession();
	    Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
		
	    if (cliente == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }
	    
	    try {
	        int cuentaOrigen = Integer.parseInt(request.getParameter("cuentaOrigen"));
	        String cbuDestino = request.getParameter("cbuDestino");
	        float importe = Float.parseFloat(request.getParameter("monto"));
	        
	     //  Acá van los println para verificar que llegaron los datos del formulario
	        System.out.println("[SERVLET] cuentaOrigen: " + cuentaOrigen);
	        System.out.println("[SERVLET] cbuDestino: " + cbuDestino);
	        System.out.println("[SERVLET] importe: " + importe);
	        
	     // Buscamos la cuenta destino por su CBU
	        Cuenta cuentaDestinoObj = cuentaNegocio.obtenerPorCBU(cbuDestino);
	        if (cuentaDestinoObj == null) {
	            request.setAttribute("mensajeError", "No se encontró ninguna cuenta con ese CBU.");
	        } else {

	        Transferencia transferencia = new Transferencia();
	        transferencia.setNroCuentaOrigen(cuentaOrigen);
	        transferencia.setNroCuentaDestino(cuentaDestinoObj.getNroCuenta());
	        transferencia.setImporte(importe);
	        
	        
	        boolean exito = transferenciaNegocio.registrarTransferencia(transferencia);
	        System.out.println("[SERVLET] Resultado de registrarTransferencia: " + exito);

	        if (exito) {
	            request.setAttribute("mensajeExito", "Transferencia realizada con éxito.");
	        } else {
	            request.setAttribute("mensajeError", "Ocurrió un error al procesar la transferencia.");
	        }
	      }

	    } catch (SaldoInsuficienteException e) {
	    	System.out.println("[SERVLET] SaldoInsuficienteException atrapada");
	        request.setAttribute("mensajeError", "Saldo insuficiente para realizar la transferencia.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensajeError", "Error inesperado al procesar la transferencia.");
	    }
	    
	 // Volvemos a cargar las cuentas para mostrar nuevamente el formulario
	    int idCliente = cliente.getIdCliente();
	    List<Cuenta> cuentas = cuentaNegocio.obtenerXIdCliente(idCliente);
	    request.setAttribute("cuentasDelCliente", cuentas);

	    request.getRequestDispatcher("transferencias.jsp").forward(request, response);
	}
}

