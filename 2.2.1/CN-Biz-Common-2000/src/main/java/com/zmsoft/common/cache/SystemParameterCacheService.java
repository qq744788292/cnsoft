package com.zmsoft.common.cache;

import java.util.List;

import org.springframework.stereotype.Component;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.EmptyHelper;

import com.zmsoft.persistent.common.SystemParameter.SystemParameterDBO;
import com.zmsoft.persistent.common.SystemParameter.SystemParameterService;

/**
 * 参数定义缓存
 * 
 * @author ZmSoft
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
	public List<SystemParameterDBO> loadCacheDatas(String type) {
		SystemParameterDBO param = new SystemParameterDBO();
		param.setType(type);
		return loadCacheDatas(1, true, param);
	}

	/**
	 * 获取参数定义信息
	 * 
	 * @return
	 */
	public SystemParameterDBO loadCacheData(String type, String key) {
		SystemParameterDBO param = new SystemParameterDBO();
		param.setType(type);
		param.setKey(key);
		return loadCacheData(param);
	}

	/**
	 * 获取参数定义信息
	 */
	public SystemParameterDBO loadCacheData(SystemParameterDBO param) {
		String key = CACHE_COMMON_PARAMETER + param.getType() + COLON + param.getKey();
		SystemParameterDBO configData = loadCacheClass(key, SystemParameterDBO.class);
		if (EmptyHelper.isEmpty(configData)) {
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
		if (EmptyHelper.isEmpty(configDatas)) {
			configDatas = getDatasFromDB(1, param);

			// 添加到缓存
			if (EmptyHelper.isNotEmpty(param.getType()))
				putCache(key, configDatas);

			// 独立缓存
			for (SystemParameterDBO item : configDatas) {
				putCache(CACHE_COMMON_PARAMETER + item.getType() + COLON + item.getKey(), item);
			}
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
		SystemParameterService service = MyBeanFactoryHelper.getBean(SystemParameterService.class.getSimpleName());
		List<SystemParameterDBO> systemDatas = service.doSelectData(param);
		logger.debug("SystemParameterDBO=====>>>>>" + systemDatas);
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
		SystemParameterService service = MyBeanFactoryHelper.getBean(SystemParameterService.class.getSimpleName());

		return service.doRead(param);
	}

	public void loadDefaultCacheDatas() {
		loadCacheDatas(EMPTY);
	}
}
