package org.zmsoft.jfp.framework.search.biz;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zmsoft.jfp.framework.search.MySearchSupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.BulkResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Update;

/**
 * 数据管理
 * 
 * @author 001745
 */
//@Service("ESDataService")
public class ESDataService extends MySearchSupport{

	// 添加数据
	/**
	 * 向一个已知索引里面添加数据
	 * 
	 * @throws Exception
	 */
	public void addDataInIndex(String index, Object data) throws Exception {
		BulkResult result;
		JestClient jestClient = myElasticsearchPool.getClient();
		Builder bulkIndexBuilder = new Bulk.Builder();
		try {
			if(data instanceof String)
				bulkIndexBuilder.addAction(new Index.Builder(data).index(index).type(ES_TYPE).build());
			else if(data instanceof JSON)
				bulkIndexBuilder.addAction(new Index.Builder(""+data).index(index).type(ES_TYPE).build());
			else
				bulkIndexBuilder.addAction(new Index.Builder(JSON.toJSONString(data)).index(index).type(ES_TYPE).build());
				// 分批提交数据
				
			result = jestClient.execute(bulkIndexBuilder.build());
			logger.debug("deleteIndex===>>>ErrorMessage=" + result.getErrorMessage() + ",JsonString=" + result.getJsonString());
		} finally {
			if (jestClient != null)
				jestClient.close();
		}
	
	}
	public void addDataInIndex(String index, List<? extends Object> datas) throws Exception {
		BulkResult result;
		JestClient jestClient = myElasticsearchPool.getClient();
		Builder bulkIndexBuilder = new Bulk.Builder();
		try {
			for (int i = 0; i < datas.size(); i++) {
				Object data = datas.get(i);
				if(data instanceof String)
					bulkIndexBuilder.addAction(new Index.Builder(data).index(index).type(ES_TYPE).build());
				else if(data instanceof JSON)
					bulkIndexBuilder.addAction(new Index.Builder(""+data).index(index).type(ES_TYPE).build());
				else
					bulkIndexBuilder.addAction(new Index.Builder(JSON.toJSONString(data)).index(index).type(ES_TYPE).build());
				// 分批提交数据
				if (i % size == 1) {
					result = jestClient.execute(bulkIndexBuilder.build());
					logger.debug("num===" + i + "...." + result.getJsonString());
					Thread.sleep(500);
					//jestClient = myElasticsearchPool.getClient();
					bulkIndexBuilder = new Bulk.Builder();
				}
			}
			// 提交剩余数据
			result = jestClient.execute(bulkIndexBuilder.build());
		} finally {
			if (jestClient != null)
				jestClient.close();
		}
	}

	// 更新数据
	public void updateDataInIndex(String index, Object data) throws Exception {
		BulkResult result;
		JestClient jestClient = myElasticsearchPool.getClient();
		Builder bulkIndexBuilder = new Bulk.Builder();
		try {
			if(data instanceof String)
				bulkIndexBuilder.addAction(new Update.Builder(data).index(index).type(ES_TYPE).build());
			else if(data instanceof JSON)
				bulkIndexBuilder.addAction(new Update.Builder(""+data).index(index).type(ES_TYPE).build());
			else
				bulkIndexBuilder.addAction(new Update.Builder(JSON.toJSONString(data)).index(index).type(ES_TYPE).build());
				// 分批提交数据
				
			result = jestClient.execute(bulkIndexBuilder.build());
			logger.debug("deleteIndex===>>>ErrorMessage=" + result.getErrorMessage() + ",JsonString=" + result.getJsonString());
		} finally {
			if (jestClient != null)
				jestClient.close();
		}
	}
	public void updateDataInIndex(String index, List<? extends Object> datas) throws Exception {
		BulkResult result;
		JestClient jestClient = myElasticsearchPool.getClient();
		Builder bulkIndexBuilder = new Bulk.Builder();
		try {
			for (int i = 0; i < datas.size(); i++) {
				Object data = datas.get(i);
				if(data instanceof String)
					bulkIndexBuilder.addAction(new Update.Builder(data).index(index).type(ES_TYPE).build());
				else if(data instanceof JSON)
					bulkIndexBuilder.addAction(new Update.Builder(""+data).index(index).type(ES_TYPE).build());
				else
					bulkIndexBuilder.addAction(new Update.Builder(JSON.toJSONString(data)).index(index).type(ES_TYPE).build());
				// 分批提交数据
				if (i % size == 1) {
					result = jestClient.execute(bulkIndexBuilder.build());
					logger.debug("num===" + i + "...." + result.getJsonString());
					Thread.sleep(500);
					//jestClient = myElasticsearchPool.getClient();
					bulkIndexBuilder = new Bulk.Builder();
				}
			}
			// 提交剩余数据
			result = jestClient.execute(bulkIndexBuilder.build());
		} finally {
			if (jestClient != null)
				jestClient.close();
		}
	}
	
	// 删除数据
	public void deleteDataInIndex(String index, Object data) throws Exception {
		BulkResult result;
		JestClient jestClient = myElasticsearchPool.getClient();
		Builder bulkIndexBuilder = new Bulk.Builder();
		try {
			if(data instanceof String)
				bulkIndexBuilder.addAction(new Delete.Builder(""+data).index(index).type(ES_TYPE).build());
			else if(data instanceof JSON)
				bulkIndexBuilder.addAction(new Delete.Builder(""+((JSONObject)data).getString("id")).index(index).type(ES_TYPE).build());
				
			result = jestClient.execute(bulkIndexBuilder.build());
			logger.debug("deleteIndex===>>>ErrorMessage=" + result.getErrorMessage() + ",JsonString=" + result.getJsonString());
		} finally {
			if (jestClient != null)
				jestClient.close();
		}
	
		
	}
	
	public void deleteDataInIndex(String index, List<? extends Object> datas) throws Exception {
		BulkResult result;
		JestClient jestClient = myElasticsearchPool.getClient();
		Builder bulkIndexBuilder = new Bulk.Builder();
		try {
			for (int i = 0; i < datas.size(); i++) {
				Object data = datas.get(i);
				if(data instanceof String)
					bulkIndexBuilder.addAction(new Delete.Builder(""+data).index(index).type(ES_TYPE).build());
				else if(data instanceof JSON)
					bulkIndexBuilder.addAction(new Delete.Builder(""+((JSONObject)data).getString("id")).index(index).type(ES_TYPE).build());
				// 分批提交数据
				if (i % size == 1) {
					result = jestClient.execute(bulkIndexBuilder.build());
					logger.debug("num===" + i + "...." + result.getJsonString());
					Thread.sleep(500);
					//jestClient = myElasticsearchPool.getClient();
					bulkIndexBuilder = new Bulk.Builder();
				}
			}
			// 提交剩余数据
			result = jestClient.execute(bulkIndexBuilder.build());
		} finally {
			if (jestClient != null)
				jestClient.close();
		}	
	}

}
