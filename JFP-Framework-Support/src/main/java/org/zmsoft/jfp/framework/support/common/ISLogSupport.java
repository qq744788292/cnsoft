package org.zmsoft.jfp.framework.support.common;

import org.zmsoft.jfp.framework.beans.pub.LogBean;

/**
 * 日志记录类
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISLogSupport {

	public static final String CONFIG_KEY = "JFP:LOGLIST";
	public static final int LOG_TYPE_LOGIN = 0;
	public static final int LOG_TYPE_OPERATION = 1;

	/**
	 * 日志记录
	 * 
	 * @param log 日志信息
	 * @param logType
	 * @return 保存结果
	 * @see LOG_TYPE_LOGIN<ISLogSupport>、LOG_TYPE_OPERATION<ISLogSupport>
	 */
	public boolean save(LogBean log, int logType);

}
