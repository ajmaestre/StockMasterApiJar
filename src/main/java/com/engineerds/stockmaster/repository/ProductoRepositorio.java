package main.java.com.engineerds.stockmaster.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.com.engineerds.stockmaster.model.Producto;

public class ProductoRepositorio extends BaseRepository<Producto>{

	@Override
	public ResultSet GetAll(Connection conexion, String tabla) {
		String query = "SELECT p.id_producto as 'producto.id', p.nombre as 'producto.nombre', "
				+ "p.descripcion as 'producto.descripcion', p.precio_unitario as 'producto.precioUnitario', "
				+ "p.cantidad as 'producto.cantidad', "
				+ "c.id_categoria as 'categoria.id', c.nombre as 'categoria.nombre', "
				+ "c.descripcion as 'categoria.descripcion' "
				+ "FROM " + tabla + " AS p "
				+ "INNER JOIN Categorias as c "
				+ "ON p.id_categoria = c.id_categoria";
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
		String query = "SELECT p.id_producto as 'producto.id', p.nombre as 'producto.nombre', "
				+ "p.descripcion as 'producto.descripcion', p.precio_unitario as 'producto.precioUnitario', "
				+ "p.cantidad as 'producto.cantidad', "
				+ "c.id_categoria as 'categoria.id', c.nombre as 'categoria.nombre', "
				+ "c.descripcion as 'categoria.descripcion' "
				+ "FROM " + tabla + " AS p "
				+ "INNER JOIN Categorias as c "
				+ "ON p.id_categoria = c.id_categoria "
				+ "WHERE p.id_categoria = " + id;
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
		String query = "SELECT p.id_producto as 'producto.id', p.nombre as 'producto.nombre', "
				+ "p.descripcion as 'producto.descripcion', p.precio_unitario as 'producto.precioUnitario', "
				+ "p.cantidad as 'producto.cantidad', "
				+ "c.id_categoria as 'categoria.id', c.nombre as 'categoria.nombre', "
				+ "c.descripcion as 'categoria.descripcion' "
				+ "FROM " + tabla + " AS p "
				+ "INNER JOIN Categorias as c "
				+ "ON p.id_categoria = c.id_categoria "
				+ "WHERE p.nombre LIKE '%" + name + "%'";
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
	public int Insert(Connection conexion, String tabla, Producto data) {
		String query = "INSERT INTO " + tabla + " (nombre, descripcion, precio_unitario, cantidad, id_categoria) VALUES ('"+
				data.getNombre() + 
				"', '" + 
				data.getDescripcion() + 
				"', " + 
				data.getPrecioUnitario() + 
				", " + 
				data.getCantidad() +
				", " + 
				data.getCategoria().getIdCategoria() +
				")";
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
		String query = "DELETE FROM " + tabla + " WHERE id_producto = " + id;
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
	public int Update(Connection conexion, String tabla, Producto data, int id) {
		String query = "UPDATE " + tabla + " SET "+
				"nombre = '" + data.getNombre() + "', "
						+ "descripcion = '" + data.getDescripcion() + "', "
								+ "precio_unitario = " + data.getPrecioUnitario() + ", "
										+ "cantidad = " + data.getCantidad() + ", "
												+ "id_categoria = " + data.getCategoria().getIdCategoria()
								+ " WHERE id_producto = " + id;
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
	
	public ResultSet GetProductsByCategoria(Connection conexion, String tabla, int id) {	
		String query = "SELECT p.id_producto as 'producto.id', p.nombre as 'producto.nombre', "
				+ "p.descripcion as 'producto.descripcion', p.precio_unitario as 'producto.precioUnitario', "
				+ "p.cantidad as 'producto.cantidad', "
				+ "c.id_categoria as 'categoria.id', c.nombre as 'categoria.nombre', "
				+ "c.descripcion as 'categoria.descripcion' "
				+ "FROM " + tabla + " AS p "
				+ "INNER JOIN Categorias as c "
				+ "ON p.id_categoria = c.id_categoria "
				+ "WHERE c.id_categoria = " + id;
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
	
	public ResultSet Count(Connection conexion, String tabla, int id) {
		String query = "SELECT cantidad FROM " + tabla + " WHERE id_producto = " + id;
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
