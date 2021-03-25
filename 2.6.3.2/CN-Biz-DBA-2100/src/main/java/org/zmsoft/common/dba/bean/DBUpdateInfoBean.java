package org.zmsoft.common.dba.bean;

import org.zmsoft.framework.beans.common.FrameworkDataBean;

/**
 * 数据库驱动配置信息
 */
public class DBUpdateInfoBean extends FrameworkDataBean {
	String pass;
	DriverInfoBean sServer;
	DriverInfoBean tServer;

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public DriverInfoBean getsServer() {
		return sServer;
	}

	public void setsServer(DriverInfoBean sServer) {
		this.sServer = sServer;
	}

	public DriverInfoBean gettServer() {
		return tServer;
	}

	public void settServer(DriverInfoBean tServer) {
		this.tServer = tServer;
	}
}
