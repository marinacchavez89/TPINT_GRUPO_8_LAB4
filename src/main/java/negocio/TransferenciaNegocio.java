package negocio;

import entidades.Cuenta;
import entidades.Cliente;
import entidades.Transferencia;
import java.util.List;
import excepciones.SaldoInsuficienteException;

public interface TransferenciaNegocio {

	public boolean registrarTransferencia(Transferencia transferencia)throws SaldoInsuficienteException;
}
