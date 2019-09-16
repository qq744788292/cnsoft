package org.zmsoft.config.redislog;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;
import org.zmsoft.framework.common.ISConfig;
import org.zmsoft.framework.utils.EmptyHelper;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service("RedisConfigService")
public class RedisLogConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "redis.log.config";

	@Override
	public String loadType() {
		return TYPE;
	}

	private String hostName = "";

	private String port = "6379";

	private String password = "";

	private String timeout = "10000";

	private JedisPool pool;

	public JedisPool loadJedisPool() {
		if (EmptyHelper.isEmpty(pool)) {
			JedisPoolConfig config = new JedisPoolConfig();
			pool = new JedisPool(config, hostName, getPort(), getTimeout(), password);
		}
		return pool;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return Integer.parseInt(port);
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeout() {
		return Integer.parseInt(timeout);
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
}