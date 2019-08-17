package org.zmsoft.common.cache;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.EmptyHelper;

import com.zmsoft.persistent.framework.common.SystemConfig.SystemConfigDBO;
import com.zmsoft.persistent.framework.common.SystemConfig.SystemConfigService;

/**
 * 系统配置缓存
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Component("SystemConfigCacheService")
public class SystemConfigCacheService extends ASystemDataCacheSupport<SystemConfigDBO> {

	/**
	 * 获取一个系统配置信息
	 *
	 * @param gameId
	 * @return
	 */
	public List<SystemConfigDBO> loadCacheDatas(boolean load) {
		String key = CACHE_COMMON_CONFIG + 0;
		List<SystemConfigDBO> configDatas = loadCacheArray(key, SystemConfigDBO.class);
		if (EmptyHelper.isEmpty(configDatas) && load) {
			SystemConfigDBO param = new SystemConfigDBO();
			configDatas = getDataFromDB(param);
			// 添加到缓存
			putCache(key, configDatas);

			// 独立缓存
			for (SystemConfigDBO item : configDatas) {
				putCache(CACHE_COMMON_CONFIG + item.getKey(), item);
			}
		}
		return configDatas;
	}

	/**
	 * 获取某个配置信息
	 * 
	 * @param key
	 * @return
	 */
	public SystemConfigDBO loadCacheData(String key) {
		SystemConfigDBO param = new SystemConfigDBO();
		param.setKey(key);
		return loadCacheData(param);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public SystemConfigDBO loadCacheData(SystemConfigDBO param) {
		String key = CACHE_COMMON_CONFIG + param.getKey();
		SystemConfigDBO configData = loadCacheClass(key, SystemConfigDBO.class);
		if (EmptyHelper.isEmpty(configData)) {
			try {
				Random r = new Random();
				Thread.sleep((r.nextInt(10)) * 1000);
			} catch (InterruptedException e) {
			}
			List<SystemConfigDBO> list = getDataFromDB(param);
			if (EmptyHelper.isNotEmpty(list)) {
				configData = list.get(0);
				putCache(key, configData);
			}
		}
		return configData;
	}

	public List<SystemConfigDBO> loadCacheDatas(SystemConfigDBO param) {
		return loadCacheDatas(true);
	}

	public void removeCacheData(SystemConfigDBO param) {
		myCacheService.removeKey(CACHE_COMMON_CONFIG + 0);
		myCacheService.removeKey(CACHE_COMMON_CONFIG + param.getKey());
	}

	/**
	 * 从数据库里读取
	 * 
	 * @param type
	 * @return
	 */
	public List<SystemConfigDBO> getDataFromDB(SystemConfigDBO param) {
		SystemConfigService service = MyBeanFactoryHelper.getBean("SystemConfigService");
		List<SystemConfigDBO> systemDatas = service.doSelectData(param);
		logger.debug("SystemConfigDBO=====>>>>>"+systemDatas);
		return systemDatas;
	}
	
	////////////////////////////////////////////////////////
	public void loadDefaultCacheData() {
		loadCacheDatas(true);
	}
}
