package org.zmsoft.persistent.common.SystemConfig;

import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;

/** 系统配置 */
public class SystemConfigDBO extends MyDataBaseObjectSupport3 {
	/**
	 * 分类
	 */
	protected String key;

	/**
	 * 配置对应的内容
	 */
	protected String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
