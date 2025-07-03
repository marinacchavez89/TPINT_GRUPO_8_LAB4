package dao;

import entidades.Cliente;
import entidades.Prestamo;
import java.util.List;

public interface PrestamoDAO{

	boolean insert(Prestamo prestamo);
	public boolean modificarEstado(Prestamo prestamo, int estado);
}