package org.zmsoft.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.cache.ISMQService;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 队列操作
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("MyMQOperatorSupport")
public class MyMQOperatorSupport extends MyTokenCommonSupport implements ISMQService {

//	public boolean pushValue(String key, String value) {
//		return myCacheService.addObjectInList(key, value, false);
//	}
//
//	public String popValue(String key) {
//		return (String) myCacheService.pollFirstObjectInList(key);
//	}
//	

	public String popValue(String key) {
		return listPop(key);
	}

	public boolean pushValue(String key, String value) {
		listAdd(key, value);
		return true;
	}

	int index = 15;

	public void setIndex(int index) {
		this.index = index;
	}

	public String get(String key) {
		Jedis jedis = getJedis();
		String value = null;
		try {
			value = jedis.get(key);
		} catch (JedisConnectionException e) {
			throw new RuntimeException("The Redis Server can't connect ......");
		} catch (Exception e) {
			logger.warn("get value from redis error[key:" + key + "] : " + e.getMessage());
		} finally {
			close(jedis);
		}
		return value;
	}
	
	public void set(String key, String value) {
		add(key, value, 1800, 0);
	}

	private void add(String key, String value, int expireTime, int failedNum) {
		if (failedNum < 3) {
			Jedis jedis = getJedis();
			try {
				jedis.set(key, value);
				if (expireTime > 0)
					jedis.expire(key, expireTime);
			} catch (JedisConnectionException e) {
				throw new RuntimeException("The Redis Server can't connect ......");
			} catch (Exception e) {
				logger.warn("add key[" + key + "] to redis error[" + failedNum + "]  : " + e.getMessage());
				add(key, value, expireTime, ++failedNum);
			} finally {
				close(jedis);
			}
		}
	}

	private void listAdd(String key, String... value) {
		Jedis jedis = getJedis();
		try {
			jedis.rpush(key, value);
		} catch (JedisConnectionException e) {
			throw new RuntimeException("The Redis Server can't connect ......");
		} catch (Exception e) {
			logger.warn("listAdd from redis error[key:" + key + "] : " + e.getMessage());
		} finally {
			close(jedis);
		}
	}

	private String listPop(String key) {
		Jedis jedis = getJedis();
		try {
			return jedis.lpop(key);
		} catch (JedisConnectionException e) {
			throw new RuntimeException("The Redis Server can't connect ......");
		} catch (Exception e) {
			logger.warn("listPop from redis error[key:" + key + "] : " + e.getMessage());
		} finally {
			close(jedis);
		}
		return null;
	}

	private Jedis getJedis() {
		return getJedisOnDB(index);
	}

	/**
	 * 获取一个链接
	 * 
	 * @param failedNum
	 *            失败次数
	 * @return
	 */
	private Jedis getJedis(int failedNum) {
		Jedis jedis = null;
		if (failedNum < 3) {
			try {
				jedis = loadJedisPool().getResource();
			} catch (Exception e) {
				try {
					switch (failedNum) {
					case 0:
						Thread.sleep(100);
						break;
					case 1:
						Thread.sleep(300);
						break;
					case 2:
						Thread.sleep(500);
						break;
					default:
						return null;
					}
					getJedis(++failedNum);
				} catch (Exception e1) {
					throw new RuntimeException("Redis connect fail");
				}
			}
		} else {
			throw new RuntimeException("Redis connect fail");
		}
		return jedis;
	}

	private Jedis getJedisOnDB(int index) {
		Jedis jedis = getJedis(0);
		if (index != 0)
			jedis.select(index);
		return jedis;
	}

	private void close(Jedis jedis) {
		if (jedis != null)
			jedis.close();
	}

	//////////////////////////////////////////////////////////////////
	@Value("${spring.redis.host}")
	private String host = "127.0.0.1";
	@Value("${spring.redis.port}")
	private int port = 6379;
	@Value("${spring.redis.password}")
	private String password = "zaq12wsx";
	@Value("${spring.redis.database}")
	private int database = 15;

	private JedisPool pool;

	private JedisPool loadJedisPool() {
		if (EmptyHelper.isEmpty(pool)) {
			JedisPoolConfig config = new JedisPoolConfig();
			pool = new JedisPool(config, host, port, 10000, password, database);
		}
		return pool;
	}


}
