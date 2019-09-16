package org.zmsoft.jfp.common.total;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.jfp.framework.elk.ELKLogData;
import org.zmsoft.jfp.framework.support.MyTokenCommonSupport;
import org.zmsoft.jfp.framework.utils.DateHelper;
import org.zmsoft.jfp.framework.utils.HttpRequestHelper;
import org.zmsoft.jfp.framework.utils.PKHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户访问统计
 * 
 * @author zmsoft
 *
 */
@Controller
public class PageViewTotalController extends MyTokenCommonSupport {

	@RequestMapping(value = "/999999", method = RequestMethod.GET)
	public ModelAndView doPageViewTotal(HttpServletRequest request, HttpServletResponse response,String page) throws Exception {
		ModelAndView model = new ModelAndView("/total");
		model.addObject("DDD", DateHelper.currentTimeMillis2());
		model.addObject("token", PKHelper.creatUUKey());
		{
			ELKLogData eld = new ELKLogData("用户访问统计");

			eld.setIpAdress(HttpRequestHelper.getClientRemoteIPAddr(request));// 请求地址

			eld.setCountType(request.getRequestURI());// 统计分类;平台
			eld.setBusinessName(page);// 业务名称;

			eld.setServiceType("PageViewTotal");// 功能名称;控制层类名称
			eld.setFunctionName("doPageViewTotal");// 功能名称;控制层方法名称

			eld.loadELKLoggerAppender().debug();
		}
		return model;
	}
}
