package app.ito.poo;

import java.time.LocalDate;
import java.util.Scanner;
import clases.ito.poo.CuentaBancaria;
import clases.ito.poo.CuentasBancarias;


import excepcion.ito.poo.RetiroEx;
import excepcion.ito.poo.BorrarEx;
import excepcion.ito.poo.CuentaEx;
import excepcion.ito.poo.DepositoEx;
import excepcion.ito.poo.NumeroCuentaEx;
import excepcion.ito.poo.RetiroExcep;
import excepcion.ito.poo.SaldoEx;



public class Aplicacion {

	
static CuentasBancarias c;
static Scanner lector= new Scanner (System.in);

 static CuentaBancaria capturaCuenta(){
    CuentaBancaria cbr= new CuentaBancaria();
    
    try {
    System.out.print("Numero de Cuenta: ");
    cbr.setNumCuenta(lector.nextLong());lector.nextLine();
    }catch(NumeroCuentaEx f){
    System.err.println(f.getMessage());
    }
    
    try {
    System.out.print("Apertura:");
    cbr.setSaldo(lector.nextFloat());lector.nextLine();
    }
    
    catch(SaldoEx f){
    System.err.println(f.getMessage());
    }
    
	System.out.print("Introduce tu nombre :");
	cbr.setNomCliente(lector.nextLine());
	
	System.out.print("Introduce la fecha en formato aaaa-mm-dd: ");
	String fecha=lector.nextLine();
	cbr.setFechaApertura(LocalDate.parse(fecha));
	return cbr;
}
 
 
 
 static void agregarCuenta() throws CuentaEx{
	 CuentaBancaria cb;
	 cb=capturaCuenta();
	 if(c.addItem(cb))
		 System.out.println("Cuenta Agregada");
	 
     else
    	 
	     throw new CuentaEx("Cuenta no agregada, verifique sus datos ");
}
 
  static CuentaBancaria alazar(String al){
	CuentaBancaria cnt=null; int i=0;
	lector.nextLine();
    for (;i <c.getSize();i++) {
      cnt = c.getItem(i);
      System.out.println(c.getItem(i)+"Cuenta a "+al);
      if(lector.nextLine().charAt(0)=='s')
    	 break;
      cnt=null;
    }
    
	return cnt;
 }
  
   static void deposito(){
	  if(!c.isFree()) {
	  CuentaBancaria count=alazar("Depositar");
	  if(count!=null) { 
		  try {
		  System.out.println("Ingrese la cantidad que desea depositar");
		  count.Deposito(lector.nextFloat());
		  }catch(DepositoEx e){
		      System.err.println(e.getMessage());
		  }
	  }
	  
	  else 
		  System.out.println("Error");
      }
} 
   
 static void retiro() throws RetiroEx  ,RetiroExcep{
	   if(!c.isFree()) {
			  CuentaBancaria count=alazar("Retirar");
			  if(count!=null) { 
				  try {
				  System.out.println("Introduce la cantidad que desaeas retirar:");
				  count.retiro(lector.nextFloat());
				  }catch(RetiroExcep m){
					    System.err.println(m.getMessage());}
			      catch(RetiroEx  m){
				    System.err.println(m.getMessage());}
			  }
			  
			  else 
				  System.out.println("Error"); 
	    }
}
 
    static void Borrar() throws BorrarEx{
	   if(!c.isFree()) {
			  CuentaBancaria cuenta=alazar("Borrar");
			  if(cuenta.getSaldo()==0) {
			  if(cuenta!=null) { 
				  c.delete(cuenta);
				  System.out.println("Ha sido borrada!");
			  }
			  
			  else 
				  System.out.println("Error");
			  }
			  
			  else
				  throw new BorrarEx("No es posible borrar una cuenta con saldo disponible");
		} 
}
    
      static void listado(){
		if(!c.isFree()) {
			System.out.println("Imprimir cuenta");
			for(int i=0;i<c.getSize();i++)
				System.out.println(c.getItem(i));
		}	
		else
			System.out.println("No existen las cuentas");
   }
      
