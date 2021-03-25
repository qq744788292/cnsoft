package org.zmsoft.framework.cache;

import java.util.List;

/**
 * 数据缓存接口定义
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISSystemDatasCache<T> {
	/**
	 * 默认加载
	 * 
	 * @throws Exception
	 */
	public void loadDefaultCacheDatas(boolean load) throws Exception;

	/**
	 * 缓存加载(不存在则数据库读取)
	 * 
	 * @param param
	 */
	public List<T> loadCacheDatas(int pageCurrent, boolean load, T param) throws Exception;

	/**
	 * 数据读取
	 * 
	 * @param param
	 */
	public List<T> getDatasFromDB(int pageCurrent, T param) throws Exception;

}
