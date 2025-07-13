package dao;

import java.util.List;

import entidades.Cuota;

public interface CuotaDAO {
	public boolean agregar(Cuota cuota);
	public Cuota obtenerPorId(int idCuota);
	public boolean actualizar(Cuota cuota);
	public List<Cuota> listarPorPrestamo (int idPrestamo);
}
