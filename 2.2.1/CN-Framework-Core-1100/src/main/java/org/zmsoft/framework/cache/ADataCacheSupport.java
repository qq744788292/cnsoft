package org.zmsoft.framework.cache;

import java.util.List;
import java.util.Random;

import org.zmsoft.framework.constants.ICacheConstants;
import org.zmsoft.framework.support.MyTokenCommonSupport;

import com.alibaba.fastjson.JSON;

public abstract class ADataCacheSupport<T> extends MyTokenCommonSupport implements ICacheConstants {

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

	public void putCache(String key, Object data) {
		if (data instanceof String)
			myCacheService.putObject(key, data, expireTimeWithSecond, translation);
		else
			myCacheService.putObject(key, JSON.toJSONString(data), expireTimeWithSecond, translation);
	}

	public Object loadCache(String key) {
		// myCacheService.expire(key, expireTimeWithSecond);
		return myCacheService.getObject(key, translation);
	}

	public T loadCacheClass(String key, Class<T> clazz) {
		Object cache = loadCache(key);
		if (cache == null)
			return null;
		return JSON.parseObject((String) cache, clazz);
	}

	public List<T> loadCacheArray(String key, Class<T> clazz) {
		Object cache = loadCache(key);
		if (cache == null)
			return null;
		return JSON.parseArray((String) cache, clazz);
	}
}
