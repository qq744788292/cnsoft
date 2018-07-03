package org.zmsoft.jfp.framework.common.push;

import org.zmsoft.jfp.framework.beans.pub.PushBean;
import org.zmsoft.jfp.framework.common.CommonChannelConfig;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.constants.pub.IPushConstant;
import org.zmsoft.jfp.framework.support.common.ISPhonePushSupport;

/**
 * 短信发送SDK
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public class UserPushSendServiceImpl extends CommonChannelConfig implements ISPhonePushSupport, IPushConstant, IFrameworkConstants {

	public UserPushSendServiceImpl() {
		this.channelKey = CONFIG_KEY;
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

		if (myCacheService == null)
			logger.error(">>>>>缓存服务没有定义...xxx");
		else {
			myCacheService.addObjectInList(channelKey, push, false);
		}
		return true;
	}
}
