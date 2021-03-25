package org.cnsoft.framework.config.system.utils;

import java.util.Map.Entry;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.cache.redis.MyCacheService;
import org.cnsoft.framework.common.ISConfig;
import org.cnsoft.framework.constants.ICCacheConstants;
import org.cnsoft.framework.utils.DateHelper;

/**
 * 配置信息同步
 */
public class MyConfigHelper {

	/**
	 * 系统配置初始化
	 * @param reload
	 * @throws Exception
	 */
	public static void prepareSystemConfig(boolean reload) throws Exception {
		for (Entry<String, ISConfig> item : MyBeanFactoryHelper.getBeans(ISConfig.class).entrySet()) {
			ISConfig config = item.getValue();
			//开启自动重载数据库配置
			config.setReload(reload);
			//初始化
			config.init();
			//其他内容更新
			config.otherWise();
		}
	}
	
	/**
	 * 系统配置初始化
	 * @param reload
	 * @throws Exception
	 */
	public static String reloadConfig() throws Exception {
		MyCacheService myCacheService = MyBeanFactoryHelper.getBean(MyCacheService.class);
		String tm = DateHelper.currentTimestamp();
		myCacheService.putObject(ICCacheConstants.CACHE_LAST_SYNC_TIME, tm);
		return tm;
	}
	
}