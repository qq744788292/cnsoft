package org.zmsoft.framework.cache;

import java.util.List;
import java.util.Random;

import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

public abstract class ADataCacheSupport<T> extends MyTokenCommonSupport {

	public void lock() {
		try {
			Random r = new Random();
			Thread.sleep((r.nextInt(10)) * 100);
		} catch (InterruptedException e) {
		}
	}

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
	protected int expireTimeWithSecond = 300;

	public void setExpireTimeWithSecond(int expireTimeWithSecond) {
		this.expireTimeWithSecond = expireTimeWithSecond;
	}

	/**
	 * 是否自动转换
	 */
	protected boolean translation = false;

	public final void putCache(String key, Object data) {
		if (data instanceof String)
			myCacheService.putObject(key, data, expireTimeWithSecond, translation);
		else
			myCacheService.putObject(key, JSON.toJSONString(data), expireTimeWithSecond, translation);
	}

	protected final Object loadCacheData(String key) {
		// myCacheService.expire(key, expireTimeWithSecond);
		return myCacheService.getObject(key, translation);
	}

	protected final T loadCacheClass(String key, Class<T> clazz) {
		Object cache = loadCacheData(key);
		if (EmptyHelper.isEmpty(cache))
			return null;
		return JSON.parseObject((String) cache, clazz);
	}

	protected final List<T> loadCacheArray(String key, Class<T> clazz) {
		Object cache = loadCacheData(key);
		if (EmptyHelper.isEmpty(cache))
			return null;
		return JSON.parseArray((String) cache, clazz);
	}
}
