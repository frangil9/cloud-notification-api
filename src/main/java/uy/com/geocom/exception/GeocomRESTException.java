package uy.com.geocom.exception;

import org.springframework.http.HttpStatus;

public class GeocomRESTException extends Exception{

	private static final long serialVersionUID = 1L;
	private final HttpStatus status;


	public GeocomRESTException(HttpStatus status) {
		this.status = status;
	}

	public GeocomRESTException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
}
