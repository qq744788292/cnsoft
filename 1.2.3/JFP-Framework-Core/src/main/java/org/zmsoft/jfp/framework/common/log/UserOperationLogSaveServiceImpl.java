package org.zmsoft.jfp.framework.common.log;

import org.zmsoft.jfp.framework.beans.pub.LogBean;
import org.zmsoft.jfp.framework.common.CommonChannelConfig;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.support.common.ISLogSupport;

import com.alibaba.fastjson.JSON;

/**
 * 操作日志
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public class UserOperationLogSaveServiceImpl extends CommonChannelConfig implements ISLogSupport {

	public UserOperationLogSaveServiceImpl() {
		this.channelKey = CONFIG_KEY;
	}

	/**
	 * 日志记录
	 * 
	 * @see IFrameworkConstants.SEMICOLON
	 * @param log
	 *            日志信息
	 * @return 发送结果
	 */
	public boolean save(LogBean log, int logType) {
		logger.debug(log.toString());

		if (myCacheService == null)
			logger.error(">>>>>缓存服务没有定义...xxx");
		else {
			myCacheService.addObjectInList(channelKey + logType, JSON.toJSONString(log), false);
		}
		return true;
	}
}
