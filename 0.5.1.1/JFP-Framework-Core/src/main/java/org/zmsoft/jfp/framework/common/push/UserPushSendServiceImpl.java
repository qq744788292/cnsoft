package org.zmsoft.jfp.framework.common.push;

import org.zmsoft.jfp.framework.beans.pub.PushBean;
import org.zmsoft.jfp.framework.common.CommonChannelConfig;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.constants.pub.IPushConstant;
import org.zmsoft.jfp.framework.support.common.ISPhonePushSupport;

/**
 * 短信发送SDK
 * 
 * @author zmsoft
 * @version 2.4.1 2015/8/15
 * @version 2.3.0 2015/6/15
 * @since 2.3.0
 * 
 */
public class UserPushSendServiceImpl extends CommonChannelConfig implements ISPhonePushSupport, IPushConstant, IFrameworkConstants {

	public UserPushSendServiceImpl() {
		this(ISPhonePushSupport.CONFIG_KEY);
	}

	public UserPushSendServiceImpl(String key) {
		this.channelKey = key;
	}

	/**
	 * 消息推送
	 */
	@Override
	public boolean push(PushBean push) {
		logger.debug(push.toString());

		if (SYSTEM_NAME.equals(push.getCompanyId()))
			push.setSourceCmp(ONE);
		else
			push.setSourceCmp(TWO);

		if (catchService == null)
			logger.error(">>>>>缓存服务没有定义...xxx");
		else {
			catchService.addObjectInList(channelKey, push, false);
		}
		return true;
	}
}
