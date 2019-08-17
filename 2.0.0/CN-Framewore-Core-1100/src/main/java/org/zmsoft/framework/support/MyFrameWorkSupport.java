package org.zmsoft.framework.support;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.framework.cache.ISCacheService;
import org.zmsoft.framework.constants.IDBConstants;
import org.zmsoft.framework.constants.IFrameworkConstants;

/**
 * 异步线程超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 1.0.0 2018/02/02
 * @see <ObjectBean>
 */
public class MyFrameWorkSupport implements IFrameworkConstants, IDBConstants {
	// 日志
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// 缓存中心
	@Resource
	protected ISCacheService myCacheService;

	/**
	 * 进程阻塞时间（分钟）
	 */
	protected int waitTimeMinute = 10;

	public void setWaitTimeMinute(int waitTimeMinute) {
		this.waitTimeMinute = waitTimeMinute;
		this.waitTimeSecond = 60 * this.waitTimeMinute;
	}

	/**
	 * 进程阻塞时间（秒）
	 */
	protected int waitTimeSecond = 60 * waitTimeMinute;

	public void setWaitTimeSecond(int waitTimeSecond) {
		this.waitTimeSecond = waitTimeSecond;
	}

}
