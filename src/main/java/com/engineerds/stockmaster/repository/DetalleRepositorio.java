package main.java.com.engineerds.stockmaster.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.com.engineerds.stockmaster.model.Detalle;

public class DetalleRepositorio extends BaseRepository<Detalle>{

	@Override
	public ResultSet GetAll(Connection conexion, String tabla) {
		String query = "SELECT "
				+ "d.id_detalle as 'detalle.id', "
				+ "d.cantidad as 'detalle.cantidad', "
				+ "d.precio_unitario as 'detalle.precioUnitario', "
				+ "d.precio_total as 'detalle.precioTotal', "
				+ "f.id_factura as 'factura.id', " 
				+ "f.fecha as 'factura.fecha', "
				+ "f.transaccion as 'factura.transaccion', "
				+ "f.estado as 'factura.estado', "
				+ "f.subtotal as 'factura.subtotal', "
				+ "f.impuesto as 'factura.impuesto', "
				+ "f.total as 'factura.total', "
				+ "f.cantidad_total as 'factura.cantidadTotal', "
				+ "p.id_producto as 'producto.id', "
				+ "p.nombre as 'producto.nombre', "
				+ "p.descripcion as 'producto.descripcion', "
				+ "p.precio_unitario as 'producto.precioUnitario', "
				+ "p.cantidad as 'producto.cantidad' "
				+ "FROM " + tabla + " AS d "
				+ "INNER JOIN Facturas AS f ON d.id_factura = f.id_factura "
				+ "INNER JOIN Productos AS p ON d.id_producto = p.id_producto"
				+ "";
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
		String query = "SELECT "
				+ "d.id_detalle as 'detalle.id', "
				+ "d.cantidad as 'detalle.cantidad', "
				+ "d.precio_unitario as 'detalle.precioUnitario', "
				+ "d.precio_total as 'detalle.precioTotal', "
				+ "f.id_factura as 'factura.id', " 
				+ "f.fecha as 'factura.fecha', "
				+ "f.transaccion as 'factura.transaccion', "
				+ "f.estado as 'factura.estado', "
				+ "f.subtotal as 'factura.subtotal', "
				+ "f.impuesto as 'factura.impuesto', "
				+ "f.total as 'factura.total', "
				+ "f.cantidad_total as 'factura.cantidadTotal', "
				+ "p.id_producto as 'producto.id', "
				+ "p.nombre as 'producto.nombre', "
				+ "p.descripcion as 'producto.descripcion', "
				+ "p.precio_unitario as 'producto.precioUnitario', "
				+ "p.cantidad as 'producto.cantidad' "
				+ "FROM " + tabla + " AS d "
				+ "INNER JOIN Facturas AS f ON d.id_factura = f.id_factura "
				+ "INNER JOIN Productos AS p ON d.id_producto = p.id_producto"
				+ " WHERE id_detalle = " + id;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet GetByName(Connection conexion, String tabla, String name, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Insert(Connection conexion, String tabla, Detalle data) {
		String query = "INSERT INTO " + tabla + " (cantidad, precio_unitario, precio_total, id_factura, id_producto) VALUES ('"+
				data.getCantidad() + 
				"', " + 
				data.getPrecioUnitario() + 
				", " +
				data.getPrecioTotal() + 
				", " +
				data.getFactura().getIdFactura() + 
				", " +
				data.getProducto().getIdProducto() +
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
		String query = "DELETE FROM " + tabla + " WHERE idDetalle = " + id;
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
	public int Update(Connection conexion, String tabla, Detalle data, int id) {
		String query = "UPDATE " + tabla + " SET "+
				"cantidad = '" + data.getCantidad() + "', "
						+ "precio_unitario = " + data.getPrecioUnitario() + ", "
								+ "precio_total = " + data.getPrecioTotal() + ", "
								+ "id_factura = " + data.getFactura().getIdFactura() + ", "
										+ "id_producto = " + data.getProducto().getIdProducto()
								+ " WHERE id_detalle = " + id;
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
	
	public ResultSet GetDetallesByCliente(Connection conexion, String tabla, int id) {
		String query = "SELECT "
				+ "d.id_detalle as 'detalle.id', "
				+ "d.cantidad as 'detalle.cantidad', "
				+ "d.precio_unitario as 'detalle.precioUnitario', "
				+ "d.precio_total as 'detalle.precioTotal', "
				+ "f.id_factura as 'factura.id', " 
				+ "f.fecha as 'factura.fecha', "
				+ "f.transaccion as 'factura.transaccion', "
				+ "f.estado as 'factura.estado', "
				+ "f.subtotal as 'factura.subtotal', "
				+ "f.impuesto as 'factura.impuesto', "
				+ "f.total as 'factura.total', "
				+ "f.cantidad_total as 'factura.cantidadTotal', "
				+ "p.id_producto as 'producto.id', "
				+ "p.nombre as 'producto.nombre', "
				+ "p.descripcion as 'producto.descripcion', "
				+ "p.precio_unitario as 'producto.precioUnitario', "
				+ "p.cantidad as 'producto.cantidad' "
				+ "FROM " + tabla + " AS d "
				+ "INNER JOIN Facturas AS f ON d.id_factura = f.id_factura "
				+ "INNER JOIN Productos AS p ON d.id_producto = p.id_producto"
				+ " WHERE id_cliente = " + id;
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
	
	public ResultSet GetDetallesByFactura(Connection conexion, String tabla, int id){
		String query = "SELECT "
				+ "d.id_detalle as 'detalle.id', "
				+ "d.cantidad as 'detalle.cantidad', "
				+ "d.precio_unitario as 'detalle.precioUnitario', "
				+ "d.precio_total as 'detalle.precioTotal', "
				+ "f.id_factura as 'factura.id', " 
				+ "f.fecha as 'factura.fecha', "
				+ "f.transaccion as 'factura.transaccion', "
				+ "f.estado as 'factura.estado', "
				+ "f.subtotal as 'factura.subtotal', "
				+ "f.impuesto as 'factura.impuesto', "
				+ "f.total as 'factura.total', "
				+ "f.cantidad_total as 'factura.cantidadTotal', "
				+ "p.id_producto as 'producto.id', "
				+ "p.nombre as 'producto.nombre', "
				+ "p.descripcion as 'producto.descripcion', "
				+ "p.precio_unitario as 'producto.precioUnitario', "
				+ "p.cantidad as 'producto.cantidad' "
				+ "FROM " + tabla + " AS d "
				+ "INNER JOIN Facturas AS f ON d.id_factura = f.id_factura "
				+ "INNER JOIN Productos AS p ON d.id_producto = p.id_producto"
				+ " WHERE d.id_factura = " + id;
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
	
	public ResultSet GetLastDetalle(Connection conexion, String tabla){	
		String query = "SELECT "
				+ "d.id_detalle as 'detalle.id', "
				+ "d.cantidad as 'detalle.cantidad', "
				+ "d.precio_unitario as 'detalle.precioUnitario', "
				+ "d.precio_total as 'detalle.precioTotal', "
				+ "f.id_factura as 'factura.id', " 
				+ "f.fecha as 'factura.fecha', "
				+ "f.transaccion as 'factura.transaccion', "
				+ "f.estado as 'factura.estado', "
				+ "f.subtotal as 'factura.subtotal', "
				+ "f.impuesto as 'factura.impuesto', "
				+ "f.total as 'factura.total', "
				+ "f.cantidad_total as 'factura.cantidadTotal', "
				+ "p.id_producto as 'producto.id', "
				+ "p.nombre as 'producto.nombre', "
				+ "p.descripcion as 'producto.descripcion', "
				+ "p.precio_unitario as 'producto.precioUnitario', "
				+ "p.cantidad as 'producto.cantidad' "
				+ "FROM " + tabla + " AS d "
				+ "INNER JOIN Facturas AS f ON d.id_factura = f.id_factura "
				+ "INNER JOIN Productos AS p ON d.id_producto = p.id_producto"
				+ " ORDER BY id_detalle DESC LIMIT 1";
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
