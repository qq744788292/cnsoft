package com.zmsoft.common.cache;

import java.util.List;

import org.springframework.stereotype.Component;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.EmptyHelper;

import com.zmsoft.persistent.common.SystemClassify.SystemClassifyDBO;
import com.zmsoft.persistent.common.SystemClassify.SystemClassifyService;

/**
 * 业务分类缓存
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Component("SystemClassifyCacheService")
public class SystemClassifyCacheService extends ASystemDataCacheSupport<SystemClassifyDBO> {

	public List<SystemClassifyDBO> loadCacheDatas(boolean load) {
		return loadCacheDatas(EMPTY, load);
	}

	/**
	 * 获取一个分类的配置信息
	 *
	 * @param gameId
	 * @return
	 */
	public List<SystemClassifyDBO> loadCacheDatas(String hdp, boolean load) {
		String key = CACHE_COMMON_CLASSIFY;
		if (EmptyHelper.isEmpty(hdp)) {
			key = key + ZERO;
		} else {
			key = key + hdp;
		}
		List<SystemClassifyDBO> configDatas = loadCacheArray(key, SystemClassifyDBO.class);
		if (EmptyHelper.isEmpty(configDatas) && load) {
			SystemClassifyDBO param = new SystemClassifyDBO();
			param.setHdp(hdp);
			configDatas = getDatasFromDB(1, param);
			// 添加到缓存
			putCache(key, configDatas);

			// 独立缓存
			for (SystemClassifyDBO item : configDatas) {
				putCache(CACHE_COMMON_CLASSIFY + item.getClassifyId(), item);
			}
		}
		return configDatas;
	}

	public SystemClassifyDBO loadCacheData(SystemClassifyDBO param) {
		String key = CACHE_COMMON_CLASSIFY + param.getClassifyId();
		SystemClassifyDBO configData = loadCacheClass(key, SystemClassifyDBO.class);
		if (EmptyHelper.isEmpty(configData)) {

			putCache(key, configData);
		}
		return configData;
	}

	public List<SystemClassifyDBO> loadCacheDatas(int pageCurrent, boolean load, SystemClassifyDBO param) {
		return loadCacheDatas(true);
	}

	/**
	 * 从数据库里读取
	 * 
	 * @param type
	 * @return
	 */
	public List<SystemClassifyDBO> getDatasFromDB(int pageCurrent, SystemClassifyDBO param) {
		SystemClassifyService service = MyBeanFactoryHelper.getBean(SystemClassifyService.class.getSimpleName());
		List<SystemClassifyDBO> systemDatas = service.doSelectData(param);
		logger.debug("SystemClassifyDBO=====>>>>>" + systemDatas);
		return systemDatas;
	}

	public void loadDefaultCacheDatas() {
		loadCacheDatas(true);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获取配置信息
	 * 
	 * @return
	 */
	public SystemClassifyDBO loadCacheData(String classifyId) {
		SystemClassifyDBO param = new SystemClassifyDBO();
		param.setClassifyId(classifyId);
		return loadCacheData(param);
	}

	public void removeCacheData(SystemClassifyDBO param) {
		myCacheService.removeKey(CACHE_COMMON_CLASSIFY + 0);
		myCacheService.removeKey(CACHE_COMMON_CLASSIFY + param.getClassifyId());

	}

	@Override
	public SystemClassifyDBO getDataFromDB(SystemClassifyDBO param) {
		SystemClassifyService service = MyBeanFactoryHelper.getBean(SystemClassifyService.class.getSimpleName());
		return service.doRead(param);
	}
}
