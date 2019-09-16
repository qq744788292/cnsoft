package org.zmsoft.jfp.manager.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.user.LoginerBean;
import org.zmsoft.jfp.framework.beans.user.UserBean;
import org.zmsoft.jfp.framework.cache.session.SessionHelper;
import org.zmsoft.jfp.framework.support.MyTokenCommonSupport;
import org.zmsoft.jfp.framework.token.TokenBusinessSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.DateHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.framework.utils.HttpRequestHelper;

/**
 * 运维人员登录
 * 
 * @author zmsoft
 * @version 0.0.1 2017/03/15
 * @since 0.0.1 2017/03/15
 */
@Controller
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ManagerLoginController extends MyTokenCommonSupport {

	/**
	 * 用户退出登录
	 * 
	 * @param request
	 * @param response
	 * @param loginer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ManagerHome", method = RequestMethod.GET)
	public ModelAndView doManagerHomeGET(HttpServletRequest request, HttpServletResponse response, String token) throws Exception {
		ModelAndView model = new ModelAndView("redirect:/");
		return model;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 用户登录页面
	 * 
	 * @param request
	 * @param response
	 * @param loginer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ManagerLogin", method = RequestMethod.GET)
	public ModelAndView doManagerLogin(HttpServletRequest request, HttpServletResponse response, String message, LoginerBean loginer) throws Exception {

		ModelAndView model = new ModelAndView("/main/login");
		model.addObject("DDD", DateHelper.currentTimeMillisCN1());
		model.addObject("loginer", loginer);
		model.addObject("message", message);

		return model;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ManagerLogin", method = RequestMethod.POST)
	public ModelAndView doLogin99991001POST(HttpServletRequest request, HttpServletResponse response, String token, UserBean loginer) throws Exception {
		ModelAndView model = new ModelAndView("/main/home");
		// 运维人员登录
		ManagerLoginBiz ManagerAdminLoginBiz_ = BeanFactoryHelper.getBean("ManagerLoginBiz");
		// 制作Token
		TokenBusinessSupport tokenBean = new TokenBusinessSupport();
		tokenBean.setSessionId(request.getSession().getId());
		tokenBean.setIpAdress(HttpRequestHelper.getClientRemoteIPAddr(request, false));
		tokenBean.setClientTimestamp();
		tokenBean.setMyCacheService(myCacheService);

		// 开始登录过程
		RESTResultBean<String> result = ManagerAdminLoginBiz_.doLogIn(tokenBean, loginer);

		if (!ZERO.equals(result.getCode())) {
			return doManagerLogin(request, response, result.getMsg(), loginer);
		}

		model.addObject("DDD", DateHelper.currentTimeMillis2());
		model.addObject("MENU", result.getData());
		model.addObject("token", tokenBean.getToken());
		model.addObject("loginer", tokenBean.currentUser());

		return model;
	}

	/**
	 * 用户退出登录
	 * 
	 * @param request
	 * @param response
	 * @param loginer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ManagerLogout", method = RequestMethod.GET)
	public ModelAndView doLogoutGET(HttpServletRequest request, HttpServletResponse response, String token) throws Exception {
		return doLogoutPOST(request, response, token);
	}

	/**
	 * 用户退出登录
	 * 
	 * @param request
	 * @param response
	 * @param loginer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ManagerLogout", method = RequestMethod.POST)
	public ModelAndView doLogoutPOST(HttpServletRequest request, HttpServletResponse response, String token) throws Exception {

		if (EmptyHelper.isNotEmpty(token) && doCheckToken(token)) {
			SessionHelper.sessionDestroyed();
		}

		ModelAndView model = new ModelAndView("redirect:/");
		return model;
	}

}
