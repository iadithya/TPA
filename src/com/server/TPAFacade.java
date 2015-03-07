package com.server;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import com.app.api.IRole;
import com.app.api.IUser;
import com.server.auth.AuthenticationFactory;
import com.server.auth.IAuthenticator;
import com.server.constants.Constants;
import com.server.dao.MongoDBConnection;
import com.server.execption.BaseException;
import com.server.factory.ManagersFactory;
import com.server.util.PropertiesLoader;

public class TPAFacade {
	
private static Properties dbProperties;
	
	private String userName;
	private boolean isAuthenticated = false;
	public void init(String userName, String password) throws BaseException{
//		boolean auth = false;
		
		try {
			dbProperties = PropertiesLoader.getInstance().getProperties(Constants.DB_PROPERTIES);
			
			try {
				String token = initialize(userName, password);
				if(token!=null){
					isAuthenticated = true;
					this.userName = userName;
				}
//				if(!auth){
//					throw new BaseException("Authentication is failed...");
//				} else {
//					isAuthenticated = auth;
//				}
			} catch (Throwable e) {
				throw new BaseException(e.getMessage(), e);
			}
//			if(auth){
//			
//				
				
//			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage(), e);
		}
	}
	public TPAFacade() {
		// TODO Auto-generated constructor stub
	}

	private static String initialize(String userName, String password) throws FileNotFoundException, Throwable {
		String domainName = null;
		String databaseType = null;

		databaseType = dbProperties.getProperty("DB_SELECTION");
		domainName = "default";
		// Initializing the connection pool

		try {
			MongoDBConnection.getInstance().setMongoProperties(domainName, dbProperties);
			/*Properties connectionProperties = new Properties();
			if (dbProperties != null) {
				connectionProperties.put("db/properties/poolName", domainName);
				connectionProperties.put("db/properties/url", dbProperties.getProperty("DB_URL"));
				connectionProperties.put("db/properties/driver", dbProperties.getProperty("DB_DRIVER"));
				connectionProperties.put("db/properties/username", dbProperties.getProperty("DB_USER"));
				connectionProperties.put("db/properties/password", dbProperties.getProperty("DB_PWD"));
				connectionProperties.put("db/properties/maxconnections", dbProperties.getProperty("DB_MAX_CONNECTIONS"));
				connectionProperties.put("db/properties/idleconnectiontime", dbProperties.getProperty("DB_IDLE_CONNECTION_TIME"));
				connectionProperties.put("db/properties/maxconnectiontime", dbProperties.getProperty("DB_MAX_CONNECTION_TIME"));

				if (dbProperties.getProperty("CONNECTION_POOLTYPE") != null){
					connectionProperties.put("db/properties/connectionPoolType", dbProperties.getProperty("CONNECTION_POOLTYPE"));
				}
			}
			System.out.println("In UMSFacade initialize the connection properties "+connectionProperties);
			DatabaseConnectionFactory.getDatabaseHandle(connectionProperties);
			System.out.println("Data base connection pool is initialized...");
			
			String queryStore = databaseType + ".xml";


			try {
				if (queryStore != null) {
					IQueryInfo iQueryInfo = QueryInfoFactory.getInstance().getQueryInfoImplementation();
					iQueryInfo.loadStore(queryStore);
				}
			} catch (Throwable e) {
				e.printStackTrace();
				throw new Throwable(e.getMessage(), e);
			}*/
			IAuthenticator iAuth = AuthenticationFactory.getInstance().getAuthenticator();
			return iAuth.authenticate(userName, password);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new Throwable(ex.getMessage(), ex);
		}
	}

	public IUser newUser() throws RemoteException, Throwable {
		if (isAuthenticated) {
			IUser impl = ManagersFactory.getInstance().getUser(this.userName);
			return impl;
		} else {
			return null;
		}
	}
		public IRole newRole() throws RemoteException, Throwable {
		if (isAuthenticated) {
			IRole impl = ManagersFactory.getInstance().getRole(this.userName);
			return impl;
		} else {
			return null;
		}
	}
	
	

}
