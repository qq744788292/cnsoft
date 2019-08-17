package com.zmsoft.controller.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICacheConstants;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.token.TokenBusinessSupport;

/**
 * 用户登出
 * 
 * @author FCY
 * @version 2.0.1 2018/7/8 新建
 * @since 2.0.1 2018/7/8
 * @see <ApiUserInfoController>,<LCJUserLoginController>,<WxIndexController>
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/1.0/player", method = { RequestMethod.POST })
public class ApiPlayerLogoutController extends MyTokenCommonSupport implements ICacheConstants {

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public RESTResultBean<UserBean> userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RESTResultBean<UserBean> result = new RESTResultBean<UserBean>();
		//退出
		doDestroyedLogin(request, response);
		
		TokenBusinessSupport _TokenBusinessSupport_ = TokenBusinessSupport.defaultToken(request);
		_TokenBusinessSupport_.setMyCacheService(myCacheService);
		_TokenBusinessSupport_.saveToken();
		
		result.setToken(_TokenBusinessSupport_.getToken());
		// 设定提示信息
		result.setResult(0, "Guester OK");
		// 输出结果日志
		logger.debug("result=====>>>>" + result.toString());
		return result;
	}
}
