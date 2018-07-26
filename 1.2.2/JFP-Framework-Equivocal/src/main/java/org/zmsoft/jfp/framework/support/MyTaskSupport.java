package org.zmsoft.jfp.framework.support;

import java.util.Random;

import org.zmsoft.jfp.framework.biz.ISTask;
import org.zmsoft.jfp.framework.biz.common.ISProcess;

/**
 * 定时作业服务超类
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class MyTaskSupport extends MyJobSupport implements ISProcess, ISTask {

	public boolean doProcess() throws Exception {
		// 防止并发，随机休眠
		{
			Random rd = new Random();
			Thread.sleep(rd.nextInt(10) * 1000);
		}

		if (checkLock() == true)
			return false;
		startLock();
		try {
			doProcessRepeat();
		} finally {
			endLock();
		}
		return true;
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
