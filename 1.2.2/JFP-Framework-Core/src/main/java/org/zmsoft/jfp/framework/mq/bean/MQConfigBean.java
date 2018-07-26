package org.zmsoft.jfp.framework.mq.bean;

import org.zmsoft.jfp.framework.beans.ObjectBean;

/**
 * MQ配置信息
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class MQConfigBean extends ObjectBean {

	/**
	 * 服务地址
	 */
	protected String hostIp;
	/**
	 * 服务端口
	 */
	protected int hostPort;
	/**
	 * 超时时间
	 */
	protected int closeTimeout;

	/**
	 * 队列名称
	 */
	protected String queueName;

	/**
	 * 用户名
	 */
	protected String username;
	/**
	 * 密码
	 */
	protected String password;

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public int getHostPort() {
		return hostPort;
	}

	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}

	public int getCloseTimeout() {
		return closeTimeout;
	}

	public void setCloseTimeout(int closeTimeout) {
		this.closeTimeout = closeTimeout;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
