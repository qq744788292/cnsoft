package org.zmsoft.framework.beans;

import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.utils.DateHelper;

/**
 * 用户登录信息
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */

public class TokenBean extends ObjectBean implements IFrameworkConstants {

	/**
	 * 数据所属
	 */
	protected String hdp = SYSTEM;

	/**
	 * IP地址
	 */
	private String ipAdress;
	/**
	 * 登录时间
	 */
	private String clientTimestamp;
	/**
	 * 用户会话标识
	 */
	private String sessionId;

	///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 服务器认证授权码（登记授权）
	 */
	private String token;

	///////////////////////////////////////////////////////////////////////////////////
	public void setHdp(String hdp) {
		this.hdp = hdp;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public void setClientTimestamp() {
		this.clientTimestamp = DateHelper.currentTimestamp();
	}

	public void setClientTimestamp(String clientTimestamp) {
		this.clientTimestamp = clientTimestamp;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String currentBizData() {
		return clientTimestamp + DOWN_LINE + ipAdress + DOWN_LINE + hdp;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////

}
