package com.app.api;

import com.server.dao.enums.eServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateeServiceException;
import com.server.execption.NoSucheServiceException;
import com.server.vo.eServiceVO;

public interface IeService {
	
	
	public void createeService(eServiceVO eService) throws DuplicateeServiceException, BaseException;
	public void deleteeService(String name) throws NoSucheServiceException, BaseException;
	
	public void deleteeServiceByID(String eServiceID) throws NoSucheServiceException, BaseException;
	public void updateeService(eServiceVO eService) throws NoSucheServiceException, BaseException;
	
	public eServiceVO geteService(String name) throws NoSucheServiceException, BaseException;
	public eServiceVO geteServiceById(String eServiceId) throws NoSucheServiceException, BaseException;
	public boolean iseServiceExist(String name) throws BaseException;
	
	public int searchCount(String searchPattern, eServiceColumnsEnum columnEnum) throws BaseException;
	
	public eServiceVO[] search(String searchPattern, eServiceColumnsEnum columnEnum) throws BaseException;

	

}
