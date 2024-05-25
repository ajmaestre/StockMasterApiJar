package main.java.com.engineerds.stockmaster.api.usuario;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import main.java.com.engineerds.stockmaster.model.Response;
import main.java.com.engineerds.stockmaster.model.Usuario;
import main.java.com.engineerds.stockmaster.service.UsuarioService;
import main.java.com.engineerds.stockmaster.utilities.ExtractBody;
import main.java.com.engineerds.stockmaster.utilities.ExtractParams;
import main.java.com.engineerds.stockmaster.utilities.HandleResponse;


public class UsuarioHandler implements HttpHandler {

	private UsuarioService usuarioService = new UsuarioService();
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private Usuario usuario;
	
	private ObjectMapper objectMapper;
	private Map<String, String> queryParams;

	
	@Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
    	exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        if (path.startsWith("/usuario/lista")) {
            getAll(exchange);
        } else if (path.startsWith("/usuario/byid")) {
            get(exchange);
        } else if (path.startsWith("/usuario/byname")) {
            getByName(exchange);
        } else if (path.startsWith("/usuario/save")) {
            save(exchange);
        } else if (path.startsWith("/usuario/update")) {
            update(exchange);
        } else if (path.startsWith("/usuario/delete")) {
            delete(exchange);
        } else if (path.startsWith("/usuario/login")) {
            login(exchange);
        } else {
            handleDefault(exchange);
        }
    }

    private void getAll(HttpExchange exchange) throws IOException {
        try {
        	usuarios = usuarioService.getUsuarios();
        	objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(usuarios);
            sendResponse(exchange, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la lista de usuarios a formato Json");
        } catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al obtener la lista de usuarios");
		}
    }

    private void get(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		usuario = usuarioService.getUsuario(id);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(usuario);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir al usuario a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar al usuario");
		}
    }
    
    private void getByName(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		String name = this.queryParams.get("name");
    		usuarios = usuarioService.getUsuariosByName(name);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(usuarios);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir al usuario a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar al usuario");
		}
    }
    
    private void save(HttpExchange exchange) throws IOException {
    	try {
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
            usuario = this.objectMapper.readValue(requestBody, Usuario.class);
            int res = usuarioService.guardarUsuario(usuario);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir al usuario a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al registrar al usuario");
		}
    }
    
    private void update(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
            usuario = this.objectMapper.readValue(requestBody, Usuario.class);
            int res = usuarioService.editarUsuario(usuario, id);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir al usuario a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al actualizar al usuario");
		}
    }
    
    private void delete(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		int res = usuarioService.eliminarUsuario(id);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al eliminar al usuario");
		}
    }
    
    private void login(HttpExchange exchange) throws IOException {
    	try {
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
            usuario = this.objectMapper.readValue(requestBody, Usuario.class);
            boolean res = usuarioService.getUsuario(usuario);
            sendResponse(exchange, HandleResponse.responseBoolean(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir los datos a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al iniciar la sesion");
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
        Response response = new Response(0, String.valueOf(code), message);
        objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(response);
        exchange.sendResponseHeaders(code, 0);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(json.getBytes());
            os.close();
        }
    }
    
}
