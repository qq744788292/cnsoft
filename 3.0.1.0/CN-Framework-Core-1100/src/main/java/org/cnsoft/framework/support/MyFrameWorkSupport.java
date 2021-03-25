package org.cnsoft.framework.support;

import javax.annotation.Resource;

import org.cnsoft.framework.cache.redis.MyCacheService;
import org.cnsoft.framework.constants.ICDBConstants;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.core.ObjectBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 核心框架超类
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 1.0.0 2018/02/02
 * @see <ObjectBean>
 */
public class MyFrameWorkSupport extends ObjectBean implements ICFrameworkConstants, ICDBConstants {
	// 日志
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 进程阻塞时间（分钟）
	 */
	protected int waitTimeMinute = 5;

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
	
	@Value("${spring.application.name}")
	protected String myAppName = "CNSoft";// 服务名称（项目名称）
	@Value("${model.id}")
	protected String myModelId = "10";// 服务名称（项目名称）

	public String getMyModelId() {
		return myModelId;
	}

	public String getMyAppName() {
		return myAppName + TRANSVERSE_LINE + myModelId;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// 缓存中心
	@Resource
	protected MyCacheService myCacheService;

	public MyCacheService currentCacheService() {
		return myCacheService;
	}

	public void setCacheService(MyCacheService myCacheService) {
		this.myCacheService = myCacheService;
	}

}
