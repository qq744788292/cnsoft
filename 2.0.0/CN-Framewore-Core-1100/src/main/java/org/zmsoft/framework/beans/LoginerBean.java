package org.zmsoft.framework.beans;

import org.zmsoft.framework.beans.common.FrameworkDataBean;

/**
 * 用户登录信息
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */

public class LoginerBean extends FrameworkDataBean {

	/**
	 * 服务器认证授权码（登记授权）
	 */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * 账户
	 */
	private String account;
	/**
	 * 密码
	 */
	private String passWord;

	/**
	 * 安全码
	 */
	private String securityCode;

	/**
	 * -登录来源-10微信公众号11手机浏览器12苹果手机13安卓手机20绿城应用30电脑网站
	 * 
	 */
	private String loginSource;

	/**
	 * 第三方登录ID
	 * 
	 * @return
	 */
	private String openId;

	/**
	 * 登录方式-0手机号1账号2微信3支付宝99中台
	 */
	private String loginType;

	/**
	 * 登陆结果<br>
	 * 0:成功 1:密码错误 2:用户不存在 3:二次登录8:用户类型未知 9:用户异常锁定
	 */
	private String loginStatus;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(String loginSource) {
		this.loginSource = loginSource;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

}
