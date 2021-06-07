package clases.ito.poo;

import java.time.LocalDate;

import excepcion.ito.poo.SaldoEx;
import excepcion.ito.poo.NumeroCuentaEx;
import excepcion.ito.poo.RetiroExcep;
import excepcion.ito.poo.RetiroEx;
import excepcion.ito.poo.DepositoEx;

public class CuentaBancaria implements Comparable<CuentaBancaria>{
	
	
	private long numCuenta =0L;
	
	public String nomCliente="";
	
	public float saldo=0f;
	
	private LocalDate fechaApertura= null;
	
	private LocalDate fechaActualizacion= null;
	
	
	
	private void examinaCuenta(long numCuenta)throws NumeroCuentaEx {
		if( numCuenta<9999)
			throw new NumeroCuentaEx("El número de tu cuenta tiene que ser de  un mayor a 9999");	
	     }
	
	private void examinaSaldo(float saldo)throws  SaldoEx{
		if(saldo<5000)
        	throw new SaldoEx("El minimo de saldo necesario para la apertura una cuenta es de $5,000.00");
	}
	
	
	
	
	public CuentaBancaria(long numCuenta, String nomCliente, float saldo, LocalDate fechaApertura,
			LocalDate fechaActualizacicon) throws NumeroCuentaEx ,SaldoEx{
		super();
		examinaCuenta(numCuenta);
		examinaSaldo(saldo);
		this.numCuenta = numCuenta;
		this.nomCliente = nomCliente;
		this.saldo = saldo;
		this.fechaApertura = fechaApertura;
		this.fechaActualizacion = fechaActualizacicon;
	}
	
	
	
	public CuentaBancaria() {
		// Start of user code constructor for CuentaBancaria)
		// End of user code
	}

	
	
	/**
	 * Description of the method deposito.
	 * @param cantidad 
	 * @return 
	 */
	public boolean Deposito(float cantidad) throws DepositoEx {
		boolean deposito= false;
		if (cantidad>=500 && cantidad<=25000) {
			this.saldo += cantidad;
			deposito= true;
			this.fechaActualizacion = LocalDate.now();
		}else
			throw new DepositoEx ("El minimo del deposito debe ser de $500 hasta $25000");
		return deposito;
	}
	
	
	
	/**
	 * Description of the method retiro.
	 * @param cantidad 
	 * @return 
	 */
	public boolean retiro(float cantidad) throws RetiroExcep, RetiroEx {
		boolean retiro = false;
		if (cantidad>=100 && cantidad<=6000 && cantidad%100==0) {
			if (this.saldo >= cantidad) {
				this.saldo -= cantidad;
				retiro = true;
				this.fechaActualizacion = LocalDate.now();
			}else if(this.saldo<=cantidad) {
		    	throw new RetiroEx("No hay fondos suficientes");}
		}else 
				throw new RetiroExcep ("La cantidad debe ser de 100 en 100. Con un maximo de $6000 para retirar");
		
		return retiro;
	}
	

	
	// Start of user code (user defined methods for CuentaBancaria)

	// End of user code
	/**
	 * Returns numCuenta.
	 * @return numCuenta 
	 */
	
	
	public long getNumCuenta() {
		return this.numCuenta;
	}

	/**
	 * Sets a value to attribute numCuenta. 
	 * @param newNumCuenta 
	 * @throws NumeroCuentaEx 
	 */
	public void setNumCuenta(long newNumCuenta) throws NumeroCuentaEx {
		examinaCuenta(numCuenta);
	    this.numCuenta = numCuenta;
	}

	/**
	 * Returns nomCliente.
	 * @return nomCliente 
	 */
	public String getNomCliente() {
		return this.nomCliente;
	}

	/**
	 * Sets a value to attribute nomCliente. 
	 * @param newNomCliente 
	 */
	public void setNomCliente(String newNomCliente) {
		this.nomCliente = newNomCliente;
	}

	/**
	 * Returns saldo.
	 * @return saldo 
	 */
	public float getSaldo() {
		return this.saldo;
	}

	/**
	 * Sets a value to attribute saldo. 
	 * @param newSaldo 
	 */
	public void setSaldo(float saldo) throws SaldoEx {
		examinaSaldo(saldo);
	    this.saldo = saldo;
	}

	/**
	 * Returns fechaApertura.
	 * @return fechaApertura 
	 */
	public java.time.LocalDate getFechaApertura() {
		return this.fechaApertura;
	}

	/**
	 * Sets a value to attribute fechaApertura. 
	 * @param newFechaApertura 
	 */
	public void setFechaApertura(java.time.LocalDate newFechaApertura) {
		this.fechaApertura = newFechaApertura;
	}

	/**
	 * Returns fechaActualizacion.
	 * @return fechaActualizacion 
	 */
	public java.time.LocalDate getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	/**
	 * Sets a value to attribute fechaActualizacion. 
	 * @param newFechaActualizacion 
	 */
	public void setFechaActualizacion(java.time.LocalDate newFechaActualizacion) {
		this.fechaActualizacion = newFechaActualizacion;
	}
	


	@Override
	public String toString() {
		return "CuentaBancaria [numCuenta=" + numCuenta + ", nomCliente=" + nomCliente + ", saldo=" + saldo
				+ ", fechaApertura=" + fechaApertura  + "]";
	}
	
	
	
	public int compareTo(CuentaBancaria o) {
		int compare=0;
		
		if(this.numCuenta<o.getNumCuenta())
			compare=-1;
		
		else if(this.numCuenta>o.getNumCuenta())
			compare=1;
		return compare;
		
	}
}