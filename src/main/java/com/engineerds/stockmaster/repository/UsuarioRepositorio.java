package main.java.com.engineerds.stockmaster.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.com.engineerds.stockmaster.model.Usuario;

public class UsuarioRepositorio extends BaseRepository<Usuario>{
	
	
	public ResultSet GetUsuario(Connection conexion, String tabla, Usuario usuario){
		String query = "SELECT * FROM " + tabla + " WHERE nombre = '" + usuario.getNombre() + "' AND clave = '" + usuario.getClave() + "' ";
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
		String query = "SELECT * FROM " + tabla + " WHERE type = '" + type + "'";
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
	public ResultSet Get(Connection conexion, String tabla, int id) {
		String query = "SELECT * FROM " + tabla + " WHERE id_usuario = " + id;
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
		String query = "SELECT * FROM " + tabla + " WHERE type = '" + type + "' AND " + "nombre LIKE '%" + name + "%'";
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
	public int Insert(Connection conexion, String tabla, Usuario data) {
		String query = "INSERT INTO " + tabla + " (nombre, clave, tipo) VALUES ('"+
				data.getNombre() + "', '" + data.getClave() + "', '" + data.getTipo() + "')";
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
		String query = "DELETE FROM " + tabla + " WHERE id_usuario = " + id;
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
	public int Update(Connection conexion, String tabla, Usuario data, int id) {
		String query = "UPDATE " + tabla + " SET "+
				"nombre = '" + data.getNombre() + "', "
						+ "clave = '" + data.getClave() + "', "
						+ "tipo = '" + data.getTipo() + "'"
								+ "WHERE id_usuario = " + id;
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
