package org.cnsoft.framework.cache;

import java.util.List;
import java.util.Map;

/**
 * 缓存队列
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public interface ISCacheService {

	////////// 数据缓存////////////////

	/**
	 * 删除一个Key
	 * 
	 * @param key
	 * @return
	 */
	public boolean removeKey(String key);

	/**
	 * 保存数据到缓存中心 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public boolean putObject(String key, String value);

	/**
	 * 保存数据到缓存中心 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public boolean putObject(String key, String value, int expireTimeWithSecond);

	/**
	 * 从缓存中心获取数据 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public String getObject(String key);

	/**
	 * 删除缓存中心里面的一个数据 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public String deleteObject(String key);

	///////////////// 消息队列///////////////////
	/**
	 * 从缓存中心获取Map里面的全部数据 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public Map<Object, Object> getAllObjectInMap(String key);

	/**
	 * 返回名称为key的map的长度 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public long sizeOfMap(String key);

	/**
	 * 将指定Map内元素插入缓存数据 <br>
	 * （基于保存Key,Map数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public boolean addMapAll(String key, Map<String, String> value);

	/**
	 * 追加数据到已有的缓存数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public boolean addObjectInMap(String rkey, String mkey, String value);

	/**
	 * 从已有的缓存数据队列里面删除一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public String removeObjectInMap(String rkey, String mkey);

	/**
	 * 从已有的缓存数据里面获取一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public String findObjectInMap(String rkey, String mkey);

	/////////////// 消息队列/////////////////
	/**
	 * 返回名称为key的list的长度 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public long sizeOfList(String key);

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
	public boolean addObjectInList(String key, String value);

	/**
	 * 返回名称为key的list中index位置的元素 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public String indexObjectInList(String key, int index);

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据 <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	public String pollFirstObjectInList(String key);

	/**
	 * 从已有的缓存数据里面获取并移除最后一个数据 <br>
	 * （基于保存Key,Deque数据队列）
	 * 
	 * @return
	 */
	public String pollLastObjectInList(String key);

	/**
	 * 
	 * @param key
	 * @param waitTimeSecond
	 */
	public void expire(String key, int waitTimeSecond);

}
