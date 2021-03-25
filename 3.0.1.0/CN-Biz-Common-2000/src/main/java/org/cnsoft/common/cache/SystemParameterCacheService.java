package org.cnsoft.common.cache;

import java.util.List;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.persistent.common.SystemParameter.SystemParameterDBO;
import org.cnsoft.persistent.common.SystemParameter.SystemParameterDao;
import org.springframework.stereotype.Component;

/**
 * 参数定义缓存
 * 
 * @author CNSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Component("SystemParameterCacheService")
public class SystemParameterCacheService extends ASystemDataCacheSupport<SystemParameterDBO> {

	/**
	 * 获取一个分类的参数定义信息
	 *
	 * @param gameId
	 * @return
	 */
	public List<SystemParameterDBO> loadParameters(boolean load, String type) {
		SystemParameterDBO param = new SystemParameterDBO();
		param.setType(type);
		return loadCacheDatas(1, load, param);
	}

	/**
	 * 获取参数定义信息
	 * 
	 * @return
	 */
	public SystemParameterDBO loadParameter(boolean load, String type, String key) {
		SystemParameterDBO param = new SystemParameterDBO();
		param.setType(type);
		param.setKey(key);
		return loadCacheData(load, param);
	}

	/**
	 * 获取参数定义信息
	 */
	public SystemParameterDBO loadCacheData(boolean load, SystemParameterDBO param) {
		String key = CACHE_COMMON_PARAMETER + param.getType() + COLON + param.getKey();
		SystemParameterDBO configData = loadCacheClass(key, SystemParameterDBO.class);
		if (load || EmptyHelper.isEmpty(configData)) {
			configData = getDataFromDB(param);
			// 添加到缓存
			if (EmptyHelper.isNotEmpty(configData))
				putCache(key, configData);
		}
		return configData;
	}

	public List<SystemParameterDBO> loadCacheDatas(int pageCurrent, boolean load, SystemParameterDBO param) {
		String key = CACHE_COMMON_PARAMETER + param.getType() + COLON + 0;
		List<SystemParameterDBO> configDatas = loadCacheArray(key, SystemParameterDBO.class);
		if (load || EmptyHelper.isEmpty(configDatas)) {
			configDatas = getDatasFromDB(1, param);

			// 添加到缓存
			if (EmptyHelper.isNotEmpty(param.getType()))
				putCache(key, configDatas);

			// 独立缓存
			// for (SystemParameterDBO item : configDatas) {
			// putCache(CACHE_COMMON_PARAMETER + item.getType() + COLON +
			// item.getKey(), item);
			// }
		}
		return configDatas;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void removeCacheData(SystemParameterDBO param) {
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
	public List<SystemParameterDBO> getDatasFromDB(int pageCurrent, SystemParameterDBO param) {
		SystemParameterDao service = MyBeanFactoryHelper.getBean(SystemParameterDao.class);
		List<SystemParameterDBO> systemDatas = service.doSelectData(param);
		// logger.debug("SystemParameterDBO=====>>>>>" + systemDatas);
		return systemDatas;
	}

	public List<SystemParameterDBO> getDataFromDB(String type) {
		SystemParameterDBO param = new SystemParameterDBO();
		param.setType(type);
		return getDatasFromDB(1, param);
	}

	/**
	 * 获取配置数据
	 * 
	 * @return
	 */
	public SystemParameterDBO getDataFromDB(SystemParameterDBO param) {
		SystemParameterDao service = MyBeanFactoryHelper.getBean(SystemParameterDao.class);

		return service.doRead(param);
	}

	public void loadDefaultCacheDatas(boolean load) {
		loadParameters(load, EMPTY);
	}
}
