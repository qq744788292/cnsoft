package org.zmsoft.framework.beans;

import com.alibaba.fastjson.JSON;

/**
 * 对象超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/08/08
 * @since 1.0.0 2018/02/02
 * @see <MyFrameWorkSupport>
 */
public class ObjectBean {

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
