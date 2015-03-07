package com.server.dao.daoapi;

import com.server.dao.enums.BPELProxyServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.vo.BPELProxyServicesVO;

public interface IBPELProxyServiceDAO {
	
	public void createBPELProxyService(BPELProxyServicesVO bpelProxyServicesVO) throws BaseException;

	public void deleteBPELProxyServiceByID(String bpelProxyServiceId) throws BaseException;

	public void deleteBPELProxyService(String name) throws BaseException;

	public void updateBPELProxyService(BPELProxyServicesVO bpelProxyService) throws BaseException;

	public BPELProxyServicesVO getBPELProxyService(String name) throws BaseException;

	public BPELProxyServicesVO getBPELProxyServiceById(String bpelProxyServiceId) throws BaseException;
	
	public int searchCount(String searchPattern, BPELProxyServiceColumnsEnum columnEnum) throws BaseException;
	
	public BPELProxyServicesVO[] search(String searchPattern, BPELProxyServiceColumnsEnum columnEnum) throws BaseException;
	

}
