package org.zmsoft.jfp.common.cache;

import java.util.List;

import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.persistent.common.ConfigData.ConfigDataDBO;
import org.zmsoft.jfp.persistent.common.ConfigData.ConfigDataService;

/**
 * 配置信息缓存
 * 
 * @author cq
 * @version 0.0.1 2017/07/12
 * @since 0.0.1 2017/07/12
 */
public class ConfigCacheService extends ADataCacheSupport<ConfigDataDBO> {

	/**
	 * 获取一个分类的配置信息
	 *
	 * @param gameId
	 * @return
	 */
	public List<ConfigDataDBO> loadConfigDatas(String type) {
		String ck = CACHE_COMMON_CONFIG + type + 0;
		List<ConfigDataDBO> configDatas = loadCacheArray(ck, ConfigDataDBO.class);
		if (EmptyHelper.isEmpty(configDatas) || configDatas.isEmpty()) {
			configDatas = getConfigDatasFromDB(type);
			// 添加到缓存
			putCache(ck, configDatas);

			// 独立缓存
			for (ConfigDataDBO item : configDatas) {
				putCache(CACHE_COMMON_CONFIG + type + item.getKey(), item);
			}
		}
		return configDatas;
	}

	/**
	 * 获取配置信息
	 * 
	 * @return
	 */
	public ConfigDataDBO loadConfigData(String type, String key) {
		String ck = CACHE_COMMON_CONFIG + type + key;
		ConfigDataDBO configData = loadCacheClass(ck, ConfigDataDBO.class);
		if (EmptyHelper.isEmpty(configData)) {
			configData = loadConfigDataFromDB(type, key);
			// 添加到缓存
			putCache(ck, configData);

		}
		return configData;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 从数据库里读取
	 * 
	 * @param type
	 * @return
	 */
	public List<ConfigDataDBO> getConfigDatasFromDB(String type) {
		ConfigDataService service = BeanFactoryHelper.getBean("ConfigDataService");
		ConfigDataDBO param = new ConfigDataDBO();
		param.setType(type);
		List<ConfigDataDBO> configDatas = service.doSelectData(param);
		return configDatas;
	}

	/**
	 * 获取配置的值
	 * 
	 * @return
	 */
	public ConfigDataDBO loadConfigDataFromDB(String type, String key) {
		ConfigDataService service = BeanFactoryHelper.getBean("ConfigDataService");
		ConfigDataDBO param = new ConfigDataDBO();
		param.setType(type);
		param.setKey(key);
		ConfigDataDBO configData = service.doRead(param);
		return configData;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void loadCacheData() {
		
	}

	@Override
	public void removeCacheData(ConfigDataDBO param) {
		myCacheService.removeKey(CACHE_COMMON_CONFIG + param.getType() + 0);
		myCacheService.removeKey(CACHE_COMMON_CONFIG + param.getType() + param.getKey());

	}

}
