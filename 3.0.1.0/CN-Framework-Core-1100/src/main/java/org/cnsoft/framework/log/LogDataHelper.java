package org.cnsoft.framework.log;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.cache.client.ClientBean;
import org.cnsoft.framework.cache.request.ClientRequestHelper;
import org.cnsoft.framework.cache.session.SessionHelper;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.utils.EmptyHelper;

/**
 * 日志统一收集输出
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class LogDataHelper implements ICFrameworkConstants {

	public static void saveLog(String serviceName, Object logData) {
		saveLog(serviceName, logData, null);
	}

	public static void saveLog(String serviceName, Object logData, Long executeTime) {
		try {
			LogDataSupport myLogDataSupport = MyBeanFactoryHelper.getBean(LogDataSupport.class);
			ClientBean cbs = ClientRequestHelper.getClientBusinessSupport();
			LogDataBean remoteLogData = new LogDataBean();
			remoteLogData.setApi(cbs.getReferralLink());
			remoteLogData.setIpAdress(cbs.getClientIp());
			remoteLogData.setServiceName(serviceName);
			String userId = SessionHelper.currentUserId();
			if (EmptyHelper.isEmpty(userId))
				userId = GUEST;
			remoteLogData.setUserId(userId);
			remoteLogData.setLogData(logData);
			remoteLogData.setExecuteTime(executeTime);
			myLogDataSupport.setLogData(remoteLogData);
			myLogDataSupport.flush();
		} catch (Exception e) {

		}
	}
}
