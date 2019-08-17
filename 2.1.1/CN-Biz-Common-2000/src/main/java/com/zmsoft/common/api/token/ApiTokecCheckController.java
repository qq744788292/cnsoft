package com.zmsoft.common.api.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyControllerSupport;

/**
 * 用户首页
 * 
 * @author spookfcy
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/token", method = { RequestMethod.POST })
public class ApiTokecCheckController extends MyControllerSupport {

	/**
	 * 用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public RESTResultBean<String> userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new RESTResultBean<>();
	}
}
