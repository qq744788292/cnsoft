package org.zmsoft.jfp.framework.beans.user;

/**
 * 系统用户信息
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */

public class UserBean extends LoginerBean {
	public UserBean() {

	}

	public UserBean(String userId) {
		this.setUserId(userId);
	}

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户昵称
	 */
	private String userNameCN;
	/**
	 * 用户头像
	 */
	private String userHeadUrl;
	/**
	 * 用户电话
	 */
	private String userPhone;

	/**
	 * 用户性别
	 */
	private String userSex = null;

	/**
	 * 用户邮箱
	 */
	private String userMail = null;

	/**
	 * 企业ID
	 */
	private String companyId;
	/**
	 * 企业全称
	 */
	private String companyNameCN;
	/**
	 * 企业电话
	 */
	private String companyPhone;

	/**
	 * 企业图标
	 */
	private String companyLogo;

	/**
	 * 登录用户标识(0游客1基本用户9超级管理员)
	 */
	private String roolType;

	/**
	 * 账户状态
	 */
	private String allowLogin = null;

	/**
	 * 在线状态
	 */
	private String onlineType = null;

	/**
	 * 最后登录IP
	 */
	private String onlineLastIp = null;

	/**
	 * 最后登录时间
	 */
	private String onlineLastTime = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNameCN() {
		return userNameCN;
	}

	public void setUserNameCN(String userNameCN) {
		this.userNameCN = userNameCN;
	}

	public String getUserHeadUrl() {
		return userHeadUrl;
	}

	public void setUserHeadUrl(String userHeadUrl) {
		this.userHeadUrl = userHeadUrl;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyNameCN() {
		return companyNameCN;
	}

	public void setCompanyNameCN(String companyNameCN) {
		this.companyNameCN = companyNameCN;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getRoolType() {
		return roolType;
	}

	public void setRoolType(String roolType) {
		this.roolType = roolType;
	}

	public String getAllowLogin() {
		return allowLogin;
	}

	public void setAllowLogin(String allowLogin) {
		this.allowLogin = allowLogin;
	}

	public String getOnlineType() {
		return onlineType;
	}

	public void setOnlineType(String onlineType) {
		this.onlineType = onlineType;
	}

	public String getOnlineLastIp() {
		return onlineLastIp;
	}

	public void setOnlineLastIp(String onlineLastIp) {
		this.onlineLastIp = onlineLastIp;
	}

	public String getOnlineLastTime() {
		return onlineLastTime;
	}

	public void setOnlineLastTime(String onlineLastTime) {
		this.onlineLastTime = onlineLastTime;
	}

}
