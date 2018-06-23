package org.zmsoft.jfp.common.cache;

import java.util.List;

import org.zmsoft.jfp.framework.biz.ISCache;

/**
 * 数据缓存超类
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISCacheData<T> extends ISCache {

	/**
	 * 缓存加载(不存在则数据库读取)
	 */
	public abstract T loadCacheData(T param);

	/**
	 * 缓存销毁
	 * @param param
	 */
	public abstract void removeCacheData(T param);
	
	/**
	 * 数据读取
	 * @param param
	 */
	public abstract List<T> getDataFromDB(T param);
}
