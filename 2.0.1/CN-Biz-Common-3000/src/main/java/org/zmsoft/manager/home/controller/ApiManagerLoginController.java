package org.zmsoft.manager.home.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.manager.home.service.ISManagerLoginService;

/**
 * 用户登录
 * 
 * @author spookfcy
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/manager/user", method = { RequestMethod.POST })
public class ApiManagerLoginController extends MyTokenCommonSupport {

	@Resource
	ISManagerLoginService myManagerLoginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RESTResultBean<UserBean> userLogin(HttpServletRequest request, HttpServletResponse response, LoginerBean loginer) throws Exception {
		return myManagerLoginService.doLogin(request, response, loginer);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public RESTResultBean<UserBean> userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RESTResultBean<UserBean> result = new RESTResultBean<UserBean>();
		doDestroyedLogin(request, response);
		return result;
	}
	
}
