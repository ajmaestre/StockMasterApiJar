package main.java.com.engineerds.stockmaster.api.root;

import com.sun.net.httpserver.HttpServer;


public class RootController {

	public static void get(HttpServer server) {
		server.createContext("/", new RootHandler());
	}
	
}
