package org.ishome.jfp.framework.constants;
/** 
 * 短信发送定义
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public interface ISSMSConstants {
	public final static String SMS_SEND_SUCCESS = "发送成功";
	public final static String SMS_SEND_FAIL = "发送失败";
	//发送状态
	public final static String SEND_STATUS_WAIT = "0";//等待发送
	public final static String SEND_STATUS_ALREADY = "1";//已发送
	public final static String SEND_STATUS_SUCCESS = "2";//发送成功
	public final static String SEND_STATUS_FAIL = "9";//发送失败
	
	//网关状态
	public final static String NETWORK_STATUS_OPEN = "0";//使用中
	public final static String NETWORK_STATUS_CLOSE = "1";//关闭
	

}
