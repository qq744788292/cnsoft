package org.zmsoft.controller.partner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICCacheConstants;
import org.zmsoft.framework.support.MyTokenCommonSupport;

/**
 * 用户登出
 * 
 * @author ZMSoft
 * @version 2.0.1 2018/7/8 新建
 * @since 2.0.1 2018/7/8
 * @see <ApiUserInfoController>,<LCJUserLoginController>,<WxIndexController>
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/1.0/partner", method = { RequestMethod.POST })
public class ApiPartnerLogoutController extends MyTokenCommonSupport implements ICCacheConstants {

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public RESTResultBean<UserBean> userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RESTResultBean<UserBean> result = new RESTResultBean<UserBean>();
		doDestroyedLogin(request, response);
		return result;
	}
}
