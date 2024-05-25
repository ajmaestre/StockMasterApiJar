package main.java.com.engineerds.stockmaster.model;

public class Persona {

	private int idPersona;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private String tipo;
	
	public Persona(){
		
	}
	
	public Persona(int idPersona, String nombre, String direccion, String telefono, String email, String tipo){
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.tipo = tipo;
	}
	
	public int getIdPersona(){
		return this.idPersona;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public String getTelefono() {
		return this.telefono;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getTipo() {
		return this.tipo;
	}
		
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
