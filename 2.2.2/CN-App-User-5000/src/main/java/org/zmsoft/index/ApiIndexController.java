package org.zmsoft.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.token.TokenBusinessSupport;

/**
 * 默认用户登录管理
 * 
 * @author FCY
 * @version 2.0.1 2018/7/8 新建
 * @since 2.0.1 2018/7/8
 */
@Controller
public class ApiIndexController extends MyTokenCommonSupport {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean<UserBean> home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 定义返回结果集合
		RESTResultBean<UserBean> result = new RESTResultBean<UserBean>();

		TokenBusinessSupport _TokenBusinessSupport_ = TokenBusinessSupport.defaultToken(request);
		_TokenBusinessSupport_.setMyCacheService(myCacheService);
		_TokenBusinessSupport_.saveToken();

		result.setToken(_TokenBusinessSupport_.getToken());
		// 设定提示信息
		result.setResult(0, "Guester OK");
		// 输出结果日志
		logger.debug("result=====>>>>" + result.toString());
		return result;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("/index");

		// 开始登录过程
		TokenBusinessSupport _TokenBusinessSupport_ = TokenBusinessSupport.defaultToken(request);
		_TokenBusinessSupport_.setMyCacheService(myCacheService);
		_TokenBusinessSupport_.saveToken();

		// 输出结果日志
		model.addObject("token", _TokenBusinessSupport_.getToken());
		return model;
	}
}
