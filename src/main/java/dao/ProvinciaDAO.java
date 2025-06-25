package dao;

import java.util.List;
import entidades.Provincia;

public interface ProvinciaDAO {
	public List<Provincia> listarProvinciasPorPaisesResidencia(int idPaisResidencia);
}
