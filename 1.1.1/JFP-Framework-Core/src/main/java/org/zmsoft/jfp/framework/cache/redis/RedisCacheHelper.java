package org.zmsoft.jfp.framework.cache.redis;

import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;

/**
 * Redis缓存操作key-value
 * 
 * @author ZmSoft
 * @since 0.1.0 2018/2/8
 * @version 0.1.0 2018/2/8
 */
public class RedisCacheHelper implements IFrameworkConstants {
	public static void setSessionAttribute(String key, Object value) {
		ISCacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.putObject(key, value, 3600, false);
		myCache.init();
	}

	public static void setSessionAttribute(int second, String key, Object value) {
		ISCacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.putObject(key, value, second, false);
		myCache.init();
	}

	public static Object getSessionAttribute(String key) {
		ISCacheService myCache = BeanFactoryHelper.getBean("myCache");
		Object obj = myCache.getObject(key, false);
		myCache.init();
		return obj;
	}

	public static Object removeSessionAttribute(String key) {
		ISCacheService myCache = BeanFactoryHelper.getBean("myCache");
		Object obj = myCache.deleteObject(key, false);
		myCache.init();
		return obj;
	}
}
