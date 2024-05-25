package main.java.com.engineerds.stockmaster;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import main.java.com.engineerds.stockmaster.api.Routes;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        Routes routes = new Routes(server);

        System.out.println("API escuchando en el puerto " + port);
        
	}

}
