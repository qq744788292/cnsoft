package org.cnsoft.framework.cache;

import java.util.List;
import java.util.Random;

import org.cnsoft.framework.json.JSONObject;
import org.cnsoft.framework.support.MyTokenCommonSupport;

/**
 * 数据缓存管理（基于数据库）
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
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
	
	public final void putCache(String key, Object data) {
		myCacheService.putObject(key, JSONObject.toJsonString(data), expireTimeWithSecond);
	}

	protected final String loadCacheData(String key) {
		return myCacheService.getObject(key);
	}

	protected final <E> E loadCacheClass(String key, Class<E> clazz) {
		return JSONObject.parseObject(loadCacheData(key), clazz);
	}

	protected final <E> List<E> loadCacheArray(String key,  Class<E> clazz) {
		return JSONObject.parseArray(loadCacheData(key), clazz);
	}
}
