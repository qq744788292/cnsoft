package org.zmsoft.jfp.framework.support;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.pub.SMSBean;

/**
 * 短信发送网关
 * @author zmsoft
 * @version 2.4.1 2015/9/9
 * @version 2.1.3 2015/4/23
 * @since 2.1.3
 *
 */
public interface ISSMSGatewaySupport {

	/**
	 * 短信发送
	 * @param message
	 * @return
	 */
	public RESTResultBean<SMSBean> doSend(SMSBean message);
}
