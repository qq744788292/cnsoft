package org.zmsoft.controller.player;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICBussinessConstants;
import org.zmsoft.framework.login.ISPlayerLoginService;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.token.TokenBusinessSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 用户登录
 * 
 * @author spookfcy
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/1.0/player", method = { RequestMethod.POST })
public class AppPlayerLoginController extends MyTokenCommonSupport implements ICBussinessConstants{

	ISPlayerLoginService myPlayerLoginService;

	public ISPlayerLoginService getMyPlayerLoginService() {
		if (EmptyHelper.isEmpty(myPlayerLoginService))
			myPlayerLoginService = MyBeanFactoryHelper.getBean(ISPlayerLoginService.class.getSimpleName());
		return myPlayerLoginService;
	}

	/**
	 * 游客登录
	 * @param request
	 * @param response
	 * @param player
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/guest", method = RequestMethod.POST)
	public RESTResultBean<UserBean> doHomePOST(HttpServletRequest request, HttpServletResponse response,UserBean player) throws Exception {
		// 定义返回结果集合
		RESTResultBean<UserBean> result = new RESTResultBean<UserBean>();

		TokenBusinessSupport _TokenBusinessSupport_ = TokenBusinessSupport.defaultToken(request,null,player);
		_TokenBusinessSupport_.setMyCacheService(myCacheService);
		_TokenBusinessSupport_.saveToken();

		result.setToken(_TokenBusinessSupport_.getToken());
		// 设定提示信息
		result.setResult(0, "Guester OK");
		// 输出结果日志
		logger.debug("result=====>>>>" + result.toString());
		return result;
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param loginer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RESTResultBean<TokenBusinessSupport> userLogin(HttpServletRequest request, HttpServletResponse response, LoginerBean loginer) throws Exception {
		return getMyPlayerLoginService().doDefaultLogin(request, response, loginer);
	}

	/**
	 * 过期续签
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/renewal", method = RequestMethod.POST)
	public RESTResultBean<String> tokenRenewal(HttpServletRequest request, HttpServletResponse response, LoginerBean loginer) throws Exception {
		RESTResultBean<String> result = new RESTResultBean<String>();
		TokenBusinessSupport tokenBean = TokenBusinessSupport.build(loginer.getToken(), true);
		if(loginer.getUuid().equals(tokenBean.getUuid())){
			//return userService.userInfo(request,user);
		}
		result.setCode(1);
		result.setMsg("续签失败，请重新登录");
		return result;
	}
	

}
