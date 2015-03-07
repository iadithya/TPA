package com.server.vo;

import java.util.Date;

public class UserVO extends AbstractObjectValues{
	
	private String userName;
	
	private String password;
	
	private String loginId;
	
	private boolean isActive;
	
	private Date lastLogin;

	private String rollName;
	
	
	
	public UserVO(String userName, String password, String loginId,
			  String rollName) {
		super();
		this.userName = userName;
		this.password = password;
		this.loginId = loginId;
	//	this.isActive = isActive;
	//	this.lastLogin = lastLogin;
		this.rollName = rollName;
	}

	public UserVO() {
		// TODO Auto-generated constructor stub
	}


	public String getRollName() {
		return rollName;
	}

	public void setRollName(String rollName) {
		this.rollName = rollName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	
	
	
	

}
