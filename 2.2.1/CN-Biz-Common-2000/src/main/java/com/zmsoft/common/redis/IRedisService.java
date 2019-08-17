package com.zmsoft.common.redis;

import redis.clients.jedis.Jedis;

public interface IRedisService {
	public Jedis getClient();

	public void closeClient(Jedis jedis);

	/**
	 * 添加一个数据（队列）
	 * 
	 * @param key
	 * @param value
	 */
	public void addValue(String key, String value);

	/**
	 * 获得一个数据（队列）
	 * 
	 * @param key
	 * @return
	 */
	public String popValue(String key);

	/**
	 * 保存一个数据
	 * 
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value);

	/**
	 * 获得一个数据
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key);

}
