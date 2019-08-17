package org.zmsoft.manager.home.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyControllerSupport;
import org.zmsoft.manager.home.service.ISManagerHomeService;

/**
 * 用户首页
 * 
 * @author spookfcy
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/manager/user", method = { RequestMethod.POST })
public class ApiManagerHomeController extends MyControllerSupport {
	@Resource
	ISManagerHomeService myManagerHomeService;

	/**
	 * 用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public RESTResultBean<UserBean> userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return myManagerHomeService.doUserInfo();
	}

	/**
	 * 用户权限菜单
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public RESTResultBean<Map<String, String>> userMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return myManagerHomeService.doUserMenu();
	}
}
