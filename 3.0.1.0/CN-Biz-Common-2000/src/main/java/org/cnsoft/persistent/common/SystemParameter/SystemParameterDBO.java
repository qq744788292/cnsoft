package org.cnsoft.persistent.common.SystemParameter;

import org.cnsoft.framework.db.support.ext.MyDataBaseObjectSupport2;

/** 参数定义 */
public class SystemParameterDBO extends MyDataBaseObjectSupport2 {
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
