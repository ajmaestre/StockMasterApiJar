package main.java.com.engineerds.stockmaster.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.com.engineerds.stockmaster.model.Detalle;
import main.java.com.engineerds.stockmaster.model.Factura;
import main.java.com.engineerds.stockmaster.model.Producto;
import main.java.com.engineerds.stockmaster.repository.DatabaseConnection;
import main.java.com.engineerds.stockmaster.repository.DetalleRepositorio;


public class DetalleService {

	ArrayList<Detalle> detalles = new ArrayList<>();
	public Detalle detalle;
	ArrayList<Producto> productos = new ArrayList<>();
	public Producto producto;
	ArrayList<Factura> facturas = new ArrayList<>();
	public Factura factura;
	private String tabla = "Detalles";
	private DetalleRepositorio detalleRepositorio = new DetalleRepositorio();
	
	
	public DetalleService(){}
	
	public ArrayList<Detalle> getDetalles() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = detalleRepositorio.GetAll(db, tabla);
		try {
			detalles.clear();
			while(result.next()) {
				detalle = new Detalle();
				factura = new Factura();
				producto = new Producto();
				detalle.setIdDetalle(result.getInt("id_detalle"));
				detalle.setCantidad(result.getInt("cantidad"));
				detalle.setPrecioUnitario(result.getDouble("precioUnitario"));
				detalle.setPrecioTotal(result.getDouble("precioTotal"));
				factura.setIdFactura(result.getInt("factura.id"));
				factura.setFecha(result.getTimestamp("factura.fecha"));
				factura.setTransaccion(result.getString("factura.transaccion"));
				factura.setEstado(result.getString("factura.estado"));
				factura.setSubtotal(result.getDouble("factura.subtotal"));
				factura.setImpuesto(result.getDouble("factura.impuesto"));
				factura.setTotal(result.getDouble("factura.total"));
				factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
				detalle.setFactura(factura);
				producto.setIdProducto(result.getInt("producto.id"));
				producto.setNombre(result.getString("producto.nombre"));
				producto.setDescripcion(result.getString("producto.descripcion"));
				producto.setPrecioUnitario(result.getDouble("producto.precioUnitario"));
				producto.setCantidad(result.getInt("producto.cantidad"));
				detalle.setProducto(producto);
				detalles.add(detalle);
			}
			return detalles;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}

	public int getDetallesByCliente(int idCliente) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = detalleRepositorio.GetDetallesByCliente(db, tabla, idCliente);
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

	public Detalle getDetalle(int id) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = detalleRepositorio.Get(db, tabla, id);
		try {
			result.next();
			detalle = new Detalle();
			factura = new Factura();
			producto = new Producto();
			detalle.setIdDetalle(result.getInt("id_categoria"));
			detalle.setCantidad(result.getInt("cantidad"));
			detalle.setPrecioUnitario(result.getDouble("precioUnitario"));
			detalle.setPrecioTotal(result.getDouble("precioTotal"));
			factura.setIdFactura(result.getInt("factura.id"));
			factura.setFecha(result.getTimestamp("factura.fecha"));
			factura.setTransaccion(result.getString("factura.transaccion"));
			factura.setEstado(result.getString("factura.estado"));
			factura.setSubtotal(result.getDouble("factura.subtotal"));
			factura.setImpuesto(result.getDouble("factura.impuesto"));
			factura.setTotal(result.getDouble("factura.total"));
			factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
			detalle.setFactura(factura);
			producto.setIdProducto(result.getInt("producto.id"));
			producto.setNombre(result.getString("producto.nombre"));
			producto.setDescripcion(result.getString("producto.descripcion"));
			producto.setPrecioUnitario(result.getDouble("producto.precioUnitario"));
			producto.setCantidad(result.getInt("producto.cantidad"));
			detalle.setProducto(producto);
			return detalle;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Detalle> getDetallesByFactura(int id) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = detalleRepositorio.GetDetallesByFactura(db, tabla, id);
		try {
			detalles.clear();
			while(result.next()) {
				detalle = new Detalle();
				factura = new Factura();
				producto = new Producto();
				detalle.setIdDetalle(result.getInt("detalle.id"));
				detalle.setCantidad(result.getInt("detalle.cantidad"));
				detalle.setPrecioUnitario(result.getDouble("detalle.precioUnitario"));
				detalle.setPrecioTotal(result.getDouble("detalle.precioTotal"));
				factura.setIdFactura(result.getInt("factura.id"));
				factura.setFecha(result.getTimestamp("factura.fecha"));
				factura.setTransaccion(result.getString("factura.transaccion"));
				factura.setEstado(result.getString("factura.estado"));
				factura.setSubtotal(result.getDouble("factura.subtotal"));
				factura.setImpuesto(result.getDouble("factura.impuesto"));
				factura.setTotal(result.getDouble("factura.total"));
				factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
				detalle.setFactura(factura);
				producto.setIdProducto(result.getInt("producto.id"));
				producto.setNombre(result.getString("producto.nombre"));
				producto.setDescripcion(result.getString("producto.descripcion"));
				producto.setPrecioUnitario(result.getDouble("producto.precioUnitario"));
				producto.setCantidad(result.getInt("producto.cantidad"));
				detalle.setProducto(producto);
				detalles.add(detalle);
			}
			return detalles;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public Detalle getLastDetalle() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = detalleRepositorio.GetLastDetalle(db, tabla);
		try {
			result.next();
			detalle = new Detalle();
			factura = new Factura();
			producto = new Producto();
			detalle.setIdDetalle(result.getInt("id_categoria"));
			detalle.setCantidad(result.getInt("cantidad"));
			detalle.setPrecioUnitario(result.getDouble("precioUnitario"));
			detalle.setPrecioTotal(result.getDouble("precioTotal"));
			factura.setIdFactura(result.getInt("factura.id"));
			factura.setFecha(result.getTimestamp("factura.fecha"));
			factura.setTransaccion(result.getString("factura.transaccion"));
			factura.setEstado(result.getString("factura.estado"));
			factura.setSubtotal(result.getDouble("factura.subtotal"));
			factura.setImpuesto(result.getDouble("factura.impuesto"));
			factura.setTotal(result.getDouble("factura.total"));
			factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
			detalle.setFactura(factura);
			producto.setIdProducto(result.getInt("producto.id"));
			producto.setNombre(result.getString("producto.nombre"));
			producto.setDescripcion(result.getString("producto.descripcion"));
			producto.setPrecioUnitario(result.getDouble("producto.precioUnitario"));
			producto.setCantidad(result.getInt("producto.cantidad"));
			detalle.setProducto(producto);
			return detalle;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public int guardarListDetails(ArrayList<Detalle> detalles, Factura factura) {
		int result = 0;
		for(Detalle detalle: detalles) {
			detalle.setFactura(factura);
			result = guardarDetalle(detalle);
		}
		return result;
	}
	
	public int guardarDetalle(Detalle detalle) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = detalleRepositorio.Insert(db, tabla, detalle);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int eliminarDetalle(int id) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = detalleRepositorio.Delete(db, tabla, id);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int editarDetalle(Detalle detalle, int id) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = detalleRepositorio.Update(db, tabla, detalle, id);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}

}
