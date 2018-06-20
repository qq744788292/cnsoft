package org.zmsoft.jfp.framework.search.task;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.search.ESQuerySentenceFactory;
import org.zmsoft.jfp.framework.search.ISSentenceConstants;
import org.zmsoft.jfp.framework.search.bean.QueryBean;
import org.zmsoft.jfp.framework.support.MyTaskSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

/**
 * 全文检索参数缓存同步
 * 
 * @author 001745
 *
 */
public class QuerySentenceServiceJob extends MyTaskSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	// 缓存队列
	ESQuerySentenceFactory myQuerySentence;

	public ESQuerySentenceFactory getMyQuerySentence() {
		return myQuerySentence;
	}

	public void setMyQuerySentence(ESQuerySentenceFactory myQuerySentence) {
		this.myQuerySentence = myQuerySentence;
	}

	public boolean doProcessRepeat() throws Exception {
		logger.info("全文检索参数缓存同步业务  >>>>>===== 开始");
		// 数据整理,基于Redis进行缓存同步
		Set<String> keys;
		String value;
		{
			Map<String, QueryBean> searchMap = myQuerySentence.getSearchMap();
			keys = searchMap.keySet();
			for (String key : keys) {
				logger.debug("    searchMap >>>>>===== " + key);
				value = (String) myCacheService.getObject(ISSentenceConstants.SENTENCE_SCH + key, false);
				if (EmptyHelper.isNotEmpty(value))
					searchMap.put(key, JSON.parseObject(value, QueryBean.class));
			}
			myQuerySentence.setSearchMap(searchMap);
		}
		{
			Map<String, QueryBean> updateMap = myQuerySentence.getUpdateMap();
			keys = updateMap.keySet();
			for (String key : keys) {
				logger.debug("    updateMap >>>>>===== " + key);
				value = (String) myCacheService.getObject(ISSentenceConstants.SENTENCE_UPD + key, false);
				if (EmptyHelper.isNotEmpty(value))
					updateMap.put(key, JSON.parseObject(value, QueryBean.class));
			}
			myQuerySentence.setUpdateMap(updateMap);
		}
		{
			Map<String, QueryBean> creatMap = myQuerySentence.getCreatMap();
			keys = creatMap.keySet();
			for (String key : keys) {
				logger.debug("    creatMap >>>>>===== " + key);
				value = (String) myCacheService.getObject(ISSentenceConstants.SENTENCE_CRT + key, false);
				if (EmptyHelper.isNotEmpty(value))
					creatMap.put(key, JSON.parseObject(value, QueryBean.class));
			}
			myQuerySentence.setCreatMap(creatMap);
		}
		logger.info("全文检索参数缓存同步业务  <<<<<===== 结束");
		return true;
	}

}
