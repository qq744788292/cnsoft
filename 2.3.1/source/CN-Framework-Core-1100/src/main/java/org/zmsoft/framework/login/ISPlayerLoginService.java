package org.zmsoft.framework.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.token.TokenBusinessSupport;
import org.zmsoft.framework.weixin.bean.WxCallBackParamBean;

/**
 * 最终用户登录接口
 * 
 * @author spookfcy
 *
 * @param <T>
 */
public interface ISPlayerLoginService extends ICFrameworkConstants {

	/**
	 * 用户正常登录
	 * 
	 * @param request
	 * @param loginer
	 * @return
	 */
	public RESTResultBean<TokenBusinessSupport> doDefaultLogin(HttpServletRequest request, HttpServletResponse response, LoginerBean loginer) throws Exception;

	/**
	 * 用户自动登录
	 * 
	 * @param request
	 * @param token
	 *            第三方登录标识
	 * @param unionId
	 *            全局唯一ID
	 * @return
	 */
	public RESTResultBean<TokenBusinessSupport> doAutoLogin(HttpServletRequest request, HttpServletResponse response, String token, String unionId) throws Exception;

	/**
	 * 用户微信登录
	 * 
	 * @param request
	 * @param wxCallBackParamBean
	 * @return
	 */
	public RESTResultBean<TokenBusinessSupport> doWeixinLogin(HttpServletRequest request, HttpServletResponse response, WxCallBackParamBean wxCallBackParamBean) throws Exception;

}
