package org.zmsoft.framework.beans.common;

import org.zmsoft.framework.beans.ObjectBean;

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