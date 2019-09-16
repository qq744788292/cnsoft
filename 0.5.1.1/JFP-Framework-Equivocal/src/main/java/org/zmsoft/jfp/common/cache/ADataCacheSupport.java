package org.zmsoft.jfp.common.cache;

import java.util.List;

import javax.annotation.Resource;

import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

//@EnableAsync
//@Service("GameDataCache")
public abstract class ADataCacheSupport<T> implements IFrameworkConstants, ICommonDataConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	protected ISCacheService myCacheService;

	/**
	 * 缓存索引
	 */
	protected int defaultIndex = 0;

	public void setDefaultIndex(int defaultIndex) {
		this.defaultIndex = defaultIndex;
	}

	/**
	 * 缓存时间
	 */
	protected int expireTimeWithSecond = 3600;

	public void setExpireTimeWithSecond(int expireTimeWithSecond) {
		this.expireTimeWithSecond = expireTimeWithSecond;
	}

	/**
	 * 是否自动转换
	 */
	protected boolean translation = false;

	/**
	 * 缓存加载
	 */
	// @Async
	public abstract void loadCacheData();

	/**
	 * 缓存破弃
	 */
	public abstract void removeCacheData(T param);

	protected void putCache(String key, Object data) {
		if (data instanceof String)
			myCacheService.putObject(key, data, expireTimeWithSecond, translation);
		else
			myCacheService.putObject(key, JSON.toJSONString(data), expireTimeWithSecond, translation);
	}

	protected Object loadCache(String key) {
		// myCacheService.expire(key, expireTimeWithSecond);
		return myCacheService.getObject(key, translation);
	}

	protected T loadCacheClass(String key, Class<T> clazz) {
		Object cache = loadCache(key);
		if (cache == null)
			return null;
		return JSON.parseObject((String) cache, clazz);
	}

	protected List<T> loadCacheArray(String key, Class<T> clazz) {
		Object cache = loadCache(key);
		if (cache == null)
			return null;
		return JSON.parseArray((String) cache, clazz);
	}
}
