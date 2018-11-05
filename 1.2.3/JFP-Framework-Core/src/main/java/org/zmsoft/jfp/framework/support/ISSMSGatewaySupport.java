package org.zmsoft.jfp.framework.support;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.pub.SMSBean;

/**
 * 短信发送网关
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISSMSGatewaySupport {

	/**
	 * 短信发送
	 * @param message
	 * @return
	 */
	public RESTResultBean<SMSBean> doSend(SMSBean message);
}