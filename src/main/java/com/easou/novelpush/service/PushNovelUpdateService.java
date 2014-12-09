package com.easou.novelpush.service;

import com.easou.novelpush.exception.PushNovelServiceException;


/**
 * 小说更新推送服务类
 * 
 * @author xiaodong Date: 2014-8-5
 */
public interface PushNovelUpdateService {
	/**
	 * 保存有更新的小说gid: redis.lpush
	 * 
	 * @param gid
	 * @throws PushNovelServiceException
	 */
	public void lpushNovelGid(long gid) throws PushNovelServiceException ;
	/**
	 * 获取有更新的小说gid: redis.rpop
	 * 
	 * @param gid
	 * @throws PushNovelServiceException
	 */
	public long rpopNovelGid() throws PushNovelServiceException ;
	
	public void lpush(String key, String value);
	public String rpop(String key);
}
