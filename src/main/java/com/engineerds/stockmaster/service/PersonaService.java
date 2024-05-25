package main.java.com.engineerds.stockmaster.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.com.engineerds.stockmaster.model.Persona;
import main.java.com.engineerds.stockmaster.repository.DatabaseConnection;
import main.java.com.engineerds.stockmaster.repository.PersonaRepositorio;


public class PersonaService {

	private ArrayList<Persona> personas = new ArrayList<>();
	private Persona persona;
	private String tabla = "Personas";
	private PersonaRepositorio personaRepositorio = new PersonaRepositorio();
	
	
	public PersonaService(){}
	
	public ArrayList<Persona> getPersonas() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.GetAll(db, tabla);
		try {
			personas.clear();
			while(result.next()) {
				persona = new Persona();
				persona.setIdPersona(result.getInt("id_persona"));
				persona.setNombre(result.getString("nombre"));
				persona.setDireccion(result.getString("direccion"));
				persona.setTelefono(result.getString("telefono"));
				persona.setEmail(result.getString("email"));
				persona.setTipo(result.getString("tipo"));
				personas.add(persona);
			}
			return	personas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}	
	}
	
	public ArrayList<Persona> getClientes() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.GetAll(db, tabla, "CLIENTE");
		try {
			personas.clear();
			while(result.next()) {
				persona = new Persona();
				persona.setIdPersona(result.getInt("id_persona"));
				persona.setNombre(result.getString("nombre"));
				persona.setDireccion(result.getString("direccion"));
				persona.setTelefono(result.getString("telefono"));
				persona.setEmail(result.getString("email"));
				persona.setTipo(result.getString("tipo"));
				personas.add(persona);
			}
			return	personas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Persona> getClientesByName(String name) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.GetByName(db, tabla, name, "CLIENTE");
		try {
			personas.clear();
			while(result.next()) {
				persona = new Persona();
				persona.setIdPersona(result.getInt("id_persona"));
				persona.setNombre(result.getString("nombre"));
				persona.setDireccion(result.getString("direccion"));
				persona.setTelefono(result.getString("telefono"));
				persona.setEmail(result.getString("email"));
				persona.setTipo(result.getString("tipo"));
				personas.add(persona);
			}	
			return	personas;	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Persona> getProveedores() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.GetAll(db, tabla, "PROVEEDOR");
		try {
			personas.clear();
			while(result.next()) {
				persona = new Persona();
				persona.setIdPersona(result.getInt("id_persona"));
				persona.setNombre(result.getString("nombre"));
				persona.setDireccion(result.getString("direccion"));
				persona.setTelefono(result.getString("telefono"));
				persona.setEmail(result.getString("email"));
				persona.setTipo(result.getString("tipo"));
				personas.add(persona);
			}
			return	personas;	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}	
	}
	
	public ArrayList<Persona> getProveedoresByName(String name) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.GetByName(db, tabla, name, "PROVEEDOR");
		try {
			personas.clear();
			while(result.next()) {
				persona = new Persona();
				persona.setIdPersona(result.getInt("id_persona"));
				persona.setNombre(result.getString("nombre"));
				persona.setDireccion(result.getString("direccion"));
				persona.setTelefono(result.getString("telefono"));
				persona.setEmail(result.getString("email"));
				persona.setTipo(result.getString("tipo"));
				personas.add(persona);
			}
			return	personas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}

	public ArrayList<Persona> getEmpleados() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.GetAll(db, tabla, "EMPLEADO");
		try {		
			personas.clear();		
			while(result.next()) {
				persona = new Persona();
				persona.setIdPersona(result.getInt("id_persona"));
				persona.setNombre(result.getString("nombre"));
				persona.setDireccion(result.getString("direccion"));
				persona.setTelefono(result.getString("telefono"));
				persona.setEmail(result.getString("email"));
				persona.setTipo(result.getString("tipo"));
				personas.add(persona);
			}		
			return	personas;	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Persona> getEmpleadosByName(String name) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.GetByName(db, tabla, name, "EMPLEADO");
		try {
			personas.clear();
			while(result.next()) {
				persona = new Persona();
				persona.setIdPersona(result.getInt("id_persona"));
				persona.setNombre(result.getString("nombre"));
				persona.setDireccion(result.getString("direccion"));
				persona.setTelefono(result.getString("telefono"));
				persona.setEmail(result.getString("email"));
				persona.setTipo(result.getString("tipo"));
				personas.add(persona);
			}
			return	personas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}

	public Persona getPersona(int id) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.Get(db, tabla, id);
		try {
			result.next();
			persona = new Persona();
			persona.setIdPersona(result.getInt("id_persona"));
			persona.setNombre(result.getString("nombre"));
			persona.setDireccion(result.getString("direccion"));
			persona.setTelefono(result.getString("telefono"));
			persona.setEmail(result.getString("email"));
			persona.setTipo(result.getString("tipo"));
			return persona;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public Persona getUltimaPersona() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.GetLastInsert(db);
		try {
			result.next();
			persona = new Persona();
			persona.setIdPersona(result.getInt("id_persona"));
			persona.setNombre(result.getString("nombre"));
			persona.setDireccion(result.getString("direccion"));
			persona.setTelefono(result.getString("telefono"));
			persona.setEmail(result.getString("email"));
			persona.setTipo(result.getString("tipo"));
			return persona;	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public int guardarPersona(Persona persona) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.Insert(db, tabla, persona);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int eliminarPersona(int id) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.Delete(db, tabla, id);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int editarPersona(Persona persona, int id) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = personaRepositorio.Update(db, tabla, persona, id);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}

}
