package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletActivarClientes")
public class ServletActivarClientes extends HttpServlet {

    private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	HttpSession session = request.getSession();

        // Pasar mensajes de la sesión al request
        String mensaje = (String) session.getAttribute("confirmacionMensaje");
        String tipo = (String) session.getAttribute("confirmacionTipo");

        if (mensaje != null) {
            request.setAttribute("confirmacionMensaje", mensaje);
            request.setAttribute("confirmacionTipo", tipo);
            session.removeAttribute("confirmacionMensaje");
            session.removeAttribute("confirmacionTipo");
        }

        List<Cliente> clientesInactivos = clienteNegocio.listarInactivos();
        request.setAttribute("clientesInactivos", clientesInactivos);
        request.getRequestDispatcher("clientesInactivos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        boolean activado = clienteNegocio.activarCliente(idCliente);

        HttpSession session = request.getSession();

        if (activado) {
            session.setAttribute("confirmacionMensaje", "Cliente activado correctamente.");
            session.setAttribute("confirmacionTipo", "success");
        } else {
            session.setAttribute("confirmacionMensaje", "No se pudo activar el cliente.");
            session.setAttribute("confirmacionTipo", "error");
        }
        
        // Redirigimos de nuevo a la lista de clientes inactivos para ver cambios
        response.sendRedirect("ServletActivarClientes");
    }
}
