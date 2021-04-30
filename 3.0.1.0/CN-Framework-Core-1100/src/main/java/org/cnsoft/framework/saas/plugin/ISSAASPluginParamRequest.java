package org.cnsoft.framework.saas.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SAAS开发模式下业务请求拦截器
 */
public interface ISSAASPluginParamRequest {

	boolean doCheck(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
