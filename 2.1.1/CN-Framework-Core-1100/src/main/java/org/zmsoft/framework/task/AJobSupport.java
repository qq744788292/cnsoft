package org.zmsoft.framework.task;

import java.util.Random;

import org.zmsoft.framework.config.ISProcessSupport;
import org.zmsoft.framework.config.ISTaskSupport;
import org.zmsoft.framework.constants.IJobConstants;
import org.zmsoft.framework.support.MyFrameWorkSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 定时作业服务超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public abstract class AJobSupport extends MyFrameWorkSupport implements IJobConstants, ISProcessSupport, ISTaskSupport {

	/**
	 * 双重锁控制并发
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean doJobTaskProcess() throws Exception {
		// 防止并发，随机休眠
		{
			Random rd = new Random();
			Thread.sleep(rd.nextInt(10) * 1000);
		}

		if (checkLock() == true)
			return false;
		startLock();
		try {
			// 启动日志
			beforeProcess();
			// 定时任务
			doProcess();
			// 结束日志
			afterProcess();
		} finally {
			endLock();
		}
		return true;
	}

	public boolean putCacheService(String key, String value) {
		return myCacheService.putObject(key, value, 3600, false);
	}

	/**
	 * 任务Key
	 */
	protected String jobKey = "JOBKEY:" + this.getClass().getSimpleName();

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
