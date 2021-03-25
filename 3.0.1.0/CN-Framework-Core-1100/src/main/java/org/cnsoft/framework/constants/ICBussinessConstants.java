package org.cnsoft.framework.constants;

/**
 * 业务常量
 *
 * @author CNSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ICBussinessConstants {

	/**
	 * 消息队列常量定义-短信
	 */
	public final static String MQ_TOPIC_SMS = "MQ_TOPIC_SMS";
	/**
	 * 消息队列常量定义-邮件
	 */
	public final static String MQ_TOPIC_MAIL = "MQ_TOPIC_MAIL";
	/**
	 * 消息队列常量定义-微信用户
	 */
	public final static String MQ_TOPIC_WEIXIN_UserInfo = "MQ_TOPIC_WEIXIN_UserInfo";

	/**
	 * 用户首页业务业务名称
	 */
	public final static String COMMON_SERVICE_ManagerHome = "ISManagerHomeService";

	/**
	 * 管理员登录业务业务名称
	 */
	public final static String COMMON_SERVICE_Login_Manager = "ISManagerLoginService";
	/**
	 * 合作单位登录业务业务名称
	 */
	public final static String COMMON_SERVICE_Login_Partner = "ISPartnerLoginService";
	/**
	 * 玩家登录业务业务名称
	 */
	public final static String COMMON_SERVICE_Login_Player = "ISPlayerLoginService";
}
