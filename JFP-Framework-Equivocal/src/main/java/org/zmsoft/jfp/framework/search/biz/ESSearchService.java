package org.zmsoft.jfp.framework.search.biz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.jfp.framework.search.ESQuerySentenceFactory;
import org.zmsoft.jfp.framework.search.ISPrepareSearchType;
import org.zmsoft.jfp.framework.search.MySearchSupport;
import org.zmsoft.jfp.framework.search.bean.QueryBean;
import org.zmsoft.jfp.framework.search.bean.QueryResultBean;

import com.alibaba.fastjson.JSON;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * 查询数据
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
//@Service("ESSearchService")
public class ESSearchService extends MySearchSupport {

	@Resource
	ESQuerySentenceFactory sentence;

	public QueryResultBean searchDataInIndex(String queryID, Object... param) throws Exception {
		QueryResultBean resultBean = new QueryResultBean();
		if (sentence.getSearchMap().containsKey(queryID) == false) {
			throw new RuntimeException("The queryID is not exit ! >>>>>[" + queryID + "]<<<<<");
		}
		JestClient jestClient = null;
		try {
			// 获得查询语句
			QueryBean qb = sentence.getSearch(queryID);
			// 设定参数
			String query = String.format(qb.getDsl(), param);
			logger.debug("query===================================" + query);
			// 封装查询语法
			Search.Builder searchBuilder = new Search.Builder(query).addIndex(qb.getIndex()).addType(ES_TYPE);
			// 获得连接
			jestClient = myElasticsearchPool.getClient();
			// 执行查询语句
			SearchResult result = jestClient.execute(searchBuilder.build());
			// 获得返回结果
			resultBean.setErrorMessage(result.getErrorMessage());
			logger.debug("getErrorMessage=====" + result.getErrorMessage());
			resultBean.setSucceeded(result.isSucceeded());
			if (result.isSucceeded()) {
				resultBean.setTotal(result.getTotal());
				logger.debug("getTotal=====" + result.getTotal());
				resultBean.setHits(result.getSourceAsStringList());
			}
		} finally {
			if (jestClient != null)
				jestClient.close();
		}
		return resultBean;
	}

	public List<? extends Object> searchDataInIndex(Class<?> clazz, ISPrepareSearchType prepare, String queryID, Object... param) throws Exception {
		QueryResultBean resultBean = searchDataInIndex(queryID, param);
		if (clazz != null) {
			List<Object> hits = new ArrayList<Object>();
			// 整理数据结果并返回
			Object cb;
			if (resultBean.getHits() != null && resultBean.getHits().size() > 0) {
				for (Object s : resultBean.getHits()) {
					cb = JSON.parseObject(s.toString(), clazz);
					if (prepare != null)
						prepare.prepareSearchType(JSON.parseObject(s.toString()), cb);
					hits.add(cb);
				}
			}
			return hits;
		} else
			return resultBean.getHits();
	}

}
