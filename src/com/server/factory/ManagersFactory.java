package com.server.factory;

import com.app.api.IBPELProxyService;
import com.app.api.IBPELService;
import com.app.api.IMinistryProxyService;
import com.app.api.IMinistryService;
import com.app.api.IRole;
import com.app.api.IUser;
import com.app.api.IeService;
import com.app.impl.BPELProxyServiceImpl;
import com.app.impl.BPELServiceImpl;
import com.app.impl.MinistryProxyServiceImpl;
import com.app.impl.MinistryServiceImpl;
import com.app.impl.RoleImpl;
import com.app.impl.UserImpl;
import com.app.impl.eServiceImpl;

public class ManagersFactory {
	
	
	private static ManagersFactory instance;

	private ManagersFactory() {
	}

	
	public static ManagersFactory getInstance() throws Exception {
		if (instance == null) {
			return instance = new ManagersFactory();
		} else {
			return instance;
		}
	}

	/*private Object loadClass(String className) throws Exception {
		java.lang.Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			
		}
		return obj;
	}*/
	
	
	public IUser getUser(String createdBy) throws Exception {
		IUser manager = new UserImpl(createdBy);
		return manager;
	}
	public IRole getRole(String createdBy) throws Exception {
		IRole manager = new RoleImpl(createdBy);
		return manager;
	}

	public IeService geteService(String createdBy) throws Exception {
		IeService manager = new eServiceImpl(createdBy);
		return manager;
	}
	
	public IMinistryService getMinistryService(String createdBy) throws Exception {
		IMinistryService manager = new MinistryServiceImpl(createdBy);
		return manager;
	}
	
	public IMinistryProxyService getMinistryProxyService(String createdBy) throws Exception {
		IMinistryProxyService manager = new MinistryProxyServiceImpl(createdBy);
		return manager;
	}
	
	public IBPELService getBPELService(String createdBy) throws Exception {
		IBPELService manager = new BPELServiceImpl(createdBy);
		return manager;
	}
	
	public IBPELProxyService getBPELProxyService(String createdBy) throws Exception {
		IBPELProxyService manager = new BPELProxyServiceImpl(createdBy);
		return manager;
	}
	
	

}
