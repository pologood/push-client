package com.easou.novelpush.service.impl;

import com.alibaba.fastjson.JSON;
import com.easou.novelpush.constants.Constants;
import com.easou.novelpush.dao.RedisDao;
import com.easou.novelpush.dao.impl.RedisDaoImpl;
import com.easou.novelpush.datasource.JedisPoolFactory;
import com.easou.novelpush.dto.UserMessage;
import com.easou.novelpush.exception.PushNovelServiceException;
import com.easou.novelpush.service.PushUserMessageService;
import com.google.common.base.Strings;

/**
 * @author xiaodong Date: 2014-8-5
 */
public class PushUserMessageServiceImpl implements PushUserMessageService {
	private RedisDao redisDao;

	public PushUserMessageServiceImpl() throws PushNovelServiceException {
		init(JedisPoolFactory.defaultResource);
	}

	public PushUserMessageServiceImpl(String redisResourceFile) throws PushNovelServiceException {
		init(redisResourceFile);
	}

	private void init(String redisResourceFile) throws PushNovelServiceException {
		redisDao = new RedisDaoImpl(redisResourceFile);
	}

	/**
	 * 保存推送消息到redis队列 : redis.lpush 
	 * 
	 * @param userMessage
	 * @throws PushNovelServiceException
	 */
	public void lpushUserMessage(UserMessage userMessage) throws PushNovelServiceException {
		if (userMessage == null) {
			throw new PushNovelServiceException("userMessage is null");
		}
		try {
			String json = JSON.toJSONString(userMessage);
			redisDao.lpush(Constants.PUSH_USER_MESSAGE, json);
		} catch (Exception e) {
			throw new PushNovelServiceException(
					"PushUserMessageServiceImpl setUsrMessage  JSON.toJSONString is falied! userMessage :"
							+ userMessage, e);
		}
	}

	/**
	 * 从消息队列中获取最后一个数据 : redis.rpop 
	 * 
	 * @return
	 * @throws PushNovelServiceException
	 */
	public UserMessage rPopUserMessage() throws PushNovelServiceException {
		UserMessage um = null;
		try {
			String json = redisDao.rpop(Constants.PUSH_USER_MESSAGE);
			if (!Strings.isNullOrEmpty(json)) {
				um = JSON.parseObject(json, UserMessage.class);
			}

		} catch (Exception e) {
			throw new PushNovelServiceException(
					"PushUserMessageServiceImpl setUsrMessage  JSON.parseObject is falied! ", e);
		}
		return um;
	}
}
