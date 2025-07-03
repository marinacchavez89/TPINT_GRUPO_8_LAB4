package entidades;

import java.sql.Date;

public class Cuota {
	private int id_cuota;
	private int id_prestamo;
	private int numero_cuota;
	private float monto;
	private Date fecha_pago;
	private boolean estado;
	
	public Cuota() {
		
	}
	
	public Cuota(int id_cuota, int id_prestamo, int numero_cuota, float monto, Date fecha_pago, boolean estado) {
		super();
		this.id_cuota = id_cuota;
		this.id_prestamo = id_prestamo;
		this.numero_cuota = numero_cuota;
		this.monto = monto;
		this.fecha_pago = fecha_pago;
		this.estado = estado;
	}
	
	public int getIdCuota() {
		return id_cuota;
	}
	public void setIdCuota(int id_cuota) {
		this.id_cuota = id_cuota;
	}
	public int getIdPrestamo() {
		return id_prestamo;
	}
	public void setIdPrestamo(int id_prestamo) {
		this.id_prestamo = id_prestamo;
	}
	public int getNumeroCuota() {
		return numero_cuota;
	}
	public void setNumeroCuota(int numero_cuota) {
		this.numero_cuota = numero_cuota;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public Date getFechaPago() {
		return fecha_pago;
	}
	public void setFechaPago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}


