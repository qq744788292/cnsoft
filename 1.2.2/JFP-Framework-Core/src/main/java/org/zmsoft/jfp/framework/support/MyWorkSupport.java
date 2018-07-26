package org.zmsoft.jfp.framework.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.net.MyHttpServiceSupport;

/**
 * 异步线程超类
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class MyWorkSupport implements IFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

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
