package com.zmsoft.common.log.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.common.ILog;
import org.zmsoft.framework.support.MyFrameWorkSupport;
import org.zmsoft.framework.utils.HttpServiceHelper;

/**
 * 日志结构体
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("LogDataApiSupport")
public class LogDataApiSupport extends MyFrameWorkSupport implements ILog {

	// @Value("${logData.logServiceURL}")
	private String logServiceURL = "";

	/**
	 * 日志输出
	 * 
	 * @throws Exception
	 */
	@Async
	public void flush(String logMsg) {
		try {
			HttpServiceHelper.doHttpPOST(logServiceURL, logMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
