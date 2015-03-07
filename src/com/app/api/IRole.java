package com.app.api;

import com.server.execption.BaseException;
import com.server.execption.DuplicateRoleException;
import com.server.execption.NoSuchRoleException;
import com.server.vo.RoleVO;

public interface IRole {
	
	public void createRole(RoleVO role) throws DuplicateRoleException, BaseException;
	public void deleteRole(String name) throws NoSuchRoleException, BaseException;
	
	public void deleteRoleByID(String roleID) throws NoSuchRoleException, BaseException;
	public void updateRole(RoleVO role) throws NoSuchRoleException, BaseException;
	
	public RoleVO getRole(String name) throws NoSuchRoleException, BaseException;
	public RoleVO getRoleById(String roleId) throws NoSuchRoleException, BaseException;
	public boolean isRoleExist(String name) throws BaseException;
	
	//public int searchCount(String searchPattern, RoleColumnsEnum columnEnum) throws BaseException;
	//public RoleVO[] search(String searchPattern, RoleColumnsEnum columnEnum) throws BaseException;

}
