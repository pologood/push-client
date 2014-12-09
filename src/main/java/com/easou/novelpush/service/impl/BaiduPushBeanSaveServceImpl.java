package com.easou.novelpush.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.easou.novelpush.dao.MongoBaiduUserDao;
import com.easou.novelpush.dao.impl.MongoBaiduUserDaoImpl;
import com.easou.novelpush.datasource.MongoClientFactory;
import com.easou.novelpush.dto.UdidBaiduBean;
import com.easou.novelpush.dto.UserBaiduBean;
import com.easou.novelpush.exception.PushNovelServiceException;
import com.easou.novelpush.service.BaiduPushBeanSaveServce;
import com.easou.novelpush.util.JsonUtil;
import com.easou.novelpush.util.SessionInfo;
import com.easou.novelpush.util.SessionUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class BaiduPushBeanSaveServceImpl implements BaiduPushBeanSaveServce {
	private MongoBaiduUserDao mongoBaiduUserDao = new MongoBaiduUserDaoImpl();

	@Override
	public int saveUdidAndBaiDuUser(UdidBaiduBean udidBaiduBean) throws PushNovelServiceException {
		int n = -1;
		try {
			if (udidBaiduBean != null) {
				String beanToJson = JsonUtil.beanToJson(udidBaiduBean);
				MongoClient mongoClient = MongoClientFactory.getMongoClientFactory().getMongoClient();
				DB db = mongoClient.getDB(MongoClientFactory.dbName);
				DBCollection collection = db.getCollection(MongoClientFactory.udid_tableName);
				DBObject whereDb = new BasicDBObject();
				whereDb.put(MongoClientFactory.UDID, udidBaiduBean.getUdid());
				DBObject setDb = (DBObject) JSON.parse(UpdateJson(beanToJson));
				n = mongoBaiduUserDao.updateOrSaveUserIdAndBaiDu(whereDb, setDb, collection);
				return n;
			}
		} catch (Exception e) {
			throw new PushNovelServiceException("BaiduPushBeanSaveServceImpl saveUdidAndBaiDuUser ", e);
		}
		return n;
	}

	@Override
	public int saveOrUpdateUserAndBaiDuUser(UserBaiduBean userBaiduBean) throws PushNovelServiceException {
		int n = -1;
		try {
			if (userBaiduBean != null) {
				MongoClient mongoClient = MongoClientFactory.getMongoClientFactory().getMongoClient();
				DB db = mongoClient.getDB(MongoClientFactory.dbName);
				DBCollection collection_udid = db.getCollection(MongoClientFactory.udid_tableName);
				DBCollection collection_user = db.getCollection(MongoClientFactory.user_tableName);
				DBObject whereDb = new BasicDBObject();
				whereDb.put(MongoClientFactory.UDID, userBaiduBean.getUdid());
				// 根据udid查询出百度信息
				UdidBaiduBean udidBean = mongoBaiduUserDao.findUdidBeanByUdid(whereDb, collection_udid);
				if (udidBean != null) {
					// 把百度信息放入User中更新活插入user表
					userBaiduBean.setBaiduUserId(udidBean.getBaiduUserId());
					userBaiduBean.setChannelId(udidBean.getChannelId());
				}
				// 把要插入或者需要更新的数据转换成json
				String beanToJson = JsonUtil.beanToJson(userBaiduBean);
				DBObject setDb = (DBObject) JSON.parse(UpdateJson(beanToJson));
				n = mongoBaiduUserDao.updateOrSaveUserIdAndBaiDu(whereDb, setDb, collection_user);
				return n;
			}
		} catch (Exception e) {
			throw new PushNovelServiceException("BaiduPushBeanSaveServceImpl saveOrUpdateUserAndBaiDuUser ", e);
		}
		return n;

	}

	@Override
	public int deleteUserAndBaiDuUser(String sessionId) throws PushNovelServiceException {
		int n = -1;
		try {
			if (StringUtils.isNotBlank(sessionId)) {
				SessionInfo udidBySession = SessionUtil.getUdidBySession(sessionId);
				if (udidBySession.isValid() == true && udidBySession.getUid() > 0) {
					long uid = udidBySession.getUid();
					MongoClient mongoClient = MongoClientFactory.getMongoClientFactory().getMongoClient();
					DB db = mongoClient.getDB(MongoClientFactory.dbName);
					DBCollection collection = db.getCollection(MongoClientFactory.user_tableName);
					DBObject whereDb = new BasicDBObject();
					whereDb.put(MongoClientFactory.USERID, String.valueOf(uid));
					n = mongoBaiduUserDao.deleteUserIdAndBaiDu(whereDb, collection);
					return n;
				}
			}
		} catch (Exception e) {
			throw new PushNovelServiceException("BaiduPushBeanSaveServceImpl deleteUserAndBaiDuUser ", e);
		}
		return n;
	}

	@Override
	public List<UserBaiduBean> findBaiduUserByUserId(String userId) throws PushNovelServiceException {
		List<UserBaiduBean> result = new ArrayList<UserBaiduBean>();
		try {
			if (StringUtils.isNotBlank(userId)) {
				MongoClient mongoClient = MongoClientFactory.getMongoClientFactory().getMongoClient();
				DB db = mongoClient.getDB(MongoClientFactory.dbName);
				DBCollection collection_user = db.getCollection(MongoClientFactory.user_tableName);
				DBObject whereDb = new BasicDBObject();
				whereDb.put(MongoClientFactory.USERID, userId);
				result = mongoBaiduUserDao.finduserAllBeanByuserId(whereDb, collection_user);
			}

		} catch (Exception e) {
			throw new PushNovelServiceException("BaiduPushBeanSaveServceImpl findBaiduUserByUserId 查询错误：userId:"
					+ userId, e);
		}

		return result;
	}

	private static String UpdateJson(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"$set\":").append(str).append("}");
		return sb.toString();
	}

}
