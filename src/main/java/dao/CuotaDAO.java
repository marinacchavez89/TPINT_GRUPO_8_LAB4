package dao;

import entidades.Cuota;

public interface CuotaDAO {
	public boolean agregar(Cuota cuota);
	public Cuota obtenerPorId(int idCuota);
	//public boolean actualizar(Cuota cuota);
}
