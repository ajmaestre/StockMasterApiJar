package main.java.com.engineerds.stockmaster.api.proveedor;

import com.sun.net.httpserver.HttpServer;


public class ProveedorController {

	public static void getRoutes(HttpServer server) {
		server.createContext("/proveedor", new ProveedorHandler());
	}
	
}
