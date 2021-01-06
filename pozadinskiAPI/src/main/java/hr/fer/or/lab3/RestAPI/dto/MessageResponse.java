package hr.fer.or.lab3.RestAPI.dto;

public class MessageResponse {
	
	private String status;
    private String message;
    private Object response;
	public MessageResponse(String status, String message, Object response) {
		super();
		this.status = status;
		this.message = message;
		this.response = response;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
    
    

}
