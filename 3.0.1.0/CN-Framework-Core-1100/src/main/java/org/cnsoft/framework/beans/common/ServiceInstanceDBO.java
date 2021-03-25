package org.cnsoft.framework.beans.common;

import org.cnsoft.framework.core.ObjectBean;
import org.cnsoft.framework.utils.DateHelper;

/**
 * 服务器配置地址
 * 
 * @author CNSoft
 * @version 2.6.2.5 2018/10/22
 * @since 2.6.2.5 2018/10/22
 * @see <MyCunstomServer><MyHeardTaskService><CustomerServerMoitorController>
 */
public class ServiceInstanceDBO extends ObjectBean {

	private String name;
	private String host;
	private int port = 8080;
	private String serviceId;
	private String dateTime = DateHelper.currentTimeMillisCN1();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}
