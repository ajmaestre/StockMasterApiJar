package main.java.com.engineerds.stockmaster.api.root;

import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import main.java.com.engineerds.stockmaster.model.Response;

public class RootHandler implements HttpHandler {
	
	@Override
    public void handle(HttpExchange exchange) throws IOException {

        String message = "¡Bienvenido a StockMasterApi!";
        Response response = new Response(1, "200", message);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(response);
        exchange.sendResponseHeaders(200, 0);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(json.getBytes());
            os.close();
        }
        
    }
	
}
