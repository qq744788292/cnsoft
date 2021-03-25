package org.zmsoft.framework.common.buzzinezz;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求头
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public interface ISTokenSupport {

	public final static String My_CustomTokenCheck_Service = "MyCustomTokenCheckService";

	public boolean checkToken(HttpServletRequest request) throws Exception;
}
