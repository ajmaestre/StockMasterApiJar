package main.java.com.engineerds.stockmaster.model;

public class Almacen {

	private int idAlmacen;
	private String nombre;
	private String descripcion;
	private String direccion;
	private String telefono;
	
	public Almacen(){
		
	}
	
	public Almacen(int idAlmacen, String nombre, String descripcion, String direccion, String telefono){
		this.idAlmacen = idAlmacen;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	
	public int getIdAlmacen(){
		return this.idAlmacen;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public String getTelefono() {
		return this.telefono;
	}
		
	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
