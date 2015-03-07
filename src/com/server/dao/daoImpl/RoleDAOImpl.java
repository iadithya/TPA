package com.server.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.server.common.AbstractMongoClientDB;
import com.server.constants.Constants;
import com.server.dao.daoapi.IRoleDAO;
import com.server.dao.enums.RoleColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.RoleVO;

public class RoleDAOImpl extends AbstractMongoClientDB implements IRoleDAO{

	public  RoleDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createRole(RoleVO role) throws BaseException {
		MongoClient mongoCon = null;
		try{
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.ROLES);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBRoleObject(role);
			dbColl.insert(dbObject);
			System.out.println("Role is created "+role.getName()+"  ");
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
	}

	@Override
	public void deleteRole(String name) throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.ROLES);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(RoleColumnsEnum.NAME.getMessageKey(), name);
			dbColl.remove(basicDBObject);
			System.out.println("Role is deleted with name "+name);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
	}
	
	@Override
	public void deleteRoleByID(String roleID) throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.ROLES);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(roleID));
			dbColl.remove(basicDBObject);
			System.out.println("Role is deleted with id "+roleID);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
	}

	@Override
	public void updateRole(RoleVO role) throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.ROLES);
			//dbColl.setObjectClass(Role.class);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBRoleObject(role);
			dbObject.put(RoleColumnsEnum.UPDATED_DATE.getMessageKey(), role.getUpdateDate());
			dbColl.update(new BasicDBObject().append(Constants.OBJECT_ID, new ObjectId(role.getGuid())), dbObject);
			System.out.println("Role is updated with id "+role.getGuid());
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
	}

	@Override
	public RoleVO getRole(String name) throws BaseException {
		MongoClient mongoCon = null;
		RoleVO role = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.ROLES);
			
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(RoleColumnsEnum.NAME.getMessageKey(), name);
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				//Role = new Role();
				DBObject obj = dbCursor.next();
				
				role = ObjectConstructHelper.prepareRoleObject(obj);
			}
			System.out.println("Role is fetched with name "+name);
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

	@Override
	public RoleVO getRoleById(String roleId) throws BaseException {
		MongoClient mongoCon = null;
		RoleVO role = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.ROLES);
			//dbColl.setObjectClass(Role.class);
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
//	@Override
//	public int searchCount(String searchPattern, RoleColumnsEnum columnEnum) throws BaseException {
//		MongoClient mongoCon = null;
//		int count=0;
//		try{
//			System.out.println("In searching count method of ROLES with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
//			mongoCon = getMongoClientConnection();
//			DB db = mongoCon.getDB(Constants.DB_SELECTION);
//			DBCollection dbColl = db.getCollection(Constants.ROLES);
//			
//			BasicDBObject basicDBObject  = new BasicDBObject();
//			//Pattern pattern = null;
//			BasicDBObject basicDBObject_1  = null;
//			if(searchPattern!=null){
//				if(!searchPattern.equalsIgnoreCase("*")){
//					basicDBObject_1 = new BasicDBObject("$regex", EmailSenderFactory.getInstance().getSearchPattern(searchPattern));
//				} else {
//					basicDBObject_1  = new BasicDBObject("$ne", "");
//				}
//			}
//			if(columnEnum.getMessageKey().equals(RoleColumnsEnum.NAME.getMessageKey())){
//				basicDBObject.put(RoleColumnsEnum.NAME.getMessageKey(), basicDBObject_1);
//			} else if(columnEnum.getMessageKey().equals(RoleColumnsEnum.DESCRIPTION.getMessageKey())){
//				basicDBObject.put(RoleColumnsEnum.DESCRIPTION.getMessageKey(), basicDBObject_1);
//			} else {
//				basicDBObject.put("Nothing", "*");
//			}
//			
//			DBCursor dbCursor1 = dbColl.find(basicDBObject);
//			if(dbCursor1!=null){
//				count = dbCursor1.count();
//			}
//		}catch(Exception ex){
//			throw new BaseException(ex.getMessage(), ex);
//		} finally {
//			try{
//				//mongoCon.close();
//			}catch(Exception e){
//				throw new BaseException(e.getMessage(), e);
//			}
//		}
//		return count;
//	}
//	@Override
//	public RoleVO[] search(String searchPattern, RoleColumnsEnum columnEnum) throws BaseException {
//		MongoClient mongoCon = null;
//		List<RoleVO> roleList = new ArrayList<RoleVO>();
//		try{
//			System.out.println("In searching count method of ROLES with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
//			mongoCon = getMongoClientConnection();
//			DB db = mongoCon.getDB(Constants.DB_SELECTION);
//			DBCollection dbColl = db.getCollection(Constants.ROLES);
//			
//			BasicDBObject basicDBObject  = new BasicDBObject();
//			//Pattern pattern = null;
//			BasicDBObject basicDBObject_1  = null;
//			if(searchPattern!=null){
//				if(!searchPattern.equalsIgnoreCase("*")){
//					basicDBObject_1 = new BasicDBObject("$regex", EmailSenderFactory.getInstance().getSearchPattern(searchPattern));
//				} else {
//					basicDBObject_1  = new BasicDBObject("$ne", "");
//				}
//			}
//			if(columnEnum.getMessageKey().equals(RoleColumnsEnum.NAME.getMessageKey())){
//				basicDBObject.put(RoleColumnsEnum.NAME.getMessageKey(), basicDBObject_1);
//			} else if(columnEnum.getMessageKey().equals(RoleColumnsEnum.DESCRIPTION.getMessageKey())){
//				basicDBObject.put(RoleColumnsEnum.DESCRIPTION.getMessageKey(), basicDBObject_1);
//			} else {
//				basicDBObject.put("Nothing", "*");
//			}
//			
//			DBCursor dbCursor1 = dbColl.find(basicDBObject);
//			RoleVO role = null;
//			while(dbCursor1.hasNext()){
//				DBObject obj = dbCursor1.next();
//				role = ObjectConstructHelper.prepareRoleObject(obj);
//				roleList.add(role);
//			}
//		}catch(Exception ex){
//			throw new BaseException(ex.getMessage(), ex);
//		} finally {
//			try{
//				//mongoCon.close();
//			}catch(Exception e){
//				throw new BaseException(e.getMessage(), e);
//			}
//		}
//		return roleList.toArray(new RoleVO[0]);
//	}
	
}
