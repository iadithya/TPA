package com.app.action;

import java.util.Map;

import com.app.api.IUser;
import com.app.constants.TPAConstants;
import com.opensymphony.xwork2.ActionContext;
import com.server.auth.AuthenticationFactory;
import com.server.constants.Constants;
import com.server.factory.ManagersFactory;
import com.server.vo.UserVO;

public class LoginAction extends BaseAction{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String message;
	private String status;

	public LoginAction() {
	}

	public String execute() throws Exception {
		super.execute();
		String response = null;
		String out = INPUT;
		try {
			System.out.println("In login actions....."+getUserName()+ "Pass" +getPassword());
			System.out.println("In login actions....."+getUserName()+ "Pass" +getPassword());
			System.out.println("In login actions....."+getUserName()+ "Pass" +getPassword());
			
			System.out.println("getUserName "+getUserName());
			System.out.println("getPassword "+getPassword());
			
			//if (getUserName() != null && getPassword() != null) {
				
				Map<String, Object> session = ActionContext.getContext().getSession();
				System.out.println("Session : "+session);
				response = AuthenticationFactory.getInstance().getAuthenticator()
						.authenticate(session.get("userName").toString(), session.get("password").toString());
				System.out.println("response "+response);
				if (response!=null) {
					System.out.println("*******SUCCESS*****");
						session.put(TPAConstants.AUTH_TOKEN, response);
						out = SUCCESS;
						
				} else {
					// setMessage(getMessage());
					System.out.println("*******ERROR*****");
					message = "Enter valid user name and password";
				}
				
			//}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(e.getMessage());
			out = ERROR;
		}
		
		return out;
	}
	public String forgetPwd(){
		try{
			setActionType(Constants.ACTION_TYPES[3]);
			System.out.println("In forget pwd!....."+getUserName());
			if(getUserName()!=null){
				IUser iUser = ManagersFactory.getInstance().getUser(getLogInUser());
				UserVO user = null;
				try {
					user = iUser.getUserByLoginId(getUserName());
				} catch (Exception e) {
					message = "Entered mail id is not present, specify valid one.";
				}
				if (user != null) {
					String pass = user.getPassword();
					//iUser.updateUser(user);
					
					
//					boolean emailStatus = EmailSenderFactory.getInstance().sendEmail(getUserName(),"Password recovery for "
//											+ "Portal of pain dashboard application","Use below credentails to login to dashboard application,\n" +
//													"Username "+user.getName()+",\nPassword "+pass);
//					if (emailStatus) {
//						
//					} else {
//						
//					}
				}
			}
			
			
			if(getMessage()==null){
				setActionType(Constants.ACTION_TYPES[0]);
				setMessage("Email sent is successfully.");
				return SUCCESS;
			} else {
				return INPUT;
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		
			return ERROR;
		}
	}
	public void validate() {
		if (getUserName()!=null&&!getUserName().equalsIgnoreCase("")&&getUserName().length() == 0) {
			addFieldError("userName", "User Name is required");
		}
		/*
		 * else if (!getUserName().equals("Eswar")) { addFieldError("userName",
		 * "Invalid User"); }
		 */
		if (getPassword()!=null&&getPassword().length() == 0) {
			addFieldError("password", "Password is required");
		}

		

	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	

	
//	private String email;
//	private String password;
//	private String message;
//	private String status;
//	
//
//	public LoginAction() {
//
//	}
//
//	public String execute() throws Exception {
//		super.execute();
//		String response = null;
//
//		try {
//
//			System.out.println("****************In login action....."+ getEmail());
//			
//			if (getEmail() != null && getPassword() != null
//					&& getEmail().trim().length() > 1
//					&& getPassword().trim().length() > 1) {
//				Map<String, Object> session = ActionContext.getContext()
//						.getSession();
//
//				response = AuthenticationFactory.getInstance()
//						.getAuthenticator()
//						.authenticate(getEmail(), getPassword());
//
//				if (response != null
//						&& !response
//								.equalsIgnoreCase("The email or password you entered is incorrect")
//						&& !response.equalsIgnoreCase("User is deactivated.")
//						&& !response.equalsIgnoreCase("User not found")) {
//
//					session.put(TPAConstants.AUTH_TOKEN, response);
//					session.put(Constants.DASH_EVENT_POSITION, new Integer(0));
//
//					IUser iUser = ManagersFactory.getInstance().getUser(
//							getLogInUser());
//					UserVO user = iUser.getUserByLoginId(getEmail());
//					if (user != null) {
//						
//						
//					} else {
//						if(user.getRollName().equalsIgnoreCase(Constants.ROLE_S[0])){
//							return "adminpage";
//						}
//						
//					}
//					UMSEntityObj entityObj = AuthenticatedUsers.getInstance()
//							.verifyAuthenticationMApp(response);
//
//					
//					if(user.getRollName().equalsIgnoreCase(Constants.ROLE_S[0])){
//						return "adminpage";
//					}
//					if (user.getUpdateDate() == null) {
//						return NONE;
//					}
//					
////					if (events != null && events.length == 1) {
////						setEventId(events[0].getGuid());
////						setLoginEventName(events[0].getName());
////						return "none1";
////					}
//
//				} else if (response != null
//						&& response.equalsIgnoreCase("User is deactivated.")) {
//
//					setMessage("Your Account is not yet activated ");
//				} else {
//
//					setMessage("The email or password you entered is incorrect");
//				}
//
//			} else {
//				setMessage("Email and Password should not empty");
//			}
//			if (getMessage() == null) {
//				
//				return SUCCESS;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			setMessage(e.getMessage());
//			return ERROR;
//		}
//
//		return INPUT;
//	}
//
//	public String forgetPwd() {
//		try {
//			setActionType(Constants.ACTION_TYPES[3]);
//
//
//			if (getEmail() != null) {
//				IUser iUser = ManagersFactory.getInstance().getUser(
//						getLogInUser());
//				UserVO user = null;
//				try {
//					user = iUser.getUserByLoginId(getEmail());
//				} catch (Exception e) {
//					message = "No account found with that email address.";
//				}
//				if (user != null) {
//					String pass = user.getPassword();
//					// iUser.updateUser(user);
//
////					boolean emailStatus = EmailSenderFactory
////							.getInstance()
////							.sendEmail(
////									getEmail(),
////									"Password recovery for "
////											+ "Portal of pain dashboard application",
////									"Use below credentails to login to dashboard application,\n"
////											+ "Username " + user.getName()
////											+ ",\nPassword " + pass);
////					if (emailStatus) {
////					} else {
////					}
//				}
//			}
//
//			if (getMessage() == null) {
//				setActionType(Constants.ACTION_TYPES[0]);
//				setMessage("Email sent is successfully.");
//				return SUCCESS;
//			} else {
//				return INPUT;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			setMessage(e.getMessage());
//			setActionType(Constants.ACTION_TYPES[2]);
//			return ERROR;
//		}
//	}
//
//	public void validate() {
//		if (getEmail() != null && !getEmail().equalsIgnoreCase("")
//				&& getEmail().length() == 0) {
//			addFieldError("email", "Email is required");
//		}
//		/*
//		 * else if (!getEmail().equals("Eswar")) { addFieldError("userName",
//		 * "Invalid User"); }
//		 */
//		if (getPassword() != null && getPassword().length() == 0) {
//			addFieldError("password", "Password is required");
//		}
//
//	}
//
//	/**
//	 * @return the email
//	 */
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
	
}
