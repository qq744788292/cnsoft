package org.zmsoft.framework.log;

import org.springframework.stereotype.Component;
import org.zmsoft.framework.common.ISLog;
import org.zmsoft.framework.constants.ICBussinessConstants;
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
 * @see <RemoteLogServiceName>
 */
@Component("LogDataSupport")
public class LogDataSupport extends MyFrameWorkSupport implements ICBussinessConstants {
	private LogDataBean logData = null;

	public void setLogData(LogDataBean logData) {
		this.logData = logData;
	}

	/**
	 * 日志输出
	 * 
	 * @throws Exception
	 */
	public void flush() {
		String log;
		// 日志内容输出制作
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
		ISLog remoteLog = MyBeanFactoryHelper.getBean(RemoteLogServiceName);
		if (EmptyHelper.isNotEmpty(remoteLog)) {
			remoteLog.flush(logData);
		}
	}

}