    static float montoTota() {
	float mon=0;
	for(int i=0;i<c.getSize();i++) {
	  mon+=c.getItem(i).getSaldo();
    }
	
	return mon;
  }
    
 static void monPromedio() {
	 float prom=montoTota()/c.getSize();
	System.out.println("El promedio de las cuentas es "+ prom);
 }
 static void cuenMayoraMil() {
	if(!c.isFree()) {
		for(int i=0;i<c.getSize();i++)
			if(c.getItem(i).getSaldo()>10000)
				System.out.println(c.getItem(i));
    }
	else
    	System.out.println("No hay cuentas existentes");
}
 
static float saldoMayor() {
	float mayor=c.getItem(0).getSaldo();
	for(int i=1;i<c.getSize();i++)
		if(c.getItem(i).getSaldo()>mayor)
			mayor=c.getItem(i).getSaldo();
	return mayor;
}

static void cuentaMayor() {
	if(!c.isFree()) {
		float mayor=saldoMayor() ;
		for(int i=0;i<c.getSize();i++)
			if(c.getItem(i).getSaldo()==mayor)
				System.out.println(c.getItem(i));
	}
	else
		
		System.out.println("No hay cuentas existentes "); 	
}

static float saldoMenor() {
	float menor=c.getItem(0).getSaldo();
	for(int i=1;i<c.getSize();i++)
		if(c.getItem(i).getSaldo()<menor)
			menor=c.getItem(i).getSaldo();
	return menor;
}

static void cuentaMenor() {
	if(!c.isFree()) {
		float menor=saldoMenor() ;
		for(int i=0;i<c.getSize();i++)
			if(c.getItem(i).getSaldo()==menor)
				System.out.println(c.getItem(i));
	}
	else
		
		System.out.println("no hay cuentas existentes "); 	
}

  static void inicializa() {
	c=new CuentasBancarias();
}
  
  
  static void menuConsultas() {
	  int opcion=0;
	  while(true) {
		  System.out.println(" 1) Consultar total de las cuentas");  
		  
		  System.out.println(" 2) Consultar promedio de las cuentas");
		  
		  System.out.println(" 3) Consultar cuenta con un saldo mayor a los $10,000");
		  
		  System.out.println(" 4) Consultar cuenta con el mayor saldo");  
		  
		  System.out.println(" 5) Consultar cuenta con el minimo saldo"); 
		  
		  System.out.println(" 6) Exit");
		  
		  
		  opcion=lector.nextInt();
		  switch(opcion){
		  
		  	case 1:System.out.println(montoTota());break;
		  	case 2:monPromedio();break;
		  	case 3:cuenMayoraMil();break;
		  	case 4:cuentaMayor();break;
		  	case 5:cuentaMenor();break;
	     }
      }
  }
  
  
   static void menuoperaciones() throws CuentaEx, BorrarEx, RetiroEx, RetiroExcep {
	  int opcio;
	  
	  while(true) {	  
		  System.out.println(" 1) Agregar una cuenta "); 
		  System.out.println(" 2) Lista de cuentas ");  
		  System.out.println(" 3) Hacer un deposito ");  
		  System.out.println(" 4) Hacer un retiro");  
		  System.out.println(" 5) Borrar una cuenta ");  
		  System.out.println(" 6) Menu de consultas");  
		  System.out.println(" 7) Exit");
		  opcio=lector.nextInt();
		  
		  
		  switch(opcio){
		  	case 1:agregarCuenta();break;
		  	case 2:listado();break;
		  	case 3:deposito();break;
		  	case 4:retiro();break;
		  	case 5:Borrar();break;
		  	case 6:menuConsultas();break;
	     }
      }
   }
   
   
   static void run() throws CuentaEx, BorrarEx, RetiroEx, RetiroExcep {
	inicializa();
	menuoperaciones(); 
  }
}