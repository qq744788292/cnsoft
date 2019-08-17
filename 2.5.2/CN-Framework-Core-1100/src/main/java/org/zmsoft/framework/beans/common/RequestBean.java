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
public class RequestBean extends ObjectBean implements ICFrameworkConstants {

	protected String requestBody = null;

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
}
