package main.java.com.engineerds.stockmaster.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.com.engineerds.stockmaster.model.Categoria;
import main.java.com.engineerds.stockmaster.repository.CategoriaRepositorio;
import main.java.com.engineerds.stockmaster.repository.DatabaseConnection;


public class CategoriaService {

	private ArrayList<Categoria> categorias = new ArrayList<>();
	private Categoria categoria;
	private String tabla = "Categorias";
	private CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorio();
	
	
	public CategoriaService(){}
	
	public ArrayList<Categoria> getCategorias() {	
		ResultSet result;
		Connection conexion = DatabaseConnection.connectDatabase();
		result = categoriaRepositorio.GetAll(conexion, tabla);
		try {
			categorias.clear();
			while(result.next()) {
				categoria = new Categoria();
				categoria.setIdCategoria(result.getInt("id_categoria"));
				categoria.setNombre(result.getString("nombre"));
				categoria.setDescripcion(result.getString("descripcion"));
				categorias.add(categoria);
			}
			return categorias;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			DatabaseConnection.desconnectDatabase(conexion);
		}
	}
	
	public ArrayList<Categoria> getCategoriasByName(String name) {	
		ResultSet result;
		Connection conexion = DatabaseConnection.connectDatabase();
		result = categoriaRepositorio.GetByName(conexion, tabla, name);	
		try {		
			categorias.clear();		
			while(result.next()) {
				categoria = new Categoria();
				categoria.setIdCategoria(result.getInt("id_categoria"));
				categoria.setNombre(result.getString("nombre"));
				categoria.setDescripcion(result.getString("descripcion"));
				categorias.add(categoria);
			}	
			return categorias;	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			DatabaseConnection.desconnectDatabase(conexion);
		}
	}
	
	public Categoria getCategoria(int id) {
		ResultSet result;
		Connection conexion = DatabaseConnection.connectDatabase();
		result = categoriaRepositorio.Get(conexion, tabla, id);
		try {
			result.next();
			categoria = new Categoria();
			categoria.setIdCategoria(result.getInt("id_categoria"));
			categoria.setNombre(result.getString("nombre"));
			categoria.setDescripcion(result.getString("descripcion"));
			return categoria;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			DatabaseConnection.desconnectDatabase(conexion);
		}
	}
	
	public int guardarCategoria(Categoria categoria) {
		int result;
		Connection conexion = DatabaseConnection.connectDatabase();
		result = categoriaRepositorio.Insert(conexion, tabla, categoria);
		DatabaseConnection.desconnectDatabase(conexion);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int eliminarCategoria(int id) {
		int result;
		Connection conexion = DatabaseConnection.connectDatabase();
		result = categoriaRepositorio.Delete(conexion, tabla, id);
		DatabaseConnection.desconnectDatabase(conexion);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int editarCategoria(Categoria categoria, int id) {
		int result;
		Connection conexion = DatabaseConnection.connectDatabase();
		result = categoriaRepositorio.Update(conexion, tabla, categoria, id);
		DatabaseConnection.desconnectDatabase(conexion);
		if(result == 1) {
			return result;
		}
		return 0;
	}

}
