package com.server.execption;

import java.io.Serializable;

public class BaseException extends Exception implements Serializable {

	private static final long serialVersionUID = 5982471197637654008L;

	public BaseException() {
	}
	public BaseException(String message) {
		super(message);
	}
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
	public BaseException(Throwable cause) {
		super(cause);
	}

}
