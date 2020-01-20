package org.zmsoft.common.service;

import org.springframework.stereotype.Component;
import org.zmsoft.config.sms.SMSConfigService;
import org.zmsoft.framework.beans.SMSBean;
import org.zmsoft.framework.common.ISSend;
import org.zmsoft.framework.constants.ICBussinessConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 短信发送
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("MySMSOperatorSupport")
public class MySMSOperatorSupport extends MyTokenCommonSupport implements ICBussinessConstants {

	private SMSConfigService mySMSConfig;

	public SMSConfigService getMySMSConfig() {
		if (EmptyHelper.isEmpty(mySMSConfig))
			mySMSConfig = MyBeanFactoryHelper.getBean(SMSConfigService.class);
		return mySMSConfig;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
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

	/**
	 * 消息发送实体
	 * 
	 * @param sms
	 * @throws Exception
	 */
	public void doSendSMS(SMSBean sms) throws Exception {
		logger.debug("sms===>>>>" + sms.toString());
		// 1:队列/2:接口
		if (TWO.equals(getMySMSConfig().getType())) {
			ISSend sender = MyBeanFactoryHelper.getBean(getMySMSConfig().getSupport());
			if (EmptyHelper.isNotEmpty(sender))
				sender.flush(sms.toString());
		} else {
			MyMQOperatorSupport sender = MyBeanFactoryHelper.getBean(MyMQOperatorSupport.class);
			if (EmptyHelper.isNotEmpty(sender))
				sender.pushValue(MQ_TOPIC_SMS, sms.toString());
		}
	}

}
