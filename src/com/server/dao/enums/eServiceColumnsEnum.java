package com.server.dao.enums;

public enum eServiceColumnsEnum {
	
	
	eSERVICE_NAME("eSERVICE_NAME"),
	MINSTRY_NAME("MINSTRY_NAME"),
	PROJECT_MANAGER_NAME("PROJECT_MANAGER_NAME"),
	UPDATED_DATE("UPDATED_DATE"),
	CREATED_BY("CREATED_BY");
	
	private String messageKey = "";
	eServiceColumnsEnum(String key) {
		messageKey = key;
	}
	public String getMessageKey() {
		return messageKey;
	}

}
