package entidades;

public class Nacionalidad {
	private int idNacionalidad;
	private String descripcion;
	
	//constructores
	public Nacionalidad() {
		
	}
	
	public Nacionalidad(int idNacionalidad, String descripcion) {
		this.idNacionalidad = idNacionalidad;
		this.descripcion = descripcion;
	}
	
	//getters y setters
	public int getIdNacionalidad() {
		return idNacionalidad;
	}
	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
}