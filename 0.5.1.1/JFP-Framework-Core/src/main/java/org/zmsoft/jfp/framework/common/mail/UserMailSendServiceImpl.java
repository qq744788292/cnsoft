package org.zmsoft.jfp.framework.common.mail;

import org.zmsoft.jfp.framework.beans.pub.MailBean;
import org.zmsoft.jfp.framework.common.CommonChannelConfig;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.constants.pub.IMailConstants;
import org.zmsoft.jfp.framework.support.common.ISMailSupport;

/**
 * 邮件发送SDK
 * 
 * @author zmsoft
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 * 
 */
public class UserMailSendServiceImpl extends CommonChannelConfig implements ISMailSupport, IMailConstants, IFrameworkConstants {
	public UserMailSendServiceImpl() {
		this(ISMailSupport.CONFIG_KEY);
	}

	public UserMailSendServiceImpl(String key) {
		this.channelKey = key;
	}

	@Override
	public boolean send(MailBean mial) {
		logger.debug(mial.toString());

		if (catchService == null)
			logger.error(">>>>>缓存服务没有定义...xxx");
		else {
			catchService.addObjectInList(channelKey, mial, false);
		}
		return true;
	}

}
