package com.app.api;

import com.server.dao.enums.UserColumnsEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateUserException;
import com.server.execption.NoSuchRoleException;
import com.server.execption.NoSuchUserException;
import com.server.execption.ValidateEntityException;
import com.server.vo.UserVO;

public interface IUser {
	
	 
		public void createUser(UserVO user) throws DuplicateUserException, NoSuchRoleException,  ValidateEntityException, BaseException;
		public void deleteUserByID(String userID) throws NoSuchUserException, BaseException;
		public void updateUser(UserVO user) throws NoSuchUserException, NoSuchRoleException,  ValidateEntityException, BaseException;
		public UserVO getUserByLoginId(String email) throws NoSuchUserException, BaseException;
		public UserVO getUserById(String userId) throws NoSuchUserException, BaseException;
		public boolean isUserLoginIdExist(String email) throws BaseException;
		public UserVO[] listLoggedInUsers() throws BaseException;
		public UserVO[] listAllUsers() throws BaseException;
		public int searchCount(String searchPattern, UserColumnsEnum columnEnum) throws BaseException;
		public UserVO[] search(String searchPattern, UserColumnsEnum columnEnum) throws BaseException;
		public String[] listAllDesignations() throws BaseException;
	
	
	
	

}
