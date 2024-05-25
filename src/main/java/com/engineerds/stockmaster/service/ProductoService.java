package main.java.com.engineerds.stockmaster.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.com.engineerds.stockmaster.model.Categoria;
import main.java.com.engineerds.stockmaster.model.Producto;
import main.java.com.engineerds.stockmaster.repository.DatabaseConnection;
import main.java.com.engineerds.stockmaster.repository.ProductoRepositorio;


public class ProductoService {

	private Categoria categoria;
	private ArrayList<Producto> productos = new ArrayList<>();
	private Producto producto;
	private String tabla = "Productos";
	private ProductoRepositorio productoRepositorio = new ProductoRepositorio();
	
	
	public ProductoService(){}
	
	public ArrayList<Producto> getProductos() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = productoRepositorio.GetAll(db, tabla);
		try {
			productos.clear();
			while(result.next()) {
				producto = new Producto();
				categoria = new Categoria();
				producto.setIdProducto(result.getInt("producto.id"));
				producto.setNombre(result.getString("producto.nombre"));
				producto.setDescripcion(result.getString("producto.descripcion"));
				producto.setPrecioUnitario(result.getDouble("producto.precioUnitario"));
				producto.setCantidad(result.getInt("producto.cantidad"));
				categoria.setIdCategoria(result.getInt("categoria.id"));
				categoria.setNombre(result.getString("categoria.nombre"));
				categoria.setDescripcion(result.getString("categoria.descripcion"));
				producto.setCategoria(categoria);
				productos.add(producto);
			}
			return productos;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public int getProductosByCategoria(int idCategoria) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = productoRepositorio.GetProductsByCategoria(db, tabla, idCategoria);
		int count = 0;
		try {
			while(result.next()) {
				count++;
			}
			return count;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}

	public Producto getProducto(int id) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = productoRepositorio.Get(db, tabla, id);
		try {
			result.next();
			producto = new Producto();
			categoria = new Categoria();
			producto.setIdProducto(result.getInt("producto.id"));
			producto.setNombre(result.getString("producto.nombre"));
			producto.setDescripcion(result.getString("producto.descripcion"));
			producto.setPrecioUnitario(result.getDouble("producto.precioUnitario"));
			producto.setCantidad(result.getInt("producto.cantidad"));
			categoria.setIdCategoria(result.getInt("categoria.id"));
			categoria.setNombre(result.getString("categoria.nombre"));
			categoria.setDescripcion(result.getString("categoria.descripcion"));
			producto.setCategoria(categoria);
			return producto;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}	
	}
	
	public ArrayList<Producto> getProductosByName(String name) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = productoRepositorio.GetByName(db, tabla, name);
		try {
			productos.clear();
			while(result.next()) {
				producto = new Producto();
				categoria = new Categoria();
				producto.setIdProducto(result.getInt("producto.id"));
				producto.setNombre(result.getString("producto.nombre"));
				producto.setDescripcion(result.getString("producto.descripcion"));
				producto.setPrecioUnitario(result.getDouble("producto.precioUnitario"));
				producto.setCantidad(result.getInt("producto.cantidad"));
				categoria.setIdCategoria(result.getInt("categoria.id"));
				categoria.setNombre(result.getString("categoria.nombre"));
				categoria.setDescripcion(result.getString("categoria.descripcion"));
				producto.setCategoria(categoria);
				productos.add(producto);
			}
			return productos;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public Producto getLastProducto() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = productoRepositorio.GetLastInsert(db);
		try {
			result.next();
			producto = new Producto();
			categoria = new Categoria();
			producto.setIdProducto(result.getInt("producto.id"));
			producto.setNombre(result.getString("producto.nombre"));
			producto.setDescripcion(result.getString("producto.descripcion"));
			producto.setPrecioUnitario(result.getDouble("producto.precioUnitario"));
			producto.setCantidad(result.getInt("producto.cantidad"));
			categoria.setIdCategoria(result.getInt("categoria.id"));
			categoria.setNombre(result.getString("categoria.nombre"));
			categoria.setDescripcion(result.getString("categoria.descripcion"));
			producto.setCategoria(categoria);
			return producto;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public int guardarProducto(Producto producto) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = productoRepositorio.Insert(db, tabla, producto);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int eliminarProducto(int id) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = productoRepositorio.Delete(db, tabla, id);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int editarProducto(Producto producto, int id) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = productoRepositorio.Update(db, tabla, producto, id);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int getCantidad(int id) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = productoRepositorio.Count(db, tabla, id);
		try {
			result.next();
			int cantidad = 0;
			cantidad = result.getInt("cantidad");
			return cantidad;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}	
	}

}
