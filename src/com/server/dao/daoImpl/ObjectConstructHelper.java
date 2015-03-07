package com.server.dao.daoImpl;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.server.constants.Constants;
import com.server.dao.enums.BPELProxyServiceColumnsEnum;
import com.server.dao.enums.BPELServiceColumnEnum;
import com.server.dao.enums.MinistryProxyServiceColumnEnum;
import com.server.dao.enums.MinistryServiceColumnsEnum;
import com.server.dao.enums.RoleColumnsEnum;
import com.server.dao.enums.UserColumnsEnum;
import com.server.dao.enums.eServiceColumnsEnum;
import com.server.vo.BPELProxyServicesVO;
import com.server.vo.BPELServicesVO;
import com.server.vo.MinistryProxyServicesVO;
import com.server.vo.MinistryServicesVO;
import com.server.vo.RoleVO;
import com.server.vo.UserVO;
import com.server.vo.eServiceVO;

public class ObjectConstructHelper {

	
	public ObjectConstructHelper() {

	}

	public static BasicDBObject prepareBasicDBUserObject(UserVO userVO) {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put(UserColumnsEnum.USER_NAME.getMessageKey(), userVO.getUserName());
		dbObject.put(UserColumnsEnum.USER_PASS.getMessageKey(), userVO.getPassword());
		dbObject.put(UserColumnsEnum.LOGIN_ID.getMessageKey(), userVO.getLoginId());
		dbObject.put(UserColumnsEnum.ROLE_NAME.getMessageKey(), userVO.getRollName());
		dbObject.put(UserColumnsEnum.LAST_LOGIN.getMessageKey(), userVO.getLastLogin());
		dbObject.put(UserColumnsEnum.CREATED_DATE.getMessageKey(), userVO.getCreateDate());
		dbObject.put(UserColumnsEnum.UPDATED_DATE.getMessageKey(), userVO.getUpdateDate());
		dbObject.put(UserColumnsEnum.CREATED_BY.getMessageKey(), userVO.getCreatedBy());
		dbObject.put(UserColumnsEnum.UPDATED_BY.getMessageKey(), userVO.getUpdatedBy());
		dbObject.put(UserColumnsEnum.IS_ACTIVE.getMessageKey(), userVO.isActive());
		
		return dbObject;
	}

	public static UserVO prepareUserObject(DBObject dbObject) {
		UserVO user = new UserVO();
		user.setId((ObjectId)dbObject.get(Constants.OBJECT_ID));
		user.setUserName((String) dbObject.get(UserColumnsEnum.USER_NAME.getMessageKey()));
		user.setLoginId((String) dbObject.get(UserColumnsEnum.LOGIN_ID.getMessageKey()));
		user.setActive((Boolean) dbObject.get(UserColumnsEnum.IS_ACTIVE.getMessageKey()));
		user.setCreatedBy((String) dbObject.get(UserColumnsEnum.CREATED_BY.getMessageKey()));
		user.setRollName((String) dbObject.get(UserColumnsEnum.ROLE_NAME.getMessageKey()));
		user.setUpdatedBy((String) dbObject.get(UserColumnsEnum.UPDATED_BY.getMessageKey()));
		user.setUpdateDate((Date) dbObject.get(UserColumnsEnum.UPDATED_DATE.getMessageKey()));
		user.setCreateDate((Date) dbObject.get(UserColumnsEnum.CREATED_DATE.getMessageKey()));
		user.setLastLogin((Date) dbObject.get(UserColumnsEnum.LAST_LOGIN.getMessageKey()));
		if(dbObject.get(UserColumnsEnum.USER_PASS.getMessageKey()) !=null ){
			user.setPassword((String) dbObject.get(UserColumnsEnum.USER_PASS.getMessageKey()));
		}
		return user;
	}
	
	public static BasicDBObject prepareBasicDBRoleObject(RoleVO role) {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put(RoleColumnsEnum.NAME.getMessageKey(), role.getName());
		dbObject.put(RoleColumnsEnum.UPDATED_DATE.getMessageKey(), role.getUpdateDate());
		dbObject.put(RoleColumnsEnum.CREATED_BY.getMessageKey(), role.getCreatedBy());
		return dbObject;
	}

