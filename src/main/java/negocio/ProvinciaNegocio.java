package negocio;

import java.util.List;

import entidades.Provincia;

public interface ProvinciaNegocio {
	public  List<Provincia> obtenerPorIdPaisResidencia(int idPaisReisencia);
}
