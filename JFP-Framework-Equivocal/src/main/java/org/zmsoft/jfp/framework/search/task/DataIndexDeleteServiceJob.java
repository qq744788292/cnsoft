package org.zmsoft.jfp.framework.search.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.search.ESQuerySentenceFactory;
import org.zmsoft.jfp.framework.search.ISSentenceConstants;
import org.zmsoft.jfp.framework.search.biz.ESDataService;
import org.zmsoft.jfp.framework.support.MyTaskSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

/**
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class DataIndexDeleteServiceJob extends MyTaskSupport {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	ESQuerySentenceFactory myQuerySentence;

	public ESQuerySentenceFactory getMyQuerySentence() {
		return myQuerySentence;
	}

	public void setMyQuerySentence(ESQuerySentenceFactory myQuerySentence) {
		this.myQuerySentence = myQuerySentence;
	}

	Map<String, String> delKeys;

	public Map<String, String> getDelKeys() {
		return delKeys;
	}

	public void setDelKeys(Map<String, String> delKeys) {
		this.delKeys = delKeys;
	}

	ESDataService dataService;

	public ESDataService getDataService() {
		return dataService;
	}

	public void setDataService(ESDataService dataService) {
		this.dataService = dataService;
	}

	public boolean doProcessRepeat() throws Exception {
		logger.info("全文检索索引数据删除业务  >>>>>===== 开始");
		if (EmptyHelper.isEmpty(delKeys)) {
			logger.info("全文检索索引数据删除业务  xxxxxxxxxx 中断");
			return false;
		}
		Iterator<Entry<String, String>> iter = delKeys.entrySet().iterator();
		String fid = "";
		String id = "";
		String idx = "";
		String redisKey = "";
		ArrayList<String> datas;
		while (iter.hasNext()) {
			datas = new ArrayList<String>();
			Map.Entry<String, String> entry = iter.next();
			redisKey = entry.getValue();// 缓存Key
			String[] s = entry.getKey().split("#");// 索引名字
			if (s.length > 0)
				idx = s[0];
			if (s.length > 1)
				fid = s[1];
			logger.info("全文检索索引数据删除业务  >>>>>===== 开始 ..... " + entry);
//			if (myQuerySentence.containsIndex(idx) == false) {
//				// myCacheService.removeKey(ISSentenceConstants.COMPANY_DEL +
//				// key);
//				logger.info("全文检索索引数据删除业务  xxxxx===== 取消 ..... " + entry);
//				continue;
//			}
			id = (String) myCacheService.pollFirstObjectInList(ISSentenceConstants.INDEX_DEL + redisKey, false);
			while (EmptyHelper.isNotEmpty(id) == true) {
				id = fid + id;
				datas.add(id);
				id = (String) myCacheService.pollFirstObjectInList(ISSentenceConstants.INDEX_DEL + redisKey, false);
				logger.info("    删除数据......" + redisKey + "=" + id);
				if (datas.size() >= 100) {
					dataService.deleteDataInIndex(idx, datas);
					datas = new ArrayList<String>();
					Thread.sleep(1000);
				}
			}
			if (datas.size() > 0)
				dataService.deleteDataInIndex(idx, datas);
			logger.info("全文检索索引数据删除业务  =====<<<<< 结束 ..... " + entry);
		}

		myCacheService.init();
		logger.info("全文检索索引数据删除业务  <<<<<===== 结束");
		return true;
	}

}