package com.easou.novelpush;

import java.util.List;

import com.easou.novelpush.datasource.MongoClientFactory;
import com.easou.novelpush.exception.PushNovelServiceException;
import com.easou.novelpush.service.BaiduPushBeanSaveServce;
import com.easou.novelpush.service.impl.BaiduPushBeanSaveServceImpl;
import com.easou.novelpush.util.SessionUtil;
import com.mongodb.MongoClient;

public class Test {
	public static void main(String[] args) {
		testDeleteBysessionId();
		// DBObject o = new BasicDBObject();
		// o.put("qw", 1111);
		// o.put("qwerq", 3243654);
		// System.out.println(o.toString());

		// try {

		// List<UserBaiduBean> f = servce.findBaiduUserByUserId("userId100");
		// for (UserBaiduBean u : f) {
		// System.out.println(u.getUdid());
		// }
		//
		// for (int i = 0; i < 100; i++) {
		// UdidBaiduBean udid = new UdidBaiduBean();
		// udid.setBaiduUserId("baiuserId" + i);
		// udid.setChannelId("channelIdsssss" + i);
		// udid.setOs("ossssss" + i);
		// udid.setUdid("udid" + i);
		// boolean ud = servce.saveUdidAndBaiDuUser(udid);
		// System.out.println("udid:" + ud + "    " + i);
		// UserBaiduBean user = new UserBaiduBean();
		// user.setOs("xxxxxxx" + i);
		// user.setUdid("udid" + i);
		// user.setUserId("userId100");
		// boolean us = servce.saveOrUpdateUserAndBaiDuUser(user);
		// System.out.println("userId:" + us + "    " + i);
		// }

		//
	}

	public static void testContentMongo() {
		try {
			MongoClient mongoClient = MongoClientFactory.getMongoClientFactory().getMongoClient();
			List<String> databaseNames = mongoClient.getDatabaseNames();
			for (String s : databaseNames) {
				System.out.println(s);
			}
			System.out.println("==================");

			System.out.println(MongoClientFactory.dbName);
			System.out.println(MongoClientFactory.UDID);
			System.out.println(MongoClientFactory.udid_tableName);
			System.out.println(MongoClientFactory.user_tableName);
			System.out.println(MongoClientFactory.USERID);

		} catch (PushNovelServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDeleteBysessionId() {
		try {
			String SessionId = "XEiDhZy-DZ1k3e3Odj0U";
			long uid = SessionUtil.getUdidBySession(SessionId).getUid();
			BaiduPushBeanSaveServce servce = new BaiduPushBeanSaveServceImpl();
			int deleteUserAndBaiDuUser = servce.deleteUserAndBaiDuUser(SessionId);
			System.out.println(deleteUserAndBaiDuUser);
			System.out.println(uid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
