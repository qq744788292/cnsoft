package org.cnsoft.framework.log;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.common.ISLog;
import org.cnsoft.framework.constants.ICBussinessConstants;
import org.cnsoft.framework.support.MyFrameWorkSupport;
import org.cnsoft.framework.utils.DateHelper;
import org.cnsoft.framework.utils.EmptyHelper;
import org.springframework.stereotype.Component;

/**
 * 日志结构体
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("LogDataSupport")
public class LogDataSupport extends MyFrameWorkSupport implements ICBussinessConstants {

	/**
	 * 第三方日志采集业务名称
	 */
	public final static String BizLogServiceName = "ISBizLogOperatorService";
	
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
			log = logData.toJsonString();
		} catch (Exception e) {
			logData.setLogData(EMPTY);
			log = logData.toJsonString();
		}

		// 开启本地输出
		if (logger.isDebugEnabled())
			logger.debug(log);

		// 激活log输出
		ISLog remoteLog = MyBeanFactoryHelper.getBean(BizLogServiceName);
		if (EmptyHelper.isNotEmpty(remoteLog)) {
			remoteLog.flush(logData);
		}
	}

}
