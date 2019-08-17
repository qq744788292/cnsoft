package com.zmsoft.controller.home.player;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.IBussinessConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyControllerSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import com.zmsoft.service.player.ISPlayerHomeService;

/**
 * 用户首页
 * 
 * @author spookfcy
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/1.0/player/home", method = { RequestMethod.POST })
public class ApiPlayerHomeController extends MyControllerSupport implements IBussinessConstants {
	ISPlayerHomeService myPlayerHomeService;

	public ISPlayerHomeService getMyPlayerHomeService() {
		if (EmptyHelper.isEmpty(myPlayerHomeService))
			myPlayerHomeService = MyBeanFactoryHelper.getBean(ISPlayerHomeService.class.getSimpleName());
		return myPlayerHomeService;
	}

	/**
	 * 用户登陆信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public RESTResultBean<UserBean> doUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getMyPlayerHomeService().doUserInfo();
	}
}
