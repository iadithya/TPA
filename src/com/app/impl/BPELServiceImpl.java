package com.app.impl;

import java.util.Date;

import com.app.api.IBPELService;
import com.server.dao.cache.AccessDAOz;
import com.server.dao.daoapi.IBPELServiceDAO;
import com.server.dao.enums.BPELServiceColumnEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateBPELServiceException;
import com.server.execption.NoSuchBPELServiceException;
import com.server.vo.BPELServicesVO;

public class BPELServiceImpl implements IBPELService {


	private IBPELServiceDAO bpelServiceDAO = null;
	private String createdBy = null;

	// private String message = null;
	
	public BPELServiceImpl(String createdBy) throws BaseException {
		try {
			bpelServiceDAO = AccessDAOz.getInstance().getBPELServiceDAO ();
			this.createdBy = createdBy;
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}
	
	
	
	@Override
	public void createBPELService(BPELServicesVO bpelService)
			throws DuplicateBPELServiceException, BaseException {
		// boolean isSuccess=false;
		try {
			if (bpelService != null) {
				if (bpelServiceDAO.getBPELService(bpelService.getBpelServiceName()) != null) {
					throw new DuplicateBPELServiceException(
							"bpelService is already present with name "
									+ bpelService.getBpelServiceName());
				}
				bpelService.setCreatedBy(createdBy);
				bpelServiceDAO.createBPELService(bpelService);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG2.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{Role.getName()});
			} else {
				throw new Exception("bpelService object is null in create mService method");
			}
		} catch (DuplicateBPELServiceException ex) {
			throw new DuplicateBPELServiceException(ex.getMessage(), ex);
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
	public void deleteeBPELService(String name)
			throws NoSuchBPELServiceException, BaseException {
		BPELServicesVO bpelService = null;
		// boolean isSuccess=false;

		try {
			if (name != null) {

				if ((bpelService = bpelServiceDAO.getBPELService(name)) == null) {
					throw new NoSuchBPELServiceException(
							"No such BPEL Service is present with name " + name);
				}
				if (bpelService != null) {
					bpelService.setUpdateDate(new Date());
				}
				bpelServiceDAO.deleteBPELService(name);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{createdBy});
			} else {
				throw new Exception("BPEL Service name should not be empty");
			}
		} catch (NoSuchBPELServiceException ex) {
			throw new NoSuchBPELServiceException(ex.getMessage(), ex);
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
	public void deleteBPELServiceByID(String bpelServiceId)
			throws NoSuchBPELServiceException, BaseException {
		BPELServicesVO bpelService = null;
		// boolean isSuccess=false;

		try {
			if (bpelServiceId != null) {

				if ((bpelService = bpelServiceDAO.getBPELServiceById(bpelServiceId)) == null) {
					throw new NoSuchBPELServiceException(
							"No such BPEL Service is present with id " + bpelServiceId);
				}
				if (bpelService != null) {
					bpelService.setUpdateDate(new Date());
				}
				bpelServiceDAO.deleteBPELServiceByID(bpelServiceId);;
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{createdBy});
			} else {
				throw new Exception("bpelService id should not be empty");
			}
		} catch (NoSuchBPELServiceException ex) {
			throw new NoSuchBPELServiceException(ex.getMessage(), ex);
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
	public void updateBPELService(BPELServicesVO bpelService)
			throws NoSuchBPELServiceException, BaseException {
		try {
			if (bpelService != null) {
				if (bpelServiceDAO.getBPELServiceById(bpelService.getGuid()) == null) {
					throw new NoSuchBPELServiceException(
							"No such mService is present with id " + bpelService.getGuid());
				}
				bpelService.setUpdateDate(new Date());
				bpelService.setCreatedBy(createdBy);
				bpelServiceDAO.updateBPELService(bpelService);
				// isSuccess=true;
				// message =
				// UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG8.getStatusMessage(UMSHelper.getLoggingBundle(),
				// new String[]{Role.getName()});
			} else {
				throw new Exception("bpelService object is null to update.");
			}
		} catch (NoSuchBPELServiceException ex) {
			throw new NoSuchBPELServiceException(ex.getMessage(), ex);
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
	public BPELServicesVO getBPELService(String name)
			throws NoSuchBPELServiceException, BaseException {
		
		try {
			if (name != null) {
				BPELServicesVO bpelService = bpelServiceDAO.getBPELService(name);
				if (bpelService == null) {
					throw new NoSuchBPELServiceException(
							"No such bpelService is present with name " + name);
				} else {
					return bpelService;
				}
			} else {
				throw new Exception("bpelService name should not be empty..");
			}
		} catch (NoSuchBPELServiceException ex) {
			ex.printStackTrace();
			throw new NoSuchBPELServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}

	@Override
	public BPELServicesVO getBPELServiceById(String bpelServiceId)
			throws NoSuchBPELServiceException, BaseException {
		try {
			if (bpelServiceId != null) {
				BPELServicesVO bpelService = bpelServiceDAO.getBPELServiceById(bpelServiceId);
				if (bpelService == null) {
					throw new NoSuchBPELServiceException(
							"No such bpelServiceId is present with id " + bpelServiceId);
				} else {
					return bpelService;
				}
			} else {
				throw new Exception("bpelService should not be empty");
			}
		} catch (NoSuchBPELServiceException ex) {
			throw new NoSuchBPELServiceException(ex.getMessage(), ex);
		} catch (Throwable ex) {
			throw new BaseException(ex);
		}
	}

	@Override
	public boolean isBPELServiceExist(String name) throws BaseException {
		try {
			if (name != null) {
				BPELServicesVO bpelService = bpelServiceDAO.getBPELService(name);
				if (bpelService == null) {
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
			BPELServiceColumnEnum columnEnum) throws BaseException {
		try{
			int count = 0;
			count = bpelServiceDAO.searchCount( searchPattern, columnEnum);
			
			if(count > 0){
					System.out.println("Admin bpelServiceDAO size is "+count);
			}
			return count;
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}

	@Override
	public BPELServicesVO[] search(String searchPattern,
			BPELServiceColumnEnum columnEnum) throws BaseException {
		try{
			
			BPELServicesVO[] bpelservices = null;
			bpelservices = bpelServiceDAO.search( searchPattern, columnEnum);
				
			if(bpelservices != null){
					System.out.println("Admin bpelServiceDAO size is "+bpelservices.length);
				
				return bpelservices;
			} else {
				return null;
			}
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}


	
	
}
