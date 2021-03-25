package org.zmsoft.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.RandomHelper;

/**
 * 接口控制层超类<br>
 * 需要用户登录
 * 
 * @author ZmSoft
 * @version 2.6.2 2019/10/10
 * @since 2.0.0 2018/10/10
 * @see <MyPageControllerSupport>
 *
 */
public class MyControllerSupport extends MyTokenCommonSupport {
	/**
	 * 页面返回（token自动添加）
	 * 
	 * @param viewName
	 * @return
	 */
	public ModelAndView getModelAndView(String viewName) {
		ModelAndView model = new ModelAndView(viewName);
		model.addObject("token", SessionHelper.getSessionAttribute().getToken());
		return model;
	}

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

}
