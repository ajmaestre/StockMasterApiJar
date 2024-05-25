package main.java.com.engineerds.stockmaster.model;

import java.sql.Timestamp;

public class Factura {

	private int idFactura;
	private Timestamp fecha;
	private String transaccion;
	private String estado;
	private double subtotal;
	private double impuesto;
	private double total;
	private int cantidadTotal;
	private Almacen almacen;
	private Persona cliente;
	private Persona empleado;
	
	public Factura(){ 
		
	}
	
	public Factura(int idFactura, Timestamp fecha, 
					String transaccion, String estado, 
					double subtotal, double impuesto, double total, int cantidadTotal, 
					Almacen almacen, Persona cliente, Persona empleado){
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.transaccion = transaccion;
		this.estado = estado;
		this.subtotal = subtotal;
		this.impuesto = impuesto;
		this.total = total;
		this.cantidadTotal = cantidadTotal;
		this.almacen = almacen;
		this.cliente = cliente;
		this.empleado = empleado;
	}
	
	public int getIdFactura(){
		return this.idFactura;
	}
	
	public Timestamp getFecha() {
		return this.fecha;
	}
	
	public String getTransaccion() {
		return this.transaccion;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public double getSubtotal() {
		return this.subtotal;
	}
	
	public double getImpuesto() {
		return this.impuesto;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public int getCantidadTotal() {
		return this.cantidadTotal;
	}
	
	public Almacen getAlmacen(){
		return this.almacen;
	}
	
	public Persona getCliente(){
		return this.cliente;
	}
	
	public Persona getEmpleado(){
		return this.empleado;
	}
	
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	
	public void setFecha(Timestamp fecha){
		this.fecha = fecha;
	}
	
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}
	
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	
	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}
	
	public void setEmpleado(Persona empleado) {
		this.empleado = empleado;
	}
	
}
