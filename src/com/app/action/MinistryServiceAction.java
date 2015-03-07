package com.app.action;

import com.app.api.IMinistryService;
import com.app.api.IRole;
import com.app.api.IeService;
import com.server.constants.Constants;
import com.server.dao.enums.eServiceColumnsEnum;
import com.server.factory.ManagersFactory;
import com.server.vo.MinistryServicesVO;
import com.server.vo.RoleVO;
import com.server.vo.eServiceVO;



public class MinistryServiceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private MinistryServicesVO[] allMinistryService = null;
	private String eserviceName;
	private String serviceName;
	private String ministryName;
	private String serviceType;
	private String guid;
	private String endpoint;
	private String keyword;
	private boolean status=true;
	private boolean isWSSecurity;
	private String roleName = null;
	private RoleVO[] roles = null;
	private eServiceVO[] eservices = null;
	public MinistryServiceAction() {
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
			IMinistryService iministryService = ManagersFactory.getInstance().getMinistryService(getLogInUser());
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
					MinistryServicesVO mService = new MinistryServicesVO( eservice.getGuid(),getServiceName(), getMinistryName(), getKeyword(), getServiceType(),getEndpoint()							);
					mService.setWSSecurity(isWSSecurity());
					iministryService.createMinistryService(mService);
					System.out.println("User is created..");
				}
			 else if(!getGuid().equalsIgnoreCase("")){
				MinistryServicesVO mservice = iministryService.getMinistryServiceById(getGuid());
				mservice.setMinstryName(getMinistryName());
				if((getRoleName().equals(Constants.ROLE_S[4])||getRoleName().equals(Constants.ROLE_S[5]))){
					if(eservice!=null){
						mservice.setEserviceId(eservice.getGuid());
					} else {
						setMessage("Select the eservice.");
					}
				} 
				
				mservice.setServiceName(serviceName);
				mservice.setServiceType(serviceType);
				mservice.setEndpoint(endpoint);
				mservice.setKeywords(keyword);			
				iministryService.updateMinistryService(mservice);
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
			IMinistryService iministryService = ManagersFactory.getInstance().getMinistryService(getLogInUser());
		//	IRole iRole = ManagersFactory.getInstance().getRole(getLogInUser());
			IeService ieservice = ManagersFactory.getInstance().geteService(getLogInUser());
			if(getGuid()!=null){
				
				MinistryServicesVO ministryService = iministryService.getMinistryServiceById(getGuid());
				setMinistryName(ministryService.getMinstryName());
				setServiceName(ministryService.getServiceName());
				setServiceType(ministryService.getServiceType());
				setEndpoint(ministryService.getEndpoint());
				setKeyword(ministryService.getKeywords());
				setWSSecurity(ministryService.isWSSecurity());
				System.out.println("Customer User is fetched..");
			//	RoleVO role = iRole.getRoleById(user.getRoleId());
				eServiceVO eservice = null;
				if(ministryService.getEserviceId()!=null&&!ministryService.getEserviceId().equalsIgnoreCase("")){
					eservice = ieservice.geteServiceById(ministryService.getEserviceId());
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
				IMinistryService iministryService = ManagersFactory.getInstance().getMinistryService(getLogInUser());
				MinistryServicesVO ministryService = iministryService.getMinistryServiceById(getGuid());
				setMinistryName(ministryService.getMinstryName());
				setServiceName(ministryService.getServiceName());
				setServiceType(ministryService.getServiceType());
				setEndpoint(ministryService.getEndpoint());
				setWSSecurity(ministryService.isWSSecurity());
				setKeyword(ministryService.getKeywords());
		//		IRole iRole = ManagersFactory.getInstance().getRole(getLogInUser());
		//		RoleVO role = iRole.getRoleById(user.getRoleId());
				
				IeService ieservice = ManagersFactory.getInstance().geteService(getLogInUser());
				eServiceVO eservice = null;
				if(ministryService.getEserviceId()!=null&&!ministryService.getEserviceId().equalsIgnoreCase("")){
					eservice = ieservice.geteServiceById(ministryService.getEserviceId());
				}
		//		setRoleName(role.getName());
				if(eservice!=null){
					setEserviceName(eservice.getEserviceName());
				}
				
				System.out.println("Customer User is fetched..");
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
			IMinistryService iministryService = ManagersFactory.getInstance().getMinistryService(getLogInUser());
			if(getGuid()!=null){
				
				iministryService.deleteMinistryServiceByID(getGuid());
				System.out.println("ministry Service is deleted now..");
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

	public MinistryServicesVO[] getAllMinistryService() {
		return allMinistryService;
	}

	public void setAllMinistryService(MinistryServicesVO[] allMinistryService) {
		this.allMinistryService = allMinistryService;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean isWSSecurity() {
		return isWSSecurity;
	}

	public void setWSSecurity(boolean isWSSecurity) {
		this.isWSSecurity = isWSSecurity;
	}

	
	
	
}
