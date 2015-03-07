package com.server.auth;

import java.util.Properties;

public class AuthenticationFactory {
	
	
	/** To persist the key value pairs */
	private static Properties m_props = null;

	/** Creates a new instance of AuthenticationFactory */
	private AuthenticationFactory() throws Exception {
		initialize();
	}

	/** Creates a new instance of AuthenticationFactory */
	private static AuthenticationFactory m_self;

	/**
	 * This variable will check whether the AuthenticationFactory is
	 * initialize's or not
	 */
	private static boolean m_inited = false;

	/** Creates a new instance of Logger */

	/** Creates a new instance of Authenticator */
	private IAuthenticator m_authen = null;

	/**
	 * This method will initializes the Authentication Factory by reading the
	 * Properties from config file
	 */
	private void initialize() throws Exception {
		if (m_inited)
			return;
		try {
			m_props = new Properties();
			//m_props = PropertiesLoader.getInstance().getProperties(Constants.DB_PROPERTIES);
			m_authen = (IAuthenticator) loadClass(DBAuthenticator.class.getCanonicalName());
			m_authen.init(m_props);
			//throw new Exception("Configuration is not initialized properly");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(t.getMessage());
		}
	}

	/**
	 * This method will returns the AuthenticationFactory instance
	 */
	public static AuthenticationFactory getInstance() throws Exception {
		m_self = m_self == null ? new AuthenticationFactory() : m_self;
		return m_self;
	}
	private static Object loadClass(String className) throws Exception {
		return Class.forName(className).newInstance();
	}
	/**
	 *This method will return the IAuthenticator which been initialized
	 */
	public IAuthenticator getAuthenticator() {
		return m_authen;
	}

	/**
	 * This method will returns Properties configured in config file
	 */
	public Properties getProperties() {
		return m_props;
	}

}
