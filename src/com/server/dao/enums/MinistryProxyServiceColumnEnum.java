package com.server.dao.enums;

public enum MinistryProxyServiceColumnEnum {

	MINSTRY_PROXY_NAME("MINSTRY_PROXY_NAME"),
	ESERVICE_ID("ESERVICE_ID"),
	MINSTRY_NAME("MINSTRY_NAME"),
	SERVICE_TYPE("SERVICE_TYPE"),
	ENDPOINT_1("ENDPOINT_1"),
	ENDPOINT_2("ENDPOINT_2"),
	IS_WS_SECURTITY_1("IS_WS_SECURTITY_1"),
	IS_WS_SECURTITY_2("IS_WS_SECURTITY_2"),
	KEYWORDS("KEYWORDS"),
	UPDATED_DATE("UPDATED_DATE"),
	SERVICE_NAME("SERVICE_NAME"),
	CREATED_BY("CREATED_BY"),
	CREATED_DATE("CREATED_DATE");
	
	
	private String messageKey = "";
	MinistryProxyServiceColumnEnum(String key) {
		messageKey = key;
	}
	public String getMessageKey() {
		return messageKey;
	}
	
}
