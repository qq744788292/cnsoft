package org.zmsoft.framework.support;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.cache.ISCacheService;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.token.TokenBusinessSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.RandomHelper;

/**
 * Token工具
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public class MyTokenCommonSupport extends MyFrameWorkSupport {

	// 缓存中心
	@Resource
	protected ISCacheService myCacheService;

	public String loadSecurityCode(HttpServletRequest request, HttpServletResponse response) {
		String token = loadToken(request, response);
		return loadSecurityCode(token);
	}

	public String loadSecurityCode(String token) {
		String code = RandomHelper.getRandomNumerical(4);
		logger.debug("CheckCodeImageApi=====code>>>>>" + code + "=====token>>>>>" + token);
		myCacheService.putObject(CONSTANT_SECURITY_CODE + token, code, 600, false);
		return code;
	}

	/**
	 * 安全码验证
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean checkSecurityCode(HttpServletRequest request, HttpServletResponse response) {
		return checkSecurityCode(request, response, false);
	}

	public boolean checkSecurityCode(HttpServletRequest request, HttpServletResponse response, boolean autodelete) {
		String token = loadToken(request, response);
		String curCode = request.getParameter("securityCode");
		return checkSecurityCode(token, curCode, autodelete);
	}

	public boolean checkSecurityCode(HttpServletRequest request, String curCode, boolean autodelete) {
		String token = loadToken(request);
		return checkSecurityCode(token, curCode, autodelete);
	}

	public boolean checkSecurityCode(String token, String curCode, boolean autodelete) {
		try {
			if (EmptyHelper.isEmpty(curCode))
				return false;
			String secCode;
			if (autodelete)
				secCode = (String) myCacheService.deleteObject(CONSTANT_SECURITY_CODE + token, false);
			else
				secCode = (String) myCacheService.getObject(CONSTANT_SECURITY_CODE + token, false);
			logger.debug("CheckCodeImageApi=====curCode>>>>>" + curCode + "=====token>>>>>" + token + "=====SecurityCode>>>>>" + secCode);
			return secCode.equals(curCode);
		} catch (Exception e) {
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////

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
		String token = request.getHeader(CONSTANT_USER_TOKEN);
		if (EmptyHelper.isEmpty(token)) {
			token = request.getParameter(CONSTANT_USER_TOKEN);
			if (EmptyHelper.isEmpty(token)) {
				// token = request.getSession().getId().toUpperCase();
				// // 获得URI上面的Token
				// String[] path = request.getRequestURI().split("/");
				// if (path[path.length - 1].length() > 20)
				// token = path[path.length - 1];
			}
		}
		return token;
	}

	/**
	 * 用户登录检查
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @see #doCheckToken
	 */
	public boolean doCheckLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获得参数里面的Token
		String token = loadToken(request, response);
		if (EmptyHelper.isEmpty(token)) {
			// 清空线程缓存
			SessionHelper.setSessionAttribute(null);
			return false;
		}

		logger.debug("token==>>>" + token);

		if (doCheckToken(token) == false) {
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
		// 获得参数里面的Token
		String token = loadToken(request, response);
		if (EmptyHelper.isEmpty(token)) {
			return true;
		}

		logger.debug("token==>>>" + token);

		// 删除原有数据
		doCheckToken(token);
		SessionHelper.sessionDestroyed();

		return true;
	}

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
				String key = currentUser.getId() + currentRoleURL;

				// 获取用户请求权限
				String func = (String) myCacheService.getObject(MANAGER_ROLE_KEY + key);
				if (EmptyHelper.isEmpty(func))
					return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param bizToken
	 * @return
	 * @throws Exception
	 * @see #tokenFail
	 */
	public boolean doCheckToken(String bizToken) throws Exception {
		TokenBusinessSupport tokenBiz = TokenBusinessSupport.build(bizToken);
		tokenBiz.setMyCacheService(myCacheService);
		return tokenBiz.checkToken();
	}
}
