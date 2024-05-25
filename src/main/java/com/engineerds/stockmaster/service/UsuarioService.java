package main.java.com.engineerds.stockmaster.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.com.engineerds.stockmaster.model.Usuario;
import main.java.com.engineerds.stockmaster.repository.DatabaseConnection;
import main.java.com.engineerds.stockmaster.repository.UsuarioRepositorio;


public class UsuarioService {

	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private Usuario usuario;
	private String tabla = "Usuarios";
	private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
	
	
	public UsuarioService(){}
	
	public ArrayList<Usuario> getUsuarios() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.GetAll(db, tabla);
		try {
			usuarios.clear();
			while(result.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(result.getInt("id_usuario"));
				usuario.setNombre(result.getString("nombre"));
				usuario.setClave(result.getString("clave"));
				usuario.setTipo(result.getString("tipo"));
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Usuario> getEmpleados() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.GetAll(db, tabla, "EMPLEADO");
		try {
			usuarios.clear();
			while(result.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(result.getInt("id_usuario"));
				usuario.setNombre(result.getString("nombre"));
				usuario.setClave(result.getString("clave"));
				usuario.setTipo(result.getString("tipo"));
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Usuario> getAdmins() {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.GetAll(db, tabla, "ADMINISTRADOR");
		try {
			usuarios.clear();
			while(result.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(result.getInt("id_usuario"));
				usuario.setNombre(result.getString("nombre"));
				usuario.setClave(result.getString("clave"));
				usuario.setTipo(result.getString("tipo"));
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public Usuario getUsuario(int id) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.Get(db, tabla, id);
		try {	
			result.next();
			usuario = new Usuario();
			usuario.setIdUsuario(result.getInt("id_usuario"));
			usuario.setNombre(result.getString("nombre"));
			usuario.setClave(result.getString("clave"));
			usuario.setTipo(result.getString("tipo"));	
			return usuario;	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public boolean getUsuario(Usuario usuario) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.GetUsuario(db, tabla, usuario);
		int count = 0;
		try {
			while(result.next()) {
				count++;
			}
			if(count == 0) {
				return false;
			}	
			return true;	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Usuario> getUsuariosByName(String name) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.GetByName(db, tabla, name);
		try {
			usuarios.clear();
			while(result.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(result.getInt("id_usuario"));
				usuario.setNombre(result.getString("nombre"));
				usuario.setClave(result.getString("clave"));
				usuario.setTipo(result.getString("tipo"));
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Usuario> getEmpleadosByName(String name) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.GetByName(db, tabla, name, "EMPLEADO");
		try {
			usuarios.clear();
			while(result.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(result.getInt("id_usuario"));
				usuario.setNombre(result.getString("nombre"));
				usuario.setClave(result.getString("clave"));
				usuario.setTipo(result.getString("tipo"));
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public ArrayList<Usuario> getAdminsByName(String name) {
		ResultSet result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.GetByName(db, tabla, name, "ADMINISTRADOR");
		try {
			usuarios.clear();
			while(result.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(result.getInt("id_usuario"));
				usuario.setNombre(result.getString("nombre"));
				usuario.setClave(result.getString("clave"));
				usuario.setTipo(result.getString("tipo"));
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			DatabaseConnection.desconnectDatabase(db);
		}
	}
	
	public int guardarUsuario(Usuario usuario) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.Insert(db, tabla, usuario);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;	
	}
	
	public int eliminarUsuario(int id) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.Delete(db, tabla, id);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}
	
	public int editarUsuario(Usuario usuario, int id) {
		int result;
		Connection db = DatabaseConnection.connectDatabase();
		result = usuarioRepositorio.Update(db, tabla, usuario, id);
		DatabaseConnection.desconnectDatabase(db);
		if(result == 1) {
			return result;
		}
		return 0;
	}

}
