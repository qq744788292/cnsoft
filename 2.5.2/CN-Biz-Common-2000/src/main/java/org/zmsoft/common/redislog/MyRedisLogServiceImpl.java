package org.zmsoft.common.redislog;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.config.redislog.RedisLogConfigService;
import org.zmsoft.framework.cache.ISMQService;
import org.zmsoft.framework.support.MyFrameWorkSupport;

import redis.clients.jedis.Jedis;

@Service("MyRedisMQServiceImpl")
public class MyRedisLogServiceImpl extends MyFrameWorkSupport implements ISMQService {

	@Resource
	private RedisLogConfigService redisServiceConfig;

	public Jedis getClient() {
		return redisServiceConfig.loadJedisPool().getResource();
	}

	public void closeClient(Jedis jedis) {
		if(jedis != null){
			jedis.close();
		}
	}
	
	@Override
	public boolean pushValue(String key, String value) {
		Jedis jedis = getClient();
		try {
			jedis.rpush(key, value);
		} catch (Exception e) {
			logger.warn("listAdd from redis error[key:" + key + "] : " + e.getMessage());
		} finally {
			closeClient(jedis);
		}
		return true;
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

}
