package org.zmsoft.framework.common;

import org.zmsoft.framework.log.LogDataBean;

/**
 * 日志输出
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public interface ISLog {

	public final static String LOG_KEY = "zm_log";

	/**
	 * 日志输出
	 * 
	 * @throws Exception
	 */
	public void flush(LogDataBean logData);
}