package com.app.impl;

import java.util.Date;

import com.app.api.IMinistryProxyService;
import com.server.dao.cache.AccessDAOz;
import com.server.dao.daoapi.IMinistryProxyServiceDAO;
import com.server.dao.enums.MinistryProxyServiceColumnEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateMinistryProxyServiceException;
import com.server.execption.NoSuchMinistryProxyServiceException;
import com.server.vo.MinistryProxyServicesVO;

public class MinistryProxyServiceImpl implements IMinistryProxyService {
	
	
	private IMinistryProxyServiceDAO mpServiceDAO = null;
	private String createdBy = null;

	// private String message = null;
	
	

	public MinistryProxyServiceImpl(String createdBy) throws BaseException {
		try {
			mpServiceDAO = AccessDAOz.getInstance().getMinistryProxyServiceDAO();
			this.createdBy = createdBy;
		} catch (Exception e) {
			throw new BaseException(e);
		}
	
	}

	@Override
	public void createMinistryProxyService(MinistryProxyServicesVO mpService)
			throws DuplicateMinistryProxyServiceException, BaseException {
		// boolean isSuccess=false;
		try {
			if (mpService != null) {
				if (mpServiceDAO.getMinstryProxyService(mpService.getMinistryProxyName()) != null) {
					throw new DuplicateMinistryProxyServiceException(
							"mpService is already present with name "
									+ mpService.getMinistryProxyName());
				}
				mpService.setCreatedBy(createdBy);
				mpServiceDAO.createMinistryProxyService(mpService);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG2.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{Role.getName()});
			} else {
				throw new Exception("mpService object is null in create mService method");
			}
		} catch (DuplicateMinistryProxyServiceException ex) {
			throw new DuplicateMinistryProxyServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			throw new BaseException(ex);
		} finally {
			// if(isSuccess){
			// if(Role.getCustomerId()==null ){
			// MonitorLogUtil.createMonitorLog(createdBy, Role,
			// MonitorLogUtil.ENTITYTYPE.USER, MonitorLogUtil.OPERATIONS.CREATE,
			// message);
			// }
			// MonitorLogUtil.createMonitorLog(createdBy, Role,
			// MonitorLogUtil.ENTITYTYPE.USER, MonitorLogUtil.OPERATIONS.CREATE,
			// message);
			//
			// }
		}
		
	}

	@Override
	public void deleteeMinistryProxyService(String name)
			throws NoSuchMinistryProxyServiceException, BaseException {
		MinistryProxyServicesVO mpService = null;
		// boolean isSuccess=false;

		try {
			if (name != null) {

				if ((mpService = mpServiceDAO.getMinstryProxyService(name)) == null) {
					throw new NoSuchMinistryProxyServiceException(
							"No such Ministry Proxy Service is present with name " + name);
				}
				if (mpService != null) {
					mpService.setUpdateDate(new Date());
				}
				mpServiceDAO.deleteMinstryProxyService(name);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{createdBy});
			} else {
				throw new Exception("eService name should not be empty");
			}
		} catch (NoSuchMinistryProxyServiceException ex) {
			throw new NoSuchMinistryProxyServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			throw new BaseException(ex);
		} finally {
			// if(isSuccess){
			// if(Role.getCustomerId()==null ){
			// MonitorLogUtil.createMonitorLog(this.Rolename, Role,
			// MonitorLogUtil.ENTITYTYPE.USER, MonitorLogUtil.OPERATIONS.DELETE,
			// message);
			// }
			// MonitorLogUtil.createMonitorLog(this.createdBy, Role,
			// MonitorLogUtil.ENTITYTYPE.USER, MonitorLogUtil.OPERATIONS.DELETE,
			// message);
			// }

		}

	
	}

	@Override
	public void deleteMinistryProxyServiceByID(String mpServiceId)
			throws NoSuchMinistryProxyServiceException, BaseException {
		MinistryProxyServicesVO mpService = null;
		// boolean isSuccess=false;

		try {
			if (mpServiceId != null) {

				if ((mpService = mpServiceDAO.getMinstryProxyServiceById(mpServiceId)) == null) {
					throw new NoSuchMinistryProxyServiceException(
							"No such Ministry Proxy Service is present with id " + mpServiceId);
				}
				if (mpService != null) {
					mpService.setUpdateDate(new Date());
				}
				mpServiceDAO.deleteMinsitryProxyServiceByID(mpServiceId);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{createdBy});
			} else {
				throw new Exception("mService id should not be empty");
			}
		} catch (NoSuchMinistryProxyServiceException ex) {
			throw new NoSuchMinistryProxyServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			throw new BaseException(ex);
		} finally {
			// if(isSuccess){
			// if(Role.getCustomerId()==null ){
			// MonitorLogUtil.createMonitorLog(this.createdBy, Role,
			// MonitorLogUtil.ENTITYTYPE.USER, MonitorLogUtil.OPERATIONS.DELETE,
			// message);
			// }
			// MonitorLogUtil.createMonitorLog(this.createdBy, Role,
			// MonitorLogUtil.ENTITYTYPE.USER, MonitorLogUtil.OPERATIONS.DELETE,
			// message);
			// }

		}


		
	}

	@Override
	public void updateMinistryProxyService(MinistryProxyServicesVO mpService)
			throws NoSuchMinistryProxyServiceException, BaseException {
		try {
			if (mpService != null) {
				if (mpServiceDAO.getMinstryProxyServiceById(mpService.getGuid()) == null) {
					throw new NoSuchMinistryProxyServiceException(
							"No such mpService is present with id " + mpService.getGuid());
				}
				mpService.setUpdateDate(new Date());
				mpService.setCreatedBy(createdBy);
				mpServiceDAO.updateMinstryProxyService(mpService);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG8.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{Role.getName()});
			} else {
				throw new Exception("mService object is null to update.");
			}
		} catch (NoSuchMinistryProxyServiceException ex) {
			throw new NoSuchMinistryProxyServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			throw new BaseException(ex);
		} finally {
			// if(isSuccess){
			// if(Role.getCustomerId()==null ){
			// MonitorLogUtil.createMonitorLog(createdBy, Role,
			// MonitorLogUtil.ENTITYTYPE.USER,MonitorLogUtil.OPERATIONS.UPDATE,
			// message);
			// }
			// MonitorLogUtil.createMonitorLog(createdBy, Role,
			// MonitorLogUtil.ENTITYTYPE.USER,MonitorLogUtil.OPERATIONS.UPDATE,
			// message);
			// }
		}

	}

	@Override
	public MinistryProxyServicesVO getMinistryProxyService(String name)
			throws NoSuchMinistryProxyServiceException, BaseException {
		
		try {
			if (name != null) {
				MinistryProxyServicesVO mpService = mpServiceDAO.getMinstryProxyService(name);
				if (mpService == null) {
					throw new NoSuchMinistryProxyServiceException(
							"No such mService is present with name " + name);
				} else {
					return mpService;
				}
			} else {
				throw new Exception("mService name should not be empty..");
			}
		} catch (NoSuchMinistryProxyServiceException ex) {
			ex.printStackTrace();
			throw new NoSuchMinistryProxyServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}

	@Override
	public MinistryProxyServicesVO getMinistryProxyServiceById(
			String mpServiceId) throws NoSuchMinistryProxyServiceException,
			BaseException {
		try {
			if (mpServiceId != null) {
				MinistryProxyServicesVO mService = mpServiceDAO.getMinstryProxyServiceById(mpServiceId);
				if (mService == null) {
					throw new NoSuchMinistryProxyServiceException(
							"No such mServiceId is present with id " + mpServiceId);
				} else {
					return mService;
				}
			} else {
				throw new Exception("mServiceId should not be empty");
			}
		} catch (NoSuchMinistryProxyServiceException ex) {
			throw new NoSuchMinistryProxyServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			throw new BaseException(ex);
		}
	}

	@Override
	public boolean isMinistryProxyServiceExist(String name)
			throws BaseException {
		try {
			if (name != null) {
				MinistryProxyServicesVO mpService = mpServiceDAO.getMinstryProxyService(name);
				if (mpService == null) {
					return false;
				} else {
					return true;
				}
			} else {
				throw new Exception("name should not be empty.");
			}
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}

	@Override
	public int searchCount(String searchPattern,
			MinistryProxyServiceColumnEnum columnEnum) throws BaseException {
		try{
			int count = 0;
			count = mpServiceDAO.searchCount( searchPattern, columnEnum);
			
			if(count > 0){
					System.out.println("Admin mpServiceDAO size is "+count);
			}
			return count;
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}

	@Override
	public MinistryProxyServicesVO[] search(String searchPattern,
			MinistryProxyServiceColumnEnum columnEnum) throws BaseException {
try{
			
		MinistryProxyServicesVO[] mpservices = null;
		mpservices = mpServiceDAO.search( searchPattern, columnEnum);
				
			if(mpservices != null){
					System.out.println("Admin mpservices size is "+mpservices.length);
				
				return mpservices;
			} else {
				return null;
			}
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}
	}


	
	
