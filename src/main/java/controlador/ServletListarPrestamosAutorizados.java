package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Prestamo;
import negocio.CuentaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletListarPrestamosAutorizados
 */
@WebServlet("/ServletListarPrestamosAutorizados")
public class ServletListarPrestamosAutorizados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarPrestamosAutorizados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
        Cliente cliente = session != null ? (Cliente) session.getAttribute("clienteLogueado") : null;
        if (cliente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        List<Cuenta> cuentas = cuentaNegocio.listarCuentasFiltradas(true , cliente.getIdCliente());
        request.setAttribute("cuentasDelCliente", cuentas);

    String cuentaParam = request.getParameter("cuentaSeleccionada");
    Integer nroCuenta = null;
    if(cuentaParam != null && !cuentaParam.isEmpty())
    {
    	try {
			nroCuenta = Integer.parseInt(cuentaParam);
		} catch (NumberFormatException e) {
			nroCuenta = null;
			// TODO: handle exception
		}
    }

    request.setAttribute("cuentaSeleccionada",  cuentaParam);
    
    List<Prestamo> autorizados = new ArrayList<>();
    if (nroCuenta != null) {
        // Obtener todos los préstamos del cliente con estado aprobado
        List<Prestamo> todos = prestamoNegocio.listarPrestamosAutorizados();
        for (Prestamo p : todos) {
            // Filtrar por número de cuenta
            if (p.getNroCuenta() == nroCuenta) {
                autorizados.add(p);
            }
        }
    }
    request.setAttribute("prestamosAutorizados", autorizados);
    
        // Mostramos JSP
        request.getRequestDispatcher("listarPrestamosAutorizados.jsp").forward(request, response);
	}

	}
