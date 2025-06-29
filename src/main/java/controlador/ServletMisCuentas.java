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
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
		 
	    }
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
