package excepciones;


public class SinCuentasException extends Exception{

	public SinCuentasException () {
		
	}
	
	@Override
	public String getMessage() {
		
		return "No se encontraron cuentas asociadas al usuario.";
	}
	
	
}
