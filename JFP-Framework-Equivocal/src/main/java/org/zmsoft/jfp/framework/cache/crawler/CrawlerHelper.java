package org.zmsoft.jfp.framework.cache.crawler;

import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;

/**
 * 爬虫缓存
 */
public class CrawlerHelper {
	final static String CHK = "CrawlerCache:";

	public static void addSessionAttribute(String key, String value) {
		ISCacheService myCacheService = BeanFactoryHelper.getBean("myCache");
		myCacheService.putObject(CHK + key, value, 3600, false);
	}

	public static String getSessionAttribute(String key) {
		ISCacheService myCacheService = BeanFactoryHelper.getBean("myCache");
		myCacheService.expire(CHK + key, 3600);
		return (String) myCacheService.getObject(CHK + key, false);
	}

	public static String removeSessionAttribute(String key) {
		ISCacheService myCacheService = BeanFactoryHelper.getBean("myCache");
		String value = (String) myCacheService.getObject(CHK + key, false);
		myCacheService.removeKey(CHK + key);
		return value;
	}
}
