package com.server.dao.enums;

public enum MinistryServiceColumnsEnum {

	ESERVICE_ID("ESERVICE_ID"),
	MINSTRY_NAME("MINSTRY_NAME"),
	SERVICE_TYPE("SERVICE_TYPE"),
	ENDPOINT("ENDPOINT"),
	KEYWORDS("KEYWORDS"),
	IS_WS_SECURTITY("IS_WS_SECURTITY"),
	UPDATED_DATE("UPDATED_DATE"),
	SERVICE_NAME("SERVICE_NAME"),
	CREATED_BY("CREATED_BY"),
	CREATED_DATE("CREATED_DATE");
	
	
	private String messageKey = "";
	MinistryServiceColumnsEnum(String key) {
		messageKey = key;
	}
	public String getMessageKey() {
		return messageKey;
	}
	
}
