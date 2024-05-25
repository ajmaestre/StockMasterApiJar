package main.java.com.engineerds.stockmaster.model;

public class Producto {
	
	private int idProducto;
	private String nombre;
	private String descripcion;
	private double precioUnitario;
	private int cantidad;
	private Categoria categoria;
	
	public Producto(){
		
	}
	
	public Producto(int idProducto, String nombre, String descripcion, double precioUnitario, int cantidad, Categoria categoria){
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.categoria = categoria;
	}
	
	public int getIdProducto(){
		return this.idProducto;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public double getPrecioUnitario() {
		return this.precioUnitario;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public Categoria getCategoria(){
		return this.categoria;
	}
	
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
