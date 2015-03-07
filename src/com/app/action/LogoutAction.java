package com.app.action;

import java.util.Map;

import com.app.constants.TPAConstants;
import com.opensymphony.xwork2.ActionContext;
import com.server.dao.cache.AuthenticatedUsers;

public class LogoutAction extends BaseAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LogoutAction() {
	}

	public String execute() throws Exception {
		System.out.println("Logout Action");
		super.execute();
		Map<String, Object> session = ActionContext.getContext().getSession();
		String authToken = (String)session.get(TPAConstants.AUTH_TOKEN);
		
		if(authToken!=null){
			AuthenticatedUsers.getInstance().removeAuthToken(authToken);
			session.remove(TPAConstants.AUTH_TOKEN);
			System.out.println("Logout Action "+authToken);
		} else {
			System.out.println("Auth token is empty");
		}
		return SUCCESS;
	}

	
}