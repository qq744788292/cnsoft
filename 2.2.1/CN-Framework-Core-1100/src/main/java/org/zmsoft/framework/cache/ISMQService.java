package org.zmsoft.framework.cache;

import java.util.List;

/**
 * 缓存队列
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public interface ISMQService {
	
	/////////////// 消息队列/////////////////

	/**
	 * 将指定List内元素插入此双端队列的末尾 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public boolean addListAll(String key, List<String> value);

	/**
	 * 将指定元素插入此双端队列的末尾 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public boolean addObjectInList(String key, Object value);

	/**
	 * 将指定元素插入此双端队列的末尾 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public boolean addObjectInList(String key, Object value, boolean translation);

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据 <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	public Object pollFirstObjectInList(String key);

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据 <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	public Object pollFirstObjectInList(String key, boolean translation);
}
