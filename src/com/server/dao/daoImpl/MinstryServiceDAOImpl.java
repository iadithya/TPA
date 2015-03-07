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
import com.server.dao.daoapi.IMinstryServiceDAO;
import com.server.dao.enums.MinistryServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.MinistryServicesVO;

public class MinstryServiceDAOImpl extends AbstractMongoClientDB implements IMinstryServiceDAO{

	@Override
	public void createMinistryService(MinistryServicesVO mServicesVO)
			throws BaseException {
		MongoClient mongoCon = null;
		try{
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_SERVICE);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBMinistryServiceObject(mServicesVO);
			dbColl.insert(dbObject);
			System.out.println("Ministry Service  is created "+mServicesVO.getMinstryName()+"  ");
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
	public void deleteMinsitryServiceByID(String mServiceId)
			throws BaseException {
		
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_SERVICE);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(mServiceId));
			dbColl.remove(basicDBObject);
			System.out.println("mServiceId is deleted with id "+mServiceId);
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
	public void deleteMinstryService(String name) throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_SERVICE);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(MinistryServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), name);
			dbColl.remove(basicDBObject);
			System.out.println("MINSTRY_NAME is deleted with name "+name);
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
	public void updateMinstryService(MinistryServicesVO mService)
			throws BaseException {
		
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_SERVICE);
			//dbColl.setObjectClass(MinistryServicesVO.class);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBMinistryServiceObject(mService);
			dbObject.put(MinistryServiceColumnsEnum.UPDATED_DATE.getMessageKey(), mService.getUpdateDate());
			dbColl.update(new BasicDBObject().append(Constants.OBJECT_ID, new ObjectId(mService.getGuid())), dbObject);
			System.out.println("mService is updated with id "+mService.getGuid());
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
	public MinistryServicesVO getMinstryService(String name)
			throws BaseException {
		MongoClient mongoCon = null;
		MinistryServicesVO mService = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_SERVICE);
			
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(MinistryServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), name);
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				mService = new MinistryServicesVO();
				DBObject obj = dbCursor.next();
				
				mService = ObjectConstructHelper.prepareMinistryServiceObject(obj);
			}
			System.out.println("mService is fetched with name "+name);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
		return mService;

	}

	@Override
	public MinistryServicesVO getMinstryServiceById(String mServiceId)
			throws BaseException {
		MongoClient mongoCon = null;
		MinistryServicesVO mService = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_SERVICE);
			//dbColl.setObjectClass(Role.class);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(mServiceId));
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				mService = new MinistryServicesVO();
				DBObject obj = dbCursor.next();
				mService = ObjectConstructHelper.prepareMinistryServiceObject(obj);
			}
			System.out.println("mService is fetched with id "+mServiceId);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
		return mService;

	}

	@Override
	public int searchCount(String searchPattern,
			MinistryServiceColumnsEnum columnEnum) throws BaseException {
		MongoClient mongoCon = null;
		int count=0;
		try{
			System.out.println("In searching count method of categorys with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_SERVICE);
			
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
			if(columnEnum.getMessageKey().equals(MinistryServiceColumnsEnum.SERVICE_NAME.getMessageKey())){
				basicDBObject.put(MinistryServiceColumnsEnum.SERVICE_NAME.getMessageKey(), basicDBObject_1);
			} else if(columnEnum.getMessageKey().equals(MinistryServiceColumnsEnum.MINSTRY_NAME.getMessageKey())){
				basicDBObject.put(MinistryServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), basicDBObject_1);
			} else {
				basicDBObject.put("Nothing", "*");
			}
			
			DBCursor dbCursor1 = dbColl.find(basicDBObject);
			if(dbCursor1!=null){
				count = dbCursor1.count();
			}
				System.out.println("Size of the painPoints with searchPattern "+searchPattern+" and on the column "+columnEnum.getMessageKey()+" is "+count);
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
	public MinistryServicesVO[] search(String searchPattern,
			MinistryServiceColumnsEnum columnEnum) throws BaseException {
		MongoClient mongoCon = null;
		List<MinistryServicesVO> mServiceList = new ArrayList<MinistryServicesVO>();
		try{
			System.out.println("In searching count method of categorys with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.MINISTRY_SERVICE);
			
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
			if(columnEnum.getMessageKey().equals(MinistryServiceColumnsEnum.MINSTRY_NAME.getMessageKey())){
				basicDBObject.put(MinistryServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), basicDBObject_1);
			} else if(columnEnum.getMessageKey().equals(MinistryServiceColumnsEnum.SERVICE_NAME.getMessageKey())){
				basicDBObject.put(MinistryServiceColumnsEnum.SERVICE_NAME.getMessageKey(), basicDBObject_1);
			} 
			
			else if(columnEnum.getMessageKey().equals(MinistryServiceColumnsEnum.SERVICE_TYPE.getMessageKey())){
				basicDBObject.put(MinistryServiceColumnsEnum.SERVICE_TYPE.getMessageKey(), basicDBObject_1);
			} 
			else if(columnEnum.getMessageKey().equals(MinistryServiceColumnsEnum.ENDPOINT.getMessageKey())){
				basicDBObject.put(MinistryServiceColumnsEnum.ENDPOINT.getMessageKey(), basicDBObject_1);
			} 
			else if(columnEnum.getMessageKey().equals(MinistryServiceColumnsEnum.KEYWORDS.getMessageKey())){
				basicDBObject.put(MinistryServiceColumnsEnum.KEYWORDS.getMessageKey(), basicDBObject_1);
			} 
			else {
				basicDBObject.put("Nothing", "*");
			}
			
			DBCursor dbCursor1 = dbColl.find(basicDBObject);
			MinistryServicesVO mServicesVO = null;
			while(dbCursor1.hasNext()){
				DBObject obj = dbCursor1.next();
				mServicesVO = ObjectConstructHelper.prepareMinistryServiceObject(obj);
				mServiceList.add(mServicesVO);
			}
				System.out.println("Size of the categorys with searchPattern "+searchPattern+" and on the column "+columnEnum.getMessageKey()+" is "+mServiceList.size());
				
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
		return mServiceList.toArray(new MinistryServicesVO[0]);
	}
	
	
	
	
	

	
	
}