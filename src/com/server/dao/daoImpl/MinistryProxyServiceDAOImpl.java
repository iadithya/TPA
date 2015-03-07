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
import com.server.common.UtilFactory;
import com.server.constants.Constants;
import com.server.dao.daoapi.IMinistryProxyServiceDAO;
import com.server.dao.enums.MinistryProxyServiceColumnEnum;
import com.server.execption.BaseException;
import com.server.vo.MinistryProxyServicesVO;

public class MinistryProxyServiceDAOImpl extends AbstractMongoClientDB implements IMinistryProxyServiceDAO {

	@Override
	public void createMinistryProxyService(MinistryProxyServicesVO mpServicesVO)
			throws BaseException {
		MongoClient mongoCon = null;
		try{
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_PROXY_SERVICE);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBMinistryProxyServiceObject(mpServicesVO);
			dbColl.insert(dbObject);
			System.out.println("Ministry Service  is created "+mpServicesVO.getMinistryProxyName()+"  ");
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
	public void deleteMinsitryProxyServiceByID(String mpServiceId)
			throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_PROXY_SERVICE);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(mpServiceId));
			dbColl.remove(basicDBObject);
			System.out.println("mpServiceId is deleted with id "+mpServiceId);
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
	public void deleteMinstryProxyService(String name) throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_PROXY_SERVICE);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(MinistryProxyServiceColumnEnum.MINSTRY_PROXY_NAME.getMessageKey(), name);
			dbColl.remove(basicDBObject);
			System.out.println("MINSTRY_PROXY_NAME is deleted with name "+name);
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
	public void updateMinstryProxyService(MinistryProxyServicesVO mpService)
			throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_PROXY_SERVICE);
			//dbColl.setObjectClass(MinistryServicesVO.class);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBMinistryProxyServiceObject(mpService);
			dbObject.put(MinistryProxyServiceColumnEnum.UPDATED_DATE.getMessageKey(), mpService.getUpdateDate());
			dbColl.update(new BasicDBObject().append(Constants.OBJECT_ID, new ObjectId(mpService.getGuid())), dbObject);
			System.out.println("mpService is updated with id "+mpService.getGuid());
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
	public MinistryProxyServicesVO getMinstryProxyService(String name)
			throws BaseException {
		MongoClient mongoCon = null;
		MinistryProxyServicesVO mpService = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_PROXY_SERVICE);
			
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(MinistryProxyServiceColumnEnum.MINSTRY_NAME.getMessageKey(), name);
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				mpService = new MinistryProxyServicesVO();
				DBObject obj = dbCursor.next();
				
				mpService = ObjectConstructHelper.prepareMinistryProxyServiceObject(obj);
			}
			System.out.println("mpService is fetched with name "+name);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
		return mpService;
	}

	@Override
	public MinistryProxyServicesVO getMinstryProxyServiceById(String mpServiceId)
			throws BaseException {
		MongoClient mongoCon = null;
		MinistryProxyServicesVO mpService = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_PROXY_SERVICE);
			//dbColl.setObjectClass(Role.class);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(mpServiceId));
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				mpService = new MinistryProxyServicesVO();
				DBObject obj = dbCursor.next();
				mpService = ObjectConstructHelper.prepareMinistryProxyServiceObject(obj);
			}
			System.out.println("mpService is fetched with id "+mpServiceId);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
		return mpService;
	}

	@Override
	public int searchCount(String searchPattern,
			MinistryProxyServiceColumnEnum columnEnum) throws BaseException {
		MongoClient mongoCon = null;
		int count=0;
		try{
			System.out.println("In searching count method of categorys with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_PROXY_SERVICE);
			
			BasicDBObject basicDBObject  = new BasicDBObject();
			//Pattern pattern = null;
			BasicDBObject basicDBObject_1  = null;
			if(searchPattern!=null){
				if(!searchPattern.equalsIgnoreCase("*")){
					basicDBObject_1 = new BasicDBObject("$regex", UtilFactory.getInstance().getSearchPattern(searchPattern));
				} else {
					basicDBObject_1  = new BasicDBObject("$ne", "");
				}
			}
			if(columnEnum.getMessageKey().equals(MinistryProxyServiceColumnEnum.SERVICE_NAME.getMessageKey())){
				basicDBObject.put(MinistryProxyServiceColumnEnum.SERVICE_NAME.getMessageKey(), basicDBObject_1);
			}	else if(columnEnum.getMessageKey().equals(MinistryProxyServiceColumnEnum.MINSTRY_PROXY_NAME.getMessageKey())){
					basicDBObject.put(MinistryProxyServiceColumnEnum.MINSTRY_PROXY_NAME.getMessageKey(), basicDBObject_1);
			} else if(columnEnum.getMessageKey().equals(MinistryProxyServiceColumnEnum.MINSTRY_NAME.getMessageKey())){
				basicDBObject.put(MinistryProxyServiceColumnEnum.MINSTRY_NAME.getMessageKey(), basicDBObject_1);
			} else {
				basicDBObject.put("Nothing", "*");
			}
			
			DBCursor dbCursor1 = dbColl.find(basicDBObject);
			if(dbCursor1!=null){
				count = dbCursor1.count();
			}
				System.out.println("Size of the ministryService proxy with searchPattern "+searchPattern+" and on the column "+columnEnum.getMessageKey()+" is "+count);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
		return count;
	}

	@Override
	public MinistryProxyServicesVO[] search(String searchPattern,
			MinistryProxyServiceColumnEnum columnEnum) throws BaseException {
		MongoClient mongoCon = null;
		List<MinistryProxyServicesVO> mpServiceList = new ArrayList<MinistryProxyServicesVO>();
		try{
			System.out.println("In searching count method of mpServiceList with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_PROXY_SERVICE);
			
			BasicDBObject basicDBObject  = new BasicDBObject();
			//Pattern pattern = null;
			BasicDBObject basicDBObject_1  = null;
			if(searchPattern!=null){
				if(!searchPattern.equalsIgnoreCase("*")){
					basicDBObject_1 = new BasicDBObject("$regex", UtilFactory.getInstance().getSearchPattern(searchPattern));
				} else {
					basicDBObject_1  = new BasicDBObject("$ne", "");
				}
			}
			if(columnEnum.getMessageKey().equals(MinistryProxyServiceColumnEnum.MINSTRY_NAME.getMessageKey())){
				basicDBObject.put(MinistryProxyServiceColumnEnum.MINSTRY_NAME.getMessageKey(), basicDBObject_1);
			} else if(columnEnum.getMessageKey().equals(MinistryProxyServiceColumnEnum.SERVICE_NAME.getMessageKey())){
				basicDBObject.put(MinistryProxyServiceColumnEnum.SERVICE_NAME.getMessageKey(), basicDBObject_1);
			} 
			else if(columnEnum.getMessageKey().equals(MinistryProxyServiceColumnEnum.MINSTRY_PROXY_NAME.getMessageKey())){
				basicDBObject.put(MinistryProxyServiceColumnEnum.MINSTRY_PROXY_NAME.getMessageKey(), basicDBObject_1);
			} 
			else if(columnEnum.getMessageKey().equals(MinistryProxyServiceColumnEnum.SERVICE_TYPE.getMessageKey())){
				basicDBObject.put(MinistryProxyServiceColumnEnum.SERVICE_TYPE.getMessageKey(), basicDBObject_1);
			} 
			else if(columnEnum.getMessageKey().equals(MinistryProxyServiceColumnEnum.ENDPOINT_1.getMessageKey())){
				basicDBObject.put(MinistryProxyServiceColumnEnum.ENDPOINT_1.getMessageKey(), basicDBObject_1);
			} 
			
			else if(columnEnum.getMessageKey().equals(MinistryProxyServiceColumnEnum.ENDPOINT_2.getMessageKey())){
				basicDBObject.put(MinistryProxyServiceColumnEnum.ENDPOINT_2.getMessageKey(), basicDBObject_1);
			} 
			
			else if(columnEnum.getMessageKey().equals(MinistryProxyServiceColumnEnum.KEYWORDS.getMessageKey())){
				basicDBObject.put(MinistryProxyServiceColumnEnum.KEYWORDS.getMessageKey(), basicDBObject_1);
			} 
			else {
				basicDBObject.put("Nothing", "*");
			}
			
			DBCursor dbCursor1 = dbColl.find(basicDBObject);
			MinistryProxyServicesVO mpServicesVO = null;
			while(dbCursor1.hasNext()){
				DBObject obj = dbCursor1.next();
				mpServicesVO = ObjectConstructHelper.prepareMinistryProxyServiceObject(obj);
				mpServiceList.add(mpServicesVO);
			}
				System.out.println("Size of the categorys with searchPattern "+searchPattern+" and on the column "+columnEnum.getMessageKey()+" is "+mpServiceList.size());
				
//				for (eServiceVO eeee : eServiceList) {
//					
//					System.out.println("AAA   "+eeee.getEserviceName());
//					
//				}
				
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
		return mpServiceList.toArray(new MinistryProxyServicesVO[0]);
	}



}
