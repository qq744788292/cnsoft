package org.cnsoft.framework.log;

import org.cnsoft.framework.core.ObjectBean;

/**
 * 日志对象
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class LogDataBean extends ObjectBean {

	private String logTimestamp;
	private String serviceName;
	private String appName;

	public String getLogTimestamp() {
		return logTimestamp;
	}

	public void setLogTimestamp(String logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	private String ipAdress;// IP地址;
	private String api;// 接口名称
	private String userId;// 用户ID
	private long executeTime = 0l;// 耗时

	private Object logData;// 日志信息

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(long executeTime) {
		this.executeTime = executeTime;
	}

	public Object getLogData() {
		return logData;
	}

	public void setLogData(Object logData) {
		this.logData = logData;
	}
}
