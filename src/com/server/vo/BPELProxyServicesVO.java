package com.server.vo;

public class BPELProxyServicesVO extends AbstractObjectValues{
	
	private String bpelProxyName ;
	
	private String eserviceId;
	
	private String serviceName;
	
	private String ministryName;
	
	private String serviceType;
	
	private boolean isWSSecurity1;
	
	private boolean isWSSecurity2;
	
	
	private String endpoint1;
	
	private String endpoint2;
	
	private String keywords;
	
	
	

	public BPELProxyServicesVO(String bpelProxyName, String eserviceId,
			String serviceName, String ministryName, String serviceType,
			boolean isWSSecurity1, boolean isWSSecurity2, String endpoint1,
			String endpoint2, String keywords) {
		super();
		this.bpelProxyName = bpelProxyName;
		this.eserviceId = eserviceId;
		this.serviceName = serviceName;
		this.ministryName = ministryName;
		this.serviceType = serviceType;
		this.isWSSecurity1 = isWSSecurity1;
		this.isWSSecurity2 = isWSSecurity2;
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
		this.keywords = keywords;
	}

	public BPELProxyServicesVO() {
		// TODO Auto-generated constructor stub
	}

	public String getBpelProxyName() {
		return bpelProxyName;
	}

	public void setBpelProxyName(String bpelProxyName) {
		this.bpelProxyName = bpelProxyName;
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	

	
}
