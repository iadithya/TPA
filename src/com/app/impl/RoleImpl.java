package com.app.impl;

import java.util.Date;

import com.app.api.IRole;
import com.server.dao.cache.AccessDAOz;
import com.server.dao.daoapi.IRoleDAO;
import com.server.execption.BaseException;
import com.server.execption.DuplicateRoleException;
import com.server.execption.NoSuchRoleException;
import com.server.vo.RoleVO;

public class RoleImpl implements IRole {
	
	private IRoleDAO roleDAO = null;
	private String createdBy=null;
	//private String message = null;
	public RoleImpl(String createdBy) throws BaseException{
		try {
			roleDAO = AccessDAOz.getInstance().getRoleDAO();
			this.createdBy = createdBy;
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}

	public void createRole(RoleVO role) throws DuplicateRoleException, BaseException {
		//boolean isSuccess=false;
		try{
			if(role != null){
				if(roleDAO.getRole(role.getName()) != null){
					throw new DuplicateRoleException("Role is already present with name "+role.getName());
				}
				//Role.setGuid(UniqueIDGenerator.getUniqId());
				//Role.setCreatedTime(new Date());
				//Role.setUpdatedTime(new Date());
				role.setCreatedBy(createdBy);
				roleDAO.createRole(role);
				//isSuccess=true;
//				message = UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG2.getStatusMessage(UMSHelper.getLoggingBundle(),
//						new String[]{Role.getName()});
			} else {
				throw new Exception("Role object is null in createRole method");
			}
		}catch(DuplicateRoleException ex){
			throw new DuplicateRoleException(ex.getMessage(), ex);
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
		finally{
//			if(isSuccess){
//				if(Role.getCustomerId()==null ){
//				MonitorLogUtil.createMonitorLog(createdBy, Role, MonitorLogUtil.ENTITYTYPE.USER,	MonitorLogUtil.OPERATIONS.CREATE, message);
//				}
//				MonitorLogUtil.createMonitorLog(createdBy, Role, MonitorLogUtil.ENTITYTYPE.USER, MonitorLogUtil.OPERATIONS.CREATE, message);
//				
//			}
		}
	}

	
	public void deleteRole(String name) throws NoSuchRoleException,	BaseException {
		RoleVO role=null;
		//boolean isSuccess=false;
		
		try{
			if(name != null){
				
				if((role=roleDAO.getRole(name)) == null){
					throw new NoSuchRoleException("No such Role is present with name "+name);
				}
				if(role!=null){
					role.setUpdateDate(new Date());
				}
				roleDAO.deleteRole(name);
//				isSuccess=true;
//				message = UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
//						new String[]{createdBy});
			} else {
				throw new Exception("Role name should not be empty");
			}
		}catch(NoSuchRoleException ex){
			throw new NoSuchRoleException( ex.getMessage(), ex);
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
		finally{
//			if(isSuccess){
//				if(Role.getCustomerId()==null ){
//				MonitorLogUtil.createMonitorLog(this.Rolename, Role, MonitorLogUtil.ENTITYTYPE.USER,	MonitorLogUtil.OPERATIONS.DELETE, message);
//				}
//				MonitorLogUtil.createMonitorLog(this.createdBy, Role, MonitorLogUtil.ENTITYTYPE.USER,	MonitorLogUtil.OPERATIONS.DELETE, message);
//			}
			
		}		
		
	}
	/* (non-Javadoc)
	 * @see com.ums.server.api.IRoleManager#deleteRole(java.lang.String)
	 */
	public void deleteRoleByID(String roleId) throws NoSuchRoleException,	BaseException {
		RoleVO role=null;
		//boolean isSuccess=false;
		
		try{
			if(roleId != null){
				
				if((role=roleDAO.getRoleById(roleId)) == null){
					throw new NoSuchRoleException("No such Role is present with id "+roleId);
				}
				if(role!=null){
					role.setUpdateDate(new Date());
				}
				roleDAO.deleteRoleByID(roleId);
//				isSuccess=true;
//				message = UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG4.getStatusMessage(UMSHelper.getLoggingBundle(),
//						new String[]{createdBy});
			} else {
				throw new Exception("Role id should not be empty");
			}
		}catch(NoSuchRoleException ex){
			throw new NoSuchRoleException( ex.getMessage(), ex);
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
		finally{
//			if(isSuccess){
//				if(Role.getCustomerId()==null ){
//				MonitorLogUtil.createMonitorLog(this.createdBy, Role, MonitorLogUtil.ENTITYTYPE.USER,	MonitorLogUtil.OPERATIONS.DELETE, message);
//				}
//				MonitorLogUtil.createMonitorLog(this.createdBy, Role, MonitorLogUtil.ENTITYTYPE.USER,	MonitorLogUtil.OPERATIONS.DELETE, message);
//			}
			
		}		
		
	}
	
	
	public void updateRole(RoleVO role) throws NoSuchRoleException, BaseException {
	//	boolean isSuccess=false;
		
		try{
			if(role != null){
				if(roleDAO.getRoleById(role.getGuid()) == null){
					throw new NoSuchRoleException("No such Role is present with id "+role.getGuid());
				}
				role.setUpdateDate(new Date());
				role.setCreatedBy(createdBy);
				roleDAO.updateRole(role);
//				isSuccess=true;
//				message = UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG8.getStatusMessage(UMSHelper.getLoggingBundle(), 
//						new String[]{Role.getName()});
			} else {
				throw new Exception("Role object is null to update.");
			}
		}catch(NoSuchRoleException ex){
			throw new NoSuchRoleException( ex.getMessage() , ex);
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
		finally{
//			if(isSuccess){
//				if(Role.getCustomerId()==null ){
//				MonitorLogUtil.createMonitorLog(createdBy, Role, MonitorLogUtil.ENTITYTYPE.USER,MonitorLogUtil.OPERATIONS.UPDATE, message);
//				}
//				MonitorLogUtil.createMonitorLog(createdBy, Role, MonitorLogUtil.ENTITYTYPE.USER,MonitorLogUtil.OPERATIONS.UPDATE, message);
//			}
		}		
	}

	public RoleVO getRole(String name) throws NoSuchRoleException, BaseException {
		try{
			if(name != null){
				RoleVO role = roleDAO.getRole(name);
				if(role == null ){
					throw new NoSuchRoleException("No such Role is present with name "+name);
				} else {
					return role;
				}
			} else {
				throw new Exception("Role name should not be empty..");
			}
		}catch(NoSuchRoleException ex){
			ex.printStackTrace();
			throw new NoSuchRoleException( ex.getMessage(), ex);
		}catch(Throwable ex){
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}
	public RoleVO getRoleById(String roleId) throws NoSuchRoleException, BaseException {
		try{
			if(roleId!=null){
				RoleVO role = roleDAO.getRoleById(roleId);
				if(role == null ){
					throw new NoSuchRoleException("No such Role is present with id "+roleId);
				} else {
					return role;
				}
			} else {
				throw new Exception("RoleId should not be empty");
			}
		}catch(NoSuchRoleException ex){
			throw new NoSuchRoleException(ex.getMessage() , ex);
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}
	public boolean isRoleExist(String name) throws BaseException {
		try{
			if(name != null){
				RoleVO role = roleDAO.getRole(name);
				if(role == null ){
					return false;
				} else {
					return true;
				}
			} else {
				throw new Exception("name should not be empty.");
			}
		} catch(Throwable ex){
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}
//	public int searchCount( String searchPattern, RoleColumnsEnum columnEnum) throws BaseException {
//		try{
//			int count = 0;
//			count = roleDAO.searchCount( searchPattern, columnEnum);
//			
//			if(count > 0){
//			}
//			return count;
//		}catch(Throwable ex){
//			throw new BaseException(ex);
//		}
//	}
//	public RoleVO[] search(String searchPattern, RoleColumnsEnum columnEnum) throws BaseException {
//		try{
//			RoleVO[] roles = null;
//			roles = roleDAO.search( searchPattern, columnEnum);
//				
//			if(roles != null){
//				return roles;
//			} else {
//				return null;
//			}
//		}catch(Throwable ex){
//			throw new BaseException(ex);
//		}
//	}
	

}
