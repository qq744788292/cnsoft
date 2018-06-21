package org.zmsoft.jfp.framework.search.biz;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.zmsoft.jfp.framework.search.MySearchSupport;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;

/**
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
//@Service("ESIndexService")
public class ESIndexService extends MySearchSupport {

	/**
	 * 创建一个索引
	 * 
	 * @param index
	 *            索引名称，小写
	 * @throws Exception
	 */
	public void creatIndex(String index) throws Exception {
		JestClient jestClient = null;
		try {
			jestClient = myElasticsearchPool.getClient();
			JestResult result = jestClient.execute(new CreateIndex.Builder(index).build());
			logger.debug("creatIndex===" + result.getErrorMessage());
			logger.debug("creatIndex===" + result.getJsonString());
		} catch (Exception e) {
			logger.error("索引重建失败", e);
		} finally {
			if (jestClient != null)
				jestClient.close();
		}
	}

	/**
	 * 删除一个索引
	 * 
	 * @param index
	 * @throws IOException
	 */
	public void deleteIndex(String index) throws IOException {
		JestClient jestClient = null;
		try {
			jestClient = myElasticsearchPool.getClient();
			// 删除索引
			boolean indexExists = jestClient.execute(new IndicesExists.Builder(index).build()).isSucceeded();
			if (indexExists) {
				JestResult result = jestClient.execute(new DeleteIndex.Builder(index).build());
				logger.debug("deleteIndex===" + result.getErrorMessage());
				logger.debug("deleteIndex===" + result.getJsonString());
			}
		} catch (Exception e) {
			logger.error("索引重建失败", e);
		} finally {
			if (jestClient != null)
				jestClient.close();
		}
	}
}
