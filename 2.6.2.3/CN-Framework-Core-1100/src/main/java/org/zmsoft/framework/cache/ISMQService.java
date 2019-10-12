package org.zmsoft.framework.cache;

/**
 * 缓存队列
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public interface ISMQService {

	/**
	 * 添加一个数据（队列）
	 * 
	 * @param key
	 * @param value
	 */
	public boolean pushValue(String key, String value);

	/**
	 * 获得一个数据（队列）
	 * 
	 * @param key
	 * @return
	 */
	public String popValue(String key);
}
