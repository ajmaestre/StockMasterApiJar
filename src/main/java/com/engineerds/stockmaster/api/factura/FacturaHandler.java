package main.java.com.engineerds.stockmaster.api.factura;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import main.java.com.engineerds.stockmaster.model.Detalle;
import main.java.com.engineerds.stockmaster.model.Factura;
import main.java.com.engineerds.stockmaster.model.Facturas;
import main.java.com.engineerds.stockmaster.model.Response;
import main.java.com.engineerds.stockmaster.service.FacturaService;
import main.java.com.engineerds.stockmaster.utilities.ExtractBody;
import main.java.com.engineerds.stockmaster.utilities.ExtractParams;
import main.java.com.engineerds.stockmaster.utilities.HandleResponse;


public class FacturaHandler implements HttpHandler {

	private FacturaService facturaService = new FacturaService();
	private ArrayList<Factura> facturas = new ArrayList<>();
	private Factura factura;
	private Facturas detalleFactura;
	private ArrayList<Detalle> detalles;
	
	private ObjectMapper objectMapper;
	private Map<String, String> queryParams;
	

	@Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
    	exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        if (path.startsWith("/factura/lista")) {
            getAll(exchange);
        } else if (path.startsWith("/factura/venta/lista")) {
            getVentas(exchange);
        } else if (path.startsWith("/factura/compra/lista")) {
            getCompras(exchange);
        } else if (path.startsWith("/factura/byid")) {
            get(exchange);
        } else if (path.startsWith("/factura/venta/bydate")) {
        	getVentasByDate(exchange);
        } else if (path.startsWith("/factura/compra/bydate")) {
        	getComprasByDate(exchange);
        } else if (path.startsWith("/factura/save")) {
            save(exchange);
        } else {
            handleDefault(exchange);
        }
    }

    private void getAll(HttpExchange exchange) throws IOException {
        try {
        	facturas = facturaService.getFacturas();
        	objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(facturas);
            sendResponse(exchange, json);
        } catch (JsonProcessingException e) {
        	System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al convertir la lista de facturas a formato Json");
        } catch (Exception e) {
        	System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al obtener la lista de facturas");
		}
    }
    
    private void getVentas(HttpExchange exchange) throws IOException {
        try {
        	facturas = facturaService.getVentas();
        	objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(facturas);
            sendResponse(exchange, json);
        } catch (JsonProcessingException e) {
        	System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al convertir la lista a formato Json");
        } catch (Exception e) {
        	System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al obtener la lista");
		}
    }
    
    private void getCompras(HttpExchange exchange) throws IOException {
        try {
        	facturas = facturaService.getCompras();
        	objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(facturas);
            sendResponse(exchange, json);
        } catch (JsonProcessingException e) {
        	System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al convertir la lista a formato Json");
        } catch (Exception e) {
        	System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al obtener la lista");
		}
    }

    private void get(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		int id = Integer.parseInt(this.queryParams.get("id"));
    		detalleFactura = facturaService.getFacturaDetalle(id);
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(detalleFactura);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al convertir la factura a formato Json");
		} catch (Exception e) {
			System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al encontrar la factura");
		}
    }
    
    private void getVentasByDate(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		String encodedDateInit = this.queryParams.get("dateInit");
    		String encodedDateEnd = this.queryParams.get("dateEnd");
    		String dateInit = URLDecoder.decode(encodedDateInit, "UTF-8");
            String dateEnd = URLDecoder.decode(encodedDateEnd, "UTF-8");
    		facturas = facturaService.getVentasByDate(Timestamp.valueOf(dateInit), Timestamp.valueOf(dateEnd));
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(facturas);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al convertir la factura a formato Json");
		} catch (Exception e) {
			System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al encontrar la factura");
		}
    }
    
    private void getComprasByDate(HttpExchange exchange) throws IOException {
    	try {
    		this.queryParams = ExtractParams.getParams(exchange.getRequestURI().getQuery());
    		String encodedDateInit = this.queryParams.get("dateInit");
    		String encodedDateEnd = this.queryParams.get("dateEnd");
    		String dateInit = URLDecoder.decode(encodedDateInit, "UTF-8");
            String dateEnd = URLDecoder.decode(encodedDateEnd, "UTF-8");
    		facturas = facturaService.getComprasByDate(Timestamp.valueOf(dateInit), Timestamp.valueOf(dateEnd));
    		objectMapper = new ObjectMapper();
            String json = this.objectMapper.writeValueAsString(facturas);
            sendResponse(exchange, json);
		} catch (JsonProcessingException e) {
			System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al convertir la factura a formato Json");
		} catch (Exception e) {
			System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al encontrar la factura");
		}
    }
    
    private void save(HttpExchange exchange) throws IOException {
    	try {
    		String requestBody = ExtractBody.getRequestBody(exchange);
    		this.objectMapper = new ObjectMapper();
    		detalleFactura = this.objectMapper.readValue(requestBody, Facturas.class);
    		factura = detalleFactura.getFactura();
    		detalles = detalleFactura.getDetalles();
            int res = facturaService.guardarFactura(factura, detalles);
            sendResponse(exchange, HandleResponse.response(res));
		} catch (JsonProcessingException e) {
			System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al convertir el registro a formato Json");
		} catch (Exception e) {
			System.out.println(e); 
            sendErrorResponse(exchange, 500, "Error al registrar el registro");
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
