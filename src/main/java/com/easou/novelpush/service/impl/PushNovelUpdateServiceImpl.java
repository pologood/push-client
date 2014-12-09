package com.easou.novelpush.service.impl;

import java.util.List;

import com.easou.novelpush.constants.Constants;
import com.easou.novelpush.dao.RedisDao;
import com.easou.novelpush.dao.impl.RedisDaoImpl;
import com.easou.novelpush.datasource.JedisPoolFactory;
import com.easou.novelpush.exception.PushNovelServiceException;
import com.easou.novelpush.service.PushNovelUpdateService;
import com.google.common.base.Strings;

/**
 * @author xiaodong Date: 2014-8-5
 */
public class PushNovelUpdateServiceImpl implements PushNovelUpdateService {

	private RedisDao redisDao;

	public PushNovelUpdateServiceImpl() throws PushNovelServiceException {
		init(JedisPoolFactory.defaultResource);
	}

	public PushNovelUpdateServiceImpl(String redisResourceFile) throws PushNovelServiceException {
		init(redisResourceFile);
	}

	private void init(String redisResourceFile) throws PushNovelServiceException {
		redisDao = new RedisDaoImpl(redisResourceFile);
	}

	/**
	 * 保存有更新的小说gid: redis.lpush
	 * 
	 * @param gid
	 * @throws PushNovelServiceException
	 */
	public void lpushNovelGid(long gid) throws PushNovelServiceException {
		if (gid == 0) {
			throw new PushNovelServiceException("gid is 0");
		}
		redisDao.lpush(Constants.PUSH_NOVEL_UPDATE, String.valueOf(gid));
	}

	/**
	 * 获取有更新的小说gid: redis.rpop
	 * 
	 * @param gid
	 * @throws PushNovelServiceException
	 */
	public long rpopNovelGid() throws PushNovelServiceException {
		String value = redisDao.rpop(Constants.PUSH_NOVEL_UPDATE);
		long gid = 0;
		if (!Strings.isNullOrEmpty(value)) {
			gid = Long.valueOf(value);
		}
		return gid;
	}

	/**
	 * 批量获取有更新的小说gid : redis.lrange
	 * 
	 * @param gid
	 * @throws PushNovelServiceException
	 */
	public List<String> getNovelUpdateGid(long start, long end) throws PushNovelServiceException {
		return redisDao.lrange(Constants.PUSH_NOVEL_UPDATE, start, end);
	}

	/* (non-Javadoc)
	 * @see com.easou.novelpush.service.PushNovelUpdateService#lpush(java.lang.String, java.lang.String)
	 */
	@Override
	public void lpush(String key, String value) {
		try {
			redisDao.lpush(key, value);
		} catch (PushNovelServiceException e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.easou.novelpush.service.PushNovelUpdateService#rpop(java.lang.String)
	 */
	@Override
	public String rpop(String key) {
		String value = null;
		try {
			value =  redisDao.rpop(key);
		} catch (PushNovelServiceException e) {
			e.printStackTrace();
		}
		return value;
	}
	

}
