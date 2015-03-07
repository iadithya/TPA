package com.server.dao.cache;

import java.util.Date;

public class UMSEntityObj {

	
//	public UMSEntityObj(String username, String guid,  Date authenticatedTime, 
//			Date lastLogin, String role ,String loginid ) {
//		setGuid(guid);
//		setName(username);
//		setAuthenticatedTime(authenticatedTime);
//		setLastLogin(lastLogin);
//		setRoleName(role);
//	}
//
	
	
	
	
	private String guid;
	private String username;
	private Date authenticatedTime;
	private String loginid;
	//private Date lastLogin;
	private String roleName;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public UMSEntityObj(String guid, String username, Date authenticatedTime,
			String loginid, String roleName) {
		setGuid(guid);
		setUsername(username);
		setAuthenticatedTime(authenticatedTime);
		setLoginid(loginid);
		setRoleName(roleName);
		
	}



	public Date getAuthenticatedTime() {
		return authenticatedTime;
	}

	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getLoginid() {
		return loginid;
	}



	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}



	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}

	
	public void setAuthenticatedTime(Date authenticatedTime) {
		this.authenticatedTime = authenticatedTime;
	}

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


}
