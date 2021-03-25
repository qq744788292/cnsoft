package org.zmsoft.framework.weixin.bean;

/**
 * 微信自定义回调数据实体
 * 
 * @author ZMSoft
 *
 */
public class WxCallBackParamBean extends WxUserBean {

	/**
	 * 会员ID
	 */
	private String playerId;
	
	/**
	 * 1一般用户2业务员3客户
	 */
	private String loginType;
	
	/**
	 * 业务数据
	 */
	private Object data;

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/////////////////////////////////////////////////////////////////////////
	private String wxShareToken;

	public String getWxShareToken() {
		return wxShareToken;
	}

	public void setWxShareToken(String wxShareToken) {
		this.wxShareToken = wxShareToken;
	}
	/////////////////////////////////////////////////////////////////////////
	/**
	 * 用户类型（2新注册用户1注册用户0游客）
	 */
	private String roolType;

	public String getRoolType() {
		return roolType;
	}

	public void setRoolType(String roolType) {
		this.roolType = roolType;
	}

}
