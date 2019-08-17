package org.zmsoft.framework.log;

import org.zmsoft.framework.cache.client.ClientHelper;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.client.ClientBusinessSupport;
import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.support.MyBeanContextSupport;
import org.zmsoft.framework.utils.EmptyHelper;

public class LogDataHelper implements IFrameworkConstants {

	public static void saveLog(String serviceName, Object logData) {
		saveLog(serviceName, logData, null);
	}

	public static void saveLog(String serviceName, Object logData, Long executeTime) {
		try {
			LogDataSupport myLogDataSupport = MyBeanContextSupport.getBean("LogDataSupport");
			ClientBusinessSupport cbs = ClientHelper.getClientBusinessSupport();
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
