package main.java.com.engineerds.stockmaster.api.almacen;

import com.sun.net.httpserver.HttpServer;


public class AlmacenController {
	
	public static void getRoutes(HttpServer server) {
		server.createContext("/almacen", new AlmacenHandler());
	}

}
