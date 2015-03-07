package com.server.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.server.execption.NotAvailableMongoConnection;

public class MongoDBConnection {

	private static MongoDBConnection instance;
	private MongoClient mongoCon = null;
	private static HashMap<String, Properties> connProper = new HashMap<String, Properties> ();
	private MongoDBConnection(){
		
	}
	public void setMongoProperties(String domain, Properties prop) throws Exception{
		connProper.put(domain, prop);
	}
	public Properties getMongoProperties() throws Exception{
		return connProper.get("default");
	}
	/**
	 * Gives instance of ServiceLocator.
	 * 
	 * @return instance of ServiceLocator
	 * @throws Exception
	 *             Throws Exception
	 */
	public static MongoDBConnection getInstance() {
		if (instance == null) {
			return instance = new MongoDBConnection();
		} else {
			return instance;
		}
	}
	public void startMongoClient() throws NotAvailableMongoConnection{
		try{
			Properties properties = connProper.get("default");
			if(properties == null) {
				
				
			}
			String preferenceType = properties.getProperty("SEC_PREF_TYPE");;
			String db_host = properties.getProperty("DB_HOST");
	//		String[] hosts = db_host.split(",");
			int port = Integer.parseInt(properties.getProperty("DB_PORT"));
				List<ServerAddress> list = new LinkedList<ServerAddress>();
				list.add(new ServerAddress(db_host, port));
				mongoCon= new MongoClient(list);
//				if(preferenceType!=null&&(preferenceType.equalsIgnoreCase("True")||
//						preferenceType.equalsIgnoreCase("true"))){
//					mongoCon.setReadPreference(ReadPreference.secondaryPreferred());
//				}
		//	}else {
				mongoCon = new MongoClient();
		//	}
		}catch(Exception e){
		
			
			throw new NotAvailableMongoConnection("Exception in start Mongo Client : "+e.getMessage(), e);
		}
	}
	public MongoClient getMongoClientInstance(){
		return mongoCon;
	}
	
	public void closeMongoClient() throws NotAvailableMongoConnection{
		try{
			mongoCon.close();
		}catch(Exception e){
			throw new NotAvailableMongoConnection("Exception in closing Mongo Client : "+e.getMessage(), e);
		}
	}}
