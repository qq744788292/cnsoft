package org.zmsoft.framework.common;

/**
 * 消息发送
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public interface ISSend {

	/**
	 * 消息发送
	 * 
	 * @param dataMsg
	 *            JSON字符串
	 */
	public void flush(String dataMsg);
}