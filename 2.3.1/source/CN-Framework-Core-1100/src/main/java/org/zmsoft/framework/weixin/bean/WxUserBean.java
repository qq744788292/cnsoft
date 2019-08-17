package org.zmsoft.framework.weixin.bean;

import org.zmsoft.framework.beans.TokenBean;

/**
 * 微信用户资料
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
public class WxUserBean extends TokenBean {
	// 公众账号appid
	String appid;
	// 公众账号密钥
	String secret;

	String grant_type = "authorization_code";

	String code;// 临时验证码

	String state;// 状态

	String access_token;// 通过code换取网页授权access_token

	String openid;

	String channel;// 渠道

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
