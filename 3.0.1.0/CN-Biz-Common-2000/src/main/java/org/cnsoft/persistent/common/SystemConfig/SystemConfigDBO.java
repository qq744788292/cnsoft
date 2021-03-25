package org.cnsoft.persistent.common.SystemConfig;

import org.cnsoft.framework.db.support.ext.MyDataBaseObjectSupport2;

/** 系统配置 */
public class SystemConfigDBO extends MyDataBaseObjectSupport2 {
	/**
	 * 分类
	 */
	public String key;

	/**
	 * 配置对应的内容
	 */
	public String value;

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
