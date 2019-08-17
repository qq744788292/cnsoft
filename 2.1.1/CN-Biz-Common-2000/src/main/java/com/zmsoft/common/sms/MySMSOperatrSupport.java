package com.zmsoft.common.sms;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.zmsoft.config.system.MySMSConfig;
import org.zmsoft.framework.beans.SMSBean;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.HttpServiceHelper;

/**
 * 短信发送
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("MySMSOperatrSupport")
public class MySMSOperatrSupport extends MyTokenCommonSupport {

	@Resource
	MySMSConfig mySMSConfig;

	public static void main(String[] args) throws Exception {
		MySMSOperatrSupport oo = new MySMSOperatrSupport();
		oo.doSendSMS("15057177411", "fcyaaaaa");
	}

	public void doSendSMS(String mobile, String msg, String model) throws Exception {
		SMSBean sms = new SMSBean();
		sms.setMobile(mobile);
		sms.setMsg(String.format(model, msg));
		doSendSMS(sms);
	}

	public void doSendSMS(String mobile, String msg) throws Exception {
		SMSBean sms = new SMSBean();
		sms.setMobile(mobile);
		sms.setMsg(msg);
		doSendSMS(sms);
	}

	public void doSendSMS(SMSBean sms) throws Exception {
		logger.debug("sms===>>>>" + sms.toString());

		String result = HttpServiceHelper.doHttpPOST(mySMSConfig.getServiceURL(), sms);
		logger.debug(result);
	}
}
