package org.zmsoft.jfp.framework.cache.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zmsoft.jfp.framework.cache.ISCacheService;

/**
 * 本地缓存
 * @author zmsoft
 * @version 2.0.1 2015/07/07
 * @version 1.1.0 2014/12/15
 * @version 0.1.0 2014/05/30
 * @since 0.1.0 2014/5/30
 */

@SuppressWarnings("unchecked")
public class MyMapCache implements ISCacheService {

	Map<String, Object> cache = new HashMap<String, Object>();

	public boolean clear() {
		cache.clear();
		return true;
	}
	
	@Override
	public Collection<String> keys(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putObject(String key, Object value) {
		cache.put(key, value);
		return true;
	}

	@Override
	public boolean putObject(String key, Object value, int expireTimeWithSecond, boolean translation) {
		cache.put(key, value);
		return true;
	}

	@Override
	public boolean renameKey(String oldkey, String newkey) {
		cache.put(newkey, getObject(oldkey));
		return true;
	}
	
	@Override
	public Object getObject(String key) {
		return cache.get(key);
	}

	@Override
	public Object getObject(String key, boolean translation) {
		return cache.get(key);
	}

	@Override
	public Object deleteObject(String key) {
		return cache.remove(key);
	}

	@Override
	public Object deleteObject(String key, boolean translation) {
		return cache.remove(key);
	}

	@Override
	public Map<String, Object> getAllObjectInMap(String key) {
		Map<String, Object> ms = (Map<String, Object>) getObject(key);
		if (ms == null)
			ms = new HashMap<String, Object>();
		return ms;
	}

	/**
	 * 追加数据到已有的缓存数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public boolean addObjectInMap(String rkey, String mkey, Object value) {
		Map<String, Object> ms = (Map<String, Object>) getObject(rkey);
		if (ms == null)
			ms = new HashMap<String, Object>();
		ms.put(mkey, value);
		cache.put(rkey, ms);
		return true;
	}

	@Override
	public boolean addObjectInMap(String rkey, String mkey, Object value, boolean translation) {
		return addObjectInMap(rkey, mkey, value);
	}

	/**
	 * 从已有的缓存数据里面删除一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object removeObjectInMap(String rkey, String mkey) {
		Map<String, Object> ms = (Map<String, Object>) getObject(rkey);
		if (ms == null)
			ms = new HashMap<String, Object>();
		return ms.remove(mkey);
	}

	@Override
	public Object removeObjectInMap(String rkey, String mkey, boolean translation) {
		Map<String, Object> ms = (Map<String, Object>) getObject(rkey);
		if (ms == null)
			ms = new HashMap<String, Object>();
		return ms.remove(mkey);
	}

	/**
	 * 从已有的缓存数据里面获取一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object findObjectInMap(String rkey, String mkey) {
		Map<String, Object> ms = (Map<String, Object>) getObject(rkey);
		if (ms == null)
			ms = new HashMap<String, Object>();
		return ms.get(mkey);
	}

	@Override
	public Object findObjectInMap(String rkey, String mkey, boolean translation) {
		Map<String, Object> ms = (Map<String, Object>) getObject(rkey);
		if (ms == null)
			ms = new HashMap<String, Object>();
		return ms.get(mkey);
	}

	@Override
	public long sizeOfMap(String key) {
		Map<String, Object> ms = (Map<String, Object>) getObject(key);
		if (ms == null)
			return 0;
		return (long) ms.size();
	}

	///////////////////////////////////////
	@Override
	public long sizeOfList(String key) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			return 0;
		return (long) ms.size();
	}
	
	@Override
	public boolean addObjectInList(String key, Object value) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			ms = new ArrayList<Object>();
		ms.add(value);
		return true;
	}

	@Override
	public boolean addObjectInList(String key, Object value, boolean translation) {
		return addObjectInList(key, key);
	}

	@Override
	public Object peekFirstObjectInList(String key) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.get(0);
	}

	@Override
	public Object peekFirstObjectInList(String key, boolean translation) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.get(0);
	}

	@Override
	public Object peekFirstObjectInList(String key, int waitTime, boolean translation) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.get(0);
	}

	@Override
	public Object pollFirstObjectInList(String key) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.remove(0);
	}

	@Override
	public Object pollFirstObjectInList(String key, boolean translation) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.remove(0);
	}

	@Override
	public Object peekLastObjectInList(String key) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.get(ms.size());
	}

	@Override
	public Object peekLastObjectInList(String key, boolean translation) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.get(ms.size());
	}

	@Override
	public Object pollLastObjectInList(String key) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.remove(ms.size());
	}

	@Override
	public Object pollLastObjectInList(String key, boolean translation) {
		List<Object> ms = (List<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.remove(ms.size());
	}
	/**
	 * @deprecated
	 */
	@Override
	public void selectDB(int index) {
	}
	/**
	 * @deprecated
	 */
	@Override
	public void expire(String key, int timeOut) {

	}
	/**
	 * @deprecated
	 */
	@Override
	public void init() {
	}

	@Override
	public boolean addMapAll(String key, Map<String, Object> value) {
		Map<String, Object> ms = (Map<String, Object>) getObject(key);
		if (ms == null)
			ms = value;
		else {
			ms.putAll(value);
		}
		cache.put(key, ms);

		return false;
	}

	@Override
	public boolean addListAll(String key, List<Object> value) {
		List<Object> ls = (List<Object>) getObject(key);
		if (ls == null)
			ls = value;
		else
			ls.addAll(value);
		cache.put(key, ls);
		return false;
	}

	@Override
	public Object indexObjectInList(String key, int index) {
		List<Object> ls = (List<Object>) getObject(key);
		if (ls == null)
			return "";
		return ls.get(index);
	}

	@Override
	public long setnx(String key, Object value) {
		putObject(key, value);
		return 0;
	}

	@Override
	public boolean removeKey(String key) {
		cache.remove(key);
		return true;
	}

}
