package com.server.dao.enums;

public enum RoleColumnsEnum{
	NAME("NAME"),
	UPDATED_DATE("UPDATED_DATE"),
	CREATED_BY("CREATED_BY")
	;
	
	private String messageKey = "";
	RoleColumnsEnum(String key) {
		messageKey = key;
	}
	public String getMessageKey() {
		return messageKey;
	}
}