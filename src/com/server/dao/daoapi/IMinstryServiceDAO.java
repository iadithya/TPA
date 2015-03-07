package com.server.dao.daoapi;

import com.server.dao.enums.MinistryServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.MinistryServicesVO;

public interface IMinstryServiceDAO {
	
	public void createMinistryService(MinistryServicesVO mServicesVO) throws BaseException;

	public void deleteMinsitryServiceByID(String mServiceId) throws BaseException;

	public void deleteMinstryService(String name) throws BaseException;

	public void updateMinstryService(MinistryServicesVO mService) throws BaseException;

	public MinistryServicesVO getMinstryService(String name) throws BaseException;

	public MinistryServicesVO getMinstryServiceById(String mServiceId) throws BaseException;
	
	public int searchCount(String searchPattern, MinistryServiceColumnsEnum columnEnum) throws BaseException;
	
	public MinistryServicesVO[] search(String searchPattern, MinistryServiceColumnsEnum columnEnum) throws BaseException;
	

}
