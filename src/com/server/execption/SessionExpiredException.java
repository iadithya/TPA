package com.server.execption;

import java.io.Serializable;

public class SessionExpiredException extends Exception implements Serializable{
	
	public SessionExpiredException() {
	}

	/**
	 * 
	 */
	public SessionExpiredException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public SessionExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 */
	public SessionExpiredException(Throwable cause) {
		super(cause);
	}


}
