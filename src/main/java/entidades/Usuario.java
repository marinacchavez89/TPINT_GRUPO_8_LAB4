package entidades;

public class Usuario {
	private int idUsuario;
	private Integer idCliente;
	private String nombreUsuario;
	private String contrasena;
	private String tipoUsuario;
	private Boolean estado;
	
	//getters, setters y constructores
	public Usuario () {
		
	}
	
	public Usuario(int idUsuario, Integer idCliente, String nombreUsuario, String contrasena, String tipoUsuario,
			Boolean estado) {
		this.idUsuario = idUsuario;
		this.idCliente = idCliente;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.tipoUsuario = tipoUsuario;
		this.estado = estado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
}
