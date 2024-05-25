package main.java.com.engineerds.stockmaster.api.factura;

import com.sun.net.httpserver.HttpServer;


public class FacturaController {
	
	public static void getRoutes(HttpServer server) {
		server.createContext("/factura", new FacturaHandler());
	}

}
