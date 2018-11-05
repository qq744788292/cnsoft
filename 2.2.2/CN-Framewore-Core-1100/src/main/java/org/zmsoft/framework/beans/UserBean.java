package org.zmsoft.framework.beans;

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
		this.setId(userId);
	}

	/**
	 * 用户ID
	 */
	private String id;
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
	 * 用户类型（10微信公众号11手机浏览器12苹果手机13安卓手机20绿城应用30电脑网站）
	 */
	private String roolType;

	/**
	 * 是否管理员
	 */
	private String isAdmin;
	
	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	//--------------------------------------------------------------------
	/**
	 * 用户所属团队
	 */
	private String teamIds;
	private String teamNames;
	/**
	 * 用户所属角色
	 */
	private String roleIds;
	private String roleNames;
	/**
	 * 用户所属楼盘
	 */
	private String estateIds;
	private String estateNames;
	
	public String getTeamIds() {
		return teamIds;
	}

	public void setTeamIds(String teamIds) {
		this.teamIds = teamIds;
	}

	public String getTeamNames() {
		return teamNames;
	}

	public void setTeamNames(String teamNames) {
		this.teamNames = teamNames;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getEstateIds() {
		return estateIds;
	}

	public void setEstateIds(String estateIds) {
		this.estateIds = estateIds;
	}

	public String getEstateNames() {
		return estateNames;
	}

	public void setEstateNames(String estateNames) {
		this.estateNames = estateNames;
	}

}
