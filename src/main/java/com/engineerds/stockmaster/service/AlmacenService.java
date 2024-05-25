package main.java.com.engineerds.stockmaster.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.com.engineerds.stockmaster.model.Almacen;
import main.java.com.engineerds.stockmaster.repository.AlmacenRepositorio;
import main.java.com.engineerds.stockmaster.repository.DatabaseConnection;


public class AlmacenService {

	private ArrayList<Almacen> almacenes = new ArrayList<>();
	private Almacen almacen;
	private String tabla = "Almacenes";
	private AlmacenRepositorio almacenRepositorio = new AlmacenRepositorio();
	
	
	public AlmacenService(){}
	
	public ArrayList<Almacen> getAlmacenes() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = almacenRepositorio.GetAll(db, tabla);
		try {
			almacenes.clear();
			while(result.next()) {
				almacen = new Almacen();
				almacen.setIdAlmacen(result.getInt("id_almacen"));
				almacen.setNombre(result.getString("nombre"));
				almacen.setDescripcion(result.getString("descripcion"));
				almacen.setDireccion(result.getString("direccion"));
				almacen.setTelefono(result.getString("telefono"));
				almacenes.add(almacen);
			}
			return	almacenes;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
		
	}
	
	public ArrayList<Almacen> getAlmacenesByName(String name) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = almacenRepositorio.GetByName(db, tabla, name);
		try {
			almacenes.clear();
			while(result.next()) {
				almacen = new Almacen();
				almacen.setIdAlmacen(result.getInt("id_almacen"));
				almacen.setNombre(result.getString("nombre"));
				almacen.setDescripcion(result.getString("descripcion"));
				almacen.setDireccion(result.getString("direccion"));
				almacen.setTelefono(result.getString("telefono"));
				almacenes.add(almacen);
			}
			return	almacenes;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}

	public Almacen getAlmacen(int id) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = almacenRepositorio.Get(db, tabla, id);
		try {
			result.next();
			almacen = new Almacen();
			almacen.setIdAlmacen(result.getInt("id_almacen"));
			almacen.setNombre(result.getString("nombre"));
			almacen.setDescripcion(result.getString("descripcion"));
			almacen.setDireccion(result.getString("direccion"));
			almacen.setTelefono(result.getString("telefono"));
			return almacen;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public int guardarAlmacen(Almacen almacen) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = almacenRepositorio.Insert(db, tabla, almacen);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int eliminarAlmacen(int id) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = almacenRepositorio.Delete(db, tabla, id);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int editarAlmacen(Almacen almacen, int id) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = almacenRepositorio.Update(db, tabla, almacen, id);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}

}
