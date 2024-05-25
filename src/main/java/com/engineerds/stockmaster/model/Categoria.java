package main.java.com.engineerds.stockmaster.model;

public class Categoria {
	
	private int idCategoria;
	private String nombre;
	private String descripcion;
	
	public Categoria(){
		
	}
	
	public Categoria(int idCategoria, String nombre, String descripcion){
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public int getIdCategoria(){
		return this.idCategoria;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
    public String toString() {
        return "Categoria{" +
               "id=" + this.idCategoria +
               ", nombre='" + this.nombre + '\'' +
               ", descripcion='" + this.descripcion + '\'' +
               '}';
    }

}
