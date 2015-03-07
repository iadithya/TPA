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
import com.server.dao.daoapi.IBPELServiceDAO;
import com.server.dao.enums.BPELServiceColumnEnum;
import com.server.execption.BaseException;
import com.server.vo.BPELServicesVO;


public class BPELServiceDAOImpl extends AbstractMongoClientDB implements IBPELServiceDAO {

	@Override
	public void createBPELService(BPELServicesVO bpelServicesVO)
			throws BaseException {
		MongoClient mongoCon = null;
		try{
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_SERVICE);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBBPELServiceObject(bpelServicesVO);
			dbColl.insert(dbObject);
			System.out.println("BPEL Service  is created "+bpelServicesVO.getBpelServiceName()+"  ");
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
	public void deleteBPELServiceByID(String bpelServiceId)
			throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_SERVICE);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(bpelServiceId));
			dbColl.remove(basicDBObject);
			System.out.println("bpelServiceId is deleted with id "+bpelServiceId);
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
	public void deleteBPELService(String name) throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_SERVICE);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(BPELServiceColumnEnum.BPEL_SERVICE_NAME.getMessageKey(), name);
			dbColl.remove(basicDBObject);
			System.out.println("BPEL_SERVICE_NAME is deleted with name "+name);
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
	public void updateBPELService(BPELServicesVO bpelService)
			throws BaseException {
		MongoClient mongoCon = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_SERVICE);
			//dbColl.setObjectClass(MinistryServicesVO.class);
			BasicDBObject dbObject = ObjectConstructHelper.prepareBasicDBBPELServiceObject(bpelService);
			dbObject.put(BPELServiceColumnEnum.UPDATED_DATE.getMessageKey(), bpelService.getUpdateDate());
			dbColl.update(new BasicDBObject().append(Constants.OBJECT_ID, new ObjectId(bpelService.getGuid())), dbObject);
			System.out.println("bpelService is updated with id "+bpelService.getGuid());
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
	public BPELServicesVO getBPELService(String name) throws BaseException {
		MongoClient mongoCon = null;
		BPELServicesVO mService = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_SERVICE);
			
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(BPELServiceColumnEnum.BPEL_SERVICE_NAME.getMessageKey(), name);
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				mService = new BPELServicesVO();
				DBObject obj = dbCursor.next();
				
				mService = ObjectConstructHelper.prepareBPELServiceObject(obj);
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
	public BPELServicesVO getBPELServiceById(String bpelServiceId)
			throws BaseException {
		MongoClient mongoCon = null;
		BPELServicesVO bpelService = null;
		try{
			
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_SERVICE);
			//dbColl.setObjectClass(Role.class);
			BasicDBObject basicDBObject  = new BasicDBObject();
			basicDBObject.put(Constants.OBJECT_ID, new ObjectId(bpelServiceId));
			DBCursor dbCursor = dbColl.find(basicDBObject);
			if(dbCursor.hasNext()){
				bpelService = new BPELServicesVO();
				DBObject obj = dbCursor.next();
				bpelService = ObjectConstructHelper.prepareBPELServiceObject(obj);
			}
			System.out.println("bpelService is fetched with id "+bpelServiceId);
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
	public int searchCount(String searchPattern,
			BPELServiceColumnEnum columnEnum) throws BaseException {
		MongoClient mongoCon = null;
		int count=0;
		try{
			System.out.println("In searching count method of categorys with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_SERVICE);
			
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
			if(columnEnum.getMessageKey().equals(BPELServiceColumnEnum.SERVICE_NAME.getMessageKey())){
				basicDBObject.put(BPELServiceColumnEnum.SERVICE_NAME.getMessageKey(), basicDBObject_1);
			} else if(columnEnum.getMessageKey().equals(BPELServiceColumnEnum.MINSTRY_NAME.getMessageKey())){
				basicDBObject.put(BPELServiceColumnEnum.MINSTRY_NAME.getMessageKey(), basicDBObject_1);
			} 
			
			else if(columnEnum.getMessageKey().equals(BPELServiceColumnEnum.BPEL_SERVICE_NAME.getMessageKey())){
				basicDBObject.put(BPELServiceColumnEnum.BPEL_SERVICE_NAME.getMessageKey(), basicDBObject_1);
			}
			else {
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
	public BPELServicesVO[] search(String searchPattern,
			BPELServiceColumnEnum columnEnum) throws BaseException {
		MongoClient mongoCon = null;
		List<BPELServicesVO> mServiceList = new ArrayList<BPELServicesVO>();
		try{
			System.out.println("In searching count method of categorys with pattern as "+searchPattern+" and on column "+columnEnum.getMessageKey());
			mongoCon = getMongoClientConnection();
			DB db = mongoCon.getDB(Constants.DB_SELECTION);
			DBCollection dbColl = db.getCollection(Constants.BPEL_SERVICE);
			
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
			if(columnEnum.getMessageKey().equals(BPELServiceColumnEnum.MINSTRY_NAME.getMessageKey())){
				basicDBObject.put(BPELServiceColumnEnum.MINSTRY_NAME.getMessageKey(), basicDBObject_1);
			} else if(columnEnum.getMessageKey().equals(BPELServiceColumnEnum.SERVICE_NAME.getMessageKey())){
				basicDBObject.put(BPELServiceColumnEnum.SERVICE_NAME.getMessageKey(), basicDBObject_1);
			} 
			
			else if(columnEnum.getMessageKey().equals(BPELServiceColumnEnum.BPEL_SERVICE_NAME.getMessageKey())){
				basicDBObject.put(BPELServiceColumnEnum.BPEL_SERVICE_NAME.getMessageKey(), basicDBObject_1);
			} 
			
			else if(columnEnum.getMessageKey().equals(BPELServiceColumnEnum.SERVICE_TYPE.getMessageKey())){
				basicDBObject.put(BPELServiceColumnEnum.SERVICE_TYPE.getMessageKey(), basicDBObject_1);
			} 
			else if(columnEnum.getMessageKey().equals(BPELServiceColumnEnum.ENDPOINT_1.getMessageKey())){
				basicDBObject.put(BPELServiceColumnEnum.ENDPOINT_1.getMessageKey(), basicDBObject_1);
			} 
			else if(columnEnum.getMessageKey().equals(BPELServiceColumnEnum.KEYWORDS.getMessageKey())){
				basicDBObject.put(BPELServiceColumnEnum.KEYWORDS.getMessageKey(), basicDBObject_1);
			} 
			else {
				basicDBObject.put("Nothing", "*");
			}
			
			DBCursor dbCursor1 = dbColl.find(basicDBObject);
			BPELServicesVO bpelServicesVO = null;
			while(dbCursor1.hasNext()){
				DBObject obj = dbCursor1.next();
				bpelServicesVO = ObjectConstructHelper.prepareBPELServiceObject(obj);
				mServiceList.add(bpelServicesVO);
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
		return mServiceList.toArray(new BPELServicesVO[0]);
	}

	
}
