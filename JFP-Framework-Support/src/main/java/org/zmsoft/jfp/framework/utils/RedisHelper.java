package org.zmsoft.jfp.framework.utils;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

/**
 * 缓存数据对象与字符串转换
 * 
 * @author zmsoft
 * @since 0.2.1
 * @version 0.1.0 2014/11/28
 * 
 */
public class RedisHelper implements IFrameworkConstants {

	public static void copy(Jedis jd, String oldKey, String newKey, boolean retain) {
		copy(jd, oldKey, newKey, retain, Integer.MAX_VALUE);
	}

	public static void copy(Jedis jd, String oldKey, String newKey, boolean retain, int num) {
		String value;
		long size = jd.llen(oldKey);
		if (num < size)
			size = num;
		for (int i = 0; i < size; i++) {
			try {
				value = jd.lpop(oldKey);
				System.out.println(value);
				if (EmptyHelper.isEmpty(value))
					break;
				if (retain)
					jd.rpush(oldKey, value);
				jd.rpush(newKey, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Jedis oldJedis = new Jedis("172.16.2.201", 6379);
		oldJedis.auth("123456");
		oldJedis.select(4);
		Jedis newJedis = new Jedis("172.16.2.201", 6379);
		newJedis.auth("123456");
		newJedis.select(4);
		copy(oldJedis,"HTTP_PROXY:LIST1",newJedis,"HTTP_PROXY:LIST2",true);
		
		//copy(oldJedis,"PAGE:GANJI:NUM",newJedis,"PAGE:GANJI:NUM",true);
		
	}
	
	public static void copy(Jedis oldJedis, String oldKey, Jedis newJedis, String newKey, boolean retain) {
		String value;
		long size = oldJedis.llen(oldKey);
		for (int i = 0; i < size; i++) {
			try {
				if(i>100000)
				 break;
				value = oldJedis.lpop(oldKey);
				System.out.println(value);
				if (retain)
					oldJedis.rpush(oldKey, value);
				newJedis.rpush(newKey, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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

}
