package negocioImpl;

import entidades.Cuota;
import negocio.CuotaNegocio;
import dao.CuotaDAO;
import daoImpl.CuotaDAOImpl;

public class CuotaNegocioImpl implements CuotaNegocio {
	CuotaDAO cuotaDao = new CuotaDAOImpl();
	@Override
	public boolean agregarCuota(Cuota cuota) {
		return cuotaDao.agregar(cuota);
	}
	
}
