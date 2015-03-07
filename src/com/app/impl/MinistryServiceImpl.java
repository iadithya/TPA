package com.app.impl;

import java.util.Date;

import com.app.api.IMinistryService;
import com.server.dao.cache.AccessDAOz;
import com.server.dao.daoapi.IMinstryServiceDAO;
import com.server.dao.enums.MinistryServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateMinistryServiceException;

import com.server.execption.NoSuchMinistryServiceException;
import com.server.vo.MinistryServicesVO;

public class MinistryServiceImpl implements IMinistryService {
	
	
	private IMinstryServiceDAO mServiceDAO = null;
	private String createdBy = null;

	// private String message = null;
	public MinistryServiceImpl(String createdBy) throws BaseException {
		try {
			mServiceDAO = AccessDAOz.getInstance().getMinistryServiceDAO ();
			this.createdBy = createdBy;
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}
	
	
	@Override
	public void createMinistryService(MinistryServicesVO mService)
			throws DuplicateMinistryServiceException, BaseException {
		// boolean isSuccess=false;
				try {
					if (mService != null) {
						if (mServiceDAO.getMinstryService(mService.getMinstryName()) != null) {
							throw new DuplicateMinistryServiceException(
									"mService is already present with name "
											+ mService.getMinstryName());
						}
						mService.setCreatedBy(createdBy);
						mServiceDAO.createMinistryService(mService);
						// isSuccess=true;
						// message =
						// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG2.getStatusMessage(UMSHelper.getLoggingBundle(),
						// new String[]{Role.getName()});
					} else {
						throw new Exception("mService object is null in create mService method");
					}
				} catch (DuplicateMinistryServiceException ex) {
					throw new DuplicateMinistryServiceException(ex.getMessage(), ex);
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
	public void deleteeMinistryService(String name)
			throws NoSuchMinistryServiceException, BaseException {
		MinistryServicesVO mService = null;
		// boolean isSuccess=false;

		try {
			if (name != null) {

				if ((mService = mServiceDAO.getMinstryService(name)) == null) {
					throw new NoSuchMinistryServiceException(
							"No such eService is present with name " + name);
				}
				if (mService != null) {
					mService.setUpdateDate(new Date());
				}
				mServiceDAO.deleteMinstryService(name);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{createdBy});
			} else {
				throw new Exception("eService name should not be empty");
			}
		} catch (NoSuchMinistryServiceException ex) {
			throw new NoSuchMinistryServiceException(ex.getMessage(), ex);
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
	public void deleteMinistryServiceByID(String mServiceId)
			throws NoSuchMinistryServiceException, BaseException {

		MinistryServicesVO mService = null;
		// boolean isSuccess=false;

		try {
			if (mServiceId != null) {

				if ((mService = mServiceDAO.getMinstryServiceById(mServiceId)) == null) {
					throw new NoSuchMinistryServiceException(
							"No such Ministry Service is present with id " + mServiceId);
				}
				if (mService != null) {
					mService.setUpdateDate(new Date());
				}
				mServiceDAO.deleteMinsitryServiceByID(mServiceId);;
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{createdBy});
			} else {
				throw new Exception("mService id should not be empty");
			}
		} catch (NoSuchMinistryServiceException ex) {
			throw new NoSuchMinistryServiceException(ex.getMessage(), ex);
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
	public void updateMinistryService(MinistryServicesVO mService)
			throws NoSuchMinistryServiceException, BaseException {
		try {
			if (mService != null) {
				if (mServiceDAO.getMinstryServiceById(mService.getGuid()) == null) {
					throw new NoSuchMinistryServiceException(
							"No such mService is present with id " + mService.getGuid());
				}
				mService.setUpdateDate(new Date());
				mService.setCreatedBy(createdBy);
				mServiceDAO.updateMinstryService(mService);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG8.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{Role.getName()});
			} else {
				throw new Exception("mService object is null to update.");
			}
		} catch (NoSuchMinistryServiceException ex) {
			throw new NoSuchMinistryServiceException(ex.getMessage(), ex);
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
	public MinistryServicesVO getMinistryService(String name)
			throws NoSuchMinistryServiceException, BaseException {
	
		try {
			if (name != null) {
				MinistryServicesVO mService = mServiceDAO.getMinstryService(name);
				if (mService == null) {
					throw new NoSuchMinistryServiceException(
							"No such mService is present with name " + name);
				} else {
					return mService;
				}
			} else {
				throw new Exception("mService name should not be empty..");
			}
		} catch (NoSuchMinistryServiceException ex) {
			ex.printStackTrace();
			throw new NoSuchMinistryServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}

	@Override
	public MinistryServicesVO getMinistryServiceById(String mServiceId)
			throws NoSuchMinistryServiceException, BaseException {
		try {
			if (mServiceId != null) {
				MinistryServicesVO mService = mServiceDAO.getMinstryServiceById(mServiceId);
				if (mService == null) {
					throw new NoSuchMinistryServiceException(
							"No such mServiceId is present with id " + mServiceId);
				} else {
					return mService;
				}
			} else {
				throw new Exception("mServiceId should not be empty");
			}
		} catch (NoSuchMinistryServiceException ex) {
			throw new NoSuchMinistryServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			throw new BaseException(ex);
		}
	}

	@Override
	public boolean isMinistryServiceExist(String name) throws BaseException {
		try {
			if (name != null) {
				MinistryServicesVO mService = mServiceDAO.getMinstryService(name);
				if (mService == null) {
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
			MinistryServiceColumnsEnum columnEnum) throws BaseException {
		try{
			int count = 0;
			count = mServiceDAO.searchCount( searchPattern, columnEnum);
			
			if(count > 0){
					System.out.println("Admin mService size is "+count);
			}
			return count;
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}

	@Override
	public MinistryServicesVO[] search(String searchPattern,
			MinistryServiceColumnsEnum columnEnum) throws BaseException {
try{
			
			MinistryServicesVO[] mservices = null;
			mservices = mServiceDAO.search( searchPattern, columnEnum);
				
			if(mservices != null){
					System.out.println("Admin mservices size is "+mservices.length);
				
				return mservices;
			} else {
				return null;
			}
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}

}
