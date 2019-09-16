package org.zmsoft.jfp.framework.support.common;

import org.zmsoft.jfp.framework.beans.pub.LogBean;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/**
 * 日志记录类
 * @author zmsoft
 * @since 2.1.2
 * @version 2.1.2.20150417
 */
public interface ISLogSupport {

	public static final String CONFIG_KEY = "JFP:LOGLIST";
	
	/**
	 * 短信发送
	 * @see IFrameworkConstants.SEMICOLON
	 * @param log 日志信息
	 * @return 发送结果
	 */
	public boolean send(LogBean log);
	
}
