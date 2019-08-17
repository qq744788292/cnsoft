package org.zmsoft.common.task.server;

import java.net.InetAddress;

import org.springframework.scheduling.annotation.Scheduled;
import org.zmsoft.framework.constants.ICacheConstants;
import org.zmsoft.framework.task.AJobSupport;
import org.zmsoft.framework.utils.DateHelper;

public class MyHeardTaskService extends AJobSupport  implements ICacheConstants {

	// 设置启动模式-每分钟启动
	@Scheduled(cron = "0/15 * * * * ?")
	public void doQuartzService() throws Exception {
		// 激活父类启动方法
		doJobTaskProcess();
	}

	public String getLocalHost() {
		try {
			InetAddress address = InetAddress.getLocalHost();// 获取的是本地的IP地址
			return address.getHostAddress();
		} catch (Exception e) {
			return "127.0.0.1";
		}
	}

	/**
	 * 业务逻辑
	 */
	@Override
	public boolean doProcess() throws Exception {

		putCacheService(CACHE_KEY_SERVER + getLocalHost(), DateHelper.currentTimeMillisCN1());

		return true;
	}

	/**
	 * 启动开始动作
	 */
	@Override
	public boolean beforeProcess() throws Exception {
		return true;
	}

	/**
	 * 启动结束动作
	 */
	@Override
	public boolean afterProcess() throws Exception {
		return true;
	}
}