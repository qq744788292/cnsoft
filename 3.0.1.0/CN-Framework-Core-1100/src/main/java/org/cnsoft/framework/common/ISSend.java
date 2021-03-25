package org.cnsoft.framework.common;

import org.cnsoft.framework.core.ISSystem;

/**
 * 消息发送
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public interface ISSend extends ISSystem {

	/**
	 * 消息发送
	 * 
	 * @param dataMsg
	 *            JSON字符串
	 */
	public void flush(String dataMsg);
}