package com.zmsoft.controller.doc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
public class PageDocIndexController extends MyTokenCommonSupport {
	
	@RequestMapping(value = "/doc", method = RequestMethod.GET)
	public ModelAndView doDoc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("/common/doc");

		// 开始登录过程
		TokenBusinessSupport _TokenBusinessSupport_ = TokenBusinessSupport.defaultToken(request);
		_TokenBusinessSupport_.setMyCacheService(myCacheService);
		_TokenBusinessSupport_.saveToken();

		// 输出结果日志
		model.addObject("token", _TokenBusinessSupport_.getToken());
		return model;
	}

}
