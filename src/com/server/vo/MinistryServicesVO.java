package com.server.vo;

public class MinistryServicesVO extends AbstractObjectValues{
	
	
	private String eserviceId ;
	private String serviceName ;
	private String minstryName;
	private String keywords;
	private String ServiceType ;
	private String endpoint;
	private boolean isWSSecurity;
	
	

	
	public MinistryServicesVO(String eserviceId, String serviceName,
			String minstryName, String keywords, String serviceType,
			String endpoint) {
		super();
		this.eserviceId = eserviceId;
		this.serviceName = serviceName;
		this.minstryName = minstryName;
		this.keywords = keywords;
		ServiceType = serviceType;
		this.endpoint = endpoint;
	}

	public MinistryServicesVO() {
		// TODO Auto-generated constructor stub
	}

	public String getEserviceId() {
		return eserviceId;
	}

	public void setEserviceId(String eserviceId) {
		this.eserviceId = eserviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMinstryName() {
		return minstryName;
	}

	public void setMinstryName(String minstryName) {
		this.minstryName = minstryName;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getServiceType() {
		return ServiceType;
	}

	public void setServiceType(String serviceType) {
		ServiceType = serviceType;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public boolean isWSSecurity() {
		return isWSSecurity;
	}

	public void setWSSecurity(boolean isWSSecurity) {
		this.isWSSecurity = isWSSecurity;
	}


}
