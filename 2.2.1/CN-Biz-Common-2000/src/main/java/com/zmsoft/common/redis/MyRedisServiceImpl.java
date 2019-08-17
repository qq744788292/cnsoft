package com.zmsoft.common.redis;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.support.MyFrameWorkSupport;

import redis.clients.jedis.Jedis;

@Service("MyRedisServiceImpl")
public class MyRedisServiceImpl extends MyFrameWorkSupport implements IRedisService {

	@Resource
	private RedisServiceConfig redisServiceConfig;

	@Override
	public Jedis getClient() {
		return redisServiceConfig.getJedisPool().getResource();
	}

	public void closeClient(Jedis jedis) {
		if(jedis != null){
			jedis.close();
		}
	}
	
	@Override
	public void addValue(String key, String value) {
		Jedis jedis = getClient();
		try {
			jedis.rpush(key, value);
		} catch (Exception e) {
			logger.warn("listAdd from redis error[key:" + key + "] : " + e.getMessage());
		} finally {
			closeClient(jedis);
		}
	}

	@Override
	public String popValue(String key) {
		Jedis jedis = getClient();
		try {
			return jedis.lpop(key);
		} catch (Exception e) {
			logger.warn("listPop from redis error[key:" + key + "] : " + e.getMessage());
		} finally {
			closeClient(jedis);
		}
		return null;
	}

	@Override
	public void setValue(String key, String value) {
		Jedis jedis=null;
		try{
			jedis = getClient();
			jedis.set(key, value);
			logger.info("Redis set success - " + key + ", value:" + value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
		}finally{
			closeClient(jedis);
		}
	}

	@Override
	public String getValue(String key) {
		String result = null;
		Jedis jedis=null;
		try{
			jedis = getClient();
			result = jedis.get(key);
			logger.info("Redis get success - " + key + ", value:" + result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
		}finally{
			closeClient(jedis);
		}
		return result;
	}

}
