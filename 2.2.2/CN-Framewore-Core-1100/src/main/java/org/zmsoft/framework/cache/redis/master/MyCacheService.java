package org.zmsoft.framework.cache.redis.master;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import org.zmsoft.framework.cache.ISCacheService;
import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * Redis缓存实现
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
@Service("myCacheService")
public class MyCacheService implements ISCacheService, IFrameworkConstants {

	@Autowired
	StringRedisTemplate redis;

	private int waitTime = 5;

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	@Override
	public void expire(String key, int timeOut) {
		redis.expire(key, timeOut, TimeUnit.SECONDS);
	}

	@Override
	public boolean putObject(String key, Object value) {
		putObject(key, value, 3600, false);
		return true;
	}

	public boolean putObject(String key, Object value, int expireTimeWithSecond, boolean translation) {
		redis.opsForValue().set(key, getStringToRedis(value, translation), expireTimeWithSecond, TimeUnit.SECONDS);
		return true;
	}

	@Override
	public Object getObject(String key) {
		return getObject(key, false);
	}

	@Override
	public Object getObject(String key, boolean translation) {
		return getClassFromRedis(redis.opsForValue().get(key), translation);
	}

	@Override
	public Object deleteObject(String key) {
		return deleteObject(key, false);
	}

	@Override
	public Object deleteObject(String key, boolean translation) {
		removeKey(key);
		return null;
	}

	@Override
	public boolean removeKey(String key) {
		return redis.delete(key);
	}

	/**
	 * 实例化对象
	 * 
	 * @param value
	 * @return
	 */
	public static String getStringToRedis(Object value) {
		return getStringToRedis(value, true);
	}

	public static String getStringToRedis(Object value, boolean translation) {
		if (EmptyHelper.isEmpty(value))
			return null;
		if (value instanceof String)
			return (String) value;
		if (translation)
			return value.getClass().getName() + BACKSLASH2 + JSON.toJSON(value);
		else
			return (String) value;
	}

	public static final String CLASS_NAME = "className";

	/**
	 * 实例化对象
	 * 
	 * @param value
	 * @return
	 */
	/**
	 * @deprecated
	 */
	public static Object getClassFromRedis(String value) {
		return getClassFromRedis(value, true);
	}

	public static Object getClassFromRedis(String value, boolean translation) {
		if (EmptyHelper.isEmpty(value))
			return null;
		if (translation) {
			if (value.indexOf(BACKSLASH2) < 0)
				return (String) value;
			String[] values = new String[2];
			values[0] = value.substring(0, value.indexOf("//"));
			values[1] = value.substring(value.indexOf("//") + 2);
			try {
				return JSON.parseObject(values[1], Class.forName(values[0]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return values[1];
		} else {
			return value;
		}
	}

	/**
	 * @deprecated
	 */
	@Override
	public Map<String, String> getAllObjectInMap(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 追加数据到已有的缓存数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public boolean addObjectInMap(String rkey, String mkey, Object value) {
		return addObjectInMap(rkey, mkey, value, false);
	}

	/**
	 * @deprecated
	 */
	@Override
	public boolean addObjectInMap(String rkey, String mkey, Object value, boolean translation) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 从已有的缓存数据里面删除一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object removeObjectInMap(String rkey, String mkey) {
		return removeObjectInMap(rkey, mkey, false);
	}

	/**
	 * @deprecated
	 */

	@Override
	public Object removeObjectInMap(String rkey, String mkey, boolean translation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 从已有的缓存数据里面获取一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object findObjectInMap(String rkey, String mkey) {
		return findObjectInMap(rkey, mkey, false);
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object findObjectInMap(String rkey, String mkey, boolean translation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long sizeOfMap(String key) {
		return 0;
	}

	@Override
	public long sizeOfList(String key) {
		return 0;
	}

	@Override
	public boolean addObjectInList(String key, Object value) {
		return addObjectInList(key, value, false);
	}

	/**
	 * @deprecated
	 */
	@Override
	public boolean addObjectInList(String key, Object value, boolean translation) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	@Override
	public Object peekFirstObjectInList(String key) {
		return peekFirstObjectInList(key, waitTime, false);
	}

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	@Override
	public Object peekFirstObjectInList(String key, boolean translation) {
		return peekFirstObjectInList(key, waitTime, translation);
	}

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	/**
	 * @deprecated
	 */
	@Override
	public Object peekFirstObjectInList(String key, int waitTime, boolean translation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object pollFirstObjectInList(String key) {
		return pollFirstObjectInList(key, false);
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object pollFirstObjectInList(String key, boolean translation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object peekLastObjectInList(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object peekLastObjectInList(String key, boolean translation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object pollLastObjectInList(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object pollLastObjectInList(String key, boolean translation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addMapAll(String key, Map<String, Object> value) {
		for (Map.Entry<String, Object> entry : value.entrySet()) {
			addObjectInMap(key, entry.getKey(), entry.getValue());
		}
		return true;
	}

	@Override
	public boolean addListAll(String key, List<Object> value) {
		for (Object val : value)
			addObjectInList(key, val);
		return true;
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object indexObjectInList(String key, int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
