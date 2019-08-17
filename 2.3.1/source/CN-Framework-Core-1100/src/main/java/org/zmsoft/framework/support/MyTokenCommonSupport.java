package org.zmsoft.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.framework.beans.UserBean;
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

	/**
	 * 获得当前线程的安全码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String loadSecurityCode(HttpServletRequest request, HttpServletResponse response) {
		String token = loadToken(request, response);
		return loadSecurityCode(token);
	}

	/**
	 * 获得当前线程的安全码
	 * 
	 * @param token
	 * @return
	 */
	public String loadSecurityCode(String token) {
		String code = RandomHelper.getRandomNumerical(4);
		logger.debug("CheckCodeImageApi=====code>>>>>" + code + "=====token>>>>>" + token);
		myCacheService.putObject(CONSTANT_SECURITY_CODE + token, code, 600, false);
		return code;
	}

	/**
	 * 安全码验证(成功后删除)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean checkSecurityCode(HttpServletRequest request, HttpServletResponse response) {
		return checkSecurityCode(request, response, false);
	}

	/**
	 * 安全码验证
	 * 
	 * @param request
	 * @param response
	 * @param autodelete
	 *            false:成功后删除;true:自动删除
	 * @return
	 */
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
			String secCode = (String) myCacheService.getObject(CONSTANT_SECURITY_CODE + token, false);
			boolean check = secCode.equals(curCode);
			if (autodelete || check)
				myCacheService.removeKey(CONSTANT_SECURITY_CODE + token);
			logger.debug("CheckCodeImageApi=====curCode>>>>>" + curCode + "=====token>>>>>" + token + "=====SecurityCode>>>>>" + secCode);
			return check;
		} catch (Exception e) {
			return false;
		}
	}

	//////////////////////////////////////////////////////////

	/**
	 * 根据手机号获取验证码
	 * 
	 */
	public String loadPhoneNumberSecurityCode(String phoneNumber) {
		String code = RandomHelper.getRandomNumerical(4);
		logger.debug("loadPhoneNumberSecurityCode=====phoneNumber>>>>>" + phoneNumber);
		myCacheService.putObject(PHONE_SECURITY_CODE + phoneNumber, code, 600, false);
		return code;
	}

	/**
	 * 校验手机号和验证码
	 */
	public Boolean checkPhoneNumSecurityCode(String phoneNumber, String curCode) {
		try {
			if (EmptyHelper.isEmpty(curCode))
				return false;
			String secCode = (String) myCacheService.getObject(PHONE_SECURITY_CODE + phoneNumber, false);
			boolean check = secCode.equals(curCode);
			if (check)
				myCacheService.removeKey(PHONE_SECURITY_CODE + super.getToken());
			logger.debug("checkPhoneNumSecurityCode=====curCode>>>>>" + curCode + "=====SecurityCode>>>>>" + secCode);
			return check;
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
		String token = TokenBusinessSupport.loadToken(request);
		if (EmptyHelper.isEmpty(token)) {
			token = request.getSession().getId().toUpperCase();// Session
		}
		this.setToken(token);
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
	public boolean doCheckGuest(String bizToken) throws Exception {
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
	public boolean doCheckToken(String bizToken) throws Exception {
		TokenBusinessSupport tokenBiz = TokenBusinessSupport.build(bizToken, autoSplit);
		tokenBiz.setMyCacheService(myCacheService);
		return tokenBiz.checkToken();
	}

	/**
	 * 保存当前用户信息
	 * 
	 * @param bizToken
	 * @return
	 * @throws Exception
	 * @see #tokenFail
	 */
	public synchronized boolean doSaveUser(String bizToken, UserBean currentUser) throws Exception {
		TokenBusinessSupport tokenBiz = TokenBusinessSupport.build(bizToken, autoSplit);
		tokenBiz.setMyCacheService(myCacheService);
		tokenBiz.setCurUser(currentUser);
		return tokenBiz.saveToken();
	}

	boolean autoSplit = false;
}
