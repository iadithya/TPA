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
import com.server.dao.daoapi.IBPELProxyServiceDAO;
import com.server.dao.enums.BPELProxyServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.BPELProxyServicesVO;

public class BPELProxyServiceDAOImpl extends AbstractMongoClientDB implements IBPELProxyServiceDAO {

	@Override
	public void createBPELProxyService(BPELProxyServicesVO bpelProxyServicesVO)
			throws BaseException {
		MongoClient mongoCon = null;
		try{
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_PROXY_SERVICE);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBBPELProxyServiceObject(bpelProxyServicesVO);
			dbColl.insert(dbObject);
			System.out.println("BPEL PROXY Service  is created "+bpelProxyServicesVO.getBpelProxyName()+"  ");
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
	public void deleteBPELProxyServiceByID(String bpelProxyServiceId)
			throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_PROXY_SERVICE);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(bpelProxyServiceId));
			dbColl.remove(basicDBObject);
			System.out.println("bpelProxyServiceId is deleted with id "+bpelProxyServiceId);
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
	public void deleteBPELProxyService(String name) throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_PROXY_SERVICE);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(BPELProxyServiceColumnsEnum.BPEL_PROXY_SERVICE_NAME.getMessageKey(), name);
			dbColl.remove(basicDBObject);
			System.out.println("BPEL_PROXY_SERVICE_NAME is deleted with name "+name);
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
	public void updateBPELProxyService(BPELProxyServicesVO bpelProxyService)
			throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_PROXY_SERVICE);
			//dbColl.setObjectClass(MinistryServicesVO.class);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBBPELProxyServiceObject(bpelProxyService);
			dbObject.put(BPELProxyServiceColumnsEnum.UPDATED_DATE.getMessageKey(), bpelProxyService.getUpdateDate());
			dbColl.update(new BasicDBObject().append(Constants.OBJECT_ID, new ObjectId(bpelProxyService.getGuid())), dbObject);
			System.out.println("bpelProxyService is updated with id "+bpelProxyService.getGuid());
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
	public BPELProxyServicesVO getBPELProxyService(String name)
			throws BaseException {
		MongoClient mongoCon = null;
		BPELProxyServicesVO bpelService = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_PROXY_SERVICE);
			
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(BPELProxyServiceColumnsEnum.BPEL_PROXY_SERVICE_NAME.getMessageKey(), name);
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				bpelService = new BPELProxyServicesVO();
				DBObject obj = dbCursor.next();
				
				bpelService = ObjectConstructHelper.prepareBPELProxyServiceObject(obj);
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
		return bpelService;
	}

	@Override
	public BPELProxyServicesVO getBPELProxyServiceById(String bpelProxyServiceId)
			throws BaseException {
		MongoClient mongoCon = null;
		BPELProxyServicesVO bpelpService = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_PROXY_SERVICE);
			//dbColl.setObjectClass(Role.class);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(bpelProxyServiceId));
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				bpelpService = new BPELProxyServicesVO();
				DBObject obj = dbCursor.next();
				bpelpService = ObjectConstructHelper.prepareBPELProxyServiceObject(obj);
			}
			System.out.println("bpelpService is fetched with id "+bpelpService);
		}catch(Exception ex){
			throw new BaseException(ex.getMessage(), ex);
		} finally {
			try{
				//mongoCon.close();
			}catch(Exception e){
				throw new BaseException(e.getMessage(), e);
			}
		}
		return bpelpService;
	}

	@Override
	public int searchCount(String searchPattern,
			BPELProxyServiceColumnsEnum columnEnum) throws BaseException {
		MongoClient mongoCon = null;
		int count=0;
		try{
			System.out.println("In searching count method of categorys with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_PROXY_SERVICE);
			
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
			if(columnEnum.getMessageKey().equals(BPELProxyServiceColumnsEnum.SERVICE_NAME.getMessageKey())){
				basicDBObject.put(BPELProxyServiceColumnsEnum.SERVICE_NAME.getMessageKey(), basicDBObject_1);
			}	else if(columnEnum.getMessageKey().equals(BPELProxyServiceColumnsEnum.BPEL_PROXY_SERVICE_NAME.getMessageKey())){
					basicDBObject.put(BPELProxyServiceColumnsEnum.BPEL_PROXY_SERVICE_NAME.getMessageKey(), basicDBObject_1);
			} else if(columnEnum.getMessageKey().equals(BPELProxyServiceColumnsEnum.MINSTRY_NAME.getMessageKey())){
				basicDBObject.put(BPELProxyServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), basicDBObject_1);
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
	public BPELProxyServicesVO[] search(String searchPattern,
			BPELProxyServiceColumnsEnum columnEnum) throws BaseException {
		MongoClient mongoCon = null;
		List<BPELProxyServicesVO> mpServiceList = new ArrayList<BPELProxyServicesVO>();
		try{
			System.out.println("In searching count method of mpServiceList with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_PROXY_SERVICE);
			
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
			if(columnEnum.getMessageKey().equals(BPELProxyServiceColumnsEnum.MINSTRY_NAME.getMessageKey())){
				basicDBObject.put(BPELProxyServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), basicDBObject_1);
			} else if(columnEnum.getMessageKey().equals(BPELProxyServiceColumnsEnum.SERVICE_NAME.getMessageKey())){
				basicDBObject.put(BPELProxyServiceColumnsEnum.SERVICE_NAME.getMessageKey(), basicDBObject_1);
			} 
			else if(columnEnum.getMessageKey().equals(BPELProxyServiceColumnsEnum.BPEL_PROXY_SERVICE_NAME.getMessageKey())){
				basicDBObject.put(BPELProxyServiceColumnsEnum.BPEL_PROXY_SERVICE_NAME.getMessageKey(), basicDBObject_1);
			} 
			else if(columnEnum.getMessageKey().equals(BPELProxyServiceColumnsEnum.SERVICE_TYPE.getMessageKey())){
				basicDBObject.put(BPELProxyServiceColumnsEnum.SERVICE_TYPE.getMessageKey(), basicDBObject_1);
			} 
			else if(columnEnum.getMessageKey().equals(BPELProxyServiceColumnsEnum.ENDPOINT_1.getMessageKey())){
				basicDBObject.put(BPELProxyServiceColumnsEnum.ENDPOINT_1.getMessageKey(), basicDBObject_1);
			} 
			
			else if(columnEnum.getMessageKey().equals(BPELProxyServiceColumnsEnum.ENDPOINT_2.getMessageKey())){
				basicDBObject.put(BPELProxyServiceColumnsEnum.ENDPOINT_2.getMessageKey(), basicDBObject_1);
			} 
			
			else if(columnEnum.getMessageKey().equals(BPELProxyServiceColumnsEnum.KEYWORDS.getMessageKey())){
				basicDBObject.put(BPELProxyServiceColumnsEnum.KEYWORDS.getMessageKey(), basicDBObject_1);
			} 
			else {
				basicDBObject.put("Nothing", "*");
			}
			
			DBCursor dbCursor1 = dbColl.find(basicDBObject);
			BPELProxyServicesVO bpelpServicesVO = null;
			while(dbCursor1.hasNext()){
				DBObject obj = dbCursor1.next();
				bpelpServicesVO = ObjectConstructHelper.prepareBPELProxyServiceObject(obj);
				mpServiceList.add(bpelpServicesVO);
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
		return mpServiceList.toArray(new BPELProxyServicesVO[0]);
	}



	
	
	
	
	
	
	
	
	}

