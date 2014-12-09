package com.easou.novelpush.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.pool.impl.GenericObjectPool;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.easou.novelpush.exception.PushNovelServiceException;

public class JedisPoolFactory {

	public final static String defaultResource = "push-redis.properties";
	public final static String saveudid_user = "udid-user-redis.properties";
	private String resource = defaultResource;

	public JedisPool getJedisPool() throws PushNovelServiceException {
		InputStream in = null;
		ClassLoader loader = JedisPoolFactory.class.getClassLoader();
		if (loader != null) {
			in = loader.getResourceAsStream(resource);
		}
		if (in == null) {
			in = ClassLoader.getSystemResourceAsStream(resource);
		}
		if (in == null) {
			throw new PushNovelServiceException("Could not find resource file: ".concat(resource));
		}
		Properties props = new Properties();
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			throw new PushNovelServiceException("Could not load resource file: ".concat(resource), e);
		}
		JedisPool pool;
		try {
			String host = props.getProperty("host");
			int port = Integer.parseInt(props.getProperty("port", "6379"));
			int minIdle = Integer.parseInt(props.getProperty("minIdle"));
			int maxIdle = Integer.parseInt(props.getProperty("maxIdle"));
			int maxActive = Integer.parseInt(props.getProperty("maxActive"));
			int timeout = Integer.parseInt(props.getProperty("timeout"));
			String arg = "redis init error.host[" + host + "]port[" + port + "]minIdle[" + minIdle + "]maxIdle["
					+ maxIdle + "]maxActive[" + maxActive + "]";
			if (host == null || host.equals("") || port <= 0 || timeout < 0 || minIdle < 0 || maxIdle < 0
					|| maxActive < 0) {
				throw new IllegalArgumentException(arg);
			}
			if (minIdle > maxIdle || maxIdle > maxActive) {
				throw new IllegalArgumentException(arg);
			}
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMinIdle(minIdle);
			config.setMaxIdle(maxIdle);
			config.setMaxActive(maxActive);
			config.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_FAIL);
			pool = new JedisPool(config, host, port, timeout);
		} catch (Exception e) {
			throw new PushNovelServiceException("redis init error.", e);
		}
		return pool;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
}
