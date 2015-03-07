package com.app.impl;

import java.util.Date;

import com.app.api.IeService;
import com.server.dao.cache.AccessDAOz;
import com.server.dao.daoapi.IeServiceDAO;
import com.server.dao.enums.eServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateeServiceException;
import com.server.execption.NoSucheServiceException;
import com.server.vo.eServiceVO;

public class eServiceImpl implements IeService {

	private IeServiceDAO eServiceDAO = null;
	private String createdBy = null;

	// private String message = null;
	public eServiceImpl(String createdBy) throws BaseException {
		try {
			eServiceDAO = AccessDAOz.getInstance().geteServiceDAO();
			this.createdBy = createdBy;
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}

	public void createeService(eServiceVO eService)
			throws DuplicateeServiceException, BaseException {
		// boolean isSuccess=false;
		try {
			if (eService != null) {
				if (eServiceDAO.geteService(eService.getEserviceName()) != null) {
					throw new DuplicateeServiceException(
							"eService is already present with name "
									+ eService.getEserviceName());
				}
				eService.setCreatedBy(createdBy);
				eServiceDAO.createEservice(eService);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG2.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{Role.getName()});
			} else {
				throw new Exception("eService object is null in createeService method");
			}
		} catch (DuplicateeServiceException ex) {
			throw new DuplicateeServiceException(ex.getMessage(), ex);
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

	public void deleteeService(String name) throws NoSucheServiceException,
			BaseException {
		eServiceVO eService = null;
		// boolean isSuccess=false;

		try {
			if (name != null) {

				if ((eService = eServiceDAO.geteService(name)) == null) {
					throw new NoSucheServiceException(
							"No such eService is present with name " + name);
				}
				if (eService != null) {
					eService.setUpdateDate(new Date());
				}
				eServiceDAO.deleteeService(name);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{createdBy});
			} else {
				throw new Exception("eService name should not be empty");
			}
		} catch (NoSucheServiceException ex) {
			throw new NoSucheServiceException(ex.getMessage(), ex);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ums.server.api.IRoleManager#deleteRole(java.lang.String)
	 */
	public void deleteeServiceByID(String eServiceID)
			throws NoSucheServiceException, BaseException {
		eServiceVO eService = null;
		// boolean isSuccess=false;

		try {
			if (eServiceID != null) {

				if ((eService = eServiceDAO.geteServiceById(eServiceID)) == null) {
					throw new NoSucheServiceException(
							"No such eService is present with id " + eServiceID);
				}
				if (eService != null) {
					eService.setUpdateDate(new Date());
				}
				eServiceDAO.deleteeServiceByID(eServiceID);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{createdBy});
			} else {
				throw new Exception("eService id should not be empty");
			}
		} catch (NoSucheServiceException ex) {
			throw new NoSucheServiceException(ex.getMessage(), ex);
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

	public void updateeService(eServiceVO eService)
			throws NoSucheServiceException, BaseException {
		// boolean isSuccess=false;

		try {
			if (eService != null) {
				if (eServiceDAO.geteServiceById(eService.getGuid()) == null) {
					throw new NoSucheServiceException(
							"No such eService is present with id " + eService.getGuid());
				}
				eService.setUpdateDate(new Date());
				eService.setCreatedBy(createdBy);
				eServiceDAO.updateeService(eService);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG8.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{Role.getName()});
			} else {
				throw new Exception("eService object is null to update.");
			}
		} catch (NoSucheServiceException ex) {
			throw new NoSucheServiceException(ex.getMessage(), ex);
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

	public eServiceVO geteService(String name) throws NoSucheServiceException,
			BaseException {
		try {
			if (name != null) {
				eServiceVO eService = eServiceDAO.geteService(name);
				if (eService == null) {
					throw new NoSucheServiceException(
							"No such eService is present with name " + name);
				} else {
					return eService;
				}
			} else {
				throw new Exception("eService name should not be empty..");
			}
		} catch (NoSucheServiceException ex) {
			ex.printStackTrace();
			throw new NoSucheServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}

	public eServiceVO geteServiceById(String eServiceId)
			throws NoSucheServiceException, BaseException {

		try {
			if (eServiceId != null) {
				eServiceVO eService = eServiceDAO.geteServiceById(eServiceId);
				if (eService == null) {
					throw new NoSucheServiceException(
							"No such eServiceId is present with id " + eServiceId);
				} else {
					return eService;
				}
			} else {
				throw new Exception("eServiceId should not be empty");
			}
		} catch (NoSucheServiceException ex) {
			throw new NoSucheServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			throw new BaseException(ex);
		}
	}

	public boolean iseServiceExist(String name) throws BaseException {

		try {
			if (name != null) {
				eServiceVO eService = eServiceDAO.geteService(name);
				if (eService == null) {
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
	public int searchCount( String searchPattern, eServiceColumnsEnum columnEnum) throws BaseException {
		try{
			int count = 0;
			count = eServiceDAO.searchCount( searchPattern, columnEnum);
			
			if(count > 0){
					System.out.println("Admin eService size is "+count);
			}
			return count;
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}
	public eServiceVO[] search(String searchPattern, eServiceColumnsEnum columnEnum) throws BaseException {
		try{
			
			eServiceVO[] eservices = null;
			eservices = eServiceDAO.search( searchPattern, columnEnum);
				
			if(eservices != null){
					System.out.println("Admin eservices size is "+eservices.length);
				
				return eservices;
			} else {
				return null;
			}
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}

}
