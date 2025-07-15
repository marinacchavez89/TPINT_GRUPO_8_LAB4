package excepciones;

public class SinPrestamosException extends Exception {
	
	public SinPrestamosException() { 
		
	}
	
	@Override
	public String getMessage() {
		
		return "No se encontraron préstamos asociados al usuario.";
	}	
	
}
