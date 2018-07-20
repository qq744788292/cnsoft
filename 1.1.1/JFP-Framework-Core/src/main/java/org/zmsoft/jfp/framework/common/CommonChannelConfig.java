package org.zmsoft.jfp.framework.common;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/**
 * 通道服务超类（生产者端）
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class CommonChannelConfig implements IFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	protected ISCacheService myCacheService;

	/**
	 * Redis通道定义
	 */
	protected String channelKey;

	public String getChannelKey() {
		return channelKey;
	}

	public void setChannelKey(String channelKey) {
		this.channelKey = channelKey;
	}
}
