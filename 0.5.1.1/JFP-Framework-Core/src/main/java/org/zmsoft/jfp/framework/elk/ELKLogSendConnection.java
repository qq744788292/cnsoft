package org.zmsoft.jfp.framework.elk;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.constants.ILogConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * 日志推送到Redis
 * 
 * @author zmsoft
 * @version 3.2.1 2016/08/17
 * @since 3.2.1 2016/08/17
 *
 */
public class ELKLogSendConnection implements ILogConstants, IFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected ELKLogConfig config;

	protected boolean logFlag = true;

	protected String hdpName = SYSTEM;

	public String getHdpName() {
		return hdpName;
	}

	public void setHdpName(String hdpName) {
		this.hdpName = hdpName;
	}

	protected String systemName = SYSTEM;

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public void setConfig(ELKLogConfig config) {
		this.config = config;
	}

	public void setLogFlag(boolean logFlag) {
		this.logFlag = logFlag;
	}

	public void sendLogData(String key, String value) {
		if (logFlag)
			logger.debug(value);
		if (config != null) {
			Jedis jedis = null;
			try {
				if (jedis == null)
					jedis = config.loadJedis();

				jedis.rpush(key, value);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (jedis != null)
					jedis.close();
				jedis = null;
			}
		}
	}
}
