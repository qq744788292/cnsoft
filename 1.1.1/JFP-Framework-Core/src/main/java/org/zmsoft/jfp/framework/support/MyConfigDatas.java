package org.zmsoft.jfp.framework.support;

import java.util.HashMap;

/**
 * 参数配置定义
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class MyConfigDatas {

	HashMap<String, String> configs = new HashMap<String, String>();

	/**
	 * 获得一个参数配置定义
	 * 
	 * @param key
	 * @return
	 */
	public String getConfig(String key) {
		return configs.get(key);
	}

	/**
	 * 设置一个参数配置定义
	 * 
	 * @param key
	 * @param config
	 */
	public void setConfig(String key, String config) {
		this.configs.put(key, config);
	}

	/**
	 * 加载参数配置定义
	 * 
	 * @param configs
	 */
	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}

}
