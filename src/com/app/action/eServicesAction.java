package com.app.action;

import java.util.Collection;
import java.util.LinkedHashMap;

import com.app.api.IeService;
import com.server.constants.Constants;
import com.server.dao.enums.eServiceColumnsEnum;
import com.server.factory.ManagersFactory;
import com.server.vo.eServiceVO;

public class eServicesAction extends BaseAction{
	
		private static final long serialVersionUID = 1L;
		private LinkedHashMap<String, eServiceVO> eservices = new LinkedHashMap<String, eServiceVO>();
		private Collection<eServiceVO> alleservices = null;
		private String search = null;
		public eServicesAction() {
			
		}

		public String execute(){
			try{
				super.execute();
				IeService ieService = ManagersFactory.getInstance().geteService(getUserId());
				if(getSearch()!=null){
					putInMap(ieService.search(getSearch(), eServiceColumnsEnum.eSERVICE_NAME));
					putInMap(ieService.search(getSearch(), eServiceColumnsEnum.MINSTRY_NAME));
					putInMap(ieService.search(getSearch(), eServiceColumnsEnum.PROJECT_MANAGER_NAME));
				} else {
					putInMap(ieService.search(Constants.SEARCH_PATTERN, eServiceColumnsEnum.eSERVICE_NAME));
				}
				setAlleservices(eservices.values());
				if(alleservices!=null&&alleservices.size()>0){
					System.out.println("Length of the eServices are "+alleservices.size());
				} else {
					System.out.println("eServices length is zero..");
				}
			} catch(Exception e){
				setMessage(e.getMessage());
				setActionType(Constants.ACTION_TYPES[2]);
			}
			return SUCCESS;
		}

		private void putInMap(eServiceVO[] dummy_eServices){
			if(dummy_eServices!=null&&dummy_eServices.length>0){
				for(eServiceVO pp : dummy_eServices){
					if(!eservices.containsKey(pp.getGuid())){
						eservices.put(pp.getGuid(), pp);
					}
				}
			}
		}
		
		

		
		
		public Collection<eServiceVO> getAlleservices() {
			return alleservices;
		}

		public void setAlleservices(Collection<eServiceVO> alleservices) {
			this.alleservices = alleservices;
		}

		/**
		 * @return the search
		 */
		public String getSearch() {
			return search;
		}

		/**
		 * @param search the search to set
		 */
		public void setSearch(String search) {
			this.search = search;
		}


}
