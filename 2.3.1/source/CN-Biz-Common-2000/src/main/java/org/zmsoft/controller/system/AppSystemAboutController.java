package org.zmsoft.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.VersionBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.login.ISSystemAboutService;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 系统相关
 * 
 * @author spookfcy
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/1.0/system", method = { RequestMethod.POST })
public class AppSystemAboutController extends MyTokenCommonSupport {

	ISSystemAboutService mySystemAboutService;

	public ISSystemAboutService getMySystemAboutService() {
		if (EmptyHelper.isEmpty(mySystemAboutService))
			mySystemAboutService = MyBeanFactoryHelper.getBean(ISSystemAboutService.class.getSimpleName());
		return mySystemAboutService;
	}

	/**
	 * 服务条款
	 */
	@RequestMapping(value = "/clause", method = RequestMethod.POST)
	public RESTResultBean<String> doClause(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mySystemAboutService.doClause(request, response);
	}

	/**
	 * 注册协议
	 */
	@RequestMapping(value = "/treaty", method = RequestMethod.POST)
	public RESTResultBean<String> doTreaty(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mySystemAboutService.doTreaty(request, response);
	}

	/**
	 * 关于我们
	 */
	@RequestMapping(value = "/about", method = RequestMethod.POST)
	public RESTResultBean<String> doAbout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mySystemAboutService.doAbout(request, response);
	}

	/**
	 * 版本检测
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public RESTResultBean<VersionBean> doNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mySystemAboutService.doNew(request, response);
	}

}
