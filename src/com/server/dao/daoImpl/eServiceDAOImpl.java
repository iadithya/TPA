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
import com.server.dao.daoapi.IeServiceDAO;
import com.server.dao.enums.RoleColumnsEnum;
import com.server.dao.enums.eServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.eServiceVO;

public class eServiceDAOImpl extends AbstractMongoClientDB implements IeServiceDAO{

	@Override
	public void createEservice(eServiceVO eServiceVO) throws BaseException {
	
		MongoClient mongoCon = null;
		try{
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.eServices);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBeServiceObject(eServiceVO);
			dbColl.insert(dbObject);
			System.out.println("eService is created "+eServiceVO.getEserviceName()+"  ");
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
	public void deleteeService(String name) throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.eServices);
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
	public void deleteeServiceByID(String eServiceID) throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.eServices);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(eServiceID));
			dbColl.remove(basicDBObject);
			System.out.println("Role is deleted with id "+eServiceID);
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
	public void updateeService(eServiceVO eService) throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.eServices);
			//dbColl.setObjectClass(Role.class);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBeServiceObject(eService);
			dbObject.put(eServiceColumnsEnum.UPDATED_DATE.getMessageKey(), eService.getUpdateDate());
			dbColl.update(new BasicDBObject().append(Constants.OBJECT_ID, new ObjectId(eService.getGuid())), dbObject);
			System.out.println("eService is updated with id "+eService.getGuid());
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
	public eServiceVO geteService(String name) throws BaseException {
		MongoClient mongoCon = null;
		eServiceVO eService = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.eServices);
			
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(eServiceColumnsEnum.eSERVICE_NAME.getMessageKey(), name);
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				//eServiceVO = new eServiceVO();
				DBObject obj = dbCursor.next();
				
				eService = ObjectConstructHelper.prepareeServiceObject(obj);
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
		return eService;
	}

	@Override
	public eServiceVO geteServiceById(String eServiceId) throws BaseException {
		MongoClient mongoCon = null;
		eServiceVO eService = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.eServices);
			//dbColl.setObjectClass(Role.class);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(eServiceId));
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				eService = new eServiceVO();
				DBObject obj = dbCursor.next();
				eService = ObjectConstructHelper.prepareeServiceObject(obj);
			}
			System.out.println("eService is fetched with id "+eServiceId);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
		return eService;
	}

	
	@Override
	public int searchCount(String searchPattern, eServiceColumnsEnum columnEnum) throws BaseException {
		MongoClient mongoCon = null;
		int count=0;
		try{
			System.out.println("In searching count method of categorys with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.eServices);
			
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
			if(columnEnum.getMessageKey().equals(eServiceColumnsEnum.eSERVICE_NAME.getMessageKey())){
				basicDBObject.put(eServiceColumnsEnum.eSERVICE_NAME.getMessageKey(), basicDBObject_1);
			} else if(columnEnum.getMessageKey().equals(eServiceColumnsEnum.MINSTRY_NAME.getMessageKey())){
				basicDBObject.put(eServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), basicDBObject_1);
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
	public eServiceVO[] search(String searchPattern, eServiceColumnsEnum columnEnum) throws BaseException {
		MongoClient mongoCon = null;
		List<eServiceVO> eServiceList = new ArrayList<eServiceVO>();
		try{
			System.out.println("In searching count method of categorys with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.eServices);
			
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
			if(columnEnum.getMessageKey().equals(eServiceColumnsEnum.eSERVICE_NAME.getMessageKey())){
				basicDBObject.put(eServiceColumnsEnum.eSERVICE_NAME.getMessageKey(), basicDBObject_1);
			} else if(columnEnum.getMessageKey().equals(eServiceColumnsEnum.MINSTRY_NAME.getMessageKey())){
				basicDBObject.put(eServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), basicDBObject_1);
			} else {
				basicDBObject.put("Nothing", "*");
			}
			
			DBCursor dbCursor1 = dbColl.find(basicDBObject);
			eServiceVO category = null;
			while(dbCursor1.hasNext()){
				DBObject obj = dbCursor1.next();
				category = ObjectConstructHelper.prepareeServiceObject(obj);
				eServiceList.add(category);
			}
				System.out.println("Size of the categorys with searchPattern "+searchPattern+" and on the column "+columnEnum.getMessageKey()+" is "+eServiceList.size());
				
				for (eServiceVO eeee : eServiceList) {
					
					System.out.println("AAA   "+eeee.getEserviceName());
					
				}
				
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
		return eServiceList.toArray(new eServiceVO[0]);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
