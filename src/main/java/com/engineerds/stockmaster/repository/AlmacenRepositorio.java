package main.java.com.engineerds.stockmaster.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.com.engineerds.stockmaster.model.Almacen;

public class AlmacenRepositorio extends BaseRepository<Almacen>{

	@Override
	public ResultSet GetAll(Connection conexion, String tabla) {
		String query = "SELECT * FROM " + tabla; 
		Statement stmt;
		ResultSet result;
		try {
			stmt = conexion.createStatement();
			result = stmt.executeQuery(query);
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResultSet GetAll(Connection conexion, String tabla, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet Get(Connection conexion, String tabla, int id) {
		String query = "SELECT * FROM " + tabla + " WHERE id_almacen = " + id;
		Statement stmt;
		ResultSet result;
		try {
			stmt = conexion.createStatement();
			result = stmt.executeQuery(query);
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResultSet GetByName(Connection conexion, String tabla, String name) {
		String query = "SELECT * FROM " + tabla + " WHERE nombre LIKE '%" + name + "%'";
		Statement stmt;
		ResultSet result;
		try {
			stmt = conexion.createStatement();
			result = stmt.executeQuery(query);
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResultSet GetByName(Connection conexion, String tabla, String name, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Insert(Connection conexion, String tabla, Almacen data) {
		String query = "INSERT INTO " + tabla + " (nombre, descripcion, direccion, telefono) VALUES ('"+
				data.getNombre() + 
				"', '" + 
				data.getDescripcion() + 
				"', '" + 
				data.getDireccion() + 
				"', '" + 
				data.getTelefono() + 
				"')";
		Statement stmt;
		int result;
		try {
			stmt = conexion.createStatement();
			result = stmt.executeUpdate(query);
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public int Delete(Connection conexion, String tabla, int id) {
		String query = "DELETE FROM " + tabla + " WHERE id_almacen = " + id;
		Statement stmt;
		int result;
		try {
			stmt = conexion.createStatement();
			result = stmt.executeUpdate(query);
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public int Update(Connection conexion, String tabla, Almacen data, int id) {
		String query = "UPDATE " + tabla + " SET "+
				"nombre = '" + data.getNombre() + "', "
						+ "descripcion = '" + data.getDescripcion() + "', "
						+ "direccion = '" + data.getDireccion() + "', "
								+ "telefono = '" + data.getTelefono() + "'"
								+ " WHERE id_almacen = " + id;
		Statement stmt;
		int result;
		try {
			stmt = conexion.createStatement();
			result = stmt.executeUpdate(query);
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResultSet GetLastInsert(Connection conexion) {
		String query = "SELECT LAST_INSERT_ID()";
		Statement stmt;
		ResultSet result;
		try {
			stmt = conexion.createStatement();
			result = stmt.executeQuery(query);
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

}
