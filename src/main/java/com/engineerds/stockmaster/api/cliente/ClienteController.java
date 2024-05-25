package main.java.com.engineerds.stockmaster.api.cliente;

import com.sun.net.httpserver.HttpServer;


public class ClienteController {

	public static void getRoutes(HttpServer server) {
		server.createContext("/cliente", new ClienteHandler());
	}
	
}
