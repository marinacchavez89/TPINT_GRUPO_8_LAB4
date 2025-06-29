package entidades;

public class TipoMovimiento {
	private int IdTipoMovimiento;
	private String descripcion;
	
	//getters setters y constructores 
	
	public TipoMovimiento () {}

	public TipoMovimiento(int idTipoMovimiento, String descripcion) {
		IdTipoMovimiento = idTipoMovimiento;
		this.descripcion = descripcion;
	}

	public int getIdTipoMovimiento() {
		return IdTipoMovimiento;
	}

	public void setIdTipoMovimiento(int idTipoMovimiento) {
		IdTipoMovimiento = idTipoMovimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}