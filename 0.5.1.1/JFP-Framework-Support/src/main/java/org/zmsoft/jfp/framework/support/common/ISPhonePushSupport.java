package org.zmsoft.jfp.framework.support.common;

import org.zmsoft.jfp.framework.beans.pub.PushBean;

/**
 * 手机推送
 * @author zmsoft
 * @since 2.1.2
 * @version 2.1.2.20150417
 */
public interface ISPhonePushSupport {
	public static final String CONFIG_KEY = "JFP:PUSHLIST";	

	/**
	 * 消息推送到手机
	 * @return
	 */
	public boolean push(PushBean push);

}
