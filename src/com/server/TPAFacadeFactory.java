package com.server;

import com.server.execption.BaseException;

public class TPAFacadeFactory {
	
	
private static TPAFacade tfaFacade = null;
	
	private static final String tfa_FACADE = "com.server.TPAFacade";
	private TPAFacadeFactory(){
		
	}
	
	private static TPAFacadeFactory _self = null;
	
	/**
	 * @return
	 */
	public static TPAFacadeFactory getInstance(){
		_self = _self == null ? new TPAFacadeFactory(): _self;
		return _self;
	}
	
	/**
	 * @return
	 */
	public TPAFacade getTPAFacade(String userName, String password) throws BaseException{
		try {
			if(tfaFacade == null){
				tfaFacade = (TPAFacade)loadClass(tfa_FACADE);
				tfaFacade.init(userName, password);
			}else{
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e.getMessage(), e);
		}
		return tfaFacade;
	}
	private static Object loadClass(String className) throws Exception {
		return Class.forName(className).newInstance();
	}
	

}
