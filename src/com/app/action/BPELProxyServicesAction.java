package com.app.action;

import java.util.Collection;
import java.util.LinkedHashMap;

import com.app.api.IBPELProxyService;
import com.app.api.IeService;
import com.server.constants.Constants;
import com.server.dao.enums.BPELProxyServiceColumnsEnum;
import com.server.factory.ManagersFactory;
import com.server.vo.BPELProxyServicesVO;

public class BPELProxyServicesAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, BPELProxyServicesVO> bpelProxyServices = new LinkedHashMap<String, BPELProxyServicesVO>();
	private Collection<BPELProxyServicesVO> allbpelProxyServices = null;
	private String search = null;
	public BPELProxyServicesAction() {
		
	}

	public String execute(){
		try{
			super.execute();
		System.out.println("BPEL PROXY SERVICE");
			IBPELProxyService iBPELProxyService = ManagersFactory.getInstance().getBPELProxyService(getUserId());
			if(getSearch()!=null){
				putInMap(iBPELProxyService.search(getSearch(), BPELProxyServiceColumnsEnum.MINSTRY_NAME));
				putInMap(iBPELProxyService.search(getSearch(), BPELProxyServiceColumnsEnum.ENDPOINT_1));
				putInMap(iBPELProxyService.search(getSearch(), BPELProxyServiceColumnsEnum.ENDPOINT_2));
				putInMap(iBPELProxyService.search(getSearch(), BPELProxyServiceColumnsEnum.SERVICE_TYPE));
			//	putInMap(iMinstryService.search(getSearch(), MinistryServiceColumnsEnum.ESERVICE_ID));
							
			} else {
				putInMap(iBPELProxyService.search(Constants.SEARCH_PATTERN, BPELProxyServiceColumnsEnum.SERVICE_NAME));
			}
			setAllbpelProxyServices(bpelProxyServices.values());
			if(allbpelProxyServices!=null&&allbpelProxyServices.size()>0){
				System.out.println("Length of the bpelProxyServices  are "+allbpelProxyServices.size());
			} else {
				System.out.println("bpelProxyServices length is zero..");
			}
		} catch(Exception e){
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		}
		return SUCCESS;
	}

	private void putInMap(BPELProxyServicesVO[] dummy_bpelProxyServices) throws Exception{
		IeService ieService = ManagersFactory.getInstance().geteService(getLogInUser());
		if(dummy_bpelProxyServices!=null&&dummy_bpelProxyServices.length>0){
			for(BPELProxyServicesVO pp : dummy_bpelProxyServices){
		
				if(!bpelProxyServices.containsKey(pp.getGuid())){
					if(pp.getEserviceId()!=null&&!pp.getEserviceId().equalsIgnoreCase("")){
						pp.setEserviceId(ieService.geteServiceById(pp.getEserviceId()).getEserviceName());
					}
					bpelProxyServices.put(pp.getGuid(), pp);
				}
			}
		}
	}


	public LinkedHashMap<String, BPELProxyServicesVO> getBpelProxyServices() {
		return bpelProxyServices;
	}

	public void setBpelProxyServices(
			LinkedHashMap<String, BPELProxyServicesVO> bpelProxyServices) {
		this.bpelProxyServices = bpelProxyServices;
	}

	public Collection<BPELProxyServicesVO> getAllbpelProxyServices() {
		return allbpelProxyServices;
	}

	public void setAllbpelProxyServices(
			Collection<BPELProxyServicesVO> allbpelProxyServices) {
		this.allbpelProxyServices = allbpelProxyServices;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	

	


}
