package com.server.execption;

public class ValidateEntityException extends Exception {
	
	public ValidateEntityException() {
		// TODO Auto-generated constructor stub
	}
	
	public ValidateEntityException(String message) {
		super(message);
	}
	public ValidateEntityException(String message, Throwable cause) {
		super(message, cause);
	}
	public ValidateEntityException(Throwable cause) {
		super(cause);
	}

}
