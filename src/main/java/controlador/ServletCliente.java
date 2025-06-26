package controlador;

import entidades.Cliente;
import entidades.Direccion;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.PaisResidencia;
import entidades.Provincia;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import negocio.NacionalidadNegocio;
import negocioImpl.NacionalidadNegocioImpl;
import negocio.PaisResidenciaNegocio;
import negocioImpl.PaisResidenciaNegocioImpl;
import negocio.ProvinciaNegocio;
import negocioImpl.ProvinciaNegocioImpl;
import negocio.LocalidadNegocio;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {

    private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
    private NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocioImpl();
    private PaisResidenciaNegocio paisNegocio = new PaisResidenciaNegocioImpl();
    private ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImpl();
    private LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	 String idPaisParam = request.getParameter("idPais");
         if (idPaisParam != null) {
             // 1) Si vienen idPais, respondemos solo las <option> para provincias
             int idPais = Integer.parseInt(idPaisParam);
             List<Provincia> provincias = provinciaNegocio.obtenerPorIdPaisResidencia(idPais);

             response.setContentType("text/html; charset=UTF-8");
             PrintWriter out = response.getWriter();
             for (Provincia pr : provincias) {
                 out.printf("<option value=\"%d\">%s</option>%n",
                            pr.getIdProvincia(),
                            pr.getNombreProvincia());
             }
             return;
         }
         String idProvParam = request.getParameter("idProvincia");
         if (idProvParam != null && !idProvParam.isEmpty()) {
             int idProv = Integer.parseInt(idProvParam);
             List<Localidad> localidades = localidadNegocio.obtenerPorIdProvincia(idProv);

             response.setContentType("text/html; charset=UTF-8");
             PrintWriter out = response.getWriter();
                 for (Localidad localidad : localidades) {
                     out.printf("<option value=\"%d\">%s</option>%n",
                       localidad.getIdLocalidad(),
                       localidad.getNombreLocalidad()
                     );
                 }
             
             return;
         }
    	 
        List<Cliente> listaClientes = clienteNegocio.listarClientes();
        List<Nacionalidad> listaNacionalidades = nacionalidadNegocio.obtenerTodas();
        List<PaisResidencia> listaPaises = paisNegocio.obtenerTodos();
        //List<Provincia> listaProvincias = provinciaNegocio.obtenerTodas();
        //request.setAttribute("listaProvincias", listaProvincias);


        request.setAttribute("clientes", listaClientes);
        request.setAttribute("listaNacionalidades", listaNacionalidades);
        request.setAttribute("listaPaises", listaPaises);
        request.setAttribute("listaProvincias",List.of());
        request.setAttribute("paisSeleccionado", null);
        
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
