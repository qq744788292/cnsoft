package org.zmsoft.controller.sms;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.common.sms.MySMSOperatrSupport;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICBussinessConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.service.player.ISPlayerSMSCodeService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/common", method = { RequestMethod.GET, RequestMethod.POST })
public class ApiCheckCodeSMSController extends MyTokenCommonSupport implements ICBussinessConstants {

	@Resource
	private MySMSOperatrSupport mySMSOperatrSupport;

	private ISPlayerSMSCodeService myPlayerSMSCodeService;

	public ISPlayerSMSCodeService getMyPlayerSMSCodeService() {
		if (EmptyHelper.isEmpty(myPlayerSMSCodeService))
			myPlayerSMSCodeService = MyBeanFactoryHelper.getBean(ISPlayerSMSCodeService.class);
		return myPlayerSMSCodeService;
	}

	/**
	 * 显示一张图片
	 * 
	 * @param id
	 * @param token
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/smscode", method = { RequestMethod.GET, RequestMethod.POST })
	public RESTResultBean<String> doSMSCode(HttpServletRequest request, HttpServletResponse response, UserBean user) throws Exception {
		String phone = user.getUserPhone();
		String msg = getMyPlayerSMSCodeService().loadCheckCodeMessage(phone);

		mySMSOperatrSupport.doSendSMS(msg, phone);

		RESTResultBean<String> result = new RESTResultBean<String>();
		result.setData(msg);
		return result;
	}

}
