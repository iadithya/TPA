package com.app.api;

import com.server.dao.enums.BPELProxyServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.execption.DuplicateBPELProxyServiceException;
import com.server.execption.NoSuchBPELProxyServiceException;
import com.server.vo.BPELProxyServicesVO;

public interface IBPELProxyService {
	
	public void createBPELProxyService(BPELProxyServicesVO bpelppService) throws DuplicateBPELProxyServiceException, BaseException;
	public void deleteeBPELProxyService(String name) throws NoSuchBPELProxyServiceException, BaseException;
	
	public void deleteBPELProxyServiceByID(String bpelpServiceId) throws NoSuchBPELProxyServiceException, BaseException;
	public void updateBPELProxyService(BPELProxyServicesVO mpService) throws NoSuchBPELProxyServiceException, BaseException;
	
	public BPELProxyServicesVO getBPELProxyService(String name) throws NoSuchBPELProxyServiceException, BaseException;
	public BPELProxyServicesVO getBPELProxyServiceById(String mpServiceId) throws NoSuchBPELProxyServiceException, BaseException;
	public boolean isBPELProxyServiceExist(String name) throws BaseException;
	
	public int searchCount(String searchPattern, BPELProxyServiceColumnsEnum columnEnum) throws BaseException;
	
	public BPELProxyServicesVO[] search(String searchPattern, BPELProxyServiceColumnsEnum columnEnum) throws BaseException;


}
