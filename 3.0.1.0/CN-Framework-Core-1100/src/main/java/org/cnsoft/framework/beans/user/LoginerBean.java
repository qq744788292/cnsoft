package org.cnsoft.framework.beans.user;

import org.cnsoft.framework.token.TokenBean;

/**
 * 用户登录信息
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */

public class LoginerBean extends TokenBean {

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
	 * -登录来源 - 11电脑网页 12安卓手机浏览器 13苹果手机浏览器<br>
	 *          - 21APP 22安卓手机 23苹果手机<br>
	 *          - 31微信公众号 32支付宝公众号<br>
	 * 		    — 90其他<br>
	 */
	private String loginSource;

	/**
	 * 第三方登录ID（登录和续签使用）
	 * 
	 * @return
	 */
	private String openId;

	/**
	 * 登录方式-10账户20手机号30微信31QQ40支付宝50微博60极光99中台
	 */
	private String loginType;

	/**
	 * 登陆结果<br>
	 * 0:成功 1:密码错误 2:用户不存在 3:二次登录8:用户类型未知 9:用户异常锁定
	 */
	private String loginStatus;

	/**
	 * 唯一识别ID（登录和续签使用）
	 */
	private String uuid;

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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
