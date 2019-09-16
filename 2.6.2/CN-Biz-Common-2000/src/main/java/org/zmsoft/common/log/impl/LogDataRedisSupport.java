package org.zmsoft.common.log.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zmsoft.common.redislog.MyRedisLogServiceImpl;
import org.zmsoft.framework.common.ISLog;
import org.zmsoft.framework.support.MyFrameWorkSupport;

/**
 * 日志结构体
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("LogDataRedisSupport")
public class LogDataRedisSupport extends MyFrameWorkSupport implements ISLog {

	@Resource
	MyRedisLogServiceImpl myRedisServiceImpl;

	/**
	 * 日志输出
	 * 
	 * @throws Exception
	 */
	@Async("threadPoolTaskExecutor")
	public void flush(String logMsg) {
		myRedisServiceImpl.pushValue("zm-log", logMsg);
	}

}
