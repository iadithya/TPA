package com.server.vo;

public class BPELServicesVO extends AbstractObjectValues{
	
	
	private String bpelServiceName;
	
	private String eserviceId;
	
	private String serviceName;
	
	private String ministryName;
	
	private String serviceType;
	
	private boolean isWSSecurity1;
	
	private String endpoint1;
	
	private String endpoint2;
	
	private boolean isWSSecurity2 ;
	
	private String keywords;
	
	

	public BPELServicesVO(String bpelServiceName, String eserviceId,
			String serviceName, String ministryName, String serviceType,
			boolean isWSSecurity1, String endpoint1, String endpoint2,
			boolean isWSSecurity2, String keywords) {
		super();
		this.bpelServiceName = bpelServiceName;
		this.eserviceId = eserviceId;
		this.serviceName = serviceName;
		this.ministryName = ministryName;
		this.serviceType = serviceType;
		this.isWSSecurity1 = isWSSecurity1;
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
		this.isWSSecurity2 = isWSSecurity2;
		this.keywords = keywords;
	}

	public BPELServicesVO() {
		// TODO Auto-generated constructor stub
	}

	public String getBpelServiceName() {
		return bpelServiceName;
	}

	public void setBpelServiceName(String bpelServiceName) {
		this.bpelServiceName = bpelServiceName;
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

	public String getMinistryName() {
		return ministryName;
	}

	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	
	public String getEndpoint1() {
		return endpoint1;
	}

	public void setEndpoint1(String endpoint1) {
		this.endpoint1 = endpoint1;
	}

	public String getEndpoint2() {
		return endpoint2;
	}

	public void setEndpoint2(String endpoint2) {
		this.endpoint2 = endpoint2;
	}

	public boolean isWSSecurity1() {
		return isWSSecurity1;
	}

	public void setWSSecurity1(boolean isWSSecurity1) {
		this.isWSSecurity1 = isWSSecurity1;
	}

	public boolean isWSSecurity2() {
		return isWSSecurity2;
	}

	public void setWSSecurity2(boolean isWSSecurity2) {
		this.isWSSecurity2 = isWSSecurity2;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	
	

}
