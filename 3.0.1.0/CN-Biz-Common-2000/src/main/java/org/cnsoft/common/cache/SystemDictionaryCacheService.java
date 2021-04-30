package org.cnsoft.common.cache;

import java.util.List;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.persistent.common.SystemDictionary.SystemDictionaryDBO;
import org.cnsoft.persistent.common.SystemDictionary.SystemDictionaryDao;
import org.springframework.stereotype.Component;

/**
 * 数据字典缓存
 * 
 * @author CNSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Component("SystemDictionaryCacheService")
public class SystemDictionaryCacheService extends ASystemDataCacheSupport<SystemDictionaryDBO> {

	/**
	 * 获取一个分类的参数定义信息
	 *
	 * @param gameId
	 * @return
	 */
	public List<SystemDictionaryDBO> loadDictionariess(boolean load, String type) {
		SystemDictionaryDBO param = new SystemDictionaryDBO();
		param.setType(type);
		return loadCacheDatas(1, load, param);
	}

	/**
	 * 获取参数定义信息
	 * 
	 * @return
	 */
	public SystemDictionaryDBO loadDictionary(boolean load, String type, String key) {
		SystemDictionaryDBO param = new SystemDictionaryDBO();
		param.setType(type);
		param.setKey(key);
		return loadCacheData(load, param);
	}

	/**
	 * 获取参数定义信息
	 */
	public SystemDictionaryDBO loadCacheData(boolean load, SystemDictionaryDBO param) {
		String key = CACHE_COMMON_PARAMETER + param.getType() + COLON + param.getKey();
		SystemDictionaryDBO configData = loadCacheClass(key, SystemDictionaryDBO.class);
		if (load || EmptyHelper.isEmpty(configData)) {
			configData = getDataFromDB(param);
			// 添加到缓存
			if (EmptyHelper.isNotEmpty(configData))
				putCache(key, configData);
		}
		return configData;
	}

	public List<SystemDictionaryDBO> loadCacheDatas(int pageCurrent, boolean load, SystemDictionaryDBO param) {
		String key = CACHE_COMMON_PARAMETER + param.getType() + COLON + 0;
		List<SystemDictionaryDBO> configDatas = loadCacheArray(key, SystemDictionaryDBO.class);
		if (load || EmptyHelper.isEmpty(configDatas)) {
			configDatas = getDatasFromDB(1, param);

			// 添加到缓存
			if (EmptyHelper.isNotEmpty(param.getType()))
				putCache(key, configDatas);

			// 独立缓存
			// for (SystemDictionaryDBO item : configDatas) {
			// putCache(CACHE_COMMON_PARAMETER + item.getType() + COLON +
			// item.getKey(), item);
			// }
		}
		return configDatas;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void removeCacheData(SystemDictionaryDBO param) {
		myCacheService.removeKey(CACHE_COMMON_PARAMETER + param.getType() + COLON + 0);
		myCacheService.removeKey(CACHE_COMMON_PARAMETER + param.getType() + COLON + param.getKey());
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 从数据库里读取
	 * 
	 * @param type
	 * @return
	 */
	public List<SystemDictionaryDBO> getDatasFromDB(int pageCurrent, SystemDictionaryDBO param) {
		SystemDictionaryDao service = MyBeanFactoryHelper.getBean(SystemDictionaryDao.class);
		List<SystemDictionaryDBO> systemDatas = service.doSelectData(param);
		// logger.debug("SystemDictionaryDBO=====>>>>>" + systemDatas);
		return systemDatas;
	}

	public List<SystemDictionaryDBO> getDataFromDB(String type) {
		SystemDictionaryDBO param = new SystemDictionaryDBO();
		param.setType(type);
		return getDatasFromDB(1, param);
	}

	/**
	 * 获取配置数据
	 * 
	 * @return
	 */
	public SystemDictionaryDBO getDataFromDB(SystemDictionaryDBO param) {
		SystemDictionaryDao service = MyBeanFactoryHelper.getBean(SystemDictionaryDao.class);

		return service.doRead(param);
	}

	public void loadDefaultCacheDatas(boolean load) {
		loadDictionariess(load, EMPTY);
	}
}
