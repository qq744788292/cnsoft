package org.zmsoft.config.log;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;
import org.zmsoft.framework.common.ISConfig;
import org.zmsoft.framework.utils.EmptyHelper;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 第三方日志采集配置
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <LogDataRemoteSupport>
 */
@Service("RemoteLogConfigService")
public class RemoteLogConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "log.config";

	@Override
	public String loadType() {
		return TYPE;
	}

	//日志输出模式（1开启）
	private String type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// 接口接口输出地址
	private String remoteLogServiceURL;

	//redis队列配置地址
	private String hostName = "";
	private String port = "6379";
	private String password = "";
	private String timeout = "10000";

	private JedisPool pool;

	public void otherWise() throws Exception {
		loadJedisPool();
	}

	public JedisPool loadJedisPool() {
		if (EmptyHelper.isEmpty(pool)) {
			JedisPoolConfig config = new JedisPoolConfig();
			pool = new JedisPool(config, hostName, getPort(), getTimeout(), password);
		}
		return pool;
	}

	public String getRemoteLogServiceURL() {
		return remoteLogServiceURL;
	}

	public void setRemoteLogServiceURL(String remoteLogServiceURL) {
		this.remoteLogServiceURL = remoteLogServiceURL;
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