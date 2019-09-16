package org.isotope.jfp.mpc.weixin.beans.recever;

import org.isotope.jfp.framework.beans.message.info.UserReceverBean;

/**
 * 微信用户信息 <br>
 * 参考【微信企业号关注用户表】company_weixin_user
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * @see <WeixinGroupUserDBO>
 */
public class WeiXinCompanyGroupUserReceverBean extends UserReceverBean {

	/**
	 * 学校id
	 */
	private String companyId;

	/**
	 * 用户组id
	 */
	private String groupid;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 用户类别
	 */
	private String uertType;

	/**
	 * 用户昵称
	 */
	private String userName;

	/**
	 * openId
	 */
	private String openid;

	/**
	 * 微信ID
	 */
	private String wxId;

	/**
	 * 是否启用
	 */
	private String isUse;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUertType() {
		return uertType;
	}

	public void setUertType(String uertType) {
		this.uertType = uertType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

}
