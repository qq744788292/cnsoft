package org.zmsoft.jfp.framework.support;

import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.constants.pub.IJobConstants;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

/**
 * 定时作业服务超类
 * 
 * @author zmsoft
 * @version 2.4.1.20151110
 * @since 2.4.1
 */
public class MyJobSupport extends MyWorkSupport implements IJobConstants {

	/**
	 * 缓存队列
	 */
	protected ISCacheService myCacheService;

	public void setMyCacheService(ISCacheService myCacheService) {
		this.myCacheService = myCacheService;
	}

	/**
	 * 任务Key
	 */
	protected String jobKey = "JOBKEY";

	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(String jobKey) {
		if (jobKey.startsWith("JOBKEY")) {
			this.jobKey = jobKey;
		} else {
			this.jobKey = "JOBKEY:" + jobKey;
		}
	}

	/**
	 * 设置任务状态：启动
	 * 
	 * @param jobName
	 * @throws InterruptedException
	 */
	protected boolean startLock() {
		myCacheService.putObject(jobKey, JOB_FLAG_RUNNING, waitTimeSecond, false);
		return true;
	}

	/**
	 * 检查任务状态：false 未开始，true 开始
	 * 
	 * @param jobName
	 */
	protected boolean checkLock() {
		Object o = myCacheService.getObject(jobKey, false);
		return EmptyHelper.isNotEmpty(o);
	}

	/**
	 * 设置任务状态：错误
	 * 
	 * @param jobName
	 */
	protected boolean errorLock() {
		myCacheService.putObject(jobKey, JOB_FLAG_ERROR, waitTimeSecond, false);
		return true;
	}

	/**
	 * 设置任务状态：结束
	 * 
	 * @param jobName
	 */
	protected boolean endLock() {
		myCacheService.putObject(jobKey, JOB_FLAG_SUCCESS, waitTimeSecond, false);
		return true;
	}
}
