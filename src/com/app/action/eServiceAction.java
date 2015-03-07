package com.app.action;

import com.app.api.IeService;
import com.server.constants.Constants;
import com.server.factory.ManagersFactory;
import com.server.vo.eServiceVO;

public class eServiceAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private eServiceVO[] alleServices = null;
	private String guid;
	private String eserviceName ;
	
	
	

	private String minstryName;
	private String projectManagerName;
	
	
	
	
	public String getEserviceName() {
		return eserviceName;
	}

	public void setEserviceName(String eserviceName) {
		this.eserviceName = eserviceName;
	}

	public eServiceAction() {
	}

	public String execute() {
		try{
			String returnValue = super.execute();
			//if(!returnValue.equalsIgnoreCase(SUCCESS))
				//return "ANONYMOUS";
			defaultData();
		} catch(Exception e){
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		}
		return SUCCESS;
	}
	private void defaultData(){
		try{
			setGuid("");
			
			
		} catch(Exception e){
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		}
	}
	
	public String save(){
		try{
			setActionType(Constants.ACTION_TYPES[3]);
			super.execute();
			IeService ieService = ManagersFactory.getInstance().geteService(getUserId());
			System.out.println("eServiceAction.create...."+eserviceName);
			if(eserviceName!=null&&getGuid().equalsIgnoreCase("")){
				eServiceVO eService = new eServiceVO(eserviceName, minstryName,projectManagerName);
				ieService.createeService(eService);
				System.out.println("eService is created..");
			} else if(!getGuid().equalsIgnoreCase("")){
				eServiceVO eService = ieService.geteServiceById(getGuid());
				eService.setEserviceName(getEserviceName());
				eService.setMinstryName(getMinstryName());
				eService.setProjectManagerName(getProjectManagerName());
				ieService.updateeService(eService);
				System.out.println("eService is updated..");
			} 
			if(getMessage()==null){
				setActionType(Constants.ACTION_TYPES[0]);
				setMessage("Event with name "+getEserviceName()+" saved successfully.");
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
	public String get(){
		try{
			super.execute();
			if(getGuid()!=null){
				IeService ieService = ManagersFactory.getInstance().geteService(getUserId());
				eServiceVO eService = ieService.geteServiceById(getGuid());
				setEserviceName(eService.getEserviceName());
				setMinstryName(eService.getMinstryName());
				setProjectManagerName(eService.getProjectManagerName());
				System.out.println("Customer eService is fetched..");
			} else {
				setMessage("Guid is empty.");
			}
		} catch(Exception e){
			defaultData();
			setMessage(e.getMessage());
			setActionType(Constants.ACTION_TYPES[2]);
		}
		return SUCCESS;
	}
	public String view(){
		try{
			super.execute();
			if(getGuid()!=null){
				IeService ieService = ManagersFactory.getInstance().geteService(getUserId());
				eServiceVO eService = ieService.geteServiceById(getGuid());
				setEserviceName(eService.getEserviceName());
				setMinstryName(eService.getMinstryName());
				setProjectManagerName(eService.getProjectManagerName());
				System.out.println("Customer eService is fetched..");
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
	public String delete(){
		try{
			super.execute();
			if(getGuid()!=null){
				IeService ieService = ManagersFactory.getInstance().geteService(getUserId());
				ieService.deleteeServiceByID(getGuid());
				
				System.out.println(" eService is Deleted..");
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

	public eServiceVO[] getAlleServices() {
		return alleServices;
	}

	public void setAlleServices(eServiceVO[] alleServices) {
		this.alleServices = alleServices;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	


	public String getMinstryName() {
		return minstryName;
	}

	public void setMinstryName(String minstryName) {
		this.minstryName = minstryName;
	}

	public String getProjectManagerName() {
		return projectManagerName;
	}

	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}
	
	
	
	
}
