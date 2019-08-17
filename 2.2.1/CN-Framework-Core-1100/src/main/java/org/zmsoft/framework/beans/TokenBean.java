package org.zmsoft.framework.beans;

import org.zmsoft.framework.constants.IFrameworkConstants;

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
	protected String hdp;

	/**
	 * 用户唯一识别ID
	 */
	private String uuid;
	/**
	 * 登录时间
	 */
	private String clientTimestamp;

	/**
	 * session Id
	 */
	private String sessionId;

	/**
	 * 用户会话标识
	 */
	private String userId;

	public String getHdp() {
		return hdp;
	}

	public void setHdp(String hdp) {
		this.hdp = hdp;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getClientTimestamp() {
		return clientTimestamp;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}