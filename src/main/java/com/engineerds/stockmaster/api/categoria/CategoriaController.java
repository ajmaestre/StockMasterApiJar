package main.java.com.engineerds.stockmaster.api.categoria;


import com.sun.net.httpserver.HttpServer;


public class CategoriaController {
	
	public static void getRoutes(HttpServer server) {
		server.createContext("/categoria", new CategoriaHandler());
	}
	
}
