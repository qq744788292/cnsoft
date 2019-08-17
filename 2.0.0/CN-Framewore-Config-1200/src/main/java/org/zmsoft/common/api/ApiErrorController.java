package org.zmsoft.common.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyTokenCommonSupport;

/**
 * 默认用户登录管理
 * 
 * @author FCY
 * @version 2.0.1 2018/7/8 新建
 * @since 2.0.1 2018/7/8
 */
@Controller
public class ApiErrorController extends MyTokenCommonSupport {

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean<String> error(HttpServletRequest request) throws Exception {
		// 定义返回结果集合
		RESTResultBean<String> result = new RESTResultBean<String>();

		result.setResult(SYSTEM_TOKEN_ERROR_CODE, MESSAGE_TOKEN_ERROR);

		return result;
	}
}
