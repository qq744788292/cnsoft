package org.zmsoft.jfp.framework.beans.client;

import org.zmsoft.jfp.framework.beans.user.TokenBean;

/**
 * 客户端获取最新数据Bean
 * 
 * @author ZmSoft
 * @version 1.2.1 2018/7/18
 * @since 1.2.1 2018/7/18
 *
 */
public class ClientNewDataBean extends TokenBean {

	/**
	 * 客户端ID
	 */
	private String clientId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	//////////////////////////////////////////////////////////////////
	/**
	 * 页面地址
	 */
	String openUrl;
	
	/**
	 * 提示信息
	 */
	String msg;
	
	
}
