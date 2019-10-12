package org.zmsoft.common.mail;

import org.springframework.stereotype.Component;
import org.zmsoft.common.mq.MyMQOperatrSupport;
import org.zmsoft.config.mail.MailConfigService;
import org.zmsoft.framework.beans.MailBean;
import org.zmsoft.framework.common.ISSend;
import org.zmsoft.framework.constants.ICBussinessConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

/**
 * 短信发送
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("MyMailOperatrSupport")
public class MyMailOperatrSupport extends MyTokenCommonSupport  implements ICBussinessConstants {

	private MailConfigService myMailConfig;
	public MailConfigService getMyMailConfig() {
		if (EmptyHelper.isEmpty(myMailConfig))
			myMailConfig = MyBeanFactoryHelper.getBean(MailConfigService.class);
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
			MyMQOperatrSupport sender = MyBeanFactoryHelper.getBean(MyMQOperatrSupport.class);
			if (EmptyHelper.isNotEmpty(sender))
				sender.pushValue(MQ_TOPIC_MAIL, mail.toString());
		} else {
			ISSend sender = MyBeanFactoryHelper.getBean(getMyMailConfig().getSupport());
			if (EmptyHelper.isNotEmpty(sender))
				sender.flush(JSON.toJSONString(mail));
		}

	}
}
