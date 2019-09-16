package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.net.HttpProxyBean;
import org.isotope.jfp.framework.biz.ISTask;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISJobConstants;
import org.isotope.jfp.framework.net.ISHttpProxy;
import org.isotope.jfp.framework.net.MyHttpServiceSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 定时作业服务超类
 * 
 * @author Spook
 * @version 2.4.1.20151110
 * @since 2.4.1
 */
public class MyJobSupport implements ISJobConstants, ISFrameworkConstants, ISTask {

	/**
	 * 缓存队列
	 */
	protected ICacheService myCacheService;

	public ICacheService getMyCacheService() {
		return myCacheService;
	}

	public void setMyCacheService(ICacheService myCacheService) {
		this.myCacheService = myCacheService;
	}

	/**
	 * 网络通信
	 */
	protected MyHttpServiceSupport myHttpService = new MyHttpServiceSupport();

	public MyHttpServiceSupport getHttpService() {
		return myHttpService;
	}

	public void setHttpService(MyHttpServiceSupport myHttpService) {
		this.myHttpService = myHttpService;
	}

	public void setHttpProxy(ISHttpProxy httpProxy) {
		myHttpService.setHttpProxy(httpProxy);
	}

	public ISHttpProxy getHttpProxy() {
		return myHttpService.getHttpProxy();
	}

	public boolean removeHttpProxy(HttpProxyBean httpProxy) {
		if (myHttpService != null)
			return myHttpService.removeHttpProxy(httpProxy);
		return false;
	}

	/**
	 * 进程阻塞时间（分钟）
	 */
	protected int waitTimeMinute = 10;

	public int getWaitTimeMinute() {
		return waitTimeMinute;
	}

	public void setWaitTimeMinute(int waitTimeMinute) {
		this.waitTimeMinute = waitTimeMinute;
		this.waitTimeSecond = 60 * this.waitTimeMinute;
	}

	/**
	 * 进程阻塞时间（秒）
	 */
	protected int waitTimeSecond = 60 * waitTimeMinute;

	public int getWaitTimeSecond() {
		return waitTimeSecond;
	}

	public void setWaitTimeSecond(int waitTimeSecond) {
		this.waitTimeSecond = waitTimeSecond;
	}

	/**
	 * 任务Key
	 */
	protected String jobKey = "JOBKEY";

	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}

	/**
	 * 设置任务为执行状态
	 * 
	 * @param jobName
	 */
	protected boolean startLock() {
		myCacheService.putObject(jobKey, JOB_FLAG_RUNNING, waitTimeSecond, false);
		return true;
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean checkLock() {
		return EmptyHelper.isEmpty(myCacheService.getObject(jobKey, false));
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean errorLock() {
		myCacheService.putObject(jobKey, JOB_FLAG_ERROR, waitTimeSecond, false);
		return true;
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean endLock() {
		myCacheService.putObject(jobKey, JOB_FLAG_SUCCESS, waitTimeSecond, false);
		return true;
	}

	public void doProcess() throws Exception {
		if (checkLock() == false)
			return;
		startLock();
		try {
			doProcessRepeat();
		} finally {
			endLock();
		}
		// 清空数据缓存
	}

	/**
	 * 业务处理(重复运行)
	 */
	@Override
	public boolean doProcessRepeat() throws Exception {
		return false;
	}

	/**
	 * 业务处理(运行一次)
	 */
	@Override
	public boolean doProcessOnce(Object param) throws Exception {
		return false;
	}
}
