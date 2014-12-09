package com.easou.novelpush.service;

import com.easou.novelpush.dto.UserMessage;
import com.easou.novelpush.exception.PushNovelServiceException;

/**
 * 消息推送服务类
 * 
 * @author xiaodong Date: 2014-8-5
 */
public interface PushUserMessageService {
	/**
	 * 保存推送消息到redis队列 : redis.lpush
	 * 
	 * @param userMessage
	 * @throws PushNovelServiceException
	 */
	public void lpushUserMessage(UserMessage userMessage) throws PushNovelServiceException;

	/**
	 * 从消息队列中获取最后一个数据 : redis.rpop
	 * 
	 * @return
	 * @throws PushNovelServiceException
	 */
	public UserMessage rPopUserMessage() throws PushNovelServiceException;
}
