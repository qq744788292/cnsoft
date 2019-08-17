package com.zmsoft.common.mail;

import org.springframework.stereotype.Component;
import org.zmsoft.config.system.MyMailConfig;
import org.zmsoft.framework.beans.MailBean;
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
@Component("MyMailOperatrSupport")
public class MyMailOperatrSupport extends MyTokenCommonSupport  implements IBussinessConstants {

	private MyMailConfig myMailConfig;
	public MyMailConfig getMyMailConfig() {
		if (EmptyHelper.isEmpty(myMailConfig))
			myMailConfig = MyBeanFactoryHelper.getBean(MyMailConfig.class.getSimpleName());
		return myMailConfig;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
	public void doSendMail(String mail, String msg, String model) throws Exception {
		MailBean sms = new MailBean();
		sms.setTo(mail);
		//sms.setMsg(String.format(model, msg));
		doSendMail(sms);
	}

	public void doSendMail(String mobile, String msg) throws Exception {
		MailBean sms = new MailBean();
		sms.setTo(mobile);
		sms.setText(msg);
		doSendMail(sms);
	}

	public void doSendMail(MailBean mail) throws Exception {
		logger.debug("sms===>>>>" + mail.toString());

		// 1:队列/2:接口
		if (ONE.equals(getMyMailConfig().getType())) {
			MyMqOperatrSupport sender = MyBeanFactoryHelper.getBean(MyMqOperatrSupport.class.getSimpleName());
			if (EmptyHelper.isNotEmpty(sender))
				sender.addObjectInList(MQ_TOPIC_MAIL, mail);
		} else {
			ISend sender = MyBeanFactoryHelper.getBean(getMyMailConfig().getSupport());
			if (EmptyHelper.isNotEmpty(sender))
				sender.flush(JSON.toJSONString(mail));
		}

	}
}
