package com.easou.novelpush.datasource;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.easou.novelpush.exception.PushNovelServiceException;
import com.easou.novelpush.util.ResourcesUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

public class MongoClientFactory {
	private MongoClient mongoClient;
	public static String dbName = "pushDB";
	public static String user_tableName = "userPush";
	public static String udid_tableName = "pushUdid";
	public static String USERID = "userId";
	public static String UDID = "udid";

	private static MongoClientFactory clientFactory = null;

	private final static String fileName = "pushDB-mongo.properties";

	public MongoClientFactory() {
		init();
	}

	public static MongoClientFactory getMongoClientFactory() {
		if (clientFactory == null) {
			clientFactory = new MongoClientFactory();
		}
		return clientFactory;
	}

	public void init() {
		try {
			Properties properties = ResourcesUtil.getResourceAsProperties(fileName);
			String hostsString = properties.getProperty("hosts");
			dbName = properties.getProperty("dbname");
			user_tableName = properties.getProperty("user_tableName");
			udid_tableName = properties.getProperty("udid_tableName");
			USERID = properties.getProperty("USERID");
			UDID = properties.getProperty("UDID");

			String[] hostArray = hostsString.split(",");
			List<ServerAddress> serverAddressList = new ArrayList<ServerAddress>();
			for (String host : hostArray) {
				String[] serverPost = host.split(":");
				serverAddressList.add(new ServerAddress(serverPost[0], Integer.valueOf(serverPost[1])));
			}
			MongoClientOptions mco = new MongoClientOptions.Builder().socketTimeout(3000).build();
			mongoClient = new MongoClient(serverAddressList, mco);
			mongoClient.setReadPreference(ReadPreference.secondaryPreferred());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

	public MongoClient getMongoClient() throws PushNovelServiceException {
		return mongoClient;
	}
}
