package com.easou.novelpush.dao;

import java.util.List;

import com.easou.novelpush.exception.PushNovelServiceException;

/**
 * @author xiaodong Date: 2014-8-5
 */
public interface RedisDao {
	/**
	 * 将数据放入指定队列key中
	 * 
	 * @param key
	 * @param gid
	 * @throws PushNovelServiceException
	 */
	public void lpush(String key, String gid) throws PushNovelServiceException;

	/**
	 * 从指定指定队列key中获取数据
	 * 
	 * @param gid
	 */
	public String rpop(String key) throws PushNovelServiceException;

	/**
	 * 从指定指定队列key中获取批量数据
	 * 
	 * @param gid
	 */
	public List<String> lrange(String key, long start, long end) throws PushNovelServiceException;
}
