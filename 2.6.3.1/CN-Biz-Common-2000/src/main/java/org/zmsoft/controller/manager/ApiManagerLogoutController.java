package org.zmsoft.controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyTokenCommonSupport;

/**
 * 用户登录
 * 
 * @author ZMSoft
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/manager/1.0/user", method = { RequestMethod.POST })
public class ApiManagerLogoutController extends MyTokenCommonSupport {

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public RESTResultBean<UserBean> userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RESTResultBean<UserBean> result = new RESTResultBean<UserBean>();
		doDestroyedLogin(request, response);
		return result;
	}
	
}
