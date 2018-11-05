package org.zmsoft.framework.login;

import javax.servlet.http.HttpServletRequest;

import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.weixin.bean.WxUserBean;

/**
 * 用户登录接口
 * 
 * @author spookfcy
 *
 * @param <T>
 */
public interface ISUserLogin<T> {
	
	/**
	 * 用户正常登录
	 * @param request
	 * @param loginer
	 * @return
	 */
	public RESTResultBean<T> doUserBaseLogin(HttpServletRequest request, LoginerBean loginer) throws Exception;
	
	/**
	 * 用户自动登录
	 * @param request
	 * @param token 第三方登录标识
	 * @param unionId 全局唯一ID
	 * @return
	 */
	public RESTResultBean<T> doUserAutoLogin(HttpServletRequest request, String token,String unionId) throws Exception;
	
	/**
	 * 用户微信登录
	 * @param request
	 * @param wxUserBean
	 * @return
	 */
	public RESTResultBean<T> doUserWXLogin(HttpServletRequest request, WxUserBean wxUserBean) throws Exception;
}
