package main.java.com.engineerds.stockmaster.api.producto;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import main.java.com.engineerds.stockmaster.model.Producto;
import main.java.com.engineerds.stockmaster.model.Response;
import main.java.com.engineerds.stockmaster.service.ProductoService;
import main.java.com.engineerds.stockmaster.utilities.ExtractBody;
import main.java.com.engineerds.stockmaster.utilities.ExtractParams;
import main.java.com.engineerds.stockmaster.utilities.HandleResponse;

public class ProductoHandler implements HttpHandler {

	private ProductoService productoService = new ProductoService();
	private ArrayList<Producto> productos = new ArrayList<>();
	private Producto producto;
	
	private ObjectMapper objectMapper;
	private Map<String, String> queryParams;
	

	@Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
    	exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        if (path.startsWith("/producto/lista")) {
            getAll(exchange);
        } else if (path.startsWith("/producto/byid")) {
            get(exchange);
        } else if (path.startsWith("/producto/byname")) {
            getByName(exchange);
        } else if (path.startsWith("/producto/bycategoria")) {
            getByCategoria(exchange);
        } else if (path.startsWith("/producto/save")) {
            save(exchange);
        } else if (path.startsWith("/producto/update")) {
            update(exchange);
        } else if (path.startsWith("/producto/delete")) {
            delete(exchange);
        } else if (path.startsWith("/producto/count")) {
            count(exchange);
        } else {
            handleDefault(exchange);
        }
    }

    private void getAll(HttpExchange exchange) throws IOException {
        try {
        	productos = productoService.getProductos();
        	objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(productos);
            sendResponse(exchange, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la lista de productos a formato Json");
        } catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al obtener la lista de productos");
		}
    }

    private void get(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		producto = productoService.getProducto(id);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(producto);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el producto a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar el producto");
		}
    }
    
    private void getByCategoria(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		int count = productoService.getProductosByCategoria(id);
    		Response response = new Response(1, "productos", String.valueOf(count));
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(response);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el resultado a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar los registros");
		}
    }
    
    private void getByName(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		String name = this.queryParams.get("name");
    		productos = productoService.getProductosByName(name);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(productos);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el producto a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar el producto");
		}
    }
    
    private void save(HttpExchange exchange) throws IOException {
    	try {
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
    		producto = this.objectMapper.readValue(requestBody, Producto.class);
            int res = productoService.guardarProducto(producto);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el producto a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al registrar el producto");
		}
    }
    
    private void update(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
    		producto = this.objectMapper.readValue(requestBody, Producto.class);
            int res = productoService.editarProducto(producto, id);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir el producto a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al actualizar el producto");
		}
    }
    
    private void delete(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		int res = productoService.eliminarProducto(id);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al eliminar el producto");
		}
    }
    
    private void count(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		int cantidad = productoService.getCantidad(id);
    		Response response = new Response(1, "cantidad", String.valueOf(cantidad));
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(response);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al convertir la respuesta a formato Json");
		} catch (Exception e) {
			e.printStackTrace();
            sendErrorResponse(exchange, 500, "Error al encontrar el producto");
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
