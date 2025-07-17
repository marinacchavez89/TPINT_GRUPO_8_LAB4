package controlador;

import entidades.Cliente;
import entidades.Direccion;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.PaisResidencia;
import entidades.Provincia;
import entidades.Usuario;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.DireccionNegocioImpl;
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
import negocio.DireccionNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {

    private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
    private NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocioImpl();
    private PaisResidenciaNegocio paisNegocio = new PaisResidenciaNegocioImpl();
    private ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImpl();
    private DireccionNegocio direccionNegocio = new DireccionNegocioImpl();
    private LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	 String idPaisParam = request.getParameter("idPais");
         if (idPaisParam != null) {
             // 1) Si vienen idPais, respondemos solo las <option> para provincias -> AJAX
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
        HttpSession session = request.getSession();

        if (accion == null) {
            response.sendRedirect("ServletCliente"); // redirige al listado
            return;
        }

        Cliente cliente = new Cliente();

        // Recuperar valores del formulario (siempre se recuperan todos, el switch decide qué hacer)
        cliente.setIdCliente( parseInt(request.getParameter("id")) ); // Se recupera el ID del cliente
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
            // Manejar el error si la fecha no es válida, quizás con un mensaje al usuario
            e.printStackTrace();
            System.out.println("⚠️ Fecha de nacimiento inválida.");
        }

        // Direccion - Se crea el objeto Dirección con los datos del formulario, incluyendo Localidad
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(parseInt(request.getParameter("direccionId"))); // ID de la dirección (EXISTENTE)
        direccion.setCalle(request.getParameter("calle"));
        direccion.setNumero(request.getParameter("numero"));
        direccion.setCodigoPostal(request.getParameter("codigoPostal"));

        Localidad localidad = new Localidad();
        localidad.setIdLocalidad(parseInt(request.getParameter("localidad")));
        // No necesitamos poblar Provincia y PaisResidencia en Localidad aquí para la operación,
        // ya que solo necesitamos el ID de la Localidad para la Dirección.
        direccion.setLocalidad(localidad); // Se asocia la localidad a la dirección

        // Otros campos
        cliente.setCorreoElectronico(request.getParameter("email"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setEstado(true); // o según lógica

        boolean resultado = false;
        String mensajeExito = "";

        switch (accion) {
            case "Agregar"://validacion del dni y del cuil
            	 if (clienteNegocio.existeDni(cliente.getDni(), 0)) {
            		 session.setAttribute("confirmacionMensaje", "El DNI ingresado ya está registrado.");
            		    session.setAttribute("confirmacionTipo", "error");
            		    response.sendRedirect("ServletCliente");
            		    return;
                 }
                 if (clienteNegocio.existeCuil(cliente.getCuil(), 0)) {
                	 session.setAttribute("confirmacionMensaje", "El CUIL ingresado ya está registrado.");
                	 session.setAttribute("confirmacionTipo", "error");
                	 response.sendRedirect("ServletCliente");
                	 return;
                 }
            	int idDireccionNueva = direccionNegocio.agregarDireccion(direccion);
                if (idDireccionNueva > 0) {
                    direccion.setIdDireccion(idDireccionNueva);
                    cliente.setDireccion(direccion);
                    resultado = clienteNegocio.agregarCliente(cliente);

                    if (resultado) {
                        int idClienteGenerado = clienteNegocio.obtenerIdXDni(cliente.getDni());

                        if (idClienteGenerado > 0) {
                            Usuario nuevoUsuario = new Usuario();
                            nuevoUsuario.setIdCliente(idClienteGenerado);
                            nuevoUsuario.setNombreUsuario(cliente.getDni());
                            nuevoUsuario.setContrasena(cliente.getDni());
                            nuevoUsuario.setTipoUsuario("cliente");
                            nuevoUsuario.setEstado(true);

                            UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
                            boolean usuarioOk = usuarioNegocio.agregarUsuario(nuevoUsuario);

                            if (usuarioOk) {
                                //Obtener el id del usuario creado
                                int idUsuarioGenerado = usuarioNegocio.obtenerIdPorCliente(idClienteGenerado);

                                //Actualizar el cliente con su id_usuario
                                clienteNegocio.setearUsuarioEnCliente(idClienteGenerado, idUsuarioGenerado);

                                mensajeExito = "Cliente y usuario agregados correctamente.";
                            } else {
                                System.out.println("⚠️ Usuario no se pudo agregar.");
                                mensajeExito = "Error al agregar el usuario.";
                            }
                        }
                    } else {
                        System.out.println("⚠️ No se pudo insertar el cliente.");
                        mensajeExito = "Error al agregar el cliente.";
                    }
                } else {
                    mensajeExito = "Error al agregar la dirección del cliente.";
                }
                break;
            case "Modificar":
            	 if (clienteNegocio.existeDni(cliente.getDni(), cliente.getIdCliente())) {
                     mensajeExito = "El DNI ya está registrado para otro cliente.";
                     resultado = false;
                     break;
                 }
                 if (clienteNegocio.existeCuil(cliente.getCuil(), cliente.getIdCliente())) {
                     mensajeExito = "El CUIL ya está registrado para otro cliente.";
                     resultado = false;
                     break;
                 }
            	boolean modDireccion = direccionNegocio.modificarDireccion(direccion);

                // 2. Modificar el Cliente (solo si la dirección se actualizó correctamente)
            	if (modDireccion) {
                    cliente.setDireccion(direccion);
                    resultado = clienteNegocio.modificarCliente(cliente);
                    if(resultado) {
                        mensajeExito = "Cliente modificado correctamente.";
                    } else {
                        System.out.println("⚠️ No se pudo modificar el cliente.");
                        mensajeExito = "Error al modificar el cliente.";
                    }
                } else {
                    System.out.println("⚠️ No se pudo modificar la dirección, no se actualiza el cliente.");
                    mensajeExito = "Error al modificar la dirección del cliente.";
                    resultado = false;
                }
                break;
            case "Eliminar":
            	resultado = clienteNegocio.eliminarCliente(cliente.getIdCliente());
                if (resultado) {
                    mensajeExito = "Cliente eliminado correctamente.";
                } else {
                    // Colocamos el mensaje personalizado para SweetAlert de cuentas activas:
                    mensajeExito = "No se pudo eliminar el cliente ya que posee cuentas activas.";
                    session.setAttribute("confirmacionTipo", "error");
                    session.setAttribute("confirmacionMensaje", mensajeExito);
                    response.sendRedirect("ServletCliente");
                    return;
                }
                break;
        }
        
        // Si la operación fue exitosa, guarda el mensaje en la sesión
        if (resultado && !mensajeExito.isEmpty()) {
            session.setAttribute("confirmacionMensaje", mensajeExito);
            session.setAttribute("confirmacionTipo", "success"); // Para el tipo de SweetAlert
        } else if (!resultado && !mensajeExito.isEmpty()) { // Si hubo un error pero hay un mensaje específico
             session.setAttribute("confirmacionMensaje", "Ocurrió un error en la operación.");
             session.setAttribute("confirmacionTipo", "error");
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
