package org.cnsoft.framework.beans.common;

import java.util.HashMap;

import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.core.ObjectBean;

/**
 * 接口数据返回主体
 * 
 * @author CNSoft
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
