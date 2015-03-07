package com.app.configuration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.app.constants.TPAConstants;

public class MySessionListener implements HttpSessionListener {
	

	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("***********************************");
		HttpSession session = arg0.getSession();
		Object userName = session.getAttribute(TPAConstants.TPA_CUSTOMER_KEY);
		Object customerID = session.getAttribute(TPAConstants.LOGGEDIN_CUSTOMER_ID);
		session.removeAttribute(TPAConstants.TPA_CUSTOMER_KEY);
		session.removeAttribute(TPAConstants.LOGGEDIN_CUSTOMER_ID);
		
		
	}

}
