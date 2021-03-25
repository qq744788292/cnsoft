package org.zmsoft.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.token.TokenBusinessSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * Token工具
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public class MyTokenCommonSupport extends MyFrameWorkSupport {
	/**
	 * 获得Token属性值
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String loadToken(HttpServletRequest request) {
		return loadToken(request, null);
	}

	public String loadToken(HttpServletRequest request, HttpServletResponse response) {
		// 获得参数里面的Token
		String token = MyHttpRequestHelper.loadToken(request);
		if (EmptyHelper.isEmpty(token)) {
			token = request.getSession().getId().toUpperCase();// Session
		}
		this.setToken(token);
		return token;
	}
	
	/**
	 * 获得请求时间戳
	 * @param request
	 * @return
	 */
	public String loadJobId(HttpServletRequest request) {
		return loadJobId(request, null);
	}
	public String loadJobId(HttpServletRequest request, HttpServletResponse response) {
		// 获得参数里面的Token
		String jobId = MyHttpRequestHelper.loadJobId(request);
		this.setToken(jobId);
		return jobId;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
		String token = loadToken(request, response);
		String jobId = loadJobId(request, response);
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
		String token = loadToken(request, response);
		String jobId = loadJobId(request, response);
		if (EmptyHelper.isEmpty(token)) {
			return true;
		}

		logger.debug("token==>>>" + token);

		// 删除原有数据
		doCheckToken(request, jobId, token);
		SessionHelper.sessionDestroyed();

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
		String funcs = (String) myCacheService.getObject(MANAGER_ROLE_KEY + ZERO);
		if ((ONE.equals(currentUser.getIsAdmin()) == false) && EmptyHelper.isNotEmpty(funcs)) {
			// 如果当前路径需要权限管理
			if (funcs.indexOf(currentRoleURL) > -1) {
				String key = currentUser.getUserId() + currentRoleURL;

				// 获取用户请求权限
				String func = (String) myCacheService.getObject(MANAGER_ROLE_KEY + key);
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
		if (GUEST.equals(SessionHelper.currentUserHdp()))
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
		TokenBusinessSupport tokenBiz = TokenBusinessSupport.build(jobId, bizToken, autoSplit);
		tokenBiz.setMyCacheService(myCacheService);
		return tokenBiz.checkToken(request);
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
		TokenBusinessSupport tokenBiz = TokenBusinessSupport.build(jobId, bizToken, autoSplit);
		tokenBiz.setMyCacheService(myCacheService);
		tokenBiz.setCurUser(currentUser);
		return tokenBiz.saveToken(request);
	}

	boolean autoSplit = false;
}
