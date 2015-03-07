package com.app.action;

import java.util.Collection;
import java.util.LinkedHashMap;

import com.app.api.IMinistryService;
import com.app.api.IeService;
import com.server.constants.Constants;
import com.server.dao.enums.MinistryServiceColumnsEnum;
import com.server.execption.BaseException;
import com.server.execption.NoSucheServiceException;
import com.server.factory.ManagersFactory;
import com.server.vo.MinistryServicesVO;

public class MinsitryServicesAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, MinistryServicesVO> ministryServices = new LinkedHashMap<String, MinistryServicesVO>();
	private Collection<MinistryServicesVO> allMinistryServices = null;
	private String search = null;
	public MinsitryServicesAction() {
		
	}

	public String execute(){
		try{
			super.execute();
			IMinistryService iMinstryService = ManagersFactory.getInstance().getMinistryService(getUserId());
			if(getSearch()!=null){
				putInMap(iMinstryService.search(getSearch(), MinistryServiceColumnsEnum.MINSTRY_NAME));
				putInMap(iMinstryService.search(getSearch(), MinistryServiceColumnsEnum.ENDPOINT));
				putInMap(iMinstryService.search(getSearch(), MinistryServiceColumnsEnum.SERVICE_TYPE));
			//	putInMap(iMinstryService.search(getSearch(), MinistryServiceColumnsEnum.ESERVICE_ID));
							
			} else {
				putInMap(iMinstryService.search(Constants.SEARCH_PATTERN, MinistryServiceColumnsEnum.SERVICE_NAME));
			}
			setAllMinistryServices(ministryServices.values());
			if(allMinistryServices!=null&&allMinistryServices.size()>0){
				System.out.println("Length of the ministryServices are "+allMinistryServices.size());
			} else {
				System.out.println("ministryServices length is zero..");
			}
		} catch(Exception e){
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		}
		return SUCCESS;
	}

	private void putInMap(MinistryServicesVO[] dummy_ministryServices) throws Exception{
		IeService ieService = ManagersFactory.getInstance().geteService(getLogInUser());
		if(dummy_ministryServices!=null&&dummy_ministryServices.length>0){
			for(MinistryServicesVO pp : dummy_ministryServices){
				if(!ministryServices.containsKey(pp.getGuid())){
					if(pp.getEserviceId()!=null&&!pp.getEserviceId().equalsIgnoreCase("")){
						pp.setEserviceId(ieService.geteServiceById(pp.getEserviceId()).getEserviceName());
					}
					ministryServices.put(pp.getGuid(), pp);
				}
			}
		}
	}

	public LinkedHashMap<String, MinistryServicesVO> getMinistryServices() {
		return ministryServices;
	}

	public void setMinistryServices(
			LinkedHashMap<String, MinistryServicesVO> ministryServices) {
		this.ministryServices = ministryServices;
	}

	public Collection<MinistryServicesVO> getAllMinistryServices() {
		return allMinistryServices;
	}

	public void setAllMinistryServices(
			Collection<MinistryServicesVO> allMinistryServices) {
		this.allMinistryServices = allMinistryServices;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	

	


	
	

}
