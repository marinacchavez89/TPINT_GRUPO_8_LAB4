package excepciones;

public class SaldoInsuficienteException extends Exception{
	
	public SaldoInsuficienteException () {
	
	}

	@Override
	public String getMessage() {
		
		return "Saldo insuficiente para la transaccion";
	}
	
	

}
