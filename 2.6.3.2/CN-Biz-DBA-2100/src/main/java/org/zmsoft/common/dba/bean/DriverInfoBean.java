package org.zmsoft.common.dba.bean;

import org.zmsoft.framework.beans.common.FrameworkDataBean;

/**
 * 数据库驱动配置信息
 */
public class DriverInfoBean extends FrameworkDataBean {
	String username;
	String password;
	String url;
	String driverClass;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

}
