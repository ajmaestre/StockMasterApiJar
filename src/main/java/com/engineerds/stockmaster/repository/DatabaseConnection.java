package main.java.com.engineerds.stockmaster.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.java.com.engineerds.stockmaster.utilities.Config;

public class DatabaseConnection {
	
	public static Connection connectDatabase() {
		
		Connection conexion;
		
		String host = Config.DB_HOST;
		String user = Config.DB_USER;
		String password = Config.DB_PASSWORD;
		String database = Config.DB_NAME;
		
		try {
			conexion = DriverManager.getConnection(host+database, user, password);
			System.out.println("Conexión exitosa");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		
		return conexion;
		
	}
	
	public static void desconnectDatabase(Connection conexion) {
		
		try {
			conexion.close();
			System.out.println("Base de datos desconectada");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		
	}
	
}
