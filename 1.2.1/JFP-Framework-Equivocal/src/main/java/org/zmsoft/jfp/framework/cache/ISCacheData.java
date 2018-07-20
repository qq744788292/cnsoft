package org.zmsoft.jfp.framework.cache;

import java.util.List;

/**
 * 数据缓存超类
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISCacheData<T> {
	/**
	 * 默认加载
	 */
	public void loadDefaultCacheData();

	/**
	 * 缓存加载(不存在则数据库读取)
	 */
	public T loadCacheData(T param);

	/**
	 * 缓存加载(不存在则数据库读取)
	 * 
	 * @param param
	 */
	public List<T> loadCacheDatas(T param);

	/**
	 * 缓存销毁
	 * 
	 * @param param
	 */
	public void removeCacheData(T param);

	/**
	 * 数据读取
	 * 
	 * @param param
	 */
	public List<T> getDataFromDB(T param);
}
