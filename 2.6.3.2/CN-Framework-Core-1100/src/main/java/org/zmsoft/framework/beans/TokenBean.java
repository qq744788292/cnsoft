package org.zmsoft.framework.beans;

import org.zmsoft.framework.beans.common.FrameworkDataBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 用户登录信息
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */

public class TokenBean extends FrameworkDataBean implements ICFrameworkConstants {

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