package org.jfpc.framework.log.mq;

import org.jfpc.framework.mq.ISMQReceiveSupport;

/**
 * 队列处理程序
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
public class MyLogQueryListener implements ISMQReceiveSupport {
	
	/**
	 * 消息接受处理
	 */
	public void handleMessage(String message) {
		//数据库插入
		System.out.println("<<<=LogQuery=====" + message);
	}

}
