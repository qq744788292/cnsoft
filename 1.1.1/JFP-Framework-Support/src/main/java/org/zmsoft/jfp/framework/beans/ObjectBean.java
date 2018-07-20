package org.zmsoft.jfp.framework.beans;

import com.alibaba.fastjson.JSON;

/**
 * 参数配置信息
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class ObjectBean {

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
