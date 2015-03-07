package com.app.impl;

import java.util.Date;

import com.app.api.IBPELProxyService;
import com.server.dao.cache.AccessDAOz;
import com.server.dao.daoapi.IBPELProxyServiceDAO;
import com.server.dao.enums.BPELProxyServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateBPELProxyServiceException;
import com.server.execption.NoSuchBPELProxyServiceException;
import com.server.vo.BPELProxyServicesVO;

public class BPELProxyServiceImpl implements IBPELProxyService {

	private IBPELProxyServiceDAO bpelpServiceDAO = null;
	private String createdBy = null;

	// private String message = null;
	
	public BPELProxyServiceImpl(String createdBy) throws BaseException {
		try {
			bpelpServiceDAO = AccessDAOz.getInstance().getBPELProxyServiceDAO();
			this.createdBy = createdBy;
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}

	
	@Override
	public void createBPELProxyService(BPELProxyServicesVO bpelpService)
			throws DuplicateBPELProxyServiceException, BaseException {
		// boolean isSuccess=false;
		try {
			if (bpelpService != null) {
				if (bpelpServiceDAO.getBPELProxyService(bpelpService.getBpelProxyName()) != null) {
					throw new DuplicateBPELProxyServiceException(
							"bpel Proxy Service is already present with name "
									+ bpelpService.getBpelProxyName());
				}
				bpelpService.setCreatedBy(createdBy);
				bpelpServiceDAO.createBPELProxyService(bpelpService);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG2.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{Role.getName()});
			} else {
				throw new Exception("bpel Proxy Service object is null in create bpel proxy Service method");
			}
		} catch (DuplicateBPELProxyServiceException ex) {
			throw new DuplicateBPELProxyServiceException(ex.getMessage(), ex);
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
	public void deleteeBPELProxyService(String name)
			throws NoSuchBPELProxyServiceException, BaseException {
	BPELProxyServicesVO bpelpService = null;
		// boolean isSuccess=false;

		try {
			if (name != null) {

				if ((bpelpService = bpelpServiceDAO.getBPELProxyService(name)) == null) {
					throw new NoSuchBPELProxyServiceException(
							"No such BPEL Service is present with name " + name);
				}
				if (bpelpService != null) {
					bpelpService.setUpdateDate(new Date());
				}
				bpelpServiceDAO.deleteBPELProxyService(name);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{createdBy});
			} else {
				throw new Exception("BPEL Proxy Service name should not be empty");
			}
		} catch (NoSuchBPELProxyServiceException ex) {
			throw new NoSuchBPELProxyServiceException(ex.getMessage(), ex);
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
	public void deleteBPELProxyServiceByID(String bpelpServiceId)
			throws NoSuchBPELProxyServiceException, BaseException {
			BPELProxyServicesVO bpelpService = null;
		// boolean isSuccess=false;

		try {
			if (bpelpServiceId != null) {

				if ((bpelpService = bpelpServiceDAO.getBPELProxyServiceById(bpelpServiceId)) == null) {
					throw new NoSuchBPELProxyServiceException(
							"No such BPEL Proxy Service is present with id " + bpelpServiceId);
				}
				if (bpelpService != null) {
					bpelpService.setUpdateDate(new Date());
				}
				bpelpServiceDAO.deleteBPELProxyServiceByID(bpelpServiceId);;
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{createdBy});
			} else {
				throw new Exception("bpel Proxy Service id should not be empty");
			}
		} catch (NoSuchBPELProxyServiceException ex) {
			throw new NoSuchBPELProxyServiceException(ex.getMessage(), ex);
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
	public void updateBPELProxyService(BPELProxyServicesVO bpelpService)
			throws NoSuchBPELProxyServiceException, BaseException {
	
		try {
			if (bpelpService != null) {
				if (bpelpServiceDAO.getBPELProxyServiceById(bpelpService.getGuid()) == null) {
					throw new NoSuchBPELProxyServiceException(
							"No such mService is present with id " + bpelpService.getGuid());
				}
				bpelpService.setUpdateDate(new Date());
				bpelpService.setCreatedBy(createdBy);
				bpelpServiceDAO.updateBPELProxyService(bpelpService);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG8.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{Role.getName()});
			} else {
				throw new Exception("bpel Proxy Service object is null to update.");
			}
		} catch (NoSuchBPELProxyServiceException ex) {
			throw new NoSuchBPELProxyServiceException(ex.getMessage(), ex);
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
	public BPELProxyServicesVO getBPELProxyService(String name)
			throws NoSuchBPELProxyServiceException, BaseException {
		
		try {
			if (name != null) {
				BPELProxyServicesVO bpelpService = bpelpServiceDAO.getBPELProxyService(name);
				if (bpelpService == null) {
					throw new NoSuchBPELProxyServiceException(
							"No such bpel Proxy Service is present with name " + name);
				} else {
					return bpelpService;
				}
			} else {
				throw new Exception("bpel Proxy Service name should not be empty..");
			}
		} catch (NoSuchBPELProxyServiceException ex) {
			ex.printStackTrace();
			throw new NoSuchBPELProxyServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}

	@Override
	public BPELProxyServicesVO getBPELProxyServiceById(String bpelpServiceId)
			throws NoSuchBPELProxyServiceException, BaseException {
		try {
			if (bpelpServiceId != null) {
				BPELProxyServicesVO bpelService = bpelpServiceDAO.getBPELProxyServiceById(bpelpServiceId);
				if (bpelService == null) {
					throw new NoSuchBPELProxyServiceException(
							"No such bpel Proxy ServiceId is present with id " + bpelpServiceId);
				} else {
					return bpelService;
				}
			} else {
				throw new Exception("bpel Proxy Service should not be empty");
			}
		} catch (NoSuchBPELProxyServiceException ex) {
			throw new NoSuchBPELProxyServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			throw new BaseException(ex);
		}
	}


	@Override
	public boolean isBPELProxyServiceExist(String name) throws BaseException {
		try {
			if (name != null) {
				BPELProxyServicesVO bpelpService = bpelpServiceDAO.getBPELProxyService(name);
				if (bpelpService == null) {
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
			BPELProxyServiceColumnsEnum columnEnum) throws BaseException {
		try{
			int count = 0;
			count = bpelpServiceDAO.searchCount( searchPattern, columnEnum);
			
			if(count > 0){
					System.out.println("Admin bpel Proxy ServiceDAO size is "+count);
			}
			return count;
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}

	@Override
	public BPELProxyServicesVO[] search(String searchPattern,
			BPELProxyServiceColumnsEnum columnEnum) throws BaseException {
		try{
			
			BPELProxyServicesVO[] bpelpservices = null;
			bpelpservices = bpelpServiceDAO.search( searchPattern, columnEnum);
				
			if(bpelpservices != null){
					System.out.println("Admin bpelProxyServiceDAO size is "+bpelpservices.length);
				
				return bpelpservices;
			} else {
				return null;
			}
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}

	
	
}
