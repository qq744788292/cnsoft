package org.zmsoft.jfp.framework.common;

import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用Redis通道队列设置
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class CommonChannelConfig implements IFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 缓存定义
	 */
	protected ISCacheService catchService;

	public void setCatchService(ISCacheService catchService) {
		this.catchService = catchService;
	}

	public ISCacheService getCatchService() {
		return catchService;
	}

	protected int defaultIndex = 0;

	public int getDefaultIndex() {
		return defaultIndex;
	}

	public void setDefaultIndex(int defaultIndex) {
		this.defaultIndex = defaultIndex;
	}

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
