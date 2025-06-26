package dao;

import java.util.List;

import entidades.Localidad;


public interface LocalidadDAO {
	public List<Localidad> listarLocalidadesPorProvincia(int idProvincia);
}
