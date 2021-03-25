package org.cnsoft.framework.common.buzzinezz;

import javax.servlet.http.HttpServletRequest;

import org.cnsoft.framework.cache.client.RequestHeaderBean;

/**
 * 请求头
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public interface ISHeaderSupport {

	public final static String My_HttpRequestHeader_Service = "MyHttpRequestHeaderService";

	public void loadHeaderParam(HttpServletRequest request);

	public RequestHeaderBean getHeaderParams();
}
