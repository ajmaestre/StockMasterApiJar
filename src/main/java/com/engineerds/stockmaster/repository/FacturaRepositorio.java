package main.java.com.engineerds.stockmaster.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import main.java.com.engineerds.stockmaster.model.Factura;

public class FacturaRepositorio extends BaseRepository<Factura>{

	@Override
	public ResultSet GetAll(Connection conexion, String tabla) {
		String query = "SELECT " 
				+ "f.id_factura as 'factura.id', "
				+ "f.fecha as 'factura.fecha', "
				+ "f.transaccion as 'factura.transaccion', "
				+ "f.estado as 'factura.estado', "
				+ "f.subtotal as 'factura.subtotal', "
				+ "f.impuesto as 'factura.impuesto', "
				+ "f.total as 'factura.total', "
				+ "f.cantidad_total as 'factura.cantidadTotal', "
				+ "a.id_almacen as 'almacen.id', "
				+ "a.nombre as 'almacen.nombre', "
				+ "a.descripcion as 'almacen.descripcion', "
				+ "a.direccion as 'almacen.direccion', "
				+ "a.telefono as 'almacen.telefono', "
				+ "c.id_persona as 'cliente.id', "
				+ "c.nombre as 'cliente.nombre', "
				+ "c.direccion as 'cliente.direccion', "
				+ "c.telefono as 'cliente.telefono', "
				+ "c.email as 'cliente.email', "
				+ "c.tipo as 'cliente.tipo', "
				+ "e.id_persona as 'empleado.id', "
				+ "e.nombre as 'empleado.nombre', "
				+ "e.direccion as 'empleado.direccion', "
				+ "e.telefono as 'empleado.telefono', "
				+ "e.email as 'empleado.email', "
				+ "e.tipo as 'empleado.tipo' "
				+ "FROM " + tabla + " AS f "
				+ "INNER JOIN Almacenes AS a ON f.id_almacen = a.id_almacen "
				+ "INNER JOIN Personas AS c ON f.id_cliente = c.id_persona "
				+ "INNER JOIN Personas AS e ON f.id_empleado = e.id_persona "
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
		String query = "SELECT "
				+ "f.id_factura as 'factura.id', "
				+ "f.fecha as 'factura.fecha', "
				+ "f.transaccion as 'factura.transaccion', "
				+ "f.estado as 'factura.estado', "
				+ "f.subtotal as 'factura.subtotal', "
				+ "f.impuesto as 'factura.impuesto', "
				+ "f.total as 'factura.total', "
				+ "f.cantidad_total as 'factura.cantidadTotal', "
				+ "a.id_almacen as 'almacen.id', "
				+ "a.nombre as 'almacen.nombre', "
				+ "a.descripcion as 'almacen.descripcion', "
				+ "a.direccion as 'almacen.direccion', "
				+ "a.telefono as 'almacen.telefono', "
				+ "c.id_persona as 'cliente.id', "
				+ "c.nombre as 'cliente.nombre', "
				+ "c.direccion as 'cliente.direccion', "
				+ "c.telefono as 'cliente.telefono', "
				+ "c.email as 'cliente.email', "
				+ "c.tipo as 'cliente.tipo', "
				+ "e.id_persona as 'empleado.id', "
				+ "e.nombre as 'empleado.nombre', "
				+ "e.direccion as 'empleado.direccion', "
				+ "e.telefono as 'empleado.telefono', "
				+ "e.email as 'empleado.email', "
				+ "e.tipo as 'empleado.tipo' "
				+ "FROM " + tabla + " AS f "
				+ "INNER JOIN Almacenes AS a ON f.id_almacen = a.id_almacen "
				+ "INNER JOIN Personas AS c ON f.id_cliente = c.id_persona "
				+ "INNER JOIN Personas AS e ON f.id_empleado = e.id_persona "
				+ " WHERE f.transaccion = '" + type + "'";
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
		String query = "SELECT "
				+ "f.id_factura as 'factura.id', "
				+ "f.fecha as 'factura.fecha', "
				+ "f.transaccion as 'factura.transaccion', "
				+ "f.estado as 'factura.estado', "
				+ "f.subtotal as 'factura.subtotal', "
				+ "f.impuesto as 'factura.impuesto', "
				+ "f.total as 'factura.total', "
				+ "f.cantidad_total as 'factura.cantidadTotal', "
				+ "a.id_almacen as 'almacen.id', "
				+ "a.nombre as 'almacen.nombre', "
				+ "a.descripcion as 'almacen.descripcion', "
				+ "a.direccion as 'almacen.direccion', "
				+ "a.telefono as 'almacen.telefono', "
				+ "c.id_persona as 'cliente.id', "
				+ "c.nombre as 'cliente.nombre', "
				+ "c.direccion as 'cliente.direccion', "
				+ "c.telefono as 'cliente.telefono', "
				+ "c.email as 'cliente.email', "
				+ "c.tipo as 'cliente.tipo', "
				+ "e.id_persona as 'empleado.id', "
				+ "e.nombre as 'empleado.nombre', "
				+ "e.direccion as 'empleado.direccion', "
				+ "e.telefono as 'empleado.telefono', "
				+ "e.email as 'empleado.email', "
				+ "e.tipo as 'empleado.tipo' "
				+ "FROM " + tabla + " AS f "
				+ "INNER JOIN Almacenes AS a ON f.id_almacen = a.id_almacen "
				+ "INNER JOIN Personas AS c ON f.id_cliente = c.id_persona "
				+ "INNER JOIN Personas AS e ON f.id_empleado = e.id_persona "
				+ " WHERE id_factura = " + id;
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
	public int Insert(Connection conexion, String tabla, Factura factura) {
		String query = "INSERT INTO " + tabla + 
				" (fecha, transaccion, estado, subtotal, impuesto, "
				+ "total, cantidad_total, id_almacen, id_cliente, id_empleado) "
				+ "VALUES ('"+
						factura.getFecha() + "', '" + 
						factura.getTransaccion() + "', '" + 
						factura.getEstado() + "', " + 
						factura.getSubtotal() + ", " + 
						factura.getImpuesto() + ", " + 
						factura.getTotal() + ", " + 
						factura.getCantidadTotal() + ", " + 
						factura.getAlmacen().getIdAlmacen() + ", " + 
						factura.getCliente().getIdPersona() + ", " + 
						factura.getEmpleado().getIdPersona() + 
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Update(Connection conexion, String tabla, Factura data, int id) {
		// TODO Auto-generated method stub
		return 0;
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
	
	public ResultSet GetAllByDate(Connection conexion, String tabla, String type, Timestamp dateInit, Timestamp dateEnd) {
		String query = "SELECT "
				+ "f.id_factura as 'factura.id', "
				+ "f.fecha as 'factura.fecha', "
				+ "f.transaccion as 'factura.transaccion', "
				+ "f.estado as 'factura.estado', "
				+ "f.subtotal as 'factura.subtotal', "
				+ "f.impuesto as 'factura.impuesto', "
				+ "f.total as 'factura.total', "
				+ "f.cantidad_total as 'factura.cantidadTotal', "
				+ "a.id_almacen as 'almacen.id', "
				+ "a.nombre as 'almacen.nombre', "
				+ "a.descripcion as 'almacen.descripcion', "
				+ "a.direccion as 'almacen.direccion', "
				+ "a.telefono as 'almacen.telefono', "
				+ "c.id_persona as 'cliente.id', "
				+ "c.nombre as 'cliente.nombre', "
				+ "c.direccion as 'cliente.direccion', "
				+ "c.telefono as 'cliente.telefono', "
				+ "c.email as 'cliente.email', "
				+ "c.tipo as 'cliente.tipo', "
				+ "e.id_persona as 'empleado.id', "
				+ "e.nombre as 'empleado.nombre', "
				+ "e.direccion as 'empleado.direccion', "
				+ "e.telefono as 'empleado.telefono', "
				+ "e.email as 'empleado.email', "
				+ "e.tipo as 'empleado.tipo' "
				+ "FROM " + tabla + " AS f "
				+ "INNER JOIN Almacenes AS a ON f.id_almacen = a.id_almacen "
				+ "INNER JOIN Personas AS c ON f.id_cliente = c.id_persona "
				+ "INNER JOIN Personas AS e ON f.id_empleado = e.id_persona "
				+ " WHERE f.transaccion = '" + type + "' AND (f.fecha BETWEEN '" + dateInit + "' AND '"+ dateEnd + "')";
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
