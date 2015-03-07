package com.server.dao.daoapi;

import com.server.dao.enums.BPELServiceColumnEnum;
import com.server.execption.BaseException;
import com.server.vo.BPELServicesVO;

public interface IBPELServiceDAO {
	
	public void createBPELService(BPELServicesVO bpelServicesVO) throws BaseException;

	public void deleteBPELServiceByID(String bpelServiceId) throws BaseException;

	public void deleteBPELService(String name) throws BaseException;

	public void updateBPELService(BPELServicesVO bpelService) throws BaseException;

	public BPELServicesVO getBPELService(String name) throws BaseException;

	public BPELServicesVO getBPELServiceById(String bpelServiceId) throws BaseException;
	
	public int searchCount(String searchPattern, BPELServiceColumnEnum columnEnum) throws BaseException;
	
	public BPELServicesVO[] search(String searchPattern, BPELServiceColumnEnum columnEnum) throws BaseException;
	


}
