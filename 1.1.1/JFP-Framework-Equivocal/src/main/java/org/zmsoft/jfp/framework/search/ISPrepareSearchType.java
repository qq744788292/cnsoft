package org.zmsoft.jfp.framework.search;

import com.alibaba.fastjson.JSONObject;

/**
 * 检索数据整理
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public interface ISPrepareSearchType {
	
	/**
	 * 数据整理
	 * @param js 原始数据
	 * @param cb 转换后对象类
	 */
	void prepareSearchType(JSONObject js, Object cb);
}
