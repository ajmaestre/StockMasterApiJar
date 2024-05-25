package main.java.com.engineerds.stockmaster.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.engineerds.stockmaster.model.Response;

public class HandleResponse {
	
	public static String response(int res) {
    	try {
    		String json;
    		ObjectMapper objectMapper = new ObjectMapper();
            if(res == 1) {
            	String message = "¡Acción realizada!";
                Response response = new Response(1, "200", message);
                json = objectMapper.writeValueAsString(response);
            }else {
            	String message = "¡La acción no fue realizada!";
                Response response = new Response(0, "500", message);
                json = objectMapper.writeValueAsString(response);
            }
            return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
	
	public static String responseBoolean(boolean res) {
    	try {
    		String json;
    		ObjectMapper objectMapper = new ObjectMapper();
            if(res) {
            	String message = "Acceso concedido";
                Response response = new Response(1, "200", message);
                json = objectMapper.writeValueAsString(response);
            }else {
            	String message = "Acceso denegado";
                Response response = new Response(0, "500", message);
                json = objectMapper.writeValueAsString(response);
            }
            return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
	
}
