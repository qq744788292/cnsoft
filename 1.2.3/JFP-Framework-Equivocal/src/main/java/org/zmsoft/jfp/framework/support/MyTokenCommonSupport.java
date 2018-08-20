package org.zmsoft.jfp.framework.support;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.constants.IDBConstants;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.token.TokenBusinessSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.framework.utils.RandomHelper;

/**
 * Token工具
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class MyTokenCommonSupport implements IFrameworkConstants, IDBConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
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
		myCacheService.putObject(CONSTANT_SECURITY_CODE + token, code, 300, false);
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
	public String loadToken(HttpServletRequest request, HttpServletResponse response) {
		// 获得参数里面的Token
		String token = request.getParameter(CONSTANT_USER_TOKEN);
		if (EmptyHelper.isEmpty(token)) {
			token = request.getSession().getId().toUpperCase();
			// // 获得URI上面的Token
			// String[] path = request.getRequestURI().split("/");
			// if (path[path.length - 1].length() > 20)
			// token = path[path.length - 1];
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
			return false;
		}

		logger.debug("token==>>>" + token);

		if (doCheckToken(token) == false) {
			return false;
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
