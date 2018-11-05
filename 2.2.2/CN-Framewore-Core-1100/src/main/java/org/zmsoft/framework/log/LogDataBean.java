package org.zmsoft.framework.log;

import org.zmsoft.framework.beans.ObjectBean;

/**
 * 日志对象
 * 
 * @author spookfcy
 *
 */
public class LogDataBean extends ObjectBean {

	private String logTimestamp;
	private String serviceName;
	
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

	public Object getLogData1() {
		return logData;
	}

	public void setLogData(Object logData) {
		this.logData = logData;
	}
}
