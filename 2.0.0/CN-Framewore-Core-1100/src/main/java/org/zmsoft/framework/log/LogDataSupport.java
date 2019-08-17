package org.zmsoft.framework.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.utils.DateHelper;
import org.zmsoft.framework.utils.HttpServiceHelper;

/**
 * 日志结构体
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("LogDataSupport")
public class LogDataSupport implements IFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${spring.application.name}")
	private String serviceName;// 服务名称（项目名称）

	@Value("${logData.logServiceURL}")
	private String logServiceURL = "http://183.134.76.127:8810/log/append";

	@Value("${logData.logEnable}")
	private boolean logEnable = false;

	LogDataBean logData = null;

	public void setLogData(LogDataBean logData) {
		this.logData = logData;
	}

	/**
	 * 日志输出
	 * 
	 * @throws Exception
	 */
	@Async
	public void flush() throws Exception {
		String log;
		try {
			logData.setServiceName(serviceName);
			logData.setLogTimestamp(DateHelper.currentTimeMillisCN5());
			log = logData.toString();
		} catch (Exception e) {
			logData.setLogData(EMPTY);
			log = logData.toString();
		}

		if (logEnable)
			HttpServiceHelper.doHttpPOST(logServiceURL, log);
		if (logger.isDebugEnabled())
			logger.debug(log);
	}

	///////////////////////////////////////////////////////

}
