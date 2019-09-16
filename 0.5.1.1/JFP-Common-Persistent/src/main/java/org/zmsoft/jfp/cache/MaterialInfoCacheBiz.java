package org.zmsoft.jfp.cache;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zmsoft.jfp.common.cache.ADataCacheSupport;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 素材列表缓存读取
 * 
 * @author tangmm
 *
 */
@Service("MaterialInfoCacheBiz")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MaterialInfoCacheBiz extends ADataCacheSupport<JSONObject> {

//	/**
//	 * 单集素材信息
//	 * 
//	 * @param tagId
//	 * @param load
//	 * @param pageCurrent
//	 * @return
//	 */
//	public R901010MaterialDBO getBaseMaterialInfo(String materialId, boolean load) {
//		String key = IFilmDownConstants.CACHE_FILMDOWN_MATERIAL_INFO + materialId;
//		Object cache = loadCache(key);
//		R901010MaterialDBO info;
//		if (cache == null || load) {
//			// 持久层驱动
//			R901010MaterialService _R901010ArticleService_ = BeanFactoryHelper.getBean("R901010MaterialService");// 分类信息
//			R901010MaterialDBO _R901010ArticleDBO_ = new R901010MaterialDBO();
//			_R901010ArticleDBO_.setPuk(materialId);// 分类ID
//			info = _R901010ArticleService_.doRead(_R901010ArticleDBO_);
//			if (EmptyHelper.isEmpty(info)) {
//				return null;
//			}
//			putCache(key, info);
//		} else {
//			info = JSON.parseObject((String) cache, R901010MaterialDBO.class);
//		}
//		return info;
//	}
//
//	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	/**
//	 * 合集素材信息
//	 * 
//	 * @param materialId
//	 * @param load
//	 * @return
//	 */
//	public R901011CollectionDBO getCollectionMaterialInfo(String materialId, boolean load) {
//		String key = IFilmDownConstants.CACHE_FILMDOWN_MATERIAL_INFO + materialId + ":0";
//		Object cache = loadCache(key);
//		R901011CollectionDBO info;
//		if (cache == null || load) {
//			// 持久层驱动
//			R901011CollectionService _R901011CollectionService_ = BeanFactoryHelper.getBean("R901011CollectionService");// 分类信息
//			R901011CollectionDBO _R901011CollectionDBO_ = new R901011CollectionDBO();
//			_R901011CollectionDBO_.setPuk(materialId);// 分类ID
//			info = _R901011CollectionService_.doRead(_R901011CollectionDBO_);
//			if (EmptyHelper.isEmpty(info)) {
//				return null;
//			}
//			putCache(key, info);
//		} else {
//			info = JSON.parseObject((String) cache, R901011CollectionDBO.class);
//		}
//		return info;
//	}
//	/**
//	 * 合集素材信息
//	 * 
//	 * @param materialId
//	 * @param load
//	 * @return
//	 */
//	public List<R901010MaterialDBO> getCollectionMaterialList(String collectionid, boolean load) {
//		String key = IFilmDownConstants.CACHE_FILMDOWN_MATERIAL_INFO + collectionid + ":1";
//		Object cache = loadCache(key);
//		List<R901010MaterialDBO> list;
//		if (cache == null || load) {
//			// 持久层驱动
//			R901010MaterialService _R901010ArticleService_ = BeanFactoryHelper.getBean("R901010MaterialService");// 分类信息
//			R901010MaterialDBO _R901010ArticleDBO_ = new R901010MaterialDBO();
//			_R901010ArticleDBO_.setCollectionId(collectionid);// 分类ID
//			// 分页构造器
//			PageModel<R901010MaterialDBO> pageModel = BeanFactoryHelper.getBean("PageModel");
//			pageModel.setFormParamBean(_R901010ArticleDBO_);
//			pageModel.setOrderby("ORDER BY online_time DESC");
//			list = _R901010ArticleService_.doSelectPage(pageModel).getPageListData();
//			if (EmptyHelper.isEmpty(list)) {
//				return null;
//			}
//			putCache(key, list);
//		} else {
//			list = JSON.parseArray((String) cache, R901010MaterialDBO.class);
//		}
//		return list;
//	}
	
	@Override
	public void loadCacheData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCacheData(JSONObject param) {
		// TODO Auto-generated method stub

	}

}
