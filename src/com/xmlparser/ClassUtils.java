package com.xmlparser;

import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;

@SuppressWarnings("PMD")
public final class ClassUtils {

	private static java.util.Hashtable __m_classloaders = new java.util.Hashtable();

	public static Class forName(String className) throws ClassNotFoundException {
		return loadClass(className);
	}

	public static void loaderSetter(String className, ClassLoader tempLoader) {
		if (className != null && tempLoader != null)
			__m_classloaders.put(className, tempLoader);
	}

	public static ClassLoader getLoader(String className) {
		if (className == null)
			return null;
		return (ClassLoader) __m_classloaders.get(className);
	}

	private static Class loadClass(String __m_className)
			throws ClassNotFoundException {
		final String m_className = __m_className;
		Object retObject = AccessController
				.doPrivileged(new PrivilegedAction() {
					public Object run() {
						try {
							ClassLoader classLoader = getLoader(m_className);
							return Class
									.forName(m_className, true, classLoader);
						} catch (ClassNotFoundException cnfe) {
						}

						try {
							ClassLoader classLoader = Thread.currentThread()
									.getContextClassLoader();
							return Class
									.forName(m_className, true, classLoader);
						} catch (ClassNotFoundException cnfe2) {
							try {
								ClassLoader classLoader = ClassUtils.class
										.getClassLoader();
								return Class.forName(m_className, true,
										classLoader);
							} catch (ClassNotFoundException cnfe3) {
								try {
									return Class.forName(m_className);
								} catch (Throwable e) {
									return e;
								}
							}
						}
					}
				});
		if (retObject instanceof Class) {
			return (Class) retObject;
		} else if (retObject instanceof ClassNotFoundException) {
			throw (ClassNotFoundException) retObject;
		} else {
			throw new ClassNotFoundException(__m_className);
		}
	}

	public static InputStream getResourceAsStream(Class resourceClass,
			String pathName) {
		InputStream inputStream = null;

		if (resourceClass.getClassLoader() != null) {
			inputStream = resourceClass.getClassLoader().getResourceAsStream(
					pathName);
		} else {
			inputStream = ClassLoader.getSystemClassLoader()
					.getResourceAsStream(pathName);
		}
		if (inputStream == null) {
			inputStream = resourceClass.getResourceAsStream(pathName);
		}
		return inputStream;
	}

	public static Class forName(String __m_className, boolean __m_init,
			ClassLoader __m__loader) throws ClassNotFoundException {

		// Create final vars for doPrivileged block
		final String m_className = __m_className;
		final ClassLoader loader = __m__loader;
		try {
			Object retObject = AccessController
					.doPrivileged(new PrivilegedAction() {
						public Object run() {
							try {
								return Class.forName(m_className, true, loader);
							} catch (Throwable e) {
								return e;
							}
						}
					});
			if (retObject instanceof Class) {
				return (Class) retObject;
			} else if (retObject instanceof ClassNotFoundException) {
				throw (ClassNotFoundException) retObject;
			} else {
				throw new ClassNotFoundException(__m_className);
			}
		} catch (ClassNotFoundException cnfe) {
			return loadClass(m_className);
		}
	}

	public static void stopClassLoader(String className) {
		__m_classloaders.remove(className);
	}

}
