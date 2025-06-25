package negocioImpl;

import java.util.List;
import entidades.TipoCuenta;
import dao.TipoCuentaDAO;
import negocio.TipoCuentaNegocio;
import daoImpl.TipoCuentaDAOImpl;

public class TipoCuentaNegocioImpl implements TipoCuentaNegocio {
	private TipoCuentaDAO tipoCuentaDAO= new TipoCuentaDAOImpl();
	
	@Override
	public List<TipoCuenta> listar(){
		return tipoCuentaDAO.listar();
	}
	public TipoCuenta obtenerTipoCuentaXId(int id){
		return tipoCuentaDAO.obtenerTipoCuentaXId(id);
	}
}
