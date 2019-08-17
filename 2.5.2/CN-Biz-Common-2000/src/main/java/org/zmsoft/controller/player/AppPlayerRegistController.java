package org.zmsoft.controller.player;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICBussinessConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.service.player.ISPLayerRegistService;

/**
 * 用户注册
 * 
 * @author spookfcy
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/1.0/player", method = { RequestMethod.POST })
public class AppPlayerRegistController extends MyTokenCommonSupport implements ICBussinessConstants {

	ISPLayerRegistService myPLayerRegistService;

	public ISPLayerRegistService getMyPLayerRegistService() {
		if (EmptyHelper.isEmpty(myPLayerRegistService))
			myPLayerRegistService = MyBeanFactoryHelper.getBean(ISPLayerRegistService.class.getSimpleName());
		return myPLayerRegistService;
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @param loginer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public RESTResultBean<String> regist(HttpServletRequest request, HttpServletResponse response, LoginerBean loginer) throws Exception {
		return getMyPLayerRegistService().doRegist(request, response, loginer);
	}

}
