package org.zmsoft.jfp.framework.common.mail;

import org.zmsoft.jfp.framework.beans.pub.MailBean;
import org.zmsoft.jfp.framework.common.CommonChannelConfig;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.constants.pub.IMailConstants;
import org.zmsoft.jfp.framework.support.common.ISMailSupport;

/**
 * 邮件发送SDK
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public class UserMailSendServiceImpl extends CommonChannelConfig implements ISMailSupport, IMailConstants, IFrameworkConstants {
	public UserMailSendServiceImpl() {
		this.channelKey = CONFIG_KEY;
	}

	@Override
	public boolean send(MailBean mial) {
		logger.debug(mial.toString());

		if (myCacheService == null)
			logger.error(">>>>>缓存服务没有定义...xxx");
		else {
			myCacheService.addObjectInList(channelKey, mial, false);
		}
		return true;
	}

}
