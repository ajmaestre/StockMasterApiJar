package main.java.com.engineerds.stockmaster.repository;

import java.sql.Connection;
import java.sql.ResultSet;


public abstract class BaseRepository<T> {

	public abstract ResultSet GetAll(Connection conexion, String tabla);
	
	public abstract ResultSet GetAll(Connection conexion, String tabla, String type);
	
	public abstract ResultSet Get(Connection conexion, String tabla, int id);
	
	public abstract ResultSet GetByName(Connection conexion, String tabla, String name);
	
	public abstract ResultSet GetByName(Connection conexion, String tabla, String name, String type);

	public abstract int Insert(Connection conexion, String tabla, T data);
	
	public abstract int Delete(Connection conexion, String tabla, int id);
	
	public abstract int Update(Connection conexion, String tabla, T data, int id);
	
	public abstract ResultSet GetLastInsert(Connection conexion);

}
