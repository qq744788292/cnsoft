package org.zmsoft.pay3.wx.bean;

import org.zmsoft.framework.ObjectBean;

/**
 * 微信场景信息
 * 
 * @version 2.6.3.1 2020.01.28
 */
public class SceneInfoBean extends ObjectBean {
	String id; // 门店id
	String name;// 门店名称
	String area_code;// 门店行政区划码
	String address;// 门店详细地址

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
