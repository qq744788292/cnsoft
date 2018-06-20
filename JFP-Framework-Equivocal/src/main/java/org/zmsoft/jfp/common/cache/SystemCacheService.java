package org.zmsoft.jfp.common.cache;

import java.util.List;

import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.persistent.common.System.SystemDBO;
import org.zmsoft.jfp.persistent.common.System.SystemService;

/**
 * 系统信息缓存
 * 
 * @author cq
 * @version 0.0.1 2017/07/12
 * @since 0.0.1 2017/07/12
 */
public class SystemCacheService extends ADataCacheSupport<SystemDBO> {

	/**
	 * 获取一个分类的配置信息
	 *
	 * @param gameId
	 * @return
	 */
	public List<SystemDBO> loadSystemDatas() {
		String ck = CACHE_COMMON_SYSTEM + 0;
		List<SystemDBO> configDatas = loadCacheArray(ck, SystemDBO.class);
		if (EmptyHelper.isEmpty(configDatas) || configDatas.isEmpty()) {
			configDatas = loadSystemDataFromDB(EMPTY);
			// 添加到缓存
			putCache(ck, configDatas);

			// 独立缓存
			for (SystemDBO item : configDatas) {
				putCache(CACHE_COMMON_SYSTEM + item.getKey(), item);
			}
		}
		return configDatas;
	}

	/**
	 * 获取配置信息
	 * 
	 * @return
	 */
	public SystemDBO loaSystemData(String key) {
		String ck = CACHE_COMMON_SYSTEM + key;
		SystemDBO configData = loadCacheClass(ck, SystemDBO.class);
		if (EmptyHelper.isEmpty(configData)) {
			List<SystemDBO> configDatas = loadSystemDataFromDB(key);
			configData = configDatas.get(0);
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
	public List<SystemDBO> loadSystemDataFromDB(String key) {
		SystemService service = BeanFactoryHelper.getBean("SystemService");
		SystemDBO param = new SystemDBO();
		param.setKey(key);
		List<SystemDBO> systemDatas = service.doSelectData(param);
		return systemDatas;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void loadCacheData() {
		logger.debug("loadCacheData");
		loadSystemDataFromDB(EMPTY);
	}

	@Override
	public void removeCacheData(SystemDBO param) {
		myCacheService.removeKey(CACHE_COMMON_SYSTEM + 0);
		myCacheService.removeKey(CACHE_COMMON_SYSTEM + param.getKey());

	}

}
