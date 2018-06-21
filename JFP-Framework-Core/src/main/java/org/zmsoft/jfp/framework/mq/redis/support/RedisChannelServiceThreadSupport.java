package org.zmsoft.jfp.framework.mq.redis.support;

import org.zmsoft.jfp.framework.biz.ISFinished;
import org.zmsoft.jfp.framework.biz.ISReceive;
import org.zmsoft.jfp.framework.biz.common.ISCheck;
import org.zmsoft.jfp.framework.biz.common.ISInit;
import org.zmsoft.jfp.framework.biz.common.ISProcess;
import org.zmsoft.jfp.framework.common.CommonChannelConfig;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

/**
 * 通道服务超类
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class RedisChannelServiceThreadSupport extends CommonChannelConfig implements Runnable, IFrameworkConstants, ISReceive, ISInit, ISCheck, ISProcess, ISFinished {
	/**
	 * 等待时间（毫秒）
	 */
	private int waitTime = 500;

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	/**
	 * 基于消息队列获取数据
	 */
	public boolean doReceive() throws Exception {
		catchService.selectDB(defaultIndex);
		message = catchService.peekFirstObjectInList(channelKey, false);
		catchService.init();
		if (EmptyHelper.isEmpty(message))
			return false;
		return true;
	}

	/**
	 * 缓存数据（JSON格式）
	 */
	protected Object message;

	public void process() {
		(new Thread(this)).start();
	}

	/**
	 * 启动监听
	 */
	public void run() {
		while (true) {
			try {
				Thread.sleep(waitTime);
				if (doReceive()) {
					if (doInit()) {
						if (doCheck()) {
							if (doProcess()) {
								if (doFinished()) {
									logger.info(">>>>>服务处理成功<<<<<");
								} else {
									logger.info(">>>>>服务处理失败.....doFinished");
								}
							} else {
								logger.info(">>>>>服务处理失败.....doProcess");
							}
						} else {
							logger.info(">>>>>服务处理失败.....doCheck");
						}
					} else {
						logger.info(">>>>>服务处理失败.....doInit");
					}
				}
			} catch (Exception e) {
				logger.error(">>>>>服务处理异常....." + e.getMessage());
			}
		}
	}
}
