package org.zmsoft.framework.beans;

import java.util.List;

/**
 * 系统用户信息
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */

public class UserBean extends LoginerBean {
	public UserBean() {

	}

	public UserBean(String userId) {
		this.setUserId(userId);
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 数据库统一主键
	 */
	public String getId() {
		return getUserId();
	}

	public void setId(String id) {
		this.setUserId(id);
	}
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户昵称---用户联系人
	 */
	private String userNameCN;
	/**
	 * 用户头像
	 */
	private String userHeadUrl;
	/**
	 * 用户电话--用户联系人电话
	 */
	private String userPhone;

	/**
	 * 用户性别
	 */
	private String userSex;

	/**
	 * 用户邮箱
	 */
	private String userMail;

	/**
	 * 用户所属企业ID
	 */
	private String companyId;
	/**
	 * 用户所属企业名称
	 */
	private String companyNameCN;

	/**
	 * 所属部门ID
	 */
	private String departmentId;

	/**
	 * 所属部门名称
	 */
	private String departmentName;

	/**
	 * 关联企业列表
	 */
	private List<RoleBean> companyRoles;

	/**
	 * 用户类型（2新注册用户1注册用户0游客）
	 */
	private String roolType;

	/**
	 * 是否管理员
	 */
	private String isAdmin;
	/////////////////////////////////////////////////////////////////////

	/**
	 * 账户状态
	 */
	private String allowLogin;

	/**
	 * 在线状态
	 */
	private String onlineType;

	/**
	 * 最后登录IP
	 */
	private String onlineLastIp;

	/**
	 * 最后登录时间
	 */
	private String onlineLastTime;
	/////////////////////////////////////////////////////////////////////

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

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<RoleBean> getCompanyRoles() {
		return companyRoles;
	}

	public void setCompanyRoles(List<RoleBean> companyRoles) {
		this.companyRoles = companyRoles;
	}

	public String getRoolType() {
		return roolType;
	}

	public void setRoolType(String roolType) {
		this.roolType = roolType;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
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
