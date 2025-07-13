package dao;

import entidades.Cliente;
import entidades.Prestamo;
import java.util.List;

public interface PrestamoDAO{

	boolean insert(Prestamo prestamo);
	public boolean modificarEstado(Prestamo prestamo, int estado);
	
	/** vamos a necesitar esta funcion cuando necesitemos autorizar o rechazar */
	Prestamo obtenerPorId(int idPrestamo);
	/** Listado de pendientes para el admin (TODOS)*/
	List<Prestamo> listarPendientes();
	/** Listado de p´restamos de un cliente (para la vista cliente)*/
	List<Prestamo> listarPorCliente (int idCliente);
	
	List<Prestamo> listarAutorizados();
	
	boolean actualizarImporteAPagar(int idPrestamo, double importeAPagar);
}