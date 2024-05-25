package main.java.com.engineerds.stockmaster.api.persona;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import main.java.com.engineerds.stockmaster.model.Persona;
import main.java.com.engineerds.stockmaster.model.Response;
import main.java.com.engineerds.stockmaster.service.PersonaService;
import main.java.com.engineerds.stockmaster.utilities.ExtractBody;
import main.java.com.engineerds.stockmaster.utilities.ExtractParams;
import main.java.com.engineerds.stockmaster.utilities.HandleResponse;


public abstract class PersonaHandler implements HttpHandler{

	private PersonaService personaService = new PersonaService();
	private Persona persona;
	
	private ObjectMapper objectMapper;
	private Map<String, String> queryParams;
	

	@Override
    public abstract void handle(HttpExchange exchange) throws IOException;

    public void get(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		persona = personaService.getPersona(id);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(persona);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el registro a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar el registro");
		}
    }
    
    public void save(HttpExchange exchange) throws IOException {
    	try {
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
            persona = this.objectMapper.readValue(requestBody, Persona.class);
            int res = personaService.guardarPersona(persona);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el registro a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al registrar el registro");
		}
    }
    
    public void update(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
            persona = this.objectMapper.readValue(requestBody, Persona.class);
            int res = personaService.editarPersona(persona, id);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el registro a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al actualizar el registro");
		}
    }
    
    public void delete(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		int res = personaService.eliminarPersona(id);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al eliminar el registro");
		}
    }

    public void handleDefault(HttpExchange exchange) throws IOException {
    	String message = "¡Bienvenido a StockMasterApi!";
        Response response = new Response(1, "200", message);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(response);
        sendResponse(exchange, json);
    }

    public void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, 0);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
            os.close();
        }
    }
    
    public void sendErrorResponse(HttpExchange exchange, int code, String message) throws IOException {
        Response response = new Response(1, String.valueOf(code), message);
        objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(response);
        exchange.sendResponseHeaders(code, 0);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(json.getBytes());
            os.close();
        }
    }
    
}
