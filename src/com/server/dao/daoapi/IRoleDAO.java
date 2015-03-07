package com.server.dao.daoapi;

import com.server.dao.enums.RoleColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.RoleVO;

public interface IRoleDAO {
	
	public void createRole(RoleVO role) throws BaseException;
	
	public void deleteRole(String email) throws BaseException;
	
	public void deleteRoleByID(String roleId) throws BaseException;
	
	public void updateRole(RoleVO role) throws BaseException;
	public RoleVO getRole(String loginId) throws BaseException;
	public RoleVO getRoleById(String roleId) throws BaseException;
	//public int searchCount(String searchPattern, RoleColumnsEnum columnEnum) throws BaseException;
	//public RoleVO[] search(String searchPattern, RoleColumnsEnum columnEnum) throws BaseException;
	

}
