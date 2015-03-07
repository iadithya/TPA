package com.app.action;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.app.constants.TPAConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.server.dao.cache.AuthenticatedUsers;
import com.server.dao.cache.UMSEntityObj;
import com.server.factory.ManagersFactory;

public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	
	private static final long serialVersionUID = 1L;

	public HttpServletRequest _request = null;
	
	public HttpServletResponse _response = null;
	
	private String loggedInUserName = null;
	
	private String logInUser = null;
	
	private String userId = null;
	
	private Date lastLogin = null;
	
	private String rName;
	
	private String logInId;
	
	private String message;
	
	private String actionType;
	
	UMSEntityObj entityObj;
	
	public String execute() throws Exception {

		
		Map<String, Object> session = ActionContext.getContext().getSession();
		String authToken = (String) session
				.get(TPAConstants.AUTH_TOKEN);
		
		if (authToken != null) {

			
			entityObj = AuthenticatedUsers.getInstance()
					.verifyAuthenticationMApp(authToken);
			if (entityObj != null) {
				setLoggedInUserName(entityObj.getUsername());
			//	setLastLogin(entityObj.getLastLogin());
				setRName(entityObj.getRoleName());
				setLogInUser(entityObj.getUsername());
				setLogInId(entityObj.getLoginid());
				System.out.println("BaseAction>>>>>>>>>role was  is "
							+ entityObj.getRoleName() + " guid>>>>"
							+ entityObj.getGuid());
				

				setUserId(entityObj.getGuid());
				session.put("UIuserId", entityObj.getGuid());
			}

		} else {

			System.out.println("BaseAction: Auth token is null");
		}
		return SUCCESS;
	}

	public void setUpdateMyAccountValues(String upName, String upEmail)	throws Exception {

		Map<String, Object> session = ActionContext.getContext().getSession();
		String authToken = (String) session
				.get(TPAConstants.AUTH_TOKEN);
		if (authToken != null) {

				System.out.println("BaseAction>>>>>setUserSelectedEvent>>>>>Auth token is not empty..");
			
				UMSEntityObj entityObj = AuthenticatedUsers.getInstance()
					.verifyAuthenticationMApp(authToken);
			if (entityObj != null) {
				entityObj.setUsername(upName);
				entityObj.setLoginid(upEmail);
				AuthenticatedUsers.getInstance().setAuthenticationForUser(
						authToken, entityObj);
			}

		} else {

			
		}
	}

	
	public BaseAction() {

	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this._request = httpServletRequest;
		setTimeZone();
	}

	public HttpServletRequest getRequest() {
		return _request;
	}

	public HttpSession getSession() {
		return getRequest().getSession(true);
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this._response = httpServletResponse;
	}

	public HttpServletResponse getResponse() {
		return _response;
	}

	private String timeZone;

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	Cookie[] allCookies;

	public Date toClientTime(Date date) {
		if (date == null) {
			return null;
		}
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		// read the value from session
		Object timeOffSet = _request.getSession()
				.getAttribute("timezoneoffset");
		Object dst = _request.getSession().getAttribute("dst");
		sop(now);
		if (timeOffSet != null) {
			int offset = Integer.parseInt(timeOffSet.toString());
			boolean isDst = false;
			if (dst != null) {
				isDst = Boolean.parseBoolean(dst.toString());
			}

			String[] possibleIds = TimeZone.getAvailableIDs(offset);
			TimeZone clientTZ = null;

			for (String id : possibleIds) {
				TimeZone tempTZ = TimeZone.getTimeZone(id);

				if ((tempTZ.useDaylightTime() && isDst)
						|| (!tempTZ.useDaylightTime() && !isDst)) {
					clientTZ = tempTZ;
					setTimeZone(clientTZ.getID());
					break; // just take the first one
				}
			}
			if (clientTZ != null) {
				return changeTimeZone(date, clientTZ);
			}
		}
		// if there is no offset in session return the datetime in server
		// timezone
		return date;
	}

	public void setTimeZone() {
		TimeZone clientTZ = null;
		// read the value from session
		Object timeOffSet = _request.getSession()
				.getAttribute("timezoneoffset");
		Object dst = _request.getSession().getAttribute("dst");
		if (timeOffSet != null) {
			int offset = Integer.parseInt(timeOffSet.toString());
			boolean isDst = false;
			if (dst != null) {
				isDst = Boolean.parseBoolean(dst.toString());
			}

			String[] possibleIds = TimeZone.getAvailableIDs(offset);

			for (String id : possibleIds) {
				TimeZone tempTZ = TimeZone.getTimeZone(id);

				if ((tempTZ.useDaylightTime() && isDst)
						|| (!tempTZ.useDaylightTime() && !isDst)) {
					clientTZ = tempTZ;
					setTimeZone(clientTZ.getID());
					break; // just take the first one
				}
			}
		}
	}

	private Date changeTimeZone(Date date, TimeZone toTimeZone) {
		if (date == null) {
			return null;
		}
		TimeZone fromTimeZone = TimeZone.getTimeZone("UTC");
		// Get a Calendar instance using the default time zone and locale.
		Calendar calendar = Calendar.getInstance();
		// Set the calendar's time with the given date
		calendar.setTimeZone(fromTimeZone);
		calendar.setTime(date);

		// fromTimeZone.getDisplayName());
		// FROM TimeZone to UTC
		calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
		if (fromTimeZone.inDaylightTime(calendar.getTime())) {
			calendar.add(Calendar.MILLISECOND, calendar.getTimeZone()
					.getDSTSavings() * -1);
		}
		// UTC to TO TimeZone
		calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
		if (toTimeZone.inDaylightTime(calendar.getTime())) {
			calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
		}

		// fromTimeZone.getDisplayName());
		return calendar.getTime();
	}

	public Date toLocalTimeZone(Date date) {
		if (date == null) {
			return null;
		}
		setTimeZone();
		TimeZone fromTimeZone = TimeZone.getTimeZone("UTC");
		TimeZone toTimeZone = TimeZone.getDefault();
		// Get a Calendar instance using the default time zone and locale.
		Calendar calendar = Calendar.getInstance();
		// Set the calendar's time with the given date
		calendar.setTimeZone(fromTimeZone);
		calendar.setTime(date);

		// fromTimeZone.getDisplayName());
		// FROM TimeZone to UTC
		calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
		if (fromTimeZone.inDaylightTime(calendar.getTime())) {
			calendar.add(Calendar.MILLISECOND, calendar.getTimeZone()
					.getDSTSavings() * -1);
		}
		// UTC to TO TimeZone
		calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
		if (toTimeZone.inDaylightTime(calendar.getTime())) {
			calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
		}

		// fromTimeZone.getDisplayName());
		return calendar.getTime();
	}

	private void sop(Calendar now) {

	}

	public Date toUTC(Date date) {
		if (date == null) {
			return null;
		}
		TimeZone toTimeZone = TimeZone.getTimeZone("UTC");
		TimeZone fromTimeZone = TimeZone.getDefault();
		// Get a Calendar instance using the default time zone and locale.
		Calendar calendar = Calendar.getInstance();
		// Set the calendar's time with the given date
		calendar.setTimeZone(fromTimeZone);
		calendar.setTime(date);

		// fromTimeZone.getDisplayName());
		// FROM TimeZone to UTC
		calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
		if (fromTimeZone.inDaylightTime(calendar.getTime())) {
			calendar.add(Calendar.MILLISECOND, calendar.getTimeZone()
					.getDSTSavings() * -1);
		}
		// UTC to TO TimeZone
		calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
		if (toTimeZone.inDaylightTime(calendar.getTime())) {
			calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
		}

		// fromTimeZone.getDisplayName());
		return calendar.getTime();
	}

	/**
	 * @return the loggedInUserName
	 */
	public String getLoggedInUserName() {
		return loggedInUserName;
	}

	/**
	 * @param loggedInUserName
	 *            the loggedInUserName to set
	 */
	public void setLoggedInUserName(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the rName
	 */
	public String getRName() {
		return rName;
	}

	/**
	 * @param rName
	 *            the rName to set
	 */
	public void setRName(String rName) {
		this.rName = rName;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the logInUser
	 */
	public String getLogInUser() {
		return logInUser;
	}

	/**
	 * @param logInUser
	 *            the logInUser to set
	 */
	public void setLogInUser(String logInUser) {
		this.logInUser = logInUser;
	}



	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}


	/**
	 * @param actionType
	 *            the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	

	
	
	

	public String getLogInId() {
		return logInId;
	}

	public void setLogInId(String logInId) {
		this.logInId = logInId;
	}

//	@Override
//	public void setServletResponse(HttpServletResponse arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setServletRequest(HttpServletRequest arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	

}
