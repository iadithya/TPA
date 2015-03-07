package com.app.api;

import com.server.dao.enums.BPELServiceColumnEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateBPELServiceException;
import com.server.execption.NoSuchBPELServiceException;
import com.server.vo.BPELServicesVO;


public interface IBPELService {
	
	public void createBPELService(BPELServicesVO mpService) throws DuplicateBPELServiceException, BaseException;
	public void deleteeBPELService(String name) throws NoSuchBPELServiceException, BaseException;
	
	public void deleteBPELServiceByID(String mpServiceId) throws NoSuchBPELServiceException, BaseException;
	public void updateBPELService(BPELServicesVO mpService) throws NoSuchBPELServiceException, BaseException;
	
	public BPELServicesVO getBPELService(String name) throws NoSuchBPELServiceException, BaseException;
	public BPELServicesVO getBPELServiceById(String mpServiceId) throws NoSuchBPELServiceException, BaseException;
	public boolean isBPELServiceExist(String name) throws BaseException;
	
	public int searchCount(String searchPattern, BPELServiceColumnEnum columnEnum) throws BaseException;
	
	public BPELServicesVO[] search(String searchPattern, BPELServiceColumnEnum columnEnum) throws BaseException;


}
