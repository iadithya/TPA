package com.app.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.app.api.IRole;
import com.app.api.IUser;
import com.server.TPAFacade;
import com.server.TPAFacadeFactory;
import com.server.constants.Constants;
import com.server.dao.MongoDBConnection;
import com.server.util.PropertiesLoader;
import com.server.vo.RoleVO;
import com.server.vo.UserVO;



public class TPAInitiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected static Properties prop;
	protected static MongoDBConnection mongoConFac = null;
	protected void obtainFactory() {

		try {
			// if (factory == null) {
			// factory = UMSManagersFactory.getInstance();
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializing the Properties.
	 */
	private static Properties dbProperties;

	public TPAInitiServlet() {

	}

	// @Override
	public void init() throws ServletException {
		try {
			dbProperties = PropertiesLoader.getInstance().getProperties(Constants.DB_PROPERTIES);
			mongoConFac = MongoDBConnection.getInstance();
			try {
				initialize();
			} catch (Throwable e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
		prop = new Properties();
	}

	private static void initialize() throws FileNotFoundException, Throwable {
		
		try{
		
		String databaseType = null;

		databaseType = dbProperties.getProperty("DB_SELECTION");
		System.out.println("Database type....."+databaseType);
		// Initializing the connection pool

		mongoConFac.setMongoProperties("default", dbProperties);
		
		mongoConFac.startMongoClient();
		
		TPAFacadeFactory factory = TPAFacadeFactory.getInstance();
		TPAFacade umsFac = factory.getTPAFacade(Constants.UMS_TOKEN_USER_NAME, Constants.UMS_TOKEN_USER_PASSW);
		IUser iUser = umsFac.newUser();
		IRole iRole = umsFac.newRole();
		
		
		String[] roles = Constants.ROLE_S;
		List<RoleVO> rolesList = new ArrayList<RoleVO>();
		for(String cate : roles){
			if(!iRole.isRoleExist(cate)){
				RoleVO cae = new RoleVO(cate);
				iRole.createRole(cae);
				System.out.println("Role is created in InitServlet... " + cate);
				rolesList.add(iRole.getRole(cate));
			} else {
				System.out.println("Role is already present... " + cate);
				rolesList.add(iRole.getRole(cate));
			}
		}
		System.out.println("Roles size is >>>>"+rolesList.size());
		
		System.out.println("Default user creation...");
		if (!iUser.isUserLoginIdExist(Constants.ADMIN_LOGIN_ID)) {
			System.out.println("ADMIN_NAME   "+Constants.ADMIN_NAME+""+Constants.ADMIN_LOGIN_ID+""+Constants.ADMIN_PASSWD);
			UserVO user = new UserVO(Constants.ADMIN_NAME,Constants.ADMIN_LOGIN_ID, Constants.ADMIN_PASSWD, rolesList.get(0).getGuid());
			user.setActive(true);
			iUser.createUser(user);
			System.out.println("User is created in InitServlet... " + Constants.ADMIN_LOGIN_ID);
		} else {
			System.out.println("User is already present... " + Constants.ADMIN_LOGIN_ID);
		}
		String userId = iUser.getUserByLoginId(Constants.ADMIN_LOGIN_ID).getGuid();
		
//		////Needs to remove this code before release///////
//		String[] USER_S = new String[]{Constants.ADMIN_LOGIN_ID};
//		String[] USER__NAMES = new String[]{"Admin"};
//		for(int i=1;i<USER_S.length;i++){
//			if (!iUser.isUserLoginIdExist(USER_S[i])) {
//				UserVO user = new UserVO(USER__NAMES[i],  USER_S[i], Constants.ADMIN_PASSWD, rolesList.get(i).getGuid());
//				user.setActive(true);
//				iUser.createUser(user);
//				System.out.println("User is created in InitServlet... " + USER_S[i]);
//			} else {
//				System.out.println("User is already present... " + USER_S[i]);
//			}
//			//Associating with default event.
//			
//			UserVO user_1 = iUser.getUserByLoginId(USER_S[i]);
//			
//			System.out.println("Assigned the user to default event...");
//		}
		////////Until here/////////////////////
		
		
		
		}catch(Exception e){
			throw e;
		}
		
	}

	// @Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		super.service(arg0, arg1);
	}

	// @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		super.doGet(req, resp);
	}

	// @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		super.doPost(req, resp);
	}

	// @Override
	public void destroy() {
		try{
			mongoConFac.closeMongoClient();
		}catch(Exception e){
			System.out.println("Exception in destroy method of Init Servlet.."+e.getMessage());
		}
		super.destroy();
	}
}
