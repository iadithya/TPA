package com.server.dao.daoImpl;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.server.common.AbstractMongoClientDB;
import com.server.constants.Constants;
import com.server.dao.daoapi.IUserDAO;
import com.server.dao.enums.UserColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.UserVO;

public class UserDAOImpl extends AbstractMongoClientDB implements IUserDAO{

	@Override
	
	public void createUser(UserVO userVO) throws BaseException {
		MongoClient mongoCon = null;
		try{
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.USERS);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBUserObject(userVO);
			dbColl.insert(dbObject);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
	}

	

	@Override
	public void updateUser(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserVO> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO getUserById(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public UserVO getUser(String loginId) throws BaseException {
			MongoClient mongoCon = null;
			UserVO user = null;
			try{
				
				mongoCon = getMongoClientConnection();
				DB db = mongoCon.getDB(Constants.DB_SELECTION);
				DBCollection dbColl = db.getCollection(Constants.USERS);
				
				BasicDBObject basicDBObject  = new BasicDBObject();
				basicDBObject.put(UserColumnsEnum.LOGIN_ID.getMessageKey(), loginId);
				DBCursor dbCursor = dbColl.find(basicDBObject);
				if(dbCursor.hasNext()){
					//user = new User();
					DBObject obj = dbCursor.next();
					
					user = ObjectConstructHelper.prepareUserObject(obj);
//					user.setRoleName(getRoleById(user.getRoleId()).getName());
				}
				System.out.println("User is fetched with loginId "+loginId);
			}catch(Exception ex){
				throw new BaseException(ex.getMessage(), ex);
			} finally {
				try{
					//mongoCon.close();
				}catch(Exception e){
					throw new BaseException(e.getMessage(), e);
				}
			}
			return user;
	}

}
