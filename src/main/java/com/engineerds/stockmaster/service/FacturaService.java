package main.java.com.engineerds.stockmaster.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import main.java.com.engineerds.stockmaster.model.Almacen;
import main.java.com.engineerds.stockmaster.model.Detalle;
import main.java.com.engineerds.stockmaster.model.Factura;
import main.java.com.engineerds.stockmaster.model.Facturas;
import main.java.com.engineerds.stockmaster.model.Persona;
import main.java.com.engineerds.stockmaster.repository.DatabaseConnection;
import main.java.com.engineerds.stockmaster.repository.FacturaRepositorio;


public class FacturaService {

	private ArrayList<Factura> facturas = new ArrayList<>();
	private ArrayList<Detalle> detalles = new ArrayList<>();
	private Factura factura;
	private Facturas facturaDetalle;
	private Almacen almacen;
	private Persona cliente;
	private Persona empleado;
	private String tabla = "Facturas";
	private FacturaRepositorio facturaRepositorio = new FacturaRepositorio();
	private DetalleService detalleService = new DetalleService();
	
	
	public FacturaService(){} 
	
	public ArrayList<Factura> getFacturas() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = facturaRepositorio.GetAll(db, tabla);
		try {
			facturas.clear();
			while(result.next()) {
				factura = new Factura();
				almacen = new Almacen();
				cliente = new Persona();
				empleado = new Persona();
				factura.setIdFactura(result.getInt("factura.id"));
				factura.setFecha(result.getTimestamp("factura.fecha"));
				factura.setTransaccion(result.getString("factura.transaccion"));
				factura.setEstado(result.getString("factura.estado"));
				factura.setSubtotal(result.getDouble("factura.subtotal"));
				factura.setImpuesto(result.getDouble("factura.impuesto"));
				factura.setTotal(result.getDouble("factura.total"));
				factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
				almacen.setIdAlmacen(result.getInt("almacen.id"));
				almacen.setNombre(result.getString("almacen.nombre"));
				almacen.setDescripcion(result.getString("almacen.descripcion"));
				almacen.setDireccion(result.getString("almacen.direccion"));
				almacen.setTelefono(result.getString("almacen.telefono"));
				factura.setAlmacen(almacen);
				cliente.setIdPersona(result.getInt("cliente.id"));
				cliente.setNombre(result.getString("cliente.nombre"));
				cliente.setDireccion(result.getString("cliente.direccion"));
				cliente.setTelefono(result.getString("cliente.telefono"));
				cliente.setEmail(result.getString("cliente.email"));
				cliente.setTipo(result.getString("cliente.tipo"));
				factura.setCliente(cliente);
				empleado.setIdPersona(result.getInt("empleado.id"));
				empleado.setNombre(result.getString("empleado.nombre"));
				empleado.setDireccion(result.getString("empleado.direccion"));
				empleado.setTelefono(result.getString("empleado.telefono"));
				empleado.setEmail(result.getString("empleado.email"));
				empleado.setTipo(result.getString("empleado.tipo"));
				factura.setEmpleado(empleado);
				facturas.add(factura);
			}
			return facturas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public Facturas getFacturaDetalle(int id) {
		ResultSet result;
		detalles = detalleService.getDetallesByFactura(id);
		facturaDetalle = new Facturas();
		facturaDetalle.setDetalles(detalles);
		Connection db = DatabaseConnection.connectDatabase();
		result = facturaRepositorio.Get(db, tabla, id);
		try {
			result.next();
			factura = new Factura();
			almacen = new Almacen();
			cliente = new Persona();
			empleado = new Persona();
			factura.setIdFactura(result.getInt("factura.id"));
			factura.setFecha(result.getTimestamp("factura.fecha"));
			factura.setTransaccion(result.getString("factura.transaccion"));
			factura.setEstado(result.getString("factura.estado"));
			factura.setSubtotal(result.getDouble("factura.subtotal"));
			factura.setImpuesto(result.getDouble("factura.impuesto"));
			factura.setTotal(result.getDouble("factura.total"));
			factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
			almacen.setIdAlmacen(result.getInt("almacen.id"));
			almacen.setNombre(result.getString("almacen.nombre"));
			almacen.setDescripcion(result.getString("almacen.descripcion"));
			almacen.setDireccion(result.getString("almacen.direccion"));
			almacen.setTelefono(result.getString("almacen.telefono"));
			factura.setAlmacen(almacen);
			cliente.setIdPersona(result.getInt("cliente.id"));
			cliente.setNombre(result.getString("cliente.nombre"));
			cliente.setDireccion(result.getString("cliente.direccion"));
			cliente.setTelefono(result.getString("cliente.telefono"));
			cliente.setEmail(result.getString("cliente.email"));
			cliente.setTipo(result.getString("cliente.tipo"));
			factura.setCliente(cliente);
			empleado.setIdPersona(result.getInt("empleado.id"));
			empleado.setNombre(result.getString("empleado.nombre"));
			empleado.setDireccion(result.getString("empleado.direccion"));
			empleado.setTelefono(result.getString("empleado.telefono"));
			empleado.setEmail(result.getString("empleado.email"));
			empleado.setTipo(result.getString("empleado.tipo"));
			factura.setEmpleado(empleado);
			facturaDetalle.setFactura(factura);
			return facturaDetalle;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}

	public Factura getFactura(int id) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = facturaRepositorio.Get(db, tabla, id);
		try {
			result.next();
			factura = new Factura();
			almacen = new Almacen();
			cliente = new Persona();
			empleado = new Persona();
			factura.setIdFactura(result.getInt("factura.id"));
			factura.setFecha(result.getTimestamp("factura.fecha"));
			factura.setTransaccion(result.getString("factura.transaccion"));
			factura.setEstado(result.getString("factura.estado"));
			factura.setSubtotal(result.getDouble("factura.subtotal"));
			factura.setImpuesto(result.getDouble("factura.impuesto"));
			factura.setTotal(result.getDouble("factura.total"));
			factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
			almacen.setIdAlmacen(result.getInt("almacen.id"));
			almacen.setNombre(result.getString("almacen.nombre"));
			almacen.setDescripcion(result.getString("almacen.descripcion"));
			almacen.setDireccion(result.getString("almacen.direccion"));
			almacen.setTelefono(result.getString("almacen.telefono"));
			factura.setAlmacen(almacen);
			cliente.setIdPersona(result.getInt("cliente.id"));
			cliente.setNombre(result.getString("cliente.nombre"));
			cliente.setDireccion(result.getString("cliente.direccion"));
			cliente.setTelefono(result.getString("cliente.telefono"));
			cliente.setEmail(result.getString("cliente.email"));
			cliente.setTipo(result.getString("cliente.tipo"));
			factura.setCliente(cliente);
			empleado.setIdPersona(result.getInt("empleado.id"));
			empleado.setNombre(result.getString("empleado.nombre"));
			empleado.setDireccion(result.getString("empleado.direccion"));
			empleado.setTelefono(result.getString("empleado.telefono"));
			empleado.setEmail(result.getString("empleado.email"));
			empleado.setTipo(result.getString("empleado.tipo"));
			factura.setEmpleado(empleado);
			return factura;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Factura> getVentas() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = facturaRepositorio.GetAll(db, tabla, "VENTA");
		try {
			facturas.clear();
			while(result.next()) {
				factura = new Factura();
				almacen = new Almacen();
				cliente = new Persona();
				empleado = new Persona();
				factura.setIdFactura(result.getInt("factura.id"));
				factura.setFecha(result.getTimestamp("factura.fecha"));
				factura.setTransaccion(result.getString("factura.transaccion"));
				factura.setEstado(result.getString("factura.estado"));
				factura.setSubtotal(result.getDouble("factura.subtotal"));
				factura.setImpuesto(result.getDouble("factura.impuesto"));
				factura.setTotal(result.getDouble("factura.total"));
				factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
				almacen.setIdAlmacen(result.getInt("almacen.id"));
				almacen.setNombre(result.getString("almacen.nombre"));
				almacen.setDescripcion(result.getString("almacen.descripcion"));
				almacen.setDireccion(result.getString("almacen.direccion"));
				almacen.setTelefono(result.getString("almacen.telefono"));
				factura.setAlmacen(almacen);
				cliente.setIdPersona(result.getInt("cliente.id"));
				cliente.setNombre(result.getString("cliente.nombre"));
				cliente.setDireccion(result.getString("cliente.direccion"));
				cliente.setTelefono(result.getString("cliente.telefono"));
				cliente.setEmail(result.getString("cliente.email"));
				cliente.setTipo(result.getString("cliente.tipo"));
				factura.setCliente(cliente);
				empleado.setIdPersona(result.getInt("empleado.id"));
				empleado.setNombre(result.getString("empleado.nombre"));
				empleado.setDireccion(result.getString("empleado.direccion"));
				empleado.setTelefono(result.getString("empleado.telefono"));
				empleado.setEmail(result.getString("empleado.email"));
				empleado.setTipo(result.getString("empleado.tipo"));
				factura.setEmpleado(empleado);
				facturas.add(factura);
			}
			return facturas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Factura> getVentasByDate(Timestamp dateInit, Timestamp dateEnd) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = facturaRepositorio.GetAllByDate(db, tabla, "VENTA", dateInit, dateEnd);
		try {
			facturas.clear();
			while(result.next()) {
				factura = new Factura();
				almacen = new Almacen();
				cliente = new Persona();
				empleado = new Persona();
				factura.setIdFactura(result.getInt("factura.id"));
				factura.setFecha(result.getTimestamp("factura.fecha"));
				factura.setTransaccion(result.getString("factura.transaccion"));
				factura.setEstado(result.getString("factura.estado"));
				factura.setSubtotal(result.getDouble("factura.subtotal"));
				factura.setImpuesto(result.getDouble("factura.impuesto"));
				factura.setTotal(result.getDouble("factura.total"));
				factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
				almacen.setIdAlmacen(result.getInt("almacen.id"));
				almacen.setNombre(result.getString("almacen.nombre"));
				almacen.setDescripcion(result.getString("almacen.descripcion"));
				almacen.setDireccion(result.getString("almacen.direccion"));
				almacen.setTelefono(result.getString("almacen.telefono"));
				factura.setAlmacen(almacen);
				cliente.setIdPersona(result.getInt("cliente.id"));
				cliente.setNombre(result.getString("cliente.nombre"));
				cliente.setDireccion(result.getString("cliente.direccion"));
				cliente.setTelefono(result.getString("cliente.telefono"));
				cliente.setEmail(result.getString("cliente.email"));
				cliente.setTipo(result.getString("cliente.tipo"));
				factura.setCliente(cliente);
				empleado.setIdPersona(result.getInt("empleado.id"));
				empleado.setNombre(result.getString("empleado.nombre"));
				empleado.setDireccion(result.getString("empleado.direccion"));
				empleado.setTelefono(result.getString("empleado.telefono"));
				empleado.setEmail(result.getString("empleado.email"));
				empleado.setTipo(result.getString("empleado.tipo"));
				factura.setEmpleado(empleado);
				facturas.add(factura);
			}
			return facturas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Factura> getCompras() {	
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = facturaRepositorio.GetAll(db, tabla, "COMPRA");	
		try {		
			facturas.clear();		
			while(result.next()) {
				factura = new Factura();
				almacen = new Almacen();
				cliente = new Persona();
				empleado = new Persona();
				factura.setIdFactura(result.getInt("factura.id"));
				factura.setFecha(result.getTimestamp("factura.fecha"));
				factura.setTransaccion(result.getString("factura.transaccion"));
				factura.setEstado(result.getString("factura.estado"));
				factura.setSubtotal(result.getDouble("factura.subtotal"));
				factura.setImpuesto(result.getDouble("factura.impuesto"));
				factura.setTotal(result.getDouble("factura.total"));
				factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
				almacen.setIdAlmacen(result.getInt("almacen.id"));
				almacen.setNombre(result.getString("almacen.nombre"));
				almacen.setDescripcion(result.getString("almacen.descripcion"));
				almacen.setDireccion(result.getString("almacen.direccion"));
				almacen.setTelefono(result.getString("almacen.telefono"));
				factura.setAlmacen(almacen);
				cliente.setIdPersona(result.getInt("cliente.id"));
				cliente.setNombre(result.getString("cliente.nombre"));
				cliente.setDireccion(result.getString("cliente.direccion"));
				cliente.setTelefono(result.getString("cliente.telefono"));
				cliente.setEmail(result.getString("cliente.email"));
				cliente.setTipo(result.getString("cliente.tipo"));
				factura.setCliente(cliente);
				empleado.setIdPersona(result.getInt("empleado.id"));
				empleado.setNombre(result.getString("empleado.nombre"));
				empleado.setDireccion(result.getString("empleado.direccion"));
				empleado.setTelefono(result.getString("empleado.telefono"));
				empleado.setEmail(result.getString("empleado.email"));
				empleado.setTipo(result.getString("empleado.tipo"));
				factura.setEmpleado(empleado);
				facturas.add(factura);
			}		
			return facturas;	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Factura> getComprasByDate(Timestamp dateInit, Timestamp dateEnd) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = facturaRepositorio.GetAllByDate(db, tabla, "COMPRA", dateInit, dateEnd);
		try {
			facturas.clear();	
			while(result.next()) {
				factura = new Factura();
				almacen = new Almacen();
				cliente = new Persona();
				empleado = new Persona();
				factura.setIdFactura(result.getInt("factura.id"));
				factura.setFecha(result.getTimestamp("factura.fecha"));
				factura.setTransaccion(result.getString("factura.transaccion"));
				factura.setEstado(result.getString("factura.estado"));
				factura.setSubtotal(result.getDouble("factura.subtotal"));
				factura.setImpuesto(result.getDouble("factura.impuesto"));
				factura.setTotal(result.getDouble("factura.total"));
				factura.setCantidadTotal(result.getInt("factura.cantidadTotal"));
				almacen.setIdAlmacen(result.getInt("almacen.id"));
				almacen.setNombre(result.getString("almacen.nombre"));
				almacen.setDescripcion(result.getString("almacen.descripcion"));
				almacen.setDireccion(result.getString("almacen.direccion"));
				almacen.setTelefono(result.getString("almacen.telefono"));
				factura.setAlmacen(almacen);
				cliente.setIdPersona(result.getInt("cliente.id"));
				cliente.setNombre(result.getString("cliente.nombre"));
				cliente.setDireccion(result.getString("cliente.direccion"));
				cliente.setTelefono(result.getString("cliente.telefono"));
				cliente.setEmail(result.getString("cliente.email"));
				cliente.setTipo(result.getString("cliente.tipo"));
				factura.setCliente(cliente);
				empleado.setIdPersona(result.getInt("empleado.id"));
				empleado.setNombre(result.getString("empleado.nombre"));
				empleado.setDireccion(result.getString("empleado.direccion"));
				empleado.setTelefono(result.getString("empleado.telefono"));
				empleado.setEmail(result.getString("empleado.email"));
				empleado.setTipo(result.getString("empleado.tipo"));
				factura.setEmpleado(empleado);
				facturas.add(factura);
			}	
			return facturas;	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public int guardarFactura(Factura factura, ArrayList<Detalle> detalles) {
		int result, idFactura;
		ResultSet resultId;
		Connection db = DatabaseConnection.connectDatabase();
		result = facturaRepositorio.Insert(db, tabla, factura);
		try {
			if(result == 1) {
				resultId = facturaRepositorio.GetLastInsert(db);
				resultId.next();
				idFactura = resultId.getInt("LAST_INSERT_ID()");
				factura = getFactura(idFactura);
				result = detalleService.guardarListDetails(detalles, factura);
				if(result == 1) {
					return result;
				}
			}
			return 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}

}
