package entidades;
import java.util.Date;

public class Cuenta{
	private int nroCuenta;
	private int idCliente;
	private Date fechaCreación;
	private TipoCuenta idTipoCuenta;
	private String CBU;
	private float saldo;
	private boolean estado;
	
	//constructores
	public Cuenta () {
		
	}

	public Cuenta(int nroCuenta, int idCliente, Date fechaCreación, TipoCuenta idTipoCuenta, String cbu, float saldo,
			boolean estado) {
		super();
		this.nroCuenta = nroCuenta;
		this.idCliente = idCliente;
		this.fechaCreación = fechaCreación;
		this.idTipoCuenta = idTipoCuenta;
		this.CBU = cbu;
		this.saldo = saldo;
		this.estado = estado;
	}
	
	//getter y setter
	public int getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(int nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFechaCreación() {
		return fechaCreación;
	}

	public void setFechaCreación(Date fechaCreación) {
		this.fechaCreación = fechaCreación;
	}

	public TipoCuenta getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta NroCuenta: " + nroCuenta + ", idCliente: " + idCliente + ", fechaCreación: " + fechaCreación
				+ ", idTipoCuenta: " + idTipoCuenta + ", CBU: " + CBU + ", saldo: " + saldo + ", estado: " + estado;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



