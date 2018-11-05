package org.zmsoft.consumer.login.controller;

import java.util.List;

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
import org.zmsoft.framework.constants.ICacheConstants;
import org.zmsoft.framework.support.MyTokenCommonSupport;

import org.zmsoft.consumer.login.service.UserLoginServiceImpl;

/**
 * 置业绿城用户登录管理
 * 
 * @author FCY
 * @version 2.0.1 2018/7/8 新建
 * @since 2.0.1 2018/7/8
 * @see <ApiUserInfoController>,<LCJUserLoginController>,<WxIndexController>
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/1.0", method = { RequestMethod.POST })
public class ApiUsersLoginController extends MyTokenCommonSupport implements ICacheConstants {

	@Resource
	UserLoginServiceImpl UserLoginServiceImpl_;

	@RequestMapping(value = "/UserIndex/logout", method = RequestMethod.POST)
	public RESTResultBean<UserBean> userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RESTResultBean<UserBean> result = new RESTResultBean<UserBean>();
		doDestroyedLogin(request, response);
		return result;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param loginType 登录方式，必填：0手机号1账号
	 * @param userSource 登录来源，必填：10微信公众号11手机浏览器12苹果手机13安卓手机20绿城应用30电脑网站
	 * @param mobile 手机号
	 * @param password 密码
	 * @param captcha 验证码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/UserIndex/login", method = RequestMethod.POST)
	public RESTResultBean<UserBean> userLogin(HttpServletRequest request, HttpServletResponse response, 
			String loginType, String userSource, 
			String mobile, String password, String captcha) throws Exception {
		RESTResultBean<UserBean> result = new RESTResultBean<UserBean>();
		// 开始登录过程
		List<String> users = UserLoginServiceImpl_.checkUser(null);

		LoginerBean loginer = new LoginerBean();
		//loginer.setToken(resultZT.getString("token"));
		loginer.setLoginSource("20");
		loginer.setLoginType("99");
		loginer.setPv1(ZERO);
		result = UserLoginServiceImpl_.autoLogin(request, users, loginer);

		// 输出结果日志
		logger.debug("result=====>>>>" + result.toString());
		return result;
	}
}
