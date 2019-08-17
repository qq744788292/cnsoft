package com.zmsoft.common.log.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.common.ILog;
import org.zmsoft.framework.log.LogDataBean;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyFrameWorkSupport;
import org.zmsoft.framework.utils.DateHelper;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 日志结构体
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("LogDataRemoteSupport")
public class LogDataRemoteSupport extends MyFrameWorkSupport {

	@Value("${logData.remoteLogServiceName}")
	private String remoteLogServiceName;

	@Value("${logData.logEnable}")
	private boolean logEnable = false;

	private LogDataBean logData = null;

	public void setLogData(LogDataBean logData) {
		this.logData = logData;
	}

	/**
	 * 日志输出
	 * 
	 * @throws Exception
	 */
	@Async("threadPoolTaskExecutor")
	public void flush() {
		String log;
		try {
			logData.setAppName(getMyAppName());
			logData.setLogTimestamp(DateHelper.currentTimeMillisCN5());
			log = logData.toString();
		} catch (Exception e) {
			logData.setLogData(EMPTY);
			log = logData.toString();
		}
		
		//开启本地输出
		if (logger.isDebugEnabled())
			logger.debug(log);

		// 激活log输出
		logger.error("remoteLogServiceName==="+remoteLogServiceName);
		if (EmptyHelper.isNotEmpty(remoteLogServiceName)) {
			ILog remoteLog = MyBeanFactoryHelper.getBean(remoteLogServiceName);
			logger.error("remoteLog==="+remoteLogServiceName);
			if (EmptyHelper.isNotEmpty(remoteLog)){
				logger.error("log==="+log);
				remoteLog.flush(log);
			}
		}
	}

}
