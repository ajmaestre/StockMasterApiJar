package main.java.com.engineerds.stockmaster.api.almacen;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import main.java.com.engineerds.stockmaster.model.Almacen;
import main.java.com.engineerds.stockmaster.model.Response;
import main.java.com.engineerds.stockmaster.service.AlmacenService;
import main.java.com.engineerds.stockmaster.utilities.ExtractBody;
import main.java.com.engineerds.stockmaster.utilities.ExtractParams;
import main.java.com.engineerds.stockmaster.utilities.HandleResponse;

public class AlmacenHandler implements HttpHandler {

	private AlmacenService almacenService = new AlmacenService();
	private ArrayList<Almacen> almacenes = new ArrayList<>();
	private Almacen almacen;
	
	ObjectMapper objectMapper;
	Map<String, String> queryParams;
	int count = 0;

	@Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
    	exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        if (path.startsWith("/almacen/lista")) {
            getAll(exchange);
        } else if (path.startsWith("/almacen/byid")) {
            get(exchange);
        } else if (path.startsWith("/almacen/byname")) {
            getByName(exchange);
        } else if (path.startsWith("/almacen/save")) {
            save(exchange);
        } else if (path.startsWith("/almacen/update")) {
            update(exchange);
        } else if (path.startsWith("/almacen/delete")) {
            delete(exchange);
        } else {
            handleDefault(exchange);
        }
    }

    private void getAll(HttpExchange exchange) throws IOException {
        try {
        	almacenes = almacenService.getAlmacenes();
        	objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(almacenes);
            sendResponse(exchange, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la lista de almacenes a formato Json");
        } catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al obtener la lista de almacenes");
		}
    }

    private void get(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		almacen = almacenService.getAlmacen(id);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(almacen);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el almacen a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar el almacen");
		}
    }
    
    private void getByName(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		String name = this.queryParams.get("name");
    		almacenes = almacenService.getAlmacenesByName(name);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(almacenes);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el almacen a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar el almacen");
		}
    }
    
    private void save(HttpExchange exchange) throws IOException {
    	try {
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
            almacen = this.objectMapper.readValue(requestBody, Almacen.class);
            int res = almacenService.guardarAlmacen(almacen);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el almacen a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al registrar el almacen");
		}
    }
    
    private void update(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
            almacen = this.objectMapper.readValue(requestBody, Almacen.class);
            int res = almacenService.editarAlmacen(almacen, id);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la categoria a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al actualizar la categoria");
		}
    }
    
    private void delete(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		int res = almacenService.eliminarAlmacen(id);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al eliminar la categoria");
		}
    }
    
    private void handleDefault(HttpExchange exchange) throws IOException {
    	String message = "¡Bienvenido a StockMasterApi!";
        Response response = new Response(1, "200", message);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(response);
        sendResponse(exchange, json);
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, 0);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
            os.close();
        }
    }
    
    private void sendErrorResponse(HttpExchange exchange, int code, String message) throws IOException {
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
