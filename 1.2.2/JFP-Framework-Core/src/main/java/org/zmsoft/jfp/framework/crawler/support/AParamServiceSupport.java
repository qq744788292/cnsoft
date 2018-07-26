package org.zmsoft.jfp.framework.crawler.support;

import java.util.HashMap;

import javax.annotation.Resource;

import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

import com.alibaba.fastjson.JSON;

/**
 * 参数基类
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public abstract class AParamServiceSupport implements IFrameworkConstants {

	// 缓存中心
	@Resource
	protected ISCacheService myCacheService;

	/**
	 * 缓存破弃
	 */
	public void removeCacheData(String key) {
		myCacheService.removeKey(key);
	}

	protected void putCache(String key, HashMap<String, String> data) {
		myCacheService.putObject(key, JSON.toJSONString(data), 3600, false);
	}

	protected Object loadCache(String key) {
		// myCacheService.expire(key, expireTimeWithSecond);
		return myCacheService.getObject(key, false);
	}
	
	public abstract HashMap<String, String> loadCurrentPageParam();
	

	public abstract void saveCurrentPageParam(HashMap<String, String> currentPageParam);
}
