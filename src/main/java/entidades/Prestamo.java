package entidades;

import java.sql.Date;

public class Prestamo {

    private int idPrestamo;
    private int idCliente;
    private int nroCuenta;
    private Date fechaAlta;
    private double importePedido;
    private int plazoPago;
    private double importeAPagar;
    private int cantidadCuotas;
    private boolean estado;

    public  Prestamo() {}
    
    
    public Prestamo(int idPrestamo, int idCliente, int nroCuenta, Date fechaAlta, double importePedido, int plazoPago,
			double importeAPagar, int cantidadCuotas, boolean estado) {
		super();
		this.idPrestamo = idPrestamo;
		this.idCliente = idCliente;
		this.nroCuenta = nroCuenta;
		this.fechaAlta = fechaAlta;
		this.importePedido = importePedido;
		this.plazoPago = plazoPago;
		this.importeAPagar = importeAPagar;
		this.cantidadCuotas = cantidadCuotas;
		this.estado = estado;
	}


	// Getters y Setters
    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public double getImportePedido() {
        return importePedido;
    }

    public void setImportePedido(double importePedido) {
        this.importePedido = importePedido;
    }

    public int getPlazoPago() {
        return plazoPago;
    }

    public void setPlazoPago(int plazoPago) {
        this.plazoPago = plazoPago;
    }

    public double getImporteAPagar() {
        return importeAPagar;
    }

    public void setImporteAPagar(double importeAPagar) {
        this.importeAPagar = importeAPagar;
    }

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


	

	
}
