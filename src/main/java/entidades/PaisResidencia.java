package entidades;

public class PaisResidencia {
	private int idPaisResidencia;
	private String descripcion;

	// Constructores
	public PaisResidencia() {
	}

	public PaisResidencia(int idPaisResidencia, String descripcion) {
		this.idPaisResidencia = idPaisResidencia;
		this.descripcion = descripcion;
	}

	// Getters y Setters
	public int getIdPaisResidencia() {
		return idPaisResidencia;
	}

	public void setIdPaisResidencia(int idPaisResidencia) {
		this.idPaisResidencia = idPaisResidencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

