package org.zmsoft.jfp.framework.support;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.cache.ICommonDataConstants;
import org.zmsoft.jfp.framework.cache.ISCacheData;
import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

import com.alibaba.fastjson.JSON;

/**
 * 数据缓存超类
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class MyDataCacheSupport<T> implements ISCacheData<T>, IFrameworkConstants, ICommonDataConstants {

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
	 * 数据缓存
	 * @param key
	 * @param data
	 */
	protected void putCache(String key, Object data) {
		if (data instanceof String)
			myCacheService.putObject(key, data, expireTimeWithSecond, translation);
		else
			myCacheService.putObject(key, JSON.toJSONString(data), expireTimeWithSecond, translation);
	}

	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	protected Object loadCache(String key) {
		// myCacheService.expire(key, expireTimeWithSecond);
		return myCacheService.getObject(key, translation);
	}

	/**
	 * 获取缓存并转化为对象
	 * @param key
	 * @param clazz
	 * @return
	 */
	protected T loadCacheClass(String key, Class<T> clazz) {
		Object cache = loadCache(key);
		if (cache == null)
			return null;
		return JSON.parseObject((String) cache, clazz);
	}

	/**
	 * 获取缓存并转化为对象集合
	 * @param key
	 * @param clazz
	 * @return
	 */
	protected List<T> loadCacheArray(String key, Class<T> clazz) {
		Object cache = loadCache(key);
		if (cache == null)
			return null;
		return JSON.parseArray((String) cache, clazz);
	}
}
