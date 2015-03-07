package com.app.action;

import com.app.api.IMinistryProxyService;
import com.app.api.IeService;
import com.server.constants.Constants;
import com.server.dao.enums.eServiceColumnsEnum;
import com.server.factory.ManagersFactory;
import com.server.vo.MinistryProxyServicesVO;
import com.server.vo.RoleVO;
import com.server.vo.eServiceVO;

public class MinistryProxyServiceAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private MinistryProxyServicesVO[] allMinistryProxyService = null;
	private String eserviceName;
	private String ministryProxyName;
	private String serviceName;
	private String ministryName;
	private String serviceType;
	private String guid;
	private String endpoint_1;
	private String endpoint_2;
	private boolean isWSSecurity_1;
	private String keyword;
	private boolean status=true;
	private boolean isWSSecurity_2;
	private String roleName = null;
	private RoleVO[] roles = null;
	private eServiceVO[] eservices = null;
	public MinistryProxyServiceAction() {
	}

	
	public String execute()  {
		try{
			super.execute();
			
			defaultData();
			if(getMessage()==null){
				return SUCCESS;
			}
		} catch(Exception e){
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		}
		return INPUT;
	}
	private void defaultData(){
		try{
			setGuid("");
			//roles = Constants.ROLE_S;
		//	IRole iRole = ManagersFactory.getInstance().getRole(getLogInUser());
		//	roles = iRole.search(Constants.SEARCH_PATTERN, RoleColumnsEnum.NAME);
			
			IeService iCompany = ManagersFactory.getInstance().geteService(getLogInUser());
			eservices = iCompany.search(Constants.SEARCH_PATTERN, eServiceColumnsEnum.eSERVICE_NAME);
			
			//IUser iUser = ManagersFactory.getInstance().getUser(getLogInUser());
			//acctMgrs = iUser.search(Constants.ROLE_S[2], UserColumnsEnum.ROLE_ID);
			
			
		} catch(Exception e){
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		}
	}
	
	public String save() {
		try{
			setActionType(Constants.ACTION_TYPES[3]);
			super.execute();
			IMinistryProxyService iministryProxyService = ManagersFactory.getInstance().getMinistryProxyService(getLogInUser());
//			IRole iRole = ManagersFactory.getInstance().getRole(getLogInUser());
			IeService ieservice = ManagersFactory.getInstance().geteService(getLogInUser());
		//	RoleVO role = iRole.getRole(getRoleName());
			eServiceVO eservice = null;
			if(getEserviceName()!=null && !getEserviceName().equals("-Select-")){
				eservice = ieservice.geteService(getEserviceName());
			}
			//User accUserObj = null;
			System.out.println("UserAction.create...."+getMinistryName()+" "+getRoleName());
			if(getEserviceName()!=null&&getMinistryName()!=null
					&&getGuid().equalsIgnoreCase("")&&getServiceType()!=null){
						System.out.println("ERROR in Inputs");
					MinistryProxyServicesVO mpService = new MinistryProxyServicesVO( getMinistryProxyName(),eservice.getGuid(),getServiceName(), getMinistryName(),getServiceType(), isWSSecurity_1(),isWSSecurity_2(),getEndpoint_1(),getEndpoint_2(),getKeyword());
					
					iministryProxyService.createMinistryProxyService(mpService);
					System.out.println("MinistryProxy Service is created..");
				}
			 else if(!getGuid().equalsIgnoreCase("")){
				 MinistryProxyServicesVO mpservice = iministryProxyService.getMinistryProxyServiceById(getGuid());
				mpservice.setMinistryProxyName(getMinistryProxyName());
				if((getRoleName().equals(Constants.ROLE_S[4])||getRoleName().equals(Constants.ROLE_S[5]))){
					if(eservice!=null){
						mpservice.setEserviceId(eservice.getGuid());
					} else {
						setMessage("Select the eservice.");
					}
				} 
				
				mpservice.setServiceName(serviceName);
				mpservice.setServiceType(serviceType);
				mpservice.setEndpoint1(endpoint_1);
				mpservice.setEndpoint1(endpoint_2);
			//	mpservice.setWSSecurity1(isWSSecurity1);
			// mpservice.setWSSecurity2(isWSSecurity2);	
				mpservice.setKeywords(keyword);			
				iministryProxyService.updateMinistryProxyService(mpservice);
				System.out.println("User is updated..");
			} 
		
			if(getMessage()==null){
				return SUCCESS;
			} else {
				defaultData();
			}
		} catch(Exception e){
			defaultData();
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		}
		return INPUT;
	}
	public String get() {
		try{
			super.execute();
			IMinistryProxyService iministryProxyService = ManagersFactory.getInstance().getMinistryProxyService(getLogInUser());
		//	IRole iRole = ManagersFactory.getInstance().getRole(getLogInUser());
			IeService ieservice = ManagersFactory.getInstance().geteService(getLogInUser());
			if(getGuid()!=null){
				
				MinistryProxyServicesVO ministryProxyService = iministryProxyService.getMinistryProxyServiceById(getGuid());
				setMinistryName(ministryProxyService.getMinistryName());
				setMinistryProxyName(ministryProxyService.getMinistryProxyName());
				setServiceName(ministryProxyService.getServiceName());
				setServiceType(ministryProxyService.getServiceType());
				setEndpoint_1(ministryProxyService.getEndpoint1());
				setEndpoint_2(ministryProxyService.getEndpoint2());
				setWSSecurity_1(ministryProxyService.isWSSecurity1());
				setWSSecurity_2(ministryProxyService.isWSSecurity2());
				setKeyword(ministryProxyService.getKeywords());
				System.out.println("Customer User is fetched..");
			//	RoleVO role = iRole.getRoleById(user.getRoleId());
				eServiceVO eservice = null;
				if(ministryProxyService.getEserviceId()!=null&&!ministryProxyService.getEserviceId().equalsIgnoreCase("")){
					eservice = ieservice.geteServiceById(ministryProxyService.getEserviceId());
				}
		//		setRoleName(role.getName());
				if(eservice!=null){
					setEserviceName(eservice.getEserviceName());
				}
			} else {
				setMessage("Guid is empty.");
			}
			
		//	roles = iRole.search(Constants.SEARCH_PATTERN, RoleColumnsEnum.NAME);
			
			
			eservices = ieservice.search(Constants.SEARCH_PATTERN, eServiceColumnsEnum.eSERVICE_NAME);
			
		//	acctMgrs = iUser.search(Constants.ROLE_S[2], UserColumnsEnum.ROLE_ID);
			
			if(getMessage()==null){
				return SUCCESS;
			}
		} catch(Exception e){
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
			
		}
		return INPUT;
	}
	public String view() {
		try{
			super.execute();
			if(getGuid()!=null){
				IMinistryProxyService iministryProxyService = ManagersFactory.getInstance().getMinistryProxyService(getLogInUser());
				MinistryProxyServicesVO ministryProxyService = iministryProxyService.getMinistryProxyServiceById(getGuid());
				setMinistryName(ministryProxyService.getMinistryName());
				setMinistryProxyName(ministryProxyService.getMinistryProxyName());
				setServiceName(ministryProxyService.getServiceName());
				setServiceType(ministryProxyService.getServiceType());
				setEndpoint_1(ministryProxyService.getEndpoint1());
				setEndpoint_2(ministryProxyService.getEndpoint2());
				setWSSecurity_1(ministryProxyService.isWSSecurity1());
				setWSSecurity_2(ministryProxyService.isWSSecurity2());
				setKeyword(ministryProxyService.getKeywords());
		//		IRole iRole = ManagersFactory.getInstance().getRole(getLogInUser());
		//		RoleVO role = iRole.getRoleById(user.getRoleId());
				
				IeService ieservice = ManagersFactory.getInstance().geteService(getLogInUser());
				eServiceVO eservice = null;
				if(ministryProxyService.getEserviceId()!=null&&!ministryProxyService.getEserviceId().equalsIgnoreCase("")){
					eservice = ieservice.geteServiceById(ministryProxyService.getEserviceId());
				}
		//		setRoleName(role.getName());
				if(eservice!=null){
					setEserviceName(eservice.getEserviceName());
				}
				
				System.out.println("Customer ministryProxyService is fetched..");
			} else {
				setMessage("Guid is empty.");
			}
			if(getMessage()==null){
				return SUCCESS;
			}
		} catch(Exception e){
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
			
		}
		return INPUT;
	}
	public String delete() {
		try{
			super.execute();
			IMinistryProxyService iministryProxyService = ManagersFactory.getInstance().getMinistryProxyService(getLogInUser());
			if(getGuid()!=null){
				
				iministryProxyService.deleteMinistryProxyServiceByID(getGuid());
				System.out.println("ministry Proxy Service is deleted now..");
			} 
			if(getMessage()==null){
				return SUCCESS;
			}
		} catch(Exception e){
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		}
		return INPUT;
	}


	public MinistryProxyServicesVO[] getAllMinistryProxyService() {
		return allMinistryProxyService;
	}


	public void setAllMinistryProxyService(
			MinistryProxyServicesVO[] allMinistryProxyService) {
		this.allMinistryProxyService = allMinistryProxyService;
	}


	public String getEserviceName() {
		return eserviceName;
	}


	public void setEserviceName(String eserviceName) {
		this.eserviceName = eserviceName;
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public String getMinistryName() {
		return ministryName;
	}


	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}


	public String getServiceType() {
		return serviceType;
	}


	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}


	public String getGuid() {
		return guid;
	}


	public void setGuid(String guid) {
		this.guid = guid;
	}


	public String getEndpoint_1() {
		return endpoint_1;
	}


	public void setEndpoint_1(String endpoint_1) {
		this.endpoint_1 = endpoint_1;
	}


	public String getEndpoint_2() {
		return endpoint_2;
	}


	public void setEndpoint_2(String endpoint_2) {
		this.endpoint_2 = endpoint_2;
	}


	public boolean isWSSecurity_1() {
		return isWSSecurity_1;
	}


	public void setWSSecurity_1(boolean isWSSecurity_1) {
		this.isWSSecurity_1 = isWSSecurity_1;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public boolean isWSSecurity_2() {
		return isWSSecurity_2;
	}


	public void setWSSecurity_2(boolean isWSSecurity_2) {
		this.isWSSecurity_2 = isWSSecurity_2;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public RoleVO[] getRoles() {
		return roles;
	}


	public void setRoles(RoleVO[] roles) {
		this.roles = roles;
	}


	public eServiceVO[] getEservices() {
		return eservices;
	}


	public void setEservices(eServiceVO[] eservices) {
		this.eservices = eservices;
	}


	public String getMinistryProxyName() {
		return ministryProxyName;
	}


	public void setMinistryProxyName(String ministryProxyName) {
		this.ministryProxyName = ministryProxyName;
	}

	
	
	

}
