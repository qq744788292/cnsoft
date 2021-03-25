package org.cnsoft.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnsoft.framework.beans.user.UserBean;
import org.cnsoft.framework.cache.session.SessionHelper;
import org.cnsoft.framework.token.TokenServiceImpl;
import org.cnsoft.framework.utils.EmptyHelper;

/**
 * Token工具
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public class MyTokenCommonSupport extends MyBusinessSupport {
	
	/**
	 * 用户登录检查
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @see #doCheckToken
	 */
	public boolean doCheckLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获得请求头参数
		String token = MyHttpRequestHelper.loadToken(request, response);
		String jobId = MyHttpRequestHelper.loadJobId(request, response);
		if (EmptyHelper.isEmpty(token)) {
			// 清空线程缓存
			SessionHelper.setSessionAttribute(null);
			return false;
		}

		logger.debug("token==>>>" + token);

		if (doCheckToken(request, jobId, token) == false) {
			return false;
		}

		return true;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 用户权限检查
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @see #doCheckToken
	 */
	public boolean doCheckRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取接口路径
		String currentRoleURL = request.getRequestURI().toString();
		UserBean currentUser = SessionHelper.currentUser();
		// 获得所有权限
		String funcs = myCacheService.getObject(MANAGER_ROLE_KEY + ZERO);
		if ((ONE.equals(currentUser.getIsAdmin()) == false) && EmptyHelper.isNotEmpty(funcs)) {
			// 如果当前路径需要权限管理
			if (funcs.indexOf(currentRoleURL) > -1) {
				String key = currentUser.getUserId() + currentRoleURL;

				// 获取用户请求权限
				String func = myCacheService.getObject(MANAGER_ROLE_KEY + key);
				if (EmptyHelper.isEmpty(func))
					return false;
			}
		}
		return true;
	}

	/**
	 * 是否为游客（true:是）
	 * 
	 * @throws Exception
	 * @see #doCheckToken
	 */
	public boolean doCheckGuest(HttpServletRequest request, String jobId, String bizToken) throws Exception {
		if (GUEST.equals(SessionHelper.currentUserSid()))
			return true;
		return false;
	}

	/**
	 * 检查Token有效性
	 * 
	 * @param bizToken
	 * @return
	 * @throws Exception
	 * @see #tokenFail
	 */
	public boolean doCheckToken(HttpServletRequest request, String jobId, String bizToken) throws Exception {
		TokenServiceImpl tokenBiz = TokenServiceImpl.build(jobId, bizToken);
		tokenBiz.setCacheService(myCacheService);
		return tokenBiz.checkToken(request);
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @see #doCheckToken
	 */
	public boolean doDestroyedLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获得请求头参数
		String token = MyHttpRequestHelper.loadToken(request, response);
		String jobId = MyHttpRequestHelper.loadJobId(request, response);
		if (EmptyHelper.isEmpty(token)) {
			return true;
		}

		logger.debug("token==>>>" + token);

		// 删除原有数据
		doCheckToken(request, jobId, token);
		SessionHelper.sessionDestroyed();

		return true;
	}

	/**
	 * 保存当前用户信息
	 * 
	 * @param bizToken
	 * @return
	 * @throws Exception
	 * @see #tokenFail
	 */
	public synchronized boolean doSaveUser(HttpServletRequest request, String jobId, String bizToken, UserBean currentUser) throws Exception {
		TokenServiceImpl tokenBiz = TokenServiceImpl.build(jobId, bizToken);
		tokenBiz.setCurUser(currentUser);
		tokenBiz.setCacheService(myCacheService);
		return tokenBiz.saveToken(request);
	}

}
