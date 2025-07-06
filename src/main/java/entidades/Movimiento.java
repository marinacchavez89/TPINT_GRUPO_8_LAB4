package entidades;

import java.util.Date; 

public class Movimiento {
	private int idMovimiento;
	private Date fecha;
	private Cuenta cuenta;
	private String detalle;
	private float importe;
	private TipoMovimiento tipoMovimiento;

	
	// getters setters y constructores
	
	public Movimiento () {}
	
	public Movimiento(int idMovimiento, Date fecha, Cuenta cuenta, String detalle, float importe,
			TipoMovimiento tipoMovimiento) {
		this.idMovimiento = idMovimiento;
		this.fecha = fecha;
		this.cuenta = cuenta;
		this.detalle = detalle;
		this.importe = importe;
		this.tipoMovimiento = tipoMovimiento;
	}
	
	public Movimiento(Date fecha, int nroCuenta, String detalle, float importe, TipoMovimiento tipoMovimiento)
	{
	    this.fecha = fecha;
	    this.cuenta = new Cuenta(nroCuenta);    
	    this.detalle = detalle;
	    this.importe = importe;
	    this.tipoMovimiento = tipoMovimiento;
	}


	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public void setCuenta(int nroCuenta)
	{
		this.cuenta = new Cuenta(nroCuenta);
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
		
}
