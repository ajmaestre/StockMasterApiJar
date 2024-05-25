package main.java.com.engineerds.stockmaster.model;

public class Response {

	private int idResponse;
	private String code;
	private String message;
	
	public Response(){
		
	}
	
	public Response(int idResponse, String code, String message){
		this.idResponse = idResponse;
		this.code = code;
		this.message = message;
	}
	
	public int getIdResponse(){
		return this.idResponse;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setIdResponse(int idResponse) {
		this.idResponse = idResponse;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
