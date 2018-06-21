package org.zmsoft.jfp.framework.beans.common;

import org.zmsoft.jfp.framework.beans.ObjectBean;

/**
 * 参数配置信息
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */

public class ConfigBean extends ObjectBean {
	/**
	 * 配置使用关键字
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

	@Override
	public String toString() {
		return "ConfigBean [key=" + key + ", value=" + value + "]";
	}

}
