package com.easou.novelpush.dao;

import java.util.List;

import com.easou.novelpush.dto.UdidBaiduBean;
import com.easou.novelpush.dto.UserBaiduBean;
import com.easou.novelpush.exception.PushNovelServiceException;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public interface MongoBaiduUserDao {

	public int insterUdidAndBaiDu(DBObject dbObject, DBCollection collection) throws PushNovelServiceException;

	public int updateOrSaveUserIdAndBaiDu(DBObject whereDb, DBObject setDb, DBCollection collection)
			throws PushNovelServiceException;

	public int deleteUserIdAndBaiDu(DBObject whereDb, DBCollection collection) throws PushNovelServiceException;

	public UdidBaiduBean findUdidBeanByUdid(DBObject whereDb, DBCollection collection) throws PushNovelServiceException;

	public List<UserBaiduBean> finduserAllBeanByuserId(DBObject whereDb, DBCollection collection)
			throws PushNovelServiceException;

}
