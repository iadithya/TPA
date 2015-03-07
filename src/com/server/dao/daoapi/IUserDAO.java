package com.server.dao.daoapi;

import java.util.List;

import com.server.execption.BaseException;
import com.server.vo.UserVO;

public interface IUserDAO {
	
	public void createUser(UserVO user )throws BaseException;
	
	public void updateUser(String id);
	
	public List<UserVO> getAllUser();
	
	public UserVO getUserById(String id);
	
	public UserVO getUser(String loginId) throws BaseException;
	
	

}
