package org.zmsoft.jfp.persistent.common.SystemConfig;

import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;

/** 系统配置 */
public class SystemConfigDBO extends MyDataBaseObjectSupport3 {
	/**
	 * 分类
	 */
	protected String type;

	/**
	 * 配置对应的内容
	 */
	protected String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
