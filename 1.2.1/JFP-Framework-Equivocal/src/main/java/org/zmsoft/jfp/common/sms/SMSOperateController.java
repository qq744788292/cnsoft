package org.zmsoft.jfp.common.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.security.code.SecurityCodeHelper;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SMS资源管理
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SMSOperateController extends MyControllerSupport {

	/**
	 * 发送验证码
	 * 
	 * @param jobId
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/99999090", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean<String> putObject(HttpServletRequest request, HttpServletResponse response,String token, String phone) {
		RESTResultBean<String> result = new RESTResultBean<String>();
		result.setJobId(token);
		try {
			if (EmptyHelper.isNotEmpty(phone)/*&&checkSecurityCode(request,response)*/) {
				if(EmptyHelper.isEmpty(SecurityCodeHelper.loadRandomCode(phone))){//判断是否发送
					String code = SecurityCodeHelper.makeRandomNumCode(300, 4, phone);
					String content = String.format(SMSOperatrHelper.Player_Model, code);
					SMSOperatrHelper.doSendSMS(phone, content);
					result.setMsg("短信发送成功");
				}else{
					result.setMsg("短信发送成功,五分钟内有效");
				}
			} else {
				result.setCode(ONE);
				result.setMsg("输入不正确");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			result.setCode(ONE);
			result.setMsg("短信发送失败");
		}
		logger.debug(result.toString());
		return result; // 返回result对象给前端
	}

}
