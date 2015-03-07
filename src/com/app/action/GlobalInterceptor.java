package com.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.app.constants.TPAConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class GlobalInterceptor extends ActionSupport implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5273062723786580406L;

	public GlobalInterceptor() {
		System.out.println("In GlobalInterceptor constructor");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("In GlobalInterceptor invocation");
		//
		// Map<String, Object> session =
		// invocation.getInvocationContext().getSession();
		// UserVO user = (UserVO)session.get("user");
		// if (user==null) {
		// return ActionSupport.LOGIN;
		// }
		// return invocation.invoke();

		final ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(true);

		try {
			System.out.println("GlobalInterceptor intercept>>>>>");
			String authToken = (String) session
					.getAttribute(TPAConstants.AUTH_TOKEN);

			System.out
					.println("Auth token is>>>>>>>>>>>>>>>>>>>> " + authToken);
			if (authToken == null) {
				System.out
						.println("Sending SessionExpired signal to struts......");
				clearFieldErrors();
				addFieldError("loginErrMessage",
						"Please log in to access the application.");
				return "ANONYMOUS";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Invoke action
		String result = invocation.invoke();

		return result;
	}

	@Override
	public void destroy() {
		System.out.println("In interceptor destroy++++++++++++++");

	}

	@Override
	public void init() {
		System.out.println("In interceptor init++++++++++++++++=");

	}

	// }
}

/*
 * public String intercept(ActionInvocation actionInvocation) throws Exception {
 * System.out.println("Login Intercepter ***********************************");
 * final ActionContext context = actionInvocation.getInvocationContext();
 * HttpServletRequest request = (HttpServletRequest) context
 * .get(ServletActionContext.HTTP_REQUEST); HttpSession session =
 * request.getSession(true);
 * 
 * // Code
 * 
 * try {
 * 
 * System.out.println("Login intercept>>>>>"); String authToken =
 * (String)session.getAttribute(TPAConstants.AUTH_TOKEN); String loginUserName =
 * request.getParameter("userName"); String password =
 * request.getParameter("password"); String forget =
 * request.getParameter("forget"); String register =
 * request.getParameter("register");
 * 
 * if (((loginUserName==null&&password==null))
 * &&authToken==null&&forget==null&&register==null) { return "SessionExpired"; }
 * 
 * } catch (Exception e) { e.printStackTrace(); }
 * 
 * // Invoke action String result = actionInvocation.invoke();
 * 
 * return result; }
 * 
 * 
 * public void destroy() {
 * System.out.println("LoginInterceptor destroy() is called...");
 * 
 * } public void init() {
 * System.out.println("LoginInterceptor init() is called..."); } }
 */
// }