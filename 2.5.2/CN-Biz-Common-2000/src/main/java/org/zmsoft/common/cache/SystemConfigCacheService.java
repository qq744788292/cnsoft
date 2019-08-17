package org.zmsoft.common.cache;

import java.util.List;

import org.springframework.stereotype.Component;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.persistent.common.SystemConfig.SystemConfigDBO;
import org.zmsoft.persistent.common.SystemConfig.SystemConfigDao;

/**
 * 系统配置缓存
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Component("SystemConfigCacheService")
public class SystemConfigCacheService extends ASystemDataCacheSupport<SystemConfigDBO> {

	public List<SystemConfigDBO> loadCacheDatas(int pageCurrent, boolean load, SystemConfigDBO param) {
		return loadConfigs(false);
	}

	/**
	 * 获取一个系统配置信息
	 *
	 * @param gameId
	 * @return
	 */
	public List<SystemConfigDBO> loadConfigs(boolean load) {
		String key = CACHE_COMMON_CONFIG + 0;
		List<SystemConfigDBO> configDatas = loadCacheArray(key, SystemConfigDBO.class);
		if (load && EmptyHelper.isEmpty(configDatas)) {
			super.lock();
			SystemConfigDBO param = new SystemConfigDBO();
			configDatas = getDatasFromDB(1, param);
			// 添加到缓存
			putCache(key, configDatas);

			// // 独立缓存
			// for (SystemConfigDBO item : configDatas) {
			// putCache(CACHE_COMMON_CONFIG + item.getKey(), item);
			// }
		}
		return configDatas;
	}

	/**
	 * 从数据库里读取
	 * 
	 * @param type
	 * @return
	 */
	public List<SystemConfigDBO> getDatasFromDB(int pageCurrent, SystemConfigDBO param) {
		SystemConfigDao service = MyBeanFactoryHelper.getBean(SystemConfigDao.class.getSimpleName());
		List<SystemConfigDBO> systemDatas = service.doSelectData(param);
		// logger.debug("SystemConfigDBO=====>>>>>" + systemDatas);
		return systemDatas;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获取某个配置信息
	 * 
	 * @param key
	 * @return
	 */
	public SystemConfigDBO loadConfig(boolean load, String key) {
		SystemConfigDBO param = new SystemConfigDBO();
		param.setKey(key);
		return loadCacheData(load, param);
	}

	public SystemConfigDBO loadCacheData(boolean load, SystemConfigDBO param) {
		String key = CACHE_COMMON_CONFIG + param.getKey();
		SystemConfigDBO configData = loadCacheClass(key, SystemConfigDBO.class);
		if (load && EmptyHelper.isEmpty(configData)) {

			configData = getDataFromDB(param);
			if (EmptyHelper.isNotEmpty(configData)) {
				putCache(key, configData);
			}
		}
		return configData;
	}

	@Override
	public SystemConfigDBO getDataFromDB(SystemConfigDBO param) {
		SystemConfigDao service = MyBeanFactoryHelper.getBean(SystemConfigDao.class.getSimpleName());
		return service.doRead(param);
	}

	public void removeCacheData(SystemConfigDBO param) {
		myCacheService.removeKey(CACHE_COMMON_CONFIG + 0);
		myCacheService.removeKey(CACHE_COMMON_CONFIG + param.getKey());
	}

	public void loadDefaultCacheDatas(boolean load) {
		loadConfigs(load);
	}
}
