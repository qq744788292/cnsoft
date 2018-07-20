package org.zmsoft.jfp.framework.mq.rabbit;

import org.zmsoft.jfp.framework.support.ISMQReceiveSupport;

/**
 * 队列处理程序
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class MyQueryListener implements ISMQReceiveSupport {
	
	/**
	 * 消息接受处理
	 */
	public void handleMessage(String message) {
		//System.out.println("<<<=Query==========" + message);
	}

}
