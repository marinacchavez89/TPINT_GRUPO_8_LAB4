package negocio;

import entidades.Cuenta;
import entidades.Cliente;
import entidades.Transferencia;
import java.util.List;

public interface TransferenciaNegocio {

	public boolean registrarTransferencia(Transferencia transferencia);
}
