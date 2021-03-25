package org.cnsoft.framework.common.buzzinezz;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户请求唯一标识(Token)验证
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public interface ISTokenSupport {

	public final static String My_CustomTokenCheck_Service = "MyCustomTokenCheckService";

	public boolean checkToken(HttpServletRequest request) throws Exception;
}
