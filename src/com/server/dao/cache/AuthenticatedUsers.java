package com.server.dao.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import com.server.execption.BaseException;
import com.server.execption.SessionExpiredException;

public class AuthenticatedUsers {

	

	/**
	 * The actual cache that keeps the user id's to the authentication strings
	 * provided to them for further access to the cloud system.
	 */
	private HashMap<String, UMSEntityObj> mAuthenticUsers;

	/**
	 * Singleton instance object for the authenticated users cache. We must
	 * always have a single instance of the object all the time.
	 */
	private static AuthenticatedUsers __instance;

	/**
	 * The message logger for {@link AuthenticatedUsers} class
	 */

	/**
	 * Private constructor for the {@link AuthenticatedUsers} class.
	 */
	private AuthenticatedUsers() {
		mAuthenticUsers = new HashMap<String, UMSEntityObj>();
	}

	/**
	 * Instance grabber for the singleton class {@link AuthenticatedUsers}
	 * 
	 * @return A reference to the instance of the class
	 */
	public static AuthenticatedUsers getInstance() {
		if (__instance == null) {
			synchronized (AuthenticatedUsers.class) {
				if (__instance == null) {
					__instance = new AuthenticatedUsers();
				}
			}
		}

		return __instance;
	}

	/**
	 * Clones are not supported for {@link AuthenticatedUsers} so this prevents
	 * the users from doing so.
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("This is a Singleton Ojbect; Buzz off");
	}

	public boolean setAuthenticationForUser(String authToken, UMSEntityObj umsEntityObj) {
		try {
			if (umsEntityObj != null && authToken != null) {
				synchronized (AuthenticatedUsers.class) {
					umsEntityObj.setAuthenticatedTime(new Date());
					mAuthenticUsers.put(authToken, umsEntityObj);

				}
			}
		} catch (Exception e) {

			return false;
		}

		return true;
	}

	public boolean isAuthTokenExist(UMSEntityObj umsEntityObj) {
		boolean flag = false;
		try {
			if (umsEntityObj != null) {
				// synchronized (AuthenticatedUsers.class) {
				if (mAuthenticUsers.containsValue(umsEntityObj)) {
				
					System.out.println("Auth token is exist....");
					flag = true;
				}
				// }
			}
		} catch (Exception e) {
		}
		return flag;
	}

	public String getAuthToken(UMSEntityObj umsEntityObj) {
		String key = null;
		try {
			if (umsEntityObj != null) {
				Set<String> keys = mAuthenticUsers.keySet();
				UMSEntityObj obj = null;
				for (String key_n : keys) {
					obj = mAuthenticUsers.get(key_n);
					if ((obj.getGuid().equalsIgnoreCase(umsEntityObj.getGuid()) && (obj.getUsername().equalsIgnoreCase(umsEntityObj.getUsername())))) {
						key = key_n;
					}
				}
			}
		} catch (Exception e) {
		}
		return key;
	}

	/**
	 * @param authToken
	 */
	public void invalidateToken(String authToken) throws BaseException {
		try {
			if (authToken != null) {
				synchronized (AuthenticatedUsers.class) {
					UMSEntityObj umsEntityObj = mAuthenticUsers.get(authToken);
					if (umsEntityObj != null) {
						mAuthenticUsers.remove(authToken);
						// throw new
						// BaseUMSException(HttpReturnCodes.TOKEN_REVALIDATE);
					} else {
						throw new BaseException(HttpReturnCodes.TOKEN_NOT_VALID);
					}
				}
			} else {
				throw new BaseException(HttpReturnCodes.TOKEN_EMPTY);
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage(), e);
		}

	}

	public UMSEntityObj verifyAuthenticationMApp(String authToken) throws SessionExpiredException, BaseException {
		UMSEntityObj umsEntityObj = null;
		try {
			if (authToken != null) {
				synchronized (AuthenticatedUsers.class) {
					umsEntityObj = mAuthenticUsers.get(authToken);
					if (umsEntityObj != null) {
						Date fromDate = umsEntityObj.getAuthenticatedTime();
						Date now = new Date();

						long mSec = now.getTime() - fromDate.getTime();
						float timeHours = mSec / 1000 / 60 / 60;

						if (timeHours >= 168.0) {
						
							// Removing the auth token from map, since user will
							// re-authenticate again.
							mAuthenticUsers.remove(authToken);
							throw new SessionExpiredException(HttpReturnCodes.TOKEN_REVALIDATE);
						} else {
							
							return umsEntityObj;
							
						}
					} else {
//						throw new BaseUMSException(HttpReturnCodes.TOKEN_NOT_VALID);
						throw new SessionExpiredException(HttpReturnCodes.TOKEN_REVALIDATE);
					}
				}
			} else {
				throw new BaseException(HttpReturnCodes.TOKEN_EMPTY);
			}
		} catch (SessionExpiredException e) {
			throw new SessionExpiredException(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e.getMessage(), e);
		}
	}
	public void removeAuthToken(String authToken) throws SessionExpiredException, BaseException {
		try {
			if (authToken != null) {
				synchronized (AuthenticatedUsers.class) {
					mAuthenticUsers.remove(authToken);
					
				}
			} else {
				throw new BaseException(HttpReturnCodes.TOKEN_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e.getMessage(), e);
		}
	}

}
