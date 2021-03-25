package org.cnsoft.framework.token;

import org.cnsoft.framework.beans.FrameworkDataBean;
import org.cnsoft.framework.constants.ICFrameworkConstants;

/**
 * 用户登录信息
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */

public class TokenBean extends FrameworkDataBean implements ICFrameworkConstants {

	/**
	 * 浏览器客户端唯一标识
	 */
	private String clientId;
	/**
	 * 登录时间
	 */
	private String clientTimestamp;

	/**
	 * 会话识别 Id
	 */
	private String sessionId;

	/**
	 * 用户会话标识
	 */
	private String userId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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