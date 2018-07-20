package org.zmsoft.jfp.framework.support.common;

import org.zmsoft.jfp.framework.beans.pub.PushBean;

/**
 * 手机推送
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISPhonePushSupport {
	public static final String CONFIG_KEY = "JFP:PUSHLIST";	

	/**
	 * 消息推送到手机
	 * @return
	 */
	public boolean push(PushBean push);

}
