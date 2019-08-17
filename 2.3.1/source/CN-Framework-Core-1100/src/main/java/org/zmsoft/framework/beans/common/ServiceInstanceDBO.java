package org.zmsoft.framework.beans.common;

import org.zmsoft.framework.ObjectBean;

/**
 * 服务器配置地址
 * @author fcy
 *
 */
public class ServiceInstanceDBO extends ObjectBean {

	private String serviceId;

	private String host;

	private int port = 8080;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
