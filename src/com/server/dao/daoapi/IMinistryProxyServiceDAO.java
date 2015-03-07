package com.server.dao.daoapi;

import com.server.dao.enums.MinistryProxyServiceColumnEnum;
import com.server.dao.enums.MinistryServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.MinistryProxyServicesVO;
import com.server.vo.MinistryServicesVO;

public interface IMinistryProxyServiceDAO {
	
	
	public void createMinistryProxyService(MinistryProxyServicesVO mpServicesVO) throws BaseException;

	public void deleteMinsitryProxyServiceByID(String mpServiceId) throws BaseException;

	public void deleteMinstryProxyService(String name) throws BaseException;

	public void updateMinstryProxyService(MinistryProxyServicesVO mService) throws BaseException;

	public MinistryProxyServicesVO getMinstryProxyService(String name) throws BaseException;

	public MinistryProxyServicesVO getMinstryProxyServiceById(String mpServiceId) throws BaseException;
	
	public int searchCount(String searchPattern, MinistryProxyServiceColumnEnum columnEnum) throws BaseException;
	
	public MinistryProxyServicesVO[] search(String searchPattern, MinistryProxyServiceColumnEnum columnEnum) throws BaseException;
	

}
