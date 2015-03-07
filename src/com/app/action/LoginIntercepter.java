package com.app.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.app.constants.TPAConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.server.auth.AuthenticationFactory;

public class LoginIntercepter implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5273062723786580406L;

	public LoginIntercepter() {
		System.out.println("In LoginIntercepter constructor");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		// TODO Auto-generated method stub
		System.out.println("LoginIntercepter: intercept() method.... ");
		//String actionInvocationObj = arg0.invoke();
		final ActionContext context = arg0.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		Map<String, Object> session = context.getSession();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		session.put("userName", userName);
		session.put("password", password);
		return arg0.invoke();
	}

}