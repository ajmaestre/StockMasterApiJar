package main.java.com.engineerds.stockmaster.api.categoria;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import main.java.com.engineerds.stockmaster.model.Categoria;
import main.java.com.engineerds.stockmaster.model.Response;
import main.java.com.engineerds.stockmaster.service.CategoriaService;
import main.java.com.engineerds.stockmaster.utilities.ExtractBody;
import main.java.com.engineerds.stockmaster.utilities.ExtractParams;
import main.java.com.engineerds.stockmaster.utilities.HandleResponse;

public class CategoriaHandler implements HttpHandler {
	
	private CategoriaService categoriaService = new CategoriaService();
	private ArrayList<Categoria> categorias = new ArrayList<>();
	private Categoria categoria;
	
	private ObjectMapper objectMapper;
	private Map<String, String> queryParams;
	

	@Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
    	exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        if (path.startsWith("/categoria/lista")) {
            getAll(exchange);
        } else if (path.startsWith("/categoria/byid")) {
            get(exchange);
        } else if (path.startsWith("/categoria/byname")) {
            getByName(exchange);
        } else if (path.startsWith("/categoria/save")) {
            save(exchange);
        } else if (path.startsWith("/categoria/update")) {
            update(exchange);
        } else if (path.startsWith("/categoria/delete")) {
            delete(exchange);
        } else {
            handleDefault(exchange);
        }
    }

    private void getAll(HttpExchange exchange) throws IOException {
        try {
        	categorias = categoriaService.getCategorias();
        	objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(categorias);
            sendResponse(exchange, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la lista de categoria a formato Json");
        } catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al obtener la lista de categoria");
		}
    }

    private void get(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		categoria = categoriaService.getCategoria(id);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(categoria);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la categoria a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar la categoria");
		}
    }
    
    private void getByName(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		String name = this.queryParams.get("name");
    		categorias = categoriaService.getCategoriasByName(name);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(categorias);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la categoria a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar la categoria");
		}
    }
    
    private void save(HttpExchange exchange) throws IOException {
    	try {
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
            categoria = this.objectMapper.readValue(requestBody, Categoria.class);
            int res = categoriaService.guardarCategoria(categoria);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la categoria a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al registrar la categoria");
		}
    }
    
    private void update(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
            categoria = this.objectMapper.readValue(requestBody, Categoria.class);
            int res = categoriaService.editarCategoria(categoria, id);
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
    		int res = categoriaService.eliminarCategoria(id);
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
