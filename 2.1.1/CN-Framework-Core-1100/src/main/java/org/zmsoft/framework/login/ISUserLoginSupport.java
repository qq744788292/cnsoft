package org.zmsoft.framework.login;

import javax.servlet.http.HttpServletRequest;

import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.weixin.bean.WxUserBean;

/**
 * 最终用户登录接口
 * 
 * @author spookfcy
 *
 * @param <T>
 */
public interface ISUserLoginSupport<T> {
	
	/**
	 * 用户正常登录
	 * @param request
	 * @param loginer
	 * @return
	 */
	public RESTResultBean<T> doDefaultLogin(HttpServletRequest request, LoginerBean loginer) throws Exception;
	
	/**
	 * 用户自动登录
	 * @param request
	 * @param token 第三方登录标识
	 * @param unionId 全局唯一ID
	 * @return
	 */
	public RESTResultBean<T> doAutoLogin(HttpServletRequest request, String token,String unionId) throws Exception;
	
	/**
	 * 用户微信登录
	 * @param request
	 * @param wxUserBean
	 * @return
	 */
	public RESTResultBean<T> doWeixinLogin(HttpServletRequest request, WxUserBean wxUserBean) throws Exception;
}
