package com.server.vo;

public class RoleVO extends AbstractObjectValues {

	
	private String name = null;

	
	public RoleVO(String name) {
		super();
		this.name = name;
	}

	public RoleVO() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
