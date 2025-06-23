package entidades;

public class Localidad {
	private int idLocalidad;
	private String nombreLocalidad;
	private Provincia provincia;

	// Constructores
	public Localidad() {
	}

	public Localidad(int idLocalidad, String nombreLocalidad, Provincia provincia) {
		this.idLocalidad = idLocalidad;
		this.nombreLocalidad = nombreLocalidad;
		this.provincia = provincia;
	}

	// Getters y Setters
	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
}
