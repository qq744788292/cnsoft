package com.zmsoft.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.login.ISManagerLoginService;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import com.zmsoft.common.constants.IBussinessConstants;

/**
 * 用户登录
 * 
 * @author spookfcy
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/manager/home", method = { RequestMethod.POST })
public class ApiManagerLoginController extends MyTokenCommonSupport implements IBussinessConstants{

	ISManagerLoginService myManagerLoginService;

	public ISManagerLoginService getMyManagerLoginService() {
		if (EmptyHelper.isEmpty(myManagerLoginService))
			myManagerLoginService = MyBeanFactoryHelper.getBean(COMMON_SERVICE_Login_Manager);
		return myManagerLoginService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RESTResultBean<UserBean> userLogin(HttpServletRequest request, HttpServletResponse response, LoginerBean loginer) throws Exception {
		return getMyManagerLoginService().doLogin(request, response, loginer);
	}

}
