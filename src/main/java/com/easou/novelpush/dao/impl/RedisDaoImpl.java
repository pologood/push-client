package com.easou.novelpush.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.easou.novelpush.dao.RedisDao;
import com.easou.novelpush.datasource.JedisPoolFactory;
import com.easou.novelpush.exception.PushNovelServiceException;

/**
 * @author xiaodong Date: 2014-8-5
 */
public class RedisDaoImpl implements RedisDao {
	private JedisPool pool;

	public RedisDaoImpl() throws PushNovelServiceException {
		init(JedisPoolFactory.defaultResource);
	}

	public RedisDaoImpl(String resource) throws PushNovelServiceException {
		init(resource);
	}

	private void init(String resource) throws PushNovelServiceException {
		JedisPoolFactory factoryBean = new JedisPoolFactory();
		factoryBean.setResource(resource);
		pool = factoryBean.getJedisPool();
	}

	/**
	 * 将数据放入指定队列key中
	 * 
	 * @param key
	 * @param gid
	 * @throws PushNovelServiceException
	 */
	public void lpush(String key, String gid) throws PushNovelServiceException {
		boolean error = false;
		Jedis jedis = null;
		try {
			if (StringUtils.isNotBlank(gid)) {
				jedis = pool.getResource();
				jedis.lpush(key, gid);
			}
		} catch (Exception e) {
			error = true;
			throw new PushNovelServiceException("RedisDaoImpl lpush falied! key " + key + ", gid: " + gid, e);
		} finally {
			if (jedis != null) {
				if (error) {
					pool.returnBrokenResource(jedis);
				} else {
					pool.returnResource(jedis);
				}
			}
		}
	}

	/**
	 * 从指定指定队列key中获取数据
	 * 
	 * @param gid
	 */
	public String rpop(String key) throws PushNovelServiceException {
		boolean error = false;
		Jedis jedis = null;
		String gid = null;
		try {
			jedis = pool.getResource();
			gid = jedis.rpop(key);
		} catch (Exception e) {
			error = true;
			throw new PushNovelServiceException("RedisDaoImpl rpop falied! key : " + key, e);
		} finally {
			if (jedis != null) {
				if (error) {
					pool.returnBrokenResource(jedis);
				} else {
					pool.returnResource(jedis);
				}
			}
		}
		return gid;
	}

	/**
	 * 从指定指定队列key中获取批量数据
	 * 
	 * @param gid
	 */
	public List<String> lrange(String key, long start, long end) throws PushNovelServiceException {
		boolean error = false;
		Jedis jedis = null;
		List<String> gidList = null;
		try {
			jedis = pool.getResource();
			gidList = jedis.lrange(key, start, end);
		} catch (Exception e) {
			error = true;
			throw new PushNovelServiceException("RedisDaoImpl lrange falied! key : " + key + ", start:" + start
					+ ", end:" + end, e);
		} finally {
			if (jedis != null) {
				if (error) {
					pool.returnBrokenResource(jedis);
				} else {
					pool.returnResource(jedis);
				}
			}
		}
		return gidList;
	}

}
