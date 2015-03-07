package com.server.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.server.common.AbstractMongoClientDB;
import com.server.constants.Constants;
import com.server.dao.cache.AuthenticatedUsers;
import com.server.dao.cache.UMSEntityObj;
import com.server.dao.daoImpl.ObjectConstructHelper;
import com.server.dao.enums.UserColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.RoleVO;
import com.server.vo.UserVO;

public class DBAuthenticator extends AbstractMongoClientDB implements IAuthenticator{
	
	private static DBAuthenticator _self;
	private static boolean inited = false;
	
	/**
	 * The instance that is used for authenticating the users
	 */
	private AuthenticatedUsers mAuthenticatedUsers;
	/** Creates a new instance of DBAuthenticator */

	public DBAuthenticator() {
	}

	public void init(Properties props) {
		mAuthenticatedUsers = AuthenticatedUsers.getInstance();
	}

	static {
		if (!inited) {
			_self = _self == null ? new DBAuthenticator() : _self;
		}
		inited = true;
	}

	public String authenticate(String username, String password) {
		
		String returnedVal = null;
		try {
			String strPass = null;
			String id = null;
			String strUser = null;
			UMSEntityObj entityOb = null;
			Date currentTime = null;
			String loginId=null;
			if(username!=null && username.equalsIgnoreCase(Constants.UMS_TOKEN_USER_NAME) && password!=null &&
					password.equalsIgnoreCase(Constants.UMS_TOKEN_USER_PASSW)){
				
				currentTime = new Date();
				strPass = password;
				id = Constants.UMS_TOKEN_CUST_GUID;
				strUser = username;
			//	entityOb = new UMSEntityObj(username, id, "", currentTime, Constants.ROLE_S[0], username);
				entityOb = new UMSEntityObj(id, username, currentTime, null, Constants.ROLE_S[0]);
			}
		
			if (username != null && password != null && entityOb == null) {
				UserVO user = getUser(username);
				if (user != null) {
					currentTime = new Date();
					strPass = user.getPassword();
					id = user.getGuid();
					strUser = user.getUserName();
					loginId = user.getLoginId();
						System.out.println("DB Authen Id>>>>>>>>>>>Last Login is "+user.getLastLogin()+"  userGuid is "+id);
						entityOb = new UMSEntityObj(strUser, id,  currentTime, 
								getRoleById(user.getRollName()).getName(),loginId);
						user.setLastLogin(currentTime);
						updateUser(user);
						
						System.out.println("User is updated with last login..");
					
				}
			}
			if (entityOb == null) {
				throw new BaseException("Object is null, not authenticated");
			} else if (mAuthenticatedUsers.isAuthTokenExist(entityOb)) {
				returnedVal = mAuthenticatedUsers.getAuthToken(entityOb);
			} else if (strPass.equals(password)) {
				String authToken = id  + strUser + strPass + System.currentTimeMillis();

				String authKey = generateKey(authToken);
				System.out.println("After authentication the token is >>>>"+authKey);
				if (mAuthenticatedUsers.setAuthenticationForUser(authKey.toString(), entityOb)) {
					returnedVal = authKey;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return returnedVal;
	}

	private String generateKey(String authToken) throws NoSuchAlgorithmException {
		MessageDigest md5Handle = MessageDigest.getInstance("MD5");

		md5Handle.reset();
		md5Handle.update(authToken.getBytes());

		byte md5Digest[] = md5Handle.digest();

		StringBuffer authKey = new StringBuffer();

		for (int position = 0; position < md5Digest.length; position++) {
			authKey.append(Integer.toHexString(0xFF & md5Digest[position]));
		}
		return authKey.toString();
	}

	

	private void updateUser(UserVO user) throws BaseException {
		MongoClient mongoCon = null;
		try {
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.USERS);
			
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBUserObject(user);
			dbObject.put(UserColumnsEnum.UPDATED_DATE.getMessageKey(), user.getUpdateDate());
			dbColl.update(new BasicDBObject().append(Constants.OBJECT_ID, new ObjectId(user.getGuid())), dbObject);
			System.out.println("User is updated with id "+user.getGuid()+" authentication method");
			
		} catch (Exception ex) {
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try {
				//mongoCon.close();
			} catch (Exception e) {
				throw new BaseException(e.getMessage(), e);
			}
		}
	}
	private UserVO getUser(String userName) throws BaseException {
		MongoClient mongoCon = null;
		UserVO user = null;
		try {
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.USERS);
			
			BasicDBObject basicDBObject = new BasicDBObject();
			
			basicDBObject.put(UserColumnsEnum.LOGIN_ID.getMessageKey(), userName);
			basicDBObject.put(UserColumnsEnum.IS_ACTIVE.getMessageKey(), true);
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if (dbCursor.hasNext()) {
				DBObject obj = dbCursor.next();
				
				user = ObjectConstructHelper.prepareUserObject(obj);
				//user.setRoleName(getRoleById(user.getRoleId()).getName());
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try {
				//mongoCon.close();
			} catch (Exception e) {
				throw new BaseException(e.getMessage(), e);
			}
		}
		return user;
	}
	private RoleVO getRoleById(String roleId) throws BaseException {
		MongoClient mongoCon = null;
		RoleVO role = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.ROLES);
			//dbColl.setObjectClass(RoleVO.class);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(roleId));
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				role = new RoleVO();
				DBObject obj = dbCursor.next();
				role = ObjectConstructHelper.prepareRoleObject(obj);
			}
			System.out.println("Role is fetched with id "+roleId);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
		return role;
	}

}
