package org.cnsoft.framework.common;

import org.cnsoft.framework.core.ISSystem;
import org.cnsoft.framework.log.LogDataBean;

/**
 * 日志输出
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public interface ISLog extends ISSystem {

	public final static String LOG_KEY = "zm_log";

	/**
	 * 日志输出
	 * 
	 * @throws Exception
	 */
	public void flush(LogDataBean logData);
}