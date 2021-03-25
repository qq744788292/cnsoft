package org.zmsoft.framework.weixin.bean;

/**
 * 微信用户个人信息（UnionID机制）
 * 
 * @author ZMSoft
 *
 */
public class WxUserinfoEntity {
	// 普通用户的标识，对当前开发者帐号唯一
	String openid;
	// 普通用户昵称
	String nickname;
	// 普通用户性别，1为男性，2为女性
	String sex;
	// 普通用户个人资料填写的省份
	String province;
	// 普通用户个人资料填写的城市
	String city;
	// 国家，如中国为CN
	String country;
	// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	String headimgurl;
	// 用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
	String vprivilege;
	// 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
	String unionid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getVprivilege() {
		return vprivilege;
	}

	public void setVprivilege(String vprivilege) {
		this.vprivilege = vprivilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}
