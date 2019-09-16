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
@Service("MaterialListCacheBiz")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MaterialListCacheBiz extends ADataCacheSupport<JSONObject> {

//	/**
//	 * 搜索
//	 * 
//	 * @param materialId
//	 * @param load
//	 * @param pageCurrent
//	 * @return
//	 */
//	public List<R901010MaterialDBO> getSearchList(String pv1, boolean load, int pageCurrent, int pageLimit) {
//		Object cache = loadCache(IFilmDownConstants.CACHE_FILMDOWN_SEARCH_MATERIAL + pv1 + pageCurrent + pageLimit);
//		List<R901010MaterialDBO> list;
//		if (cache == null || load) {
//			list = loadMaterialList(EMPTY, EMPTY, pv1, "ORDER BY r901010_article.online_time DESC", pageCurrent, pageLimit);
//			if (EmptyHelper.isEmpty(list)) {
//				return null;
//			}
//			putCache(IFilmDownConstants.CACHE_FILMDOWN_SEARCH_MATERIAL + pv1 + pageCurrent, list);
//		} else {
//			list = JSON.parseArray((String) cache, R901010MaterialDBO.class);
//		}
//		return list;
//	}
//
//	/**
//	 * 推荐
//	 * 
//	 * @param materialId
//	 * @param load
//	 * @param pageCurrent
//	 * @return
//	 */
//	public List<R901010MaterialDBO> getTopList(String classifyId, String tagId, boolean load, int pageCurrent, int pageLimit) {
//		Object cache = loadCache(IFilmDownConstants.CACHE_FILMDOWN_TOP_MATERIAL + pageCurrent + pageLimit);
//		List<R901010MaterialDBO> list;
//		if (cache == null || load) {
//			list = loadMaterialList(classifyId, tagId, EMPTY, "ORDER BY r901010_article.top DESC", pageCurrent, pageLimit);
//			if (EmptyHelper.isEmpty(list)) {
//				return null;
//			}
//			putCache(IFilmDownConstants.CACHE_FILMDOWN_TOP_MATERIAL + 0 + pageCurrent, list);
//		} else {
//			list = JSON.parseArray((String) cache, R901010MaterialDBO.class);
//		}
//		return list;
//	}
//
//	/**
//	 * 排行
//	 * 
//	 * @param classifyId
//	 * @param tagId
//	 * @param load
//	 * @param pageCurrent
//	 * @return
//	 */
//	public List<R901010MaterialDBO> getHotList(String classifyId, String tagId, boolean load, int pageCurrent, int pageLimit) {
//		String key = IFilmDownConstants.CACHE_FILMDOWN_HOT_MATERIAL + classifyId + tagId + pageCurrent + pageLimit;
//		Object cache = loadCache(key);
//		List<R901010MaterialDBO> list;
//		if (cache == null || load) {
//			list = loadMaterialList(classifyId, tagId, EMPTY, "ORDER BY r901010_article.num_read DESC", pageCurrent, pageLimit);
//			if (EmptyHelper.isEmpty(list)) {
//				return null;
//			}
//			putCache(key, list);
//		} else {
//			list = JSON.parseArray((String) cache, R901010MaterialDBO.class);
//		}
//		return list;
//	}
//
//	//////////////////////////////////////////////////////////////////////////////////////////////////
//	/**
//	 * 随便推荐数据，根据时间排序
//	 * 
//	 * @param materialId
//	 * @param load
//	 * @param pageCurrent
//	 * @return
//	 */
//	public List<R901010MaterialDBO> getNewList(String classifyId, String tagId, boolean load, int pageCurrent, int pageLimit) {
//		String key = IFilmDownConstants.CACHE_FILMDOWN_NEW_MATERIAL + classifyId + tagId + pageCurrent + pageLimit;
//		Object cache = loadCache(key);
//		List<R901010MaterialDBO> list;
//		if (cache == null || load) {
//			list = loadMaterialList(classifyId, tagId, EMPTY, "ORDER BY r901010_article.num_read DESC", pageCurrent, pageLimit);
//			if (EmptyHelper.isEmpty(list)) {
//				return null;
//			}
//			putCache(key, list);
//		} else {
//			list = JSON.parseArray((String) cache, R901010MaterialDBO.class);
//		}
//		return list;
//	}
//
//	//////////////////////////////////////////////////////////////////////////////////////////////////
//	/**
//	 * 根据最新数据，根据时间排序
//	 * 
//	 * @param materialId
//	 * @param load
//	 * @param pageCurrent
//	 * @return
//	 */
//	public List<R901010MaterialDBO> getRandomList(boolean load, int pageCurrent, int pageLimit) {
//		String key = IFilmDownConstants.CACHE_FILMDOWN_RANDOM_MATERIAL + pageCurrent + pageLimit;
//		Object cache = loadCache(key);
//		List<R901010MaterialDBO> list;
//		if (cache == null || load) {
//			list = loadMaterialList(EMPTY, EMPTY, EMPTY, "ORDER BY rand() LIMIT " + pageLimit, pageCurrent, 0);
//			if (EmptyHelper.isEmpty(list)) {
//				return null;
//			}
//			putCache(key, list);
//		} else {
//			list = JSON.parseArray((String) cache, R901010MaterialDBO.class);
//		}
//		return list;
//	}
//
//	/**
//	 * 根据类别获得素材列表，支持分页
//	 * 
//	 * @param materialId
//	 * @param load
//	 * @param pageCurrent
//	 * @return
//	 */
//	public List<R901010MaterialDBO> getClassifyMaterialList(String classifyId, boolean load, int pageCurrent, int pageLimit) {
//		String key = IFilmDownConstants.CACHE_FILMDOWN_CLASSIFY_MATERIAL + classifyId + pageCurrent + pageLimit;
//		Object cache = loadCache(key);
//		List<R901010MaterialDBO> list;
//		if (cache == null || load) {
//			list = loadMaterialList(classifyId, EMPTY, EMPTY, "ORDER BY r901010_article.online_time DESC", pageCurrent, pageLimit);
//			if (EmptyHelper.isEmpty(list)) {
//				return null;
//			}
//			putCache(key, list);
//		} else {
//			list = JSON.parseArray((String) cache, R901010MaterialDBO.class);
//		}
//		return list;
//	}
//
//	/**
//	 * 根据标签获得素材列表，支持分页
//	 * 
//	 * @param tagId
//	 * @param load
//	 * @param pageCurrent
//	 * @return
//	 */
//	public List<R901010MaterialDBO> getTagMaterialList(String tagId, boolean load, int pageCurrent, int pageLimit) {
//		String key = IFilmDownConstants.CACHE_FILMDOWN_TAG_MATERIAL + tagId + pageCurrent + pageLimit;
//		Object cache = loadCache(key);
//		List<R901010MaterialDBO> list;
//		if (cache == null || load) {
//			list = loadMaterialList(EMPTY, tagId, EMPTY, "ORDER BY r901010_article.online_time DESC", pageCurrent, pageLimit);
//			if (EmptyHelper.isEmpty(list)) {
//				return null;
//			}
//			putCache(key, list);
//		} else {
//			list = JSON.parseArray((String) cache, R901010MaterialDBO.class);
//		}
//		return list;
//	}
//
//	/////////////////////////////////////////////////////////////////////////////////////////////////////////
//	private List<R901010MaterialDBO> loadMaterialList(String classifyId, String tagId, String pv1, String orderBy, int pageCurrent, int pageLimit) {
//		List<R901010MaterialDBO> list;
//		// 持久层驱动
//		R901050MaterialClassifyTagService _R901050ArticleClassifyTagService_ = BeanFactoryHelper.getBean("R901050ArticleClassifyTagService");// 分类信息
//		R901050MaterialClassifyTagDBO _R901050ArticleClassifyTagDBO_ = new R901050MaterialClassifyTagDBO();
//		_R901050ArticleClassifyTagDBO_.setClassifyId(classifyId);// 分类ID
//		_R901050ArticleClassifyTagDBO_.setTagId(tagId);// 标签ID
//		_R901050ArticleClassifyTagDBO_.setPv1(pv1);
//		// 分页构造器
//		PageModel<R901010MaterialDBO> pageModel = BeanFactoryHelper.getBean("PageModel");
//		pageModel.setFormParamBean(_R901050ArticleClassifyTagDBO_);
//		pageModel.setPageCurrent(pageCurrent);
//		if (pageLimit > 0)
//			pageModel.setPageLimit(pageLimit);
//		else
//			pageModel.setPageLimit(16);
//		pageModel.setOrderby(orderBy);
//		list = _R901050ArticleClassifyTagService_.doSelectPageByClassifyTag(pageModel).getPageListData();
//		if (EmptyHelper.isEmpty(list)) {
//			return null;
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
