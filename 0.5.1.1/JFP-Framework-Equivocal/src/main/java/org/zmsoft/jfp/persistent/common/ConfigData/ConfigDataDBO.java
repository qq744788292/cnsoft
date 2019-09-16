package org.zmsoft.jfp.persistent.common.ConfigData;

import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;

/** 参数配置 */
public class ConfigDataDBO extends MyDataBaseObjectSupport3 {
	/**
	 * 分类
	 */
	protected String type;

	/**
	 * 配置使用关键字
	 */
	protected String key;

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
