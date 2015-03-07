package com.app.impl;

import com.app.api.IUser;
import com.server.dao.cache.AccessDAOz;
import com.server.dao.daoapi.IRoleDAO;
import com.server.dao.daoapi.IUserDAO;
import com.server.dao.enums.UserColumnsEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateUserException;
import com.server.execption.NoSuchRoleException;
import com.server.execption.NoSuchUserException;
import com.server.execption.ValidateEntityException;
import com.server.vo.RoleVO;
import com.server.vo.UserVO;

public class UserImpl implements IUser {
	
	private IUserDAO userDAO = null;
	private IRoleDAO roleDAO = null;
	private String userName=null;
	//private String message = null;
	public UserImpl(String createdBy) throws BaseException{
		try {
			userDAO = AccessDAOz.getInstance().getUserDAO();
			roleDAO = AccessDAOz.getInstance().getRoleDAO();
			this.userName = createdBy;
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}

	public void createUser(UserVO user) throws DuplicateUserException, NoSuchRoleException ,ValidateEntityException, BaseException {
		//boolean isSuccess=false;
		try{
			if(user != null){
				if(user.getUserName()!=null&&!user.getUserName().equalsIgnoreCase("")){
				
						//throw new ValidateEntityException("UserName is Madatory");
								}
				if(userDAO.getUser(user.getLoginId()) != null){
					throw new DuplicateUserException("User is already present with email "+user.getLoginId());
				} else {
					RoleVO role = roleDAO.getRoleById(user.getRollName());
					if(role==null){
						throw new NoSuchRoleException("Role is not present with id "+user.getRollName()+", set the roleId to create an user" );
					} else {
						user.setRollName(role.getGuid());
					}
				}
				//user.setGuid(UniqueIDGenerator.getUniqId());
				//user.setCreatedTime(new Date());
				//user.setUpdatedTime(new Date());
				
				user.setCreatedBy(userName);
				userDAO.createUser(user);
//				message = UMSLoggingMessagesEnum.UMS_USER_IMPL_MSG2.getStatusMessage(UMSHelper.getLoggingBundle(),
//						new String[]{user.getEmail()});
			} else {
				throw new Exception("User object is null in createUser method");
			}
		}catch(ValidateEntityException ex){
			throw new ValidateEntityException(ex.getMessage(), ex);
		}catch(DuplicateUserException ex){
			throw new DuplicateUserException(ex.getMessage(), ex);
		}catch(NoSuchRoleException ex){
			throw new NoSuchRoleException(ex.getMessage(), ex);
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
		finally{
//			if(isSuccess){
//				if(user.getCustomerId()==null ){
//				MonitorLogUtil.createMonitorLog(userName, user, MonitorLogUtil.ENTITYTYPE.USER,	MonitorLogUtil.OPERATIONS.CREATE, message);
//				}
//				MonitorLogUtil.createMonitorLog(userName, user, MonitorLogUtil.ENTITYTYPE.USER, MonitorLogUtil.OPERATIONS.CREATE, message);
//				
//			}
		}
	}

	@Override
	public void deleteUserByID(String userID) throws NoSuchUserException,
			BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserVO user) throws NoSuchUserException,
			NoSuchRoleException, ValidateEntityException, BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserVO getUserByLoginId(String email) throws NoSuchUserException,
			BaseException {
		try{
			if(email != null){
				UserVO user = userDAO.getUser(email);
				if(user == null ){
					throw new NoSuchUserException("No such user is present with email "+email);
				} else {
					return user;
				}
			} else {
				throw new Exception("User email should not be empty..");
			}
		}catch(NoSuchUserException ex){
			ex.printStackTrace();
			throw new NoSuchUserException( ex.getMessage(), ex);
		}catch(Throwable ex){
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}

	@Override
	public UserVO getUserById(String userId) throws NoSuchUserException,
			BaseException {
		try{
			if(userId!=null){
				UserVO user = userDAO.getUserById(userId);
				if(user == null ){
					throw new NoSuchUserException("No such user is present with id "+userId);
				} else {
					return user;
				}
			} else {
				throw new Exception("UserId should not be empty");
			}
		}catch(NoSuchUserException ex){
			throw new NoSuchUserException(ex.getMessage() , ex);
		}catch(Throwable ex){
			throw new BaseException(ex);
		}
	}

	@Override
	public boolean isUserLoginIdExist(String email) throws BaseException {
		try{
			if(email != null){
				UserVO user = userDAO.getUser(email);
				if(user == null ){
					return false;
				} else {
					return true;
				}
			} else {
				throw new Exception("LoginId should not be empty.");
			}
		} catch(Throwable ex){
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}

	@Override
	public UserVO[] listLoggedInUsers() throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO[] listAllUsers() throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int searchCount(String searchPattern, UserColumnsEnum columnEnum)
			throws BaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserVO[] search(String searchPattern, UserColumnsEnum columnEnum)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] listAllDesignations() throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}



}
