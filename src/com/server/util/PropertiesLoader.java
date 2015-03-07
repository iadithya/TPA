package com.server.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	
		private static PropertiesLoader _instance;

		private static InputStream logFile = null;

		private static Properties prop = null;

		private PropertiesLoader() {
			PropertiesLoader.getInstance();
		}

		static {
			_instance = new PropertiesLoader();
			prop = new Properties();
		}

		public static PropertiesLoader getInstance() {
			return _instance;
		}
		
		public Properties getProperties(String fileName) {
			try {
				String dbConfig = System.getProperty("db.configurations");
				System.out.println("DBConfig>>>>>>>>>>>>>>>>>>"+dbConfig+" and filename is "+fileName);
				if (prop.isEmpty()) {
					if(dbConfig != null){
						logFile = new FileInputStream(dbConfig);
						prop.load(logFile);
					}else{
						logFile = this.getClass().getClassLoader().getResourceAsStream(fileName);
						prop.load(logFile);
					}
					logFile.close();
				}
			} catch (Exception e) {
			}
			//System.out.println("At last the properties are============================"+prop);
			return prop;
		}
		
		public String getValue(String key){
			return prop.getProperty(key);
		}
		
		

}
