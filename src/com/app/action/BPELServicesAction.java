package com.app.action;

import java.util.Collection;
import java.util.LinkedHashMap;

import com.app.api.IBPELService;
import com.app.api.IeService;
import com.server.constants.Constants;
import com.server.dao.enums.BPELServiceColumnEnum;
import com.server.factory.ManagersFactory;
import com.server.vo.BPELServicesVO;

public class BPELServicesAction extends BaseAction{
	
		private static final long serialVersionUID = 1L;
		private LinkedHashMap<String, BPELServicesVO> bpelServices = new LinkedHashMap<String, BPELServicesVO>();
		private Collection<BPELServicesVO> allbpelServices = null;
		private String search = null;
		public BPELServicesAction() {
			
		}

		public String execute(){
			try{
				super.execute();
				IBPELService ibpelService = ManagersFactory.getInstance().getBPELService(getUserId());
				if(getSearch()!=null){
					putInMap(ibpelService.search(getSearch(), BPELServiceColumnEnum.MINSTRY_NAME));
					putInMap(ibpelService.search(getSearch(), BPELServiceColumnEnum.ENDPOINT_1));
					putInMap(ibpelService.search(getSearch(), BPELServiceColumnEnum.ENDPOINT_2));
					putInMap(ibpelService.search(getSearch(), BPELServiceColumnEnum.SERVICE_TYPE));
				//	putInMap(iMinstryService.search(getSearch(), MinistryServiceColumnsEnum.ESERVICE_ID));
								
				} else {
					putInMap(ibpelService.search(Constants.SEARCH_PATTERN, BPELServiceColumnEnum.SERVICE_NAME));
				}
				setAllbpelServices(bpelServices.values());
				if(allbpelServices!=null&&allbpelServices.size()>0){
					System.out.println("Length of the BPELServices  are "+allbpelServices.size());
				} else {
					System.out.println("BPELServices length is zero..");
				}
			} catch(Exception e){
				setMessage(e.getMessage());
				setActionType(Constants.ACTION_TYPES[2]);
			}
			return SUCCESS;
		}

		private void putInMap(BPELServicesVO[] dummy_bpelServices) throws Exception{
			IeService ieService = ManagersFactory.getInstance().geteService(getLogInUser());
			if(dummy_bpelServices!=null&&dummy_bpelServices.length>0){
				for(BPELServicesVO pp : dummy_bpelServices){
					if(!bpelServices.containsKey(pp.getGuid())){
						if(pp.getEserviceId()!=null&&!pp.getEserviceId().equalsIgnoreCase("")){
							pp.setEserviceId(ieService.geteServiceById(pp.getEserviceId()).getEserviceName());
						}
						bpelServices.put(pp.getGuid(), pp);
					}
				}
			}
		}

		

		
		public LinkedHashMap<String, BPELServicesVO> getBpelServices() {
			return bpelServices;
		}

		public void setBpelServices(LinkedHashMap<String, BPELServicesVO> bpelServices) {
			this.bpelServices = bpelServices;
		}

		public Collection<BPELServicesVO> getAllbpelServices() {
			return allbpelServices;
		}

		public void setAllbpelServices(Collection<BPELServicesVO> allbpelServices) {
			this.allbpelServices = allbpelServices;
		}

		public String getSearch() {
			return search;
		}

		public void setSearch(String search) {
			this.search = search;
		}
		
		

		



	


}
