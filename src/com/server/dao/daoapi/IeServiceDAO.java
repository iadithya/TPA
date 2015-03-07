package com.server.dao.daoapi;

import com.server.dao.enums.eServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.eServiceVO;

public interface IeServiceDAO {

	public void createEservice(eServiceVO eServiceVO) throws BaseException;

	public void deleteeServiceByID(String eServiceID) throws BaseException;

	public void deleteeService(String name) throws BaseException;

	public void updateeService(eServiceVO eService) throws BaseException;

	public eServiceVO geteService(String name) throws BaseException;

	public eServiceVO geteServiceById(String eServiceId) throws BaseException;
	
	public int searchCount(String searchPattern, eServiceColumnsEnum columnEnum) throws BaseException;
	
	public eServiceVO[] search(String searchPattern, eServiceColumnsEnum columnEnum) throws BaseException;
	
	
	
	
	
	
	
	
}
