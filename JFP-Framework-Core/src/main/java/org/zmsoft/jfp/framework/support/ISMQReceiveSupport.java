package org.zmsoft.jfp.framework.support;

/**
 * MQ接受消息处理
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISMQReceiveSupport {

	/**
	 * 接受信息
	 * @param message
	 */
	public void handleMessage(String message);
}
