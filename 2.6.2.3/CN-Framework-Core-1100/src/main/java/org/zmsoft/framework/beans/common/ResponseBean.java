package org.zmsoft.framework.beans.common;

import org.zmsoft.framework.ObjectBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 接口数据返回主体
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class ResponseBean<T> extends ObjectBean implements ICFrameworkConstants {

	/**
	 * 返回结果
	 */
	protected Object responseBody = null;

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

}
