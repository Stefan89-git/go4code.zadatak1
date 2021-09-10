package go4code.zadatak1.controllers.util;

public class RESTError {
	
	public String message;
	
	public RESTError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RESTError(String message) {
		
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
