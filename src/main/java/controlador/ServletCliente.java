package controlador;

import entidades.Cliente;
import entidades.Direccion;
import entidades.Nacionalidad;
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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendRedirect("ServletCliente"); // redirige al listado
            return;
        }

        Cliente cliente = new Cliente();

        // Recuperar valores del formulario
        cliente.setIdCliente( parseInt(request.getParameter("id")) );
        cliente.setDni(request.getParameter("dni"));
        cliente.setCuil(request.getParameter("cuil"));
        cliente.setNombre(request.getParameter("nombre"));
        cliente.setApellido(request.getParameter("apellido"));
        String sexoStr = request.getParameter("sexo");
        cliente.setSexo((sexoStr != null && !sexoStr.isEmpty()) ? sexoStr.charAt(0) : ' ');

        // Nacionalidad
        Nacionalidad nacionalidad = new Nacionalidad();
        nacionalidad.setIdNacionalidad( parseInt(request.getParameter("nacionalidad")) );
        cliente.setNacionalidad(nacionalidad);

        // Fecha de nacimiento
        try {
            java.util.Date fecha = java.sql.Date.valueOf(request.getParameter("fechaNacimiento"));
            cliente.setFechaNacimiento(fecha);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Direccion
        Direccion direccion = new Direccion();
        direccion.setIdDireccion( parseInt(request.getParameter("direccion")) );
        cliente.setDireccion(direccion);

        cliente.setCorreoElectronico(request.getParameter("email"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setEstado(true); // o según lógica

        boolean resultado = false;

        switch (accion) {
            case "Agregar":
                resultado = clienteNegocio.agregarCliente(cliente);
                break;
            case "Modificar":
                resultado = clienteNegocio.modificarCliente(cliente);
                break;
            case "Eliminar":
                resultado = clienteNegocio.eliminarCliente(cliente.getIdCliente());
                break;
        }

        // Después de cualquier acción, redirige al listado
        response.sendRedirect("ServletCliente");
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

}
