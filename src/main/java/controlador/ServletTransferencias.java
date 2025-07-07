package controlador;

import entidades.*;
import negocio.*;
import negocioImpl.*;

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
