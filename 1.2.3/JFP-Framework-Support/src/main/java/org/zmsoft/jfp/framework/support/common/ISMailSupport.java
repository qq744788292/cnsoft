package org.zmsoft.jfp.framework.support.common;

import org.zmsoft.jfp.framework.beans.pub.MailBean;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/**
 * 邮件发送类
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISMailSupport {

	public static final String CONFIG_KEY = "JFP:MAILLIST";
	
	/**
	 * 邮件发送
	 * @see IFrameworkConstants.SEMICOLON
	 * @param companyId 企业ID
	 * @param phoneNum 手机号码
	 * @param message 短信内容
	 * @return 发送结果
	 */
	public boolean send(MailBean mial);
	
}
