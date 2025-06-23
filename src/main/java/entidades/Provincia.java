package entidades;

public class Provincia {
	private int idProvincia;
	private String nombreProvincia;
	private PaisResidencia paisResidencia;

	// Constructores
	public Provincia() {
	}

	public Provincia(int idProvincia, String nombreProvincia, PaisResidencia paisResidencia) {
		this.idProvincia = idProvincia;
		this.nombreProvincia = nombreProvincia;
		this.paisResidencia = paisResidencia;
	}

	// Getters y Setters
	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public PaisResidencia getPaisResidencia() {
		return paisResidencia;
	}

	public void setPaisResidencia(PaisResidencia paisResidencia) {
		this.paisResidencia = paisResidencia;
	}
}