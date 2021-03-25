package org.cnsoft.framework.beans.common;

import java.util.HashMap;

import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.core.ObjectBean;

/**
 * 接口请求头数据信息
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MapBean extends ObjectBean implements ICFrameworkConstants {

	/**
	 * 自定义请求头数据
	 */
	protected HashMap<String, String> map = new HashMap<String, String>();
	
	public HashMap<String, String> currentDatas() {
		return map;
	}
	
	////////////////////////////////////////////////////////////////////////
	/**
	 * 通用持久化方法
	 * @param key
	 * @param value
	 * @see <#loadAttribute>
	 */
	public void addAttribute(String key, String value) {
		this.map.put(key, value);
	}

	public String loadAttribute(String key) {
		return this.map.get(key);
	}
}
