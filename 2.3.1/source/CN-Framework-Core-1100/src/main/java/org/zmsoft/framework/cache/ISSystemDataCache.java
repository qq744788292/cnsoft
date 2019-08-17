package org.zmsoft.framework.cache;

/**
 * 数据缓存接口定义
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISSystemDataCache<T> {

	/**
	 * 缓存加载(不存在则数据库读取)
	 */
	public T loadCacheData(T param) throws Exception;

	/**
	 * 数据读取
	 * 
	 * @param param
	 */
	public T getDataFromDB(T param) throws Exception;

	/**
	 * 缓存销毁
	 * 
	 * @param param
	 */
	public void removeCacheData(T param) throws Exception;
}
