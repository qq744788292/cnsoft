package com.zmsoft.common.log.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.common.ILog;
import org.zmsoft.framework.support.MyFrameWorkSupport;

import com.zmsoft.common.redis.MyRedisServiceImpl;

/**
 * 日志结构体
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("LogDataRedisSupport")
public class LogDataRedisSupport extends MyFrameWorkSupport implements ILog {

	@Resource
	MyRedisServiceImpl myRedisServiceImpl;

	/**
	 * 日志输出
	 * 
	 * @throws Exception
	 */
	@Async("threadPoolTaskExecutor")
	public void flush(String logMsg) {
		myRedisServiceImpl.addValue("zm-log", logMsg);
	}

}
