package entidades;

import java.util.Date;



public class Transferencia {
	private int idTransferencia;
    private Date fecha;
    private int nroCuentaOrigen;
    private int nroCuentaDestino;
    private float importe;
    
    
    // constructores getters y setters
    
    public Transferencia() {}
    
	public Transferencia(int idTransferencia, Date fecha, int nroCuentaOrigen, int nroCuentaDestino, float importe) {
		this.idTransferencia = idTransferencia;
		this.fecha = fecha;
		this.nroCuentaOrigen = nroCuentaOrigen;
		this.nroCuentaDestino = nroCuentaDestino;
		this.importe = importe;
	}

	
	
	public int getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(int idTransferencia) {
		this.idTransferencia = idTransferencia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNroCuentaOrigen() {
		return nroCuentaOrigen;
	}

	public void setNroCuentaOrigen(int nroCuentaOrigen) {
		this.nroCuentaOrigen = nroCuentaOrigen;
	}

	public int getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	public void setNroCuentaDestino(int nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

    
}