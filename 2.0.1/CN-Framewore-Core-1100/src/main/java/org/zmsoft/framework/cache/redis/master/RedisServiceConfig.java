package org.zmsoft.framework.cache.redis.master;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.ObjectBean;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service("RedisServiceConfig")
public class RedisServiceConfig extends ObjectBean {

	@Value("${logData.hostName}")
	private String hostName = "123.56.211.232";
	
	@Value("${logData.port}")
	private int port = 9736;;

	private String password = "123456";

	private int timeout = 10000;

	public JedisPool getJedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		JedisPool pool = new JedisPool(config, hostName, port, timeout, password);
		return pool;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}