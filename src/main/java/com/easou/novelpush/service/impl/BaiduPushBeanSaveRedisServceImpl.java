package com.easou.novelpush.service.impl;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.easou.novelpush.constants.Constants;
import com.easou.novelpush.dao.RedisDao;
import com.easou.novelpush.dao.impl.RedisDaoImpl;
import com.easou.novelpush.datasource.JedisPoolFactory;
import com.easou.novelpush.dto.UdidBaiduBean;
import com.easou.novelpush.dto.UserBaiduBean;
import com.easou.novelpush.exception.PushNovelServiceException;
import com.easou.novelpush.service.BaiduPushBeanSaveRedisServce;

public class BaiduPushBeanSaveRedisServceImpl implements BaiduPushBeanSaveRedisServce {
	private RedisDao redisDao;

	public BaiduPushBeanSaveRedisServceImpl() throws PushNovelServiceException {
		init(JedisPoolFactory.saveudid_user);
	}

	public BaiduPushBeanSaveRedisServceImpl(String redisResourceFile) throws PushNovelServiceException {
		init(redisResourceFile);
	}

	private void init(String redisResourceFile) throws PushNovelServiceException {
		redisDao = new RedisDaoImpl(redisResourceFile);
	}

	@Override
	public void lpushUdidBaiduBean(UdidBaiduBean udidBaiduBean) throws PushNovelServiceException {
		if (udidBaiduBean == null) {
			throw new PushNovelServiceException("udidBaiduBean is null");
		}
		redisDao.lpush(Constants.PUSH_UDID_BAIDU, JSON.toJSONString(udidBaiduBean));
	}

	@Override
	public UdidBaiduBean rpopUdidBaiduBean() throws PushNovelServiceException {
		String value = redisDao.rpop(Constants.PUSH_UDID_BAIDU);
		UdidBaiduBean udidBaiduBean = null;
		if (StringUtils.isNotBlank(value)) {
			try {
				udidBaiduBean = JSON.parseObject(value.trim(), UdidBaiduBean.class);
			} catch (Exception e) {
				throw new PushNovelServiceException("redis取出udid信息： 将value json字符串转换为  UdidBaiduBean 出错. value==="
						+ value, e);
			}

		}
		return udidBaiduBean;
	}

	@Override
	public void lpushUserBaiduBean(UserBaiduBean userBaiduBean) throws PushNovelServiceException {
		if (userBaiduBean == null) {
			throw new PushNovelServiceException("udidBaiduBean is null");
		}
		redisDao.lpush(Constants.PUSH_USER_BAIDU, JSON.toJSONString(userBaiduBean));
	}

	@Override
	public UserBaiduBean rpopUserBaiduBean() throws PushNovelServiceException {
		String value = redisDao.rpop(Constants.PUSH_USER_BAIDU);
		UserBaiduBean userBaiduBean = null;
		if (StringUtils.isNotBlank(value)) {
			try {
				userBaiduBean = JSON.parseObject(value.trim(), UserBaiduBean.class);
			} catch (Exception e) {
				throw new PushNovelServiceException("redis取出user信息： 将value json字符串转换为  UserBaiduBean 出错. value==="
						+ value, e);
			}
		}
		return userBaiduBean;
	}

}
