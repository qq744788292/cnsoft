package org.zmsoft.jfp.framework.support;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.net.MyHttpServiceSupport;

/**
 * 异步线程超类
 * 
 * @author zmsoft
 * @version 3.1.2.20160505
 * @since 3.1.2
 */
public class MyWorkSupport implements IFrameworkConstants {

	/**
	 * 数据安全
	 */
	protected ISSecuritySupport mySecuritySupport;
	public void setMySecuritySupport(ISSecuritySupport mySecuritySupport) {
		this.mySecuritySupport = mySecuritySupport;
	}

	/**
	 * 网络通信
	 */
	protected MyHttpServiceSupport myHttpService = new MyHttpServiceSupport();
	public void setHttpService(MyHttpServiceSupport myHttpService) {
		this.myHttpService = myHttpService;
	}

	public MyHttpServiceSupport currentHttpServiceSupport() {
		return myHttpService;
	}
	
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
