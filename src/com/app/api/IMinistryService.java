package com.app.api;

import com.server.dao.enums.MinistryServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateMinistryServiceException;
import com.server.execption.NoSuchMinistryServiceException;
import com.server.vo.MinistryServicesVO;


public interface IMinistryService {
	
	
	public void createMinistryService(MinistryServicesVO mService) throws DuplicateMinistryServiceException, BaseException;
	public void deleteeMinistryService(String name) throws NoSuchMinistryServiceException, BaseException;
	
	public void deleteMinistryServiceByID(String mServiceId) throws NoSuchMinistryServiceException, BaseException;
	public void updateMinistryService(MinistryServicesVO mService) throws NoSuchMinistryServiceException, BaseException;
	
	public MinistryServicesVO getMinistryService(String name) throws NoSuchMinistryServiceException, BaseException;
	public MinistryServicesVO getMinistryServiceById(String mServiceId) throws NoSuchMinistryServiceException, BaseException;
	public boolean isMinistryServiceExist(String name) throws BaseException;
	
	public int searchCount(String searchPattern, MinistryServiceColumnsEnum columnEnum) throws BaseException;
	
	public MinistryServicesVO[] search(String searchPattern, MinistryServiceColumnsEnum columnEnum) throws BaseException;

	
	

}
