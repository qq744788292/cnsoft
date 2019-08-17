package com.zmsoft.controller.home.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyControllerSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import com.zmsoft.common.constants.IBussinessConstants;
import com.zmsoft.service.manager.ISManagerHomeService;

/**
 * 用户首页
 * 
 * @author spookfcy
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/manager/home", method = { RequestMethod.POST })
public class ApiManagerHomeController extends MyControllerSupport implements IBussinessConstants {
	ISManagerHomeService myManagerHomeService;

	public ISManagerHomeService getMyManagerHomeService() {
		if (EmptyHelper.isEmpty(myManagerHomeService))
			myManagerHomeService = MyBeanFactoryHelper.getBean(COMMON_SERVICE_ManagerHome);
		return myManagerHomeService;
	}

	/**
	 * 用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public RESTResultBean<UserBean> doUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getMyManagerHomeService().doUserInfo();
	}

	/**
	 * 用户权限菜单
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public RESTResultBean<Map<String, String>> doUserMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getMyManagerHomeService().doUserMenu();
	}

	/**
	 * 用户权限菜单
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public RESTResultBean<Map<String, String>> doUserRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getMyManagerHomeService().doUserRole();
	}
}
