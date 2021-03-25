package org.zmsoft.framework.beans.common;

import java.util.HashMap;

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

	protected HashMap<String, String> responseBody = new HashMap<String, String>();

	public HashMap<String, String> getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(HashMap<String, String> responseBody) {
		this.responseBody = responseBody;
	}

	public void addAttribute(String key, String value) {
		this.responseBody.put(key, value);
	}

	public String getAttribute(String key) {
		return this.responseBody.get(key);
	}
}
