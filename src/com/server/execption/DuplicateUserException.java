package com.server.execption;

public class DuplicateUserException extends Exception {
	
	public DuplicateUserException() {
		// TODO Auto-generated constructor stub
	}
	
	public DuplicateUserException(String message) {
		super(message);
	}
	public DuplicateUserException(String message, Throwable cause) {
		super(message, cause);
	}
	public DuplicateUserException(Throwable cause) {
		super(cause);
	}

}
