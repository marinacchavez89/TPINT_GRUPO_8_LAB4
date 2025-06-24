package controlador;

import entidades.Cliente;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {

    private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cliente> listaClientes = clienteNegocio.listarClientes();

        request.setAttribute("clientes", listaClientes);

        // Manda los datos al JSP para mostrar
        request.getRequestDispatcher("administracionClientes.jsp").forward(request, response);
    }
}
