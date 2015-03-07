package com.server.execption;

public class NoSuchRoleException extends Exception {
	
	public NoSuchRoleException() {
		// TODO Auto-generated constructor stub
	}
	
	public NoSuchRoleException(String message) {
		super(message);
	}
	public NoSuchRoleException(String message, Throwable cause) {
		super(message, cause);
	}
	public NoSuchRoleException(Throwable cause) {
		super(cause);
	}


}
