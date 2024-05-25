package main.java.com.engineerds.stockmaster.api.producto;

import com.sun.net.httpserver.HttpServer;


public class ProductoController {
	
	public static void getRoutes(HttpServer server) {
		server.createContext("/producto", new ProductoHandler());
	}

}
