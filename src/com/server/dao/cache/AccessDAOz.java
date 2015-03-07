package com.server.dao.cache;

import java.io.InputStream;

import org.w3c.dom.Element;

import com.server.dao.daoapi.IBPELProxyServiceDAO;
import com.server.dao.daoapi.IBPELServiceDAO;
import com.server.dao.daoapi.IMinistryProxyServiceDAO;
import com.server.dao.daoapi.IMinstryServiceDAO;
import com.server.dao.daoapi.IRoleDAO;
import com.server.dao.daoapi.IUserDAO;
import com.server.dao.daoapi.IeServiceDAO;
import com.xmlparser.ClassUtils;
import com.xmlparser.XMLDomUtils;


	
	interface DBConstants {

		public final static String USER_DAO = "user_dao/dao";
		public final static String ROLE_DAO = "role_dao/dao";
		public final static String eService_DAO ="eService_dao/dao";
		public final static String MINISTRY_SERVICE_DAO="ministry_service_dao/dao";
		public final static String MINISTRY_PROXY_SERVICE_DAO="ministry_proxy_service_dao/dao";
		public final static String BPEL_SERVICE_DAO="bpel_service_dao/dao";
		public final static String BPEL_PROXY_SERVICE_DAO="bpel_proxy_service_dao/dao";


		
	}
	public class AccessDAOz implements DBConstants {

		private static AccessDAOz _instance = null;
		private org.w3c.dom.Document m_document;

		private AccessDAOz() throws java.rmi.RemoteException {
			try {
				init();
			} catch (Exception exp) {
				throw new java.rmi.RemoteException(exp.getMessage());
			}
		}
		private void init() throws Exception {
			try {
				InputStream dao_config_file = ClassUtils.getResourceAsStream(this.getClass(), "dao_config.xml");
				m_document = com.xmlparser.XMLDomUtils.parse(dao_config_file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		private Object loadClass(String className) throws Exception {
			return Class.forName(className).newInstance();
		}
		public static AccessDAOz getInstance() throws Exception {
			return _instance = _instance == null ? new AccessDAOz() : _instance;
		}

		public IUserDAO getUserDAO() throws Exception {
			return (IUserDAO) loadClass(this.getProperty(USER_DAO));
		}
		public IRoleDAO getRoleDAO() throws Exception {
			return (IRoleDAO) loadClass(this.getProperty(ROLE_DAO));
		}
		
		public IeServiceDAO geteServiceDAO() throws Exception {
			return (IeServiceDAO) loadClass(this.getProperty(eService_DAO));
		}
		
		public IMinstryServiceDAO getMinistryServiceDAO() throws Exception {
			return (IMinstryServiceDAO) loadClass(this.getProperty(MINISTRY_SERVICE_DAO));
		}
		
		
		public IMinistryProxyServiceDAO getMinistryProxyServiceDAO() throws Exception {
			return (IMinistryProxyServiceDAO) loadClass(this.getProperty(MINISTRY_PROXY_SERVICE_DAO));
		}
		
		
		public IBPELServiceDAO getBPELServiceDAO() throws Exception {
			return (IBPELServiceDAO) loadClass(this.getProperty(BPEL_SERVICE_DAO));
		}
		
		public IBPELProxyServiceDAO getBPELProxyServiceDAO() throws Exception {
			return (IBPELProxyServiceDAO) loadClass(this.getProperty(BPEL_PROXY_SERVICE_DAO));
		}
		
		
		
		
		
		public synchronized String getProperty(String propName) {
			String str = null;
			try {
				Element elem = XMLDomUtils.getElementByPath(m_document.getDocumentElement(), propName);
				if (elem == null) {
					return null;
				}
				str = XMLDomUtils.getElementText(elem);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return str;
		}
		


}
