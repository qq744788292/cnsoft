package org.zmsoft.jfp.cache;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zmsoft.jfp.common.cache.ADataCacheSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * TODO 分类缓存读取
 * 
 * @author tangmm
 *
 */
@Service("ClassifyTagCacheBiz")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClassifyTagCacheBiz extends ADataCacheSupport<JSONObject> {
//	/**
//	 * 获取分类列表信息
//	 * 
//	 * @param puk
//	 * @return
//	 */
//	public List<R901020ClassifyDBO> getClassifyTypeList(String systype, boolean load) {
//		Object cache = loadCache(IMaterialConstants.CLASSIFY_SYS_TYPE + 1000);
//		List<R901020ClassifyDBO> list;
//		if (cache == null || load) {
//			R901020ClassifyService _R901020ClassifyService_ = BeanFactoryHelper.getBean("R901020ClassifyService");// 分类信息
//			R901020ClassifyDBO _R901020ClassifyDBO_ = new R901020ClassifyDBO();
//			_R901020ClassifyDBO_.setSysType(systype);//"1000"
//			list = _R901020ClassifyService_.doSelectData(_R901020ClassifyDBO_);
//			if (EmptyHelper.isEmpty(list)) {
//				return null;
//			}
//			putCache(IMaterialConstants.CLASSIFY_SYS_TYPE + 1000, list);
//		} else {
//			list = JSON.parseArray((String) cache, R901020ClassifyDBO.class);
//		}
//		return list;
//	}
//
//	/**
//	 * 获取分类标签列表
//	 * 
//	 * @return
//	 */
//	public List<R901030TagDBO> getClassifyTagList(String classifyId, boolean load) {
//		Object cache = loadCache(IMaterialConstants.CACHE_FILMDOWN_CLASSIFY_TAG + classifyId);
//		List<R901030TagDBO> list;
//		if (cache == null || load) {
//			// 单集
//			R901030TagService _R901030TagService_ = BeanFactoryHelper.getBean("R901030TagService");// 基本素材
//			R901030TagDBO _R901030TagDBO_ = new R901030TagDBO();
//			_R901030TagDBO_.setClassifyId(classifyId);
//			list = _R901030TagService_.doSelectData(_R901030TagDBO_);
//			if (EmptyHelper.isEmpty(list)) {
//				return null;
//			}
//			putCache(IMaterialConstants.CACHE_FILMDOWN_CLASSIFY_TAG + classifyId, list);
//		}else {
//			list = JSON.parseArray((String) cache, R901030TagDBO.class);
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
