package org.cnsoft.common.cache;

import java.util.List;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.persistent.common.SystemClassify.SystemClassifyDBO;
import org.cnsoft.persistent.common.SystemClassify.SystemClassifyDao;
import org.springframework.stereotype.Component;

/**
 * 业务分类缓存
 * 
 * @author CNSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Component("SystemClassifyCacheService")
public class SystemClassifyCacheService extends ASystemDataCacheSupport<SystemClassifyDBO> {
	public SystemClassifyDBO loadClassify(String classifyId) {
		SystemClassifyDBO param = new SystemClassifyDBO();
		param.setClassifyId(classifyId);
		return loadCacheData(false, param);
	}

	public List<SystemClassifyDBO> loadClassifys(boolean load) {
		return loadClassifys(EMPTY, load);
	}

	/**
	 * 获取一个分类的配置信息
	 *
	 * @param gameId
	 * @return
	 */
	public List<SystemClassifyDBO> loadClassifys(String sid, boolean load) {
		String key = CACHE_COMMON_CLASSIFY;
		if (EmptyHelper.isEmpty(sid)) {
			key = key + ZERO;
		} else {
			key = key + sid;
		}
		List<SystemClassifyDBO> configDatas = loadCacheArray(key, SystemClassifyDBO.class);
		if (EmptyHelper.isEmpty(configDatas) && load) {
			SystemClassifyDBO param = new SystemClassifyDBO();
			param.setSid(sid);
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

	public SystemClassifyDBO loadCacheData(boolean load, SystemClassifyDBO param) {
		String key = CACHE_COMMON_CLASSIFY + param.getClassifyId();
		SystemClassifyDBO configData = loadCacheClass(key, SystemClassifyDBO.class);
		if (load && EmptyHelper.isEmpty(configData)) {
			putCache(key, configData);
		}
		return configData;
	}

	public List<SystemClassifyDBO> loadCacheDatas(int pageCurrent, boolean load, SystemClassifyDBO param) {
		return loadClassifys(load);
	}

	/**
	 * 从数据库里读取
	 * 
	 * @param type
	 * @return
	 */
	public List<SystemClassifyDBO> getDatasFromDB(int pageCurrent, SystemClassifyDBO param) {
		SystemClassifyDao service = MyBeanFactoryHelper.getBean(SystemClassifyDao.class);
		List<SystemClassifyDBO> systemDatas = service.doSelectData(param);
		logger.debug("SystemClassifyDBO=====>>>>>" + systemDatas);
		return systemDatas;
	}

	public void loadDefaultCacheDatas(boolean load) {
		loadClassifys(load);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void removeCacheData(SystemClassifyDBO param) {
		myCacheService.removeKey(CACHE_COMMON_CLASSIFY + 0);
		myCacheService.removeKey(CACHE_COMMON_CLASSIFY + param.getClassifyId());

	}

	@Override
	public SystemClassifyDBO getDataFromDB(SystemClassifyDBO param) {
		SystemClassifyDao service = MyBeanFactoryHelper.getBean(SystemClassifyDao.class);
		return service.doRead(param);
	}
}
