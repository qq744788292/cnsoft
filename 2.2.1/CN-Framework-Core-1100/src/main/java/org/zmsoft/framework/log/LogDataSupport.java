package org.zmsoft.framework.log;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.common.ILog;
import org.zmsoft.framework.constants.IBussinessConstants;
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
@Component("LogDataSupport")
public class LogDataSupport extends MyFrameWorkSupport implements IBussinessConstants {
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

		// 开启本地输出
		if (logger.isDebugEnabled())
			logger.debug(log);

		// 激活log输出
		ILog remoteLog = MyBeanFactoryHelper.getBean(RemoteLogServiceName);
		if (EmptyHelper.isNotEmpty(remoteLog)) {
			remoteLog.flush(log);
		}
	}

}
