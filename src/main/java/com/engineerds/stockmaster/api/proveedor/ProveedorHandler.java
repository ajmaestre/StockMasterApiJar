package main.java.com.engineerds.stockmaster.api.proveedor;

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


public class ProveedorHandler extends PersonaHandler {

	private PersonaService personaService = new PersonaService();
	ArrayList<Persona> personas = new ArrayList<>();
	public Persona persona;
	
	ObjectMapper objectMapper;
	Map<String, String> queryParams;
	String path = "proveedor";

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
        	personas = personaService.getProveedores();
        	objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(personas);
            sendResponse(exchange, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la lista de proveedores a formato Json");
        } catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al obtener la lista de proveedores");
		}
    }
    
    private void getByName(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		String name = this.queryParams.get("name");
    		personas = personaService.getProveedoresByName(name);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(personas);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el proveedor a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar el proveedor");
		}
    }
    
}
