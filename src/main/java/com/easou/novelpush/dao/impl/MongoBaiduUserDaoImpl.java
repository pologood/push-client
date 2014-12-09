package com.easou.novelpush.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.easou.novelpush.dao.MongoBaiduUserDao;
import com.easou.novelpush.dto.UdidBaiduBean;
import com.easou.novelpush.dto.UserBaiduBean;
import com.easou.novelpush.exception.PushNovelServiceException;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class MongoBaiduUserDaoImpl implements MongoBaiduUserDao {

	@Override
	public int insterUdidAndBaiDu(DBObject dbObject, DBCollection collection) throws PushNovelServiceException {
		int n = -1;
		try {
			if (collection != null) {
				WriteResult insert = collection.insert(dbObject);
				n = insert.getN();
			}

		} catch (Exception e) {
			throw new PushNovelServiceException("MongoBaiduUserDaoImpl insterUdidAndBaiDu mongo插入错误  ", e);
		}
		return n;
	}

	@Override
	public int updateOrSaveUserIdAndBaiDu(DBObject whereDb, DBObject setDb, DBCollection collection)
			throws PushNovelServiceException {
		int n = -1;
		try {
			if (collection != null) {
				WriteResult update = collection.update(whereDb, setDb, true, true);
				n = update.getN();
			}
		} catch (Exception e) {
			throw new PushNovelServiceException("MongoBaiduUserDaoImpl updateOrSaveUserIdAndBaiDu mongo更新or添加错误  ", e);
		}
		return n;
	}

	@Override
	public int deleteUserIdAndBaiDu(DBObject whereDb, DBCollection collection) throws PushNovelServiceException {
		int n = -1;
		try {
			if (collection != null) {
				WriteResult remove = collection.remove(whereDb);
				n = remove.getN();
			}
		} catch (Exception e) {
			throw new PushNovelServiceException("MongoBaiduUserDaoImpl deleteUserIdAndBaiDu 删除错误错误  ", e);
		}
		return n;
	}

	@Override
	public UdidBaiduBean findUdidBeanByUdid(DBObject whereDb, DBCollection collection) throws PushNovelServiceException {
		UdidBaiduBean result = null;
		try {
			if (collection != null) {
				DBCursor find = collection.find(whereDb);
				if (find.hasNext()) {
					result = new UdidBaiduBean();
					DBObject next = find.next();
					Object os = next.get("os");
					Object udid = next.get("udid");
					Object channelId = next.get("channelId");
					Object baiduUserId = next.get("baiduUserId");
					if (os != null) {
						result.setOs(os.toString());
					}
					if (udid != null) {
						result.setUdid(udid.toString());
					}
					if (channelId != null) {
						result.setChannelId(channelId.toString());
					}
					if (baiduUserId != null) {
						result.setBaiduUserId(baiduUserId.toString());
					}

				}

			}
		} catch (Exception e) {
			throw new PushNovelServiceException("MongoBaiduUserDaoImpl findUdidBeanByUdid mongo查询udid错误 ", e);
		}
		return result;
	}

	@Override
	public List<UserBaiduBean> finduserAllBeanByuserId(DBObject whereDb, DBCollection collection)
			throws PushNovelServiceException {
		List<UserBaiduBean> result = new ArrayList<UserBaiduBean>();
		try {
			if (collection != null) {
				DBCursor find = collection.find(whereDb);
				while (find.hasNext()) {
					UserBaiduBean bean = new UserBaiduBean();
					DBObject next = find.next();
					Object os = next.get("os");
					Object udid = next.get("udid");
					Object channelId = next.get("channelId");
					Object baiduUserId = next.get("baiduUserId");
					Object userId = next.get("userId");
					if (os != null) {
						bean.setOs(os.toString());
					}
					if (udid != null) {
						bean.setUdid(udid.toString());
					}
					if (channelId != null) {
						bean.setChannelId(channelId.toString());
					}
					if (baiduUserId != null) {
						bean.setBaiduUserId(baiduUserId.toString());
					}

					if (userId != null) {
						bean.setUserId(userId.toString());
					}
					result.add(bean);
				}

			}
		} catch (Exception e) {
			throw new PushNovelServiceException("MongoBaiduUserDaoImpl finduserAllBeanByuserId mongo查询userId错误 ", e);
		}
		return result;
	}
}
