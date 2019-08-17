package com.zmsoft.common.sms;

import org.springframework.stereotype.Component;
import org.zmsoft.config.system.MySMSConfig;
import org.zmsoft.framework.beans.SMSBean;
import org.zmsoft.framework.common.ISend;
import org.zmsoft.framework.constants.IBussinessConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;
import com.zmsoft.common.mq.MyMqOperatrSupport;

/**
 * 短信发送
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("MySMSOperatrSupport")
public class MySMSOperatrSupport extends MyTokenCommonSupport implements IBussinessConstants {

	private MySMSConfig mySMSConfig;
	public MySMSConfig getMySMSConfig() {
		if (EmptyHelper.isEmpty(mySMSConfig))
			mySMSConfig = MyBeanFactoryHelper.getBean(MySMSConfig.class.getSimpleName());
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
		if (ONE.equals(getMySMSConfig().getType())) {
			MyMqOperatrSupport sender = MyBeanFactoryHelper.getBean(MyMqOperatrSupport.class.getSimpleName());
			if (EmptyHelper.isNotEmpty(sender))
				sender.addObjectInList(MQ_TOPIC_SMS, sms);
		} else {
			ISend sender = MyBeanFactoryHelper.getBean(getMySMSConfig().getSupport());
			if (EmptyHelper.isNotEmpty(sender))
				sender.flush(JSON.toJSONString(sms));
		}

	}

}
