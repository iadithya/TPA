package com.server.execption;

public class NotAvailableMongoConnection extends Exception {

	public NotAvailableMongoConnection() {
	}

	public NotAvailableMongoConnection(String message) {
		super(message);
	}

	public NotAvailableMongoConnection(String message, Throwable cause) {
		super(message, cause);
	}
}