	public static RoleVO prepareRoleObject(DBObject dbObject) {
		RoleVO role = new RoleVO();
		role.setId((ObjectId)dbObject.get(Constants.OBJECT_ID));
		role.setName((String) dbObject.get(RoleColumnsEnum.NAME.getMessageKey()));
		role.setCreatedBy((String) dbObject.get(RoleColumnsEnum.CREATED_BY.getMessageKey()));
		role.setUpdateDate((Date) dbObject.get(RoleColumnsEnum.UPDATED_DATE.getMessageKey()));
		return role;
	}

	public static BasicDBObject prepareBasicDBeServiceObject(eServiceVO eServiceVO) {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put(eServiceColumnsEnum.eSERVICE_NAME.getMessageKey(), eServiceVO.getEserviceName());
		dbObject.put(eServiceColumnsEnum.UPDATED_DATE.getMessageKey(), eServiceVO.getUpdateDate());
		dbObject.put(eServiceColumnsEnum.CREATED_BY.getMessageKey(), eServiceVO.getCreatedBy());
		dbObject.put(eServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), eServiceVO.getMinstryName());
		dbObject.put(eServiceColumnsEnum.PROJECT_MANAGER_NAME.getMessageKey(), eServiceVO.getProjectManagerName());
		return dbObject;
	}

	public static eServiceVO prepareeServiceObject(DBObject dbObject) {
		eServiceVO eService  = new eServiceVO();
		eService.setId((ObjectId)dbObject.get(Constants.OBJECT_ID));
		eService.setEserviceName((String) dbObject.get(eServiceColumnsEnum.eSERVICE_NAME.getMessageKey()));
		eService.setMinstryName((String) dbObject.get(eServiceColumnsEnum.MINSTRY_NAME.getMessageKey()));
		eService.setProjectManagerName((String) dbObject.get(eServiceColumnsEnum.PROJECT_MANAGER_NAME.getMessageKey()));
		eService.setCreatedBy((String) dbObject.get(eServiceColumnsEnum.CREATED_BY.getMessageKey()));
		eService.setUpdateDate((Date) dbObject.get(eServiceColumnsEnum.UPDATED_DATE.getMessageKey()));
		return eService;
	}

	public static BasicDBObject prepareBasicDBMinistryServiceObject(MinistryServicesVO mServicesVO) {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put(MinistryServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), mServicesVO.getMinstryName());
		dbObject.put(MinistryServiceColumnsEnum.UPDATED_DATE.getMessageKey(), mServicesVO.getUpdateDate());
		dbObject.put(MinistryServiceColumnsEnum.CREATED_BY.getMessageKey(), mServicesVO.getCreatedBy());
		dbObject.put(MinistryServiceColumnsEnum.SERVICE_TYPE.getMessageKey(), mServicesVO.getServiceType());
		dbObject.put(MinistryServiceColumnsEnum.SERVICE_NAME.getMessageKey(), mServicesVO.getServiceName());
		dbObject.put(MinistryServiceColumnsEnum.ESERVICE_ID.getMessageKey(), mServicesVO.getEserviceId());
		dbObject.put(MinistryServiceColumnsEnum.ENDPOINT.getMessageKey(), mServicesVO.getEndpoint());
		dbObject.put(MinistryServiceColumnsEnum.KEYWORDS.getMessageKey(), mServicesVO.getKeywords());
		dbObject.put(MinistryServiceColumnsEnum.CREATED_DATE.getMessageKey(), mServicesVO.getCreateDate());
		dbObject.put(MinistryServiceColumnsEnum.IS_WS_SECURTITY.getMessageKey(), mServicesVO.isWSSecurity());
		return dbObject;
	}

	public static MinistryServicesVO prepareMinistryServiceObject(DBObject dbObject) {
		MinistryServicesVO mService  = new MinistryServicesVO();
		mService.setId((ObjectId)dbObject.get(Constants.OBJECT_ID));
		mService.setMinstryName((String) dbObject.get(MinistryServiceColumnsEnum.MINSTRY_NAME.getMessageKey()));
		mService.setServiceName((String) dbObject.get(MinistryServiceColumnsEnum.SERVICE_NAME.getMessageKey()));
		mService.setServiceType((String) dbObject.get(MinistryServiceColumnsEnum.SERVICE_TYPE.getMessageKey()));
		mService.setEndpoint((String) dbObject.get(MinistryServiceColumnsEnum.ENDPOINT.getMessageKey()));
		mService.setEserviceId((String) dbObject.get(MinistryServiceColumnsEnum.ESERVICE_ID.getMessageKey()));
		mService.setKeywords((String) dbObject.get(MinistryServiceColumnsEnum.KEYWORDS.getMessageKey()));
		mService.setCreatedBy((String) dbObject.get(MinistryServiceColumnsEnum.CREATED_BY.getMessageKey()));
		mService.setUpdateDate((Date) dbObject.get(MinistryServiceColumnsEnum.UPDATED_DATE.getMessageKey()));
		mService.setWSSecurity((Boolean) dbObject.get(MinistryServiceColumnsEnum.IS_WS_SECURTITY.getMessageKey()));

		
		return mService;
	}

	public static BasicDBObject prepareBasicDBMinistryProxyServiceObject(
			MinistryProxyServicesVO mpServicesVO) {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put(MinistryProxyServiceColumnEnum.MINSTRY_NAME.getMessageKey(), mpServicesVO.getMinistryName());
		dbObject.put(MinistryProxyServiceColumnEnum.MINSTRY_PROXY_NAME.getMessageKey(), mpServicesVO.getMinistryProxyName());
		dbObject.put(MinistryProxyServiceColumnEnum.UPDATED_DATE.getMessageKey(), mpServicesVO.getUpdateDate());
		dbObject.put(MinistryProxyServiceColumnEnum.CREATED_BY.getMessageKey(), mpServicesVO.getCreatedBy());
		dbObject.put(MinistryProxyServiceColumnEnum.SERVICE_TYPE.getMessageKey(), mpServicesVO.getServiceType());
		dbObject.put(MinistryProxyServiceColumnEnum.SERVICE_NAME.getMessageKey(), mpServicesVO.getServiceName());
		dbObject.put(MinistryProxyServiceColumnEnum.ESERVICE_ID.getMessageKey(), mpServicesVO.getEserviceId());
		dbObject.put(MinistryProxyServiceColumnEnum.ENDPOINT_1.getMessageKey(), mpServicesVO.getEndpoint1());
		dbObject.put(MinistryProxyServiceColumnEnum.ENDPOINT_2.getMessageKey(), mpServicesVO.getEndpoint2());
		dbObject.put(MinistryProxyServiceColumnEnum.KEYWORDS.getMessageKey(), mpServicesVO.getKeywords());
		dbObject.put(MinistryProxyServiceColumnEnum.CREATED_DATE.getMessageKey(), mpServicesVO.getCreateDate());
		dbObject.put(MinistryProxyServiceColumnEnum.IS_WS_SECURTITY_1.getMessageKey(), mpServicesVO.isWSSecurity1());
		dbObject.put(MinistryProxyServiceColumnEnum.IS_WS_SECURTITY_2.getMessageKey(), mpServicesVO.isWSSecurity2());

		return dbObject;
	}

	public static MinistryProxyServicesVO prepareMinistryProxyServiceObject(
			DBObject dbObject) {
		MinistryProxyServicesVO mpService  = new MinistryProxyServicesVO();
		mpService.setId((ObjectId)dbObject.get(Constants.OBJECT_ID));
		mpService.setMinistryProxyName((String) dbObject.get(MinistryProxyServiceColumnEnum.MINSTRY_PROXY_NAME.getMessageKey()));
		mpService.setMinistryName((String) dbObject.get(MinistryProxyServiceColumnEnum.MINSTRY_NAME.getMessageKey()));
		mpService.setServiceName((String) dbObject.get(MinistryProxyServiceColumnEnum.SERVICE_NAME.getMessageKey()));
		mpService.setServiceType((String) dbObject.get(MinistryProxyServiceColumnEnum.SERVICE_TYPE.getMessageKey()));
		mpService.setEndpoint1((String) dbObject.get(MinistryProxyServiceColumnEnum.ENDPOINT_1.getMessageKey()));
		mpService.setEndpoint2((String) dbObject.get(MinistryProxyServiceColumnEnum.ENDPOINT_2.getMessageKey()));
		mpService.setEserviceId((String) dbObject.get(MinistryProxyServiceColumnEnum.ESERVICE_ID.getMessageKey()));
		mpService.setKeywords((String) dbObject.get(MinistryProxyServiceColumnEnum.KEYWORDS.getMessageKey()));
		mpService.setCreatedBy((String) dbObject.get(MinistryProxyServiceColumnEnum.CREATED_BY.getMessageKey()));
		mpService.setUpdateDate((Date) dbObject.get(MinistryProxyServiceColumnEnum.UPDATED_DATE.getMessageKey()));
		mpService.setWSSecurity1((Boolean) dbObject.get(MinistryProxyServiceColumnEnum.IS_WS_SECURTITY_1.getMessageKey()));
		mpService.setWSSecurity2((Boolean) dbObject.get(MinistryProxyServiceColumnEnum.IS_WS_SECURTITY_2.getMessageKey()));


		
		return mpService;

	}

	public static BasicDBObject prepareBasicDBBPELServiceObject(
			BPELServicesVO bpelServicesVO) {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put(BPELServiceColumnEnum.MINSTRY_NAME.getMessageKey(), bpelServicesVO.getMinistryName());
		dbObject.put(BPELServiceColumnEnum.BPEL_SERVICE_NAME.getMessageKey(), bpelServicesVO.getBpelServiceName());
		dbObject.put(BPELServiceColumnEnum.UPDATED_DATE.getMessageKey(), bpelServicesVO.getUpdateDate());
		dbObject.put(BPELServiceColumnEnum.CREATED_BY.getMessageKey(), bpelServicesVO.getCreatedBy());
		dbObject.put(BPELServiceColumnEnum.SERVICE_TYPE.getMessageKey(), bpelServicesVO.getServiceType());
		dbObject.put(BPELServiceColumnEnum.SERVICE_NAME.getMessageKey(), bpelServicesVO.getServiceName());
		dbObject.put(BPELServiceColumnEnum.ESERVICE_ID.getMessageKey(), bpelServicesVO.getEserviceId());
		dbObject.put(BPELServiceColumnEnum.ENDPOINT_1.getMessageKey(), bpelServicesVO.getEndpoint1());
		dbObject.put(BPELServiceColumnEnum.ENDPOINT_2.getMessageKey(), bpelServicesVO.getEndpoint2());
		dbObject.put(BPELServiceColumnEnum.KEYWORDS.getMessageKey(), bpelServicesVO.getKeywords());
		dbObject.put(BPELServiceColumnEnum.CREATED_DATE.getMessageKey(), bpelServicesVO.getCreateDate());
		dbObject.put(BPELServiceColumnEnum.IS_WS_SECURTITY_1.getMessageKey(), bpelServicesVO.isWSSecurity1());
		dbObject.put(BPELServiceColumnEnum.IS_WS_SECURTITY_2.getMessageKey(), bpelServicesVO.isWSSecurity2());

		return dbObject;
	}

	public static BPELServicesVO prepareBPELServiceObject(DBObject dbObject) {
		BPELServicesVO bpelService  = new BPELServicesVO();
		bpelService.setId((ObjectId)dbObject.get(Constants.OBJECT_ID));
		bpelService.setBpelServiceName((String) dbObject.get(BPELServiceColumnEnum.BPEL_SERVICE_NAME.getMessageKey()));
		bpelService.setMinistryName((String) dbObject.get(BPELServiceColumnEnum.MINSTRY_NAME.getMessageKey()));
		bpelService.setServiceName((String) dbObject.get(BPELServiceColumnEnum.SERVICE_NAME.getMessageKey()));
		bpelService.setServiceType((String) dbObject.get(BPELServiceColumnEnum.SERVICE_TYPE.getMessageKey()));
		bpelService.setEndpoint1((String) dbObject.get(BPELServiceColumnEnum.ENDPOINT_1.getMessageKey()));
		bpelService.setEndpoint2((String) dbObject.get(BPELServiceColumnEnum.ENDPOINT_2.getMessageKey()));
		bpelService.setEserviceId((String) dbObject.get(BPELServiceColumnEnum.ESERVICE_ID.getMessageKey()));
		bpelService.setKeywords((String) dbObject.get(BPELServiceColumnEnum.KEYWORDS.getMessageKey()));
		bpelService.setCreatedBy((String) dbObject.get(BPELServiceColumnEnum.CREATED_BY.getMessageKey()));
		bpelService.setUpdateDate((Date) dbObject.get(BPELServiceColumnEnum.UPDATED_DATE.getMessageKey()));
		bpelService.setWSSecurity1((Boolean) dbObject.get(BPELServiceColumnEnum.IS_WS_SECURTITY_1.getMessageKey()));
		bpelService.setWSSecurity2((Boolean) dbObject.get(BPELServiceColumnEnum.IS_WS_SECURTITY_2.getMessageKey()));

		return bpelService;

	}

	public static BasicDBObject prepareBasicDBBPELProxyServiceObject(
			BPELProxyServicesVO bpelProxyServicesVO) {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put(BPELProxyServiceColumnsEnum.MINSTRY_NAME.getMessageKey(), bpelProxyServicesVO.getMinistryName());
		dbObject.put(BPELProxyServiceColumnsEnum.BPEL_PROXY_SERVICE_NAME.getMessageKey(), bpelProxyServicesVO.getBpelProxyName());
		dbObject.put(BPELProxyServiceColumnsEnum.UPDATED_DATE.getMessageKey(), bpelProxyServicesVO.getUpdateDate());
		dbObject.put(BPELProxyServiceColumnsEnum.CREATED_BY.getMessageKey(), bpelProxyServicesVO.getCreatedBy());
		dbObject.put(BPELProxyServiceColumnsEnum.SERVICE_TYPE.getMessageKey(), bpelProxyServicesVO.getServiceType());
		dbObject.put(BPELProxyServiceColumnsEnum.SERVICE_NAME.getMessageKey(), bpelProxyServicesVO.getServiceName());
		dbObject.put(BPELProxyServiceColumnsEnum.ESERVICE_ID.getMessageKey(), bpelProxyServicesVO.getEserviceId());
		dbObject.put(BPELProxyServiceColumnsEnum.ENDPOINT_1.getMessageKey(), bpelProxyServicesVO.getEndpoint1());
		dbObject.put(BPELProxyServiceColumnsEnum.ENDPOINT_2.getMessageKey(), bpelProxyServicesVO.getEndpoint2());
		dbObject.put(BPELProxyServiceColumnsEnum.KEYWORDS.getMessageKey(), bpelProxyServicesVO.getKeywords());
		dbObject.put(BPELProxyServiceColumnsEnum.CREATED_DATE.getMessageKey(), bpelProxyServicesVO.getCreateDate());
		dbObject.put(BPELProxyServiceColumnsEnum.IS_WS_SECURTITY_1.getMessageKey(), bpelProxyServicesVO.isWSSecurity1());
		dbObject.put(BPELProxyServiceColumnsEnum.IS_WS_SECURTITY_2.getMessageKey(), bpelProxyServicesVO.isWSSecurity2());

		return dbObject;
	}

	public static BPELProxyServicesVO prepareBPELProxyServiceObject(DBObject dbObject) {
		BPELProxyServicesVO bpelpService  = new BPELProxyServicesVO();
		bpelpService.setId((ObjectId)dbObject.get(Constants.OBJECT_ID));
		bpelpService.setBpelProxyName((String) dbObject.get(BPELProxyServiceColumnsEnum.BPEL_PROXY_SERVICE_NAME.getMessageKey()));
		bpelpService.setMinistryName((String) dbObject.get(BPELProxyServiceColumnsEnum.MINSTRY_NAME.getMessageKey()));
		bpelpService.setServiceName((String) dbObject.get(BPELProxyServiceColumnsEnum.SERVICE_NAME.getMessageKey()));
		bpelpService.setServiceType((String) dbObject.get(BPELProxyServiceColumnsEnum.SERVICE_TYPE.getMessageKey()));
		bpelpService.setEndpoint1((String) dbObject.get(BPELProxyServiceColumnsEnum.ENDPOINT_1.getMessageKey()));
		bpelpService.setEndpoint2((String) dbObject.get(BPELProxyServiceColumnsEnum.ENDPOINT_2.getMessageKey()));
		bpelpService.setEserviceId((String) dbObject.get(BPELProxyServiceColumnsEnum.ESERVICE_ID.getMessageKey()));
		bpelpService.setKeywords((String) dbObject.get(BPELProxyServiceColumnsEnum.KEYWORDS.getMessageKey()));
		bpelpService.setCreatedBy((String) dbObject.get(BPELProxyServiceColumnsEnum.CREATED_BY.getMessageKey()));
		bpelpService.setUpdateDate((Date) dbObject.get(BPELProxyServiceColumnsEnum.UPDATED_DATE.getMessageKey()));
		bpelpService.setWSSecurity1((Boolean) dbObject.get(BPELProxyServiceColumnsEnum.IS_WS_SECURTITY_1.getMessageKey()));
		bpelpService.setWSSecurity2((Boolean) dbObject.get(BPELProxyServiceColumnsEnum.IS_WS_SECURTITY_2.getMessageKey()));

		return bpelpService;

	}
}
