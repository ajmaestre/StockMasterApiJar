package main.java.com.engineerds.stockmaster.api.usuario;

import com.sun.net.httpserver.HttpServer;


public class UsuarioController {
	
	public static void getRoutes(HttpServer server) {
		server.createContext("/usuario", new UsuarioHandler());
	}

}
