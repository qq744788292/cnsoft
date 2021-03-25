package org.cnsoft.framework.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.cnsoft.framework.cache.ISCacheService;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.support.MyFrameWorkSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Redis缓存实现
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
@Service("MyCacheService")
public class MyCacheService extends MyFrameWorkSupport implements ISCacheService, ICFrameworkConstants {

	@Autowired
	private StringRedisTemplate redisTemplate;

	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void expire(String key, int timeOut) {
		redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
	}

	@Override
	public boolean putObject(String key, String value) {
		putObject(key, value, waitTimeSecond);
		return true;
	}

	public boolean putObject(String key, String value, int expireTimeWithSecond) {
		redisTemplate.opsForValue().set(key, value, expireTimeWithSecond, TimeUnit.SECONDS);
		return true;
	}

	@Override
	public String getObject(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public String deleteObject(String key) {
		String o = getObject(key);
		removeKey(key);
		return o;
	}

	@Override
	public boolean removeKey(String key) {
		return redisTemplate.delete(key);
	}
	
	@Override
	public Map<Object, Object> getAllObjectInMap(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 追加数据到已有的缓存数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public boolean addObjectInMap(String rkey, String mkey, String value) {
		redisTemplate.opsForHash().put(rkey, mkey, value);
		return true;
	}

	/**
	 * 从已有的缓存数据里面删除一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public String removeObjectInMap(String rkey, String mkey) {
		String o = (String) redisTemplate.opsForHash().get(rkey, mkey);
		redisTemplate.opsForHash().delete(rkey, mkey);
		return o;
	}

	/**
	 * 从已有的缓存数据里面获取一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public String findObjectInMap(String rkey, String mkey) {
		return (String) redisTemplate.opsForHash().get(rkey, mkey);
	}

	@Override
	public long sizeOfMap(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	@Override
	public boolean addMapAll(String key, Map<String, String> value) {
		for (Map.Entry<String, String> entry : value.entrySet()) {
			addObjectInMap(key, entry.getKey(), entry.getValue());
		}
		return true;
	}

	@Override
	public long sizeOfList(String key) {
		return redisTemplate.opsForList().size(key);
	}

	@Override
	public boolean addObjectInList(String key, String value) {
		redisTemplate.opsForList().leftPush(key, value);
		return true;
	}

	@Override
	public String pollFirstObjectInList(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}

	@Override
	public String pollLastObjectInList(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	@Override
	public boolean addListAll(String key, List<String> value) {
		redisTemplate.opsForList().leftPushAll(key, value);
		return true;
	}

	@Override
	public String indexObjectInList(String key, int index) {
		return redisTemplate.opsForList().index(key, index);
	}
}
