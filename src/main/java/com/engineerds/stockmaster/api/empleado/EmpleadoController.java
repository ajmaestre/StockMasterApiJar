package main.java.com.engineerds.stockmaster.api.empleado;

import com.sun.net.httpserver.HttpServer;


public class EmpleadoController {
	
	public static void getRoutes(HttpServer server) {
		server.createContext("/empleado", new EmpleadoHandler());
	}

}
