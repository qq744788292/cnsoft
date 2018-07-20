package org.zmsoft.jfp.framework.biz;

/**
 * 接收数据
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public interface ISSend {

	/**
	 * 数据发送
	 */
	boolean doSend() throws Exception;
//	/**
//	 * 数据发送
//	 */
//	void doSend(String hosId, Object data) throws Exception;
}
