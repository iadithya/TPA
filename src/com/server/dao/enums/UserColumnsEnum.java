package com.server.dao.enums;

public enum UserColumnsEnum {

	USER_NAME("USER_NAME"),
	USER_PASS("USER_PASS"),
	LOGIN_ID("LOGIN_ID"),
	ROLE_NAME("ROLE_NAME"),
	CREATED_DATE("CREATED_DATE"),
	UPDATED_DATE("UPDATED_DATE"),
	LAST_LOGIN("LAST_LOGIN"),
	IS_ACTIVE("IS_ACTIVE"), 
	CREATED_BY("CREATED_BY"),
	 UPDATED_BY("UPDATED_BY");

	private String messageKey = "";

	UserColumnsEnum(String key) {
		messageKey = key;
	}

	public String getMessageKey() {
		return messageKey;
	}
}
