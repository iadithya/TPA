package com.server.common;

import com.mongodb.MongoClient;
import com.server.dao.MongoDBConnection;
import com.server.execption.NotAvailableMongoConnection;

public class AbstractMongoClientDB {
	
	
public AbstractMongoClientDB() {
		
	}
	
	
	public MongoClient getMongoClientConnection() throws NotAvailableMongoConnection{
		return MongoDBConnection.getInstance().getMongoClientInstance();
	}
	
	public void closeMongoConnection () throws NotAvailableMongoConnection{
		MongoDBConnection.getInstance().closeMongoClient();
	}

}
