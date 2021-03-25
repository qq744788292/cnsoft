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
public class RequestBean extends ObjectBean implements ICFrameworkConstants {

	protected HashMap<String, String> requestBody = new HashMap<String, String>();

	public HashMap<String, String> getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(HashMap<String, String> requestBody) {
		this.requestBody = requestBody;
	}

	public void addAttribute(String key, String value) {
		this.requestBody.put(key, value);
	}

	public String getAttribute(String key) {
		return this.requestBody.get(key);
	}
}
