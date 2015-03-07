package com.app.api;

import com.server.dao.enums.MinistryProxyServiceColumnEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateMinistryProxyServiceException;
import com.server.execption.NoSuchMinistryProxyServiceException;
import com.server.vo.MinistryProxyServicesVO;


public interface IMinistryProxyService {
	
	
	public void createMinistryProxyService(MinistryProxyServicesVO mpService) throws DuplicateMinistryProxyServiceException, BaseException;
	public void deleteeMinistryProxyService(String name) throws NoSuchMinistryProxyServiceException, BaseException;
	
	public void deleteMinistryProxyServiceByID(String mpServiceId) throws NoSuchMinistryProxyServiceException, BaseException;
	public void updateMinistryProxyService(MinistryProxyServicesVO mpService) throws NoSuchMinistryProxyServiceException, BaseException;
	
	public MinistryProxyServicesVO getMinistryProxyService(String name) throws NoSuchMinistryProxyServiceException, BaseException;
	public MinistryProxyServicesVO getMinistryProxyServiceById(String mpServiceId) throws NoSuchMinistryProxyServiceException, BaseException;
	public boolean isMinistryProxyServiceExist(String name) throws BaseException;
	
	public int searchCount(String searchPattern, MinistryProxyServiceColumnEnum columnEnum) throws BaseException;
	
	public MinistryProxyServicesVO[] search(String searchPattern, MinistryProxyServiceColumnEnum columnEnum) throws BaseException;


}
