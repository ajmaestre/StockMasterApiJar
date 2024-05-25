package main.java.com.engineerds.stockmaster.api.cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import main.java.com.engineerds.stockmaster.api.persona.PersonaHandler;
import main.java.com.engineerds.stockmaster.model.Persona;
import main.java.com.engineerds.stockmaster.service.PersonaService;
import main.java.com.engineerds.stockmaster.utilities.ExtractParams;

public class ClienteHandler extends PersonaHandler{

	private PersonaService personaService = new PersonaService();
	private ArrayList<Persona> personas = new ArrayList<>();
	
	ObjectMapper objectMapper;
	Map<String, String> queryParams;
	String path = "cliente";

	@Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
    	exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        if (path.startsWith("/"+ this.path +"/lista")) {
            getAll(exchange);
        } else if (path.startsWith("/"+ this.path +"/byid")) {
            get(exchange);
        } else if (path.startsWith("/"+ this.path +"/byname")) {
            getByName(exchange);
        } else if (path.startsWith("/"+ this.path +"/save")) {
            save(exchange);
        } else if (path.startsWith("/"+ this.path +"/update")) {
            update(exchange);
        } else if (path.startsWith("/"+ this.path +"/delete")) {
            delete(exchange);
        } else {
            handleDefault(exchange);
        }
    }

    private void getAll(HttpExchange exchange) throws IOException {
        try {
        	personas = personaService.getClientes();
        	objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(personas);
            sendResponse(exchange, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la lista de clientes a formato Json");
        } catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al obtener la lista de clientes");
		}
    }
    
    private void getByName(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		String name = this.queryParams.get("name");
    		personas = personaService.getClientesByName(name);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(personas);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el cliente a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar el cliente");
		}
    }
    
}
