package main.java.com.engineerds.stockmaster.model;

public class Usuario {
	
	private int idUsuario;
	private String nombre;
	private String clave;
	private String tipo;
	
	public Usuario(){
		
	}
	
	public Usuario(int idUsuario, String nombre, String clave, String tipo){
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.clave = clave;
		this.tipo = tipo;
	}
	
	public int getIdUsuario(){
		return this.idUsuario;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getClave() {
		return this.clave;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
