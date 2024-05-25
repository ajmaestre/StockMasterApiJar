package main.java.com.engineerds.stockmaster.model;

public class Detalle {

	private int idDetalle;
	private int cantidad;
	private double precioUnitario;
	private double precioTotal;
	private Factura factura;
	private Producto producto;
	
	public Detalle(){
		
	}
	
	public Detalle(int idDetalle, int cantidad, double precioUnitario, 
					double precioTotal, Factura factura, Producto producto){
		this.idDetalle = idDetalle;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.precioTotal = precioTotal;
		this.factura = factura;
		this.producto = producto;
	}
	
	public int getIdDetalle(){
		return this.idDetalle;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public double getPrecioUnitario() {
		return this.precioUnitario;
	}
	
	public double getPrecioTotal() {
		return this.precioTotal;
	}
	
	public Factura getFactura(){
		return this.factura;
	}
	
	public Producto getProducto(){
		return this.producto;
	}
	
	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
