package com.server.auth;

import java.util.Properties;

public interface IAuthenticator {

	public void init(Properties props);

	public String authenticate(String username, String password);

}
