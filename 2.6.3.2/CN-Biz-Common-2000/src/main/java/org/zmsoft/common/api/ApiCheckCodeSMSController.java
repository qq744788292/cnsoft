package org.zmsoft.common.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.common.service.MySMSOperatorSupport;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICBussinessConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.service.player.ISPlayerSMSCodeService;

/**
 * 短信验证码
 * @author Fcy
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/common/1.0/sms", method = { RequestMethod.GET, RequestMethod.POST })
public class ApiCheckCodeSMSController extends MyTokenCommonSupport implements ICBussinessConstants {

	@Resource
	private MySMSOperatorSupport MySMSOperatorSupport;

	private ISPlayerSMSCodeService myPlayerSMSCodeService;

	public ISPlayerSMSCodeService getMyPlayerSMSCodeService() {
		if (EmptyHelper.isEmpty(myPlayerSMSCodeService))
			myPlayerSMSCodeService = MyBeanFactoryHelper.getBean(ISPlayerSMSCodeService.class);
		return myPlayerSMSCodeService;
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param roolType 验证码分类
	 * @param token
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/code", method = { RequestMethod.GET, RequestMethod.POST })
	public RESTResultBean<String> doSMSCode(HttpServletRequest request, HttpServletResponse response, UserBean user) throws Exception {
		String phone = user.getUserPhone();
		String msg = getMyPlayerSMSCodeService().loadCheckCodeMessage(user);

		MySMSOperatorSupport.doSendSMS(msg, phone);

		RESTResultBean<String> result = new RESTResultBean<String>();
		result.setData(msg);
		return result;
	}

}
