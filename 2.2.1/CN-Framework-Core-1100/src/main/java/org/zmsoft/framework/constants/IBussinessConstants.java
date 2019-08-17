package org.zmsoft.framework.constants;

/**
 * 业务常量
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface IBussinessConstants {

	/**
	 * 消息队列Topic常量定义
	 */
	public final static String MQ_TOPIC_SMS = "MQ_TOPIC_SMS";
	public final static String MQ_TOPIC_MAIL = "MQ_TOPIC_MAIL";
	public final static String MQ_TOPIC_WEIXIN = "MQ_TOPIC_WEIXIN";
	
	//第三方日志采集
	public final static String RemoteLogServiceName = "RemoteLogServiceName";
	/**
	 * 用户首页业务
	 */
	public final static String COMMON_SERVICE_ManagerHome = "ISManagerHomeService";

	/**
	 * 用户登录业务
	 */
	public final static String COMMON_SERVICE_Login_Manager = "ISManagerLoginService";
	public final static String COMMON_SERVICE_Login_Partner = "ISPartnerLoginService";
	public final static String COMMON_SERVICE_Login_Player = "ISPlayerLoginService";
}
