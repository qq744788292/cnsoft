package com.zmsoft.amms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.jfp.framework.support.MyTokenCommonSupport;
import org.zmsoft.jfp.framework.utils.DateHelper;
import org.zmsoft.jfp.framework.utils.HttpRequestHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 管理后台
 * 
 * @author zmsoft
 * @version 0.0.1 2017/09/11
 * @since 0.0.1 2017/09/11
 */
@Controller
public class HomeIndexController extends MyTokenCommonSupport {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView doHomeIndexGET(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return doHomeIndexPOST(request, response);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView doHomeIndexPOST(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("/index");
		model.addObject("host", HttpRequestHelper.getServerAddr(request));
		model.addObject("ddd", DateHelper.currentTimeMillis2());
		model.addObject("domainName", HttpRequestHelper.getDomainName(request));
		return model;
	}
}
