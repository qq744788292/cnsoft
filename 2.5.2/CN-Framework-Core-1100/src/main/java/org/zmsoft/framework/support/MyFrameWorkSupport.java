package org.zmsoft.framework.support;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.zmsoft.framework.beans.common.FrameworkDataBean;
import org.zmsoft.framework.cache.ISCacheService;
import org.zmsoft.framework.constants.ICDBConstants;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 核心框架超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 1.0.0 2018/02/02
 * @see <ObjectBean>
 */
public class MyFrameWorkSupport extends FrameworkDataBean implements ICFrameworkConstants, ICDBConstants {
	// 日志
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// 缓存中心
	@Resource 
	protected ISCacheService myCacheService;

	@Value("${spring.application.name}")
	protected String myAppName = "ZMSoft";// 服务名称（项目名称）
	@Value("${model.type}")
	protected String myModelTyle = "dev";// 服务名称（项目名称）

	public String getMyModelTyle() {
		return myModelTyle;
	}

	public String getMyAppName() {
		return myAppName + TRANSVERSE_LINE + myModelTyle;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 进程阻塞时间（分钟）
	 */
	protected int waitTimeMinute = 15;

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
