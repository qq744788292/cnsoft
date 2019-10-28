package org.zmsoft.common.log;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zmsoft.config.log.RemoteLogConfigService;
import org.zmsoft.framework.cache.ISMQService;
import org.zmsoft.framework.common.ISLog;
import org.zmsoft.framework.log.LogDataBean;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyFrameWorkSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.HttpServiceHelper;

import redis.clients.jedis.Jedis;

/**
 * 远程输出日志
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <LogDataHelper>
 */
@Component("LogDataRemoteSupport")
public class LogDataRemoteSupport extends MyFrameWorkSupport implements ISLog, ISMQService {

	private RemoteLogConfigService myRemoteLogConfig;

	public RemoteLogConfigService getMyLogConfig() {
		if (EmptyHelper.isEmpty(myRemoteLogConfig))
			myRemoteLogConfig = MyBeanFactoryHelper.getBean(RemoteLogConfigService.class);
		return myRemoteLogConfig;
	}

	/**
	 * 日志输出
	 * 
	 * @throws Exception
	 */
	@Async("threadPoolTaskExecutor")
	public void flush(LogDataBean logData) {
		try {
			// 日志输出模式（0关闭1消息队列2远程接口）
			if (ZERO.equals(getMyLogConfig().getType())) {

			} else if (ONE.equals(getMyLogConfig().getType()) && EmptyHelper.isNotEmpty(getMyLogConfig().getHostName())) {
				pushValue(LOG_KEY, logData.toString());
			} else if (TWO.equals(getMyLogConfig().getType()) && EmptyHelper.isNotEmpty(getMyLogConfig().getRemoteLogServiceURL())) {
				HttpServiceHelper.doHttpPOST(getMyLogConfig().getRemoteLogServiceURL(), logData);
			}
		} catch (Exception e) {
			logger.error("日志输出配置错误=====>>>>>" + e.getMessage());
		}
	}

	//////////////////////////////////////////////////////////////////////
	public Jedis getClient() {
		return getMyLogConfig().loadJedisPool().getResource();
	}

	public void closeClient(Jedis jedis) {
		if (jedis != null) {
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
