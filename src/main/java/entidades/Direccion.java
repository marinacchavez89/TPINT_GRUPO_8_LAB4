package entidades;

public class Direccion {
	private int idDireccion;
	private Localidad localidad;
	private String codigoPostal;
	private String calle;
	private String numero;

	// Constructores, getters y setters
	public Direccion() {}

	public Direccion(int idDireccion, Localidad localidad, String codigoPostal, String calle, String numero) {
		this.idDireccion = idDireccion;
		this.localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.calle = calle;
		this.numero = numero;
	}

	public int getIdDireccion() {
		return idDireccion;
	}
	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
}