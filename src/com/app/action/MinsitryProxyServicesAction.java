package com.app.action;

import java.util.Collection;
import java.util.LinkedHashMap;

import com.app.api.IMinistryProxyService;
import com.app.api.IeService;
import com.server.constants.Constants;
import com.server.dao.enums.MinistryProxyServiceColumnEnum;
import com.server.factory.ManagersFactory;
import com.server.vo.MinistryProxyServicesVO;

public class MinsitryProxyServicesAction extends BaseAction{
	
	
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, MinistryProxyServicesVO> ministryProxyServices = new LinkedHashMap<String, MinistryProxyServicesVO>();
	private Collection<MinistryProxyServicesVO> allMinistryProxyServices = null;
	private String search = null;
	public MinsitryProxyServicesAction() {
		
	}

	public String execute(){
		try{
			super.execute();
			IMinistryProxyService iMinstryProxyService = ManagersFactory.getInstance().getMinistryProxyService(getUserId());
			if(getSearch()!=null){
				putInMap(iMinstryProxyService.search(getSearch(), MinistryProxyServiceColumnEnum.MINSTRY_NAME));
				putInMap(iMinstryProxyService.search(getSearch(), MinistryProxyServiceColumnEnum.ENDPOINT_1));
				putInMap(iMinstryProxyService.search(getSearch(), MinistryProxyServiceColumnEnum.ENDPOINT_2));
				putInMap(iMinstryProxyService.search(getSearch(), MinistryProxyServiceColumnEnum.SERVICE_TYPE));
			//	putInMap(iMinstryService.search(getSearch(), MinistryServiceColumnsEnum.ESERVICE_ID));
							
			} else {
				putInMap(iMinstryProxyService.search(Constants.SEARCH_PATTERN, MinistryProxyServiceColumnEnum.SERVICE_NAME));
			}
			setAllMinistryProxyServices(ministryProxyServices.values());
			if(allMinistryProxyServices!=null&&allMinistryProxyServices.size()>0){
				System.out.println("Length of the aMinistryProxyServices  are "+allMinistryProxyServices.size());
			} else {
				System.out.println("ministryProxyServices length is zero..");
			}
		} catch(Exception e){
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		}
		return SUCCESS;
	}

	private void putInMap(MinistryProxyServicesVO[] dummy_ministryProxyServices) throws Exception{
		IeService ieService = ManagersFactory.getInstance().geteService(getLogInUser());
		if(dummy_ministryProxyServices!=null&&dummy_ministryProxyServices.length>0){
			for(MinistryProxyServicesVO pp : dummy_ministryProxyServices){
				if(!ministryProxyServices.containsKey(pp.getGuid())){
					if(pp.getEserviceId()!=null&&!pp.getEserviceId().equalsIgnoreCase("")){
						pp.setEserviceId(ieService.geteServiceById(pp.getEserviceId()).getEserviceName());
					}
					ministryProxyServices.put(pp.getGuid(), pp);
				}
			}
		}
	}

	

	public LinkedHashMap<String, MinistryProxyServicesVO> getMinistryProxyServices() {
		return ministryProxyServices;
	}

	public void setMinistryProxyServices(
			LinkedHashMap<String, MinistryProxyServicesVO> ministryProxyServices) {
		this.ministryProxyServices = ministryProxyServices;
	}

	public Collection<MinistryProxyServicesVO> getAllMinistryProxyServices() {
		return allMinistryProxyServices;
	}

	public void setAllMinistryProxyServices(
			Collection<MinistryProxyServicesVO> allMinistryProxyServices) {
		this.allMinistryProxyServices = allMinistryProxyServices;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	

	



}
