package org.zmsoft.framework.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.weixin.bean.WxCallBackParamBean;

/**
 * 最终用户登录接口
 * 
 * @author ZMSoft
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
	public RESTResultBean<UserBean> doDefaultLogin(HttpServletRequest request, HttpServletResponse response, LoginerBean loginer) throws Exception;

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
	public RESTResultBean<UserBean> doAutoLogin(HttpServletRequest request, HttpServletResponse response, String token, String unionId) throws Exception;

	/**
	 * 用户微信登录
	 * 
	 * @param request
	 * @param wxCallBackParamBean
	 * @return
	 */
	public RESTResultBean<UserBean> doWeixinLogin(HttpServletRequest request, HttpServletResponse response, WxCallBackParamBean wxCallBackParamBean) throws Exception;

	/**
	 * 根据用户ID登录
	 * 
	 * @param request
	 * @param response
	 * @param curUser
	 * @return
	 * @throws Exception
	 * @see #doAutoLogin
	 */
	public RESTResultBean<UserBean> doPlayerLogin(HttpServletRequest request, HttpServletResponse response, UserBean curUser) throws Exception;
}
