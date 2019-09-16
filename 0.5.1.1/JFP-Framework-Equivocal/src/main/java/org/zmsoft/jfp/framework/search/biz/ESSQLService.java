package org.zmsoft.jfp.framework.search.biz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.zmsoft.jfp.framework.search.ISPrepareDataType;
import org.zmsoft.jfp.framework.search.MySearchSupport;
import org.zmsoft.jfp.framework.search.bean.QueryBean;
import org.zmsoft.jfp.framework.search.utils.IndexNameHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSONObject;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.BulkResult;
import io.searchbox.core.BulkResult.BulkResultItem;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.mapping.PutMapping;

/**
 * 基于数据库表建立索引
 * 
 * @author 001745
 *
 */
public class ESSQLService extends MySearchSupport {

	/**
	 * 分页查询并创建索引
	 * 
	 * @param tableName
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public void creatIndexBySQL(String actionID) throws Exception {
		creatIndexBySQL(actionID, ONE, EMPTY, EMPTY);
	}

	public void creatIndexBySQL(String actionID, String creatFlag, String from2, String size2) throws Exception {
		creatIndexBySQL(myESQuerySentenceFactory.getCreat(actionID), creatFlag, from2, size2);
	}

	public void creatIndexBySQL(ISPrepareDataType prepare, String actionID, String creatFlag, String from2, String size2) throws Exception {
		creatIndexBySQL(prepare, myESQuerySentenceFactory.getCreat(actionID), creatFlag, from2, size2);
	}

	public void creatIndexBySQL(QueryBean qb, String creatFlag, String from2, String size2) throws Exception {
		creatIndexBySQL(null, qb, creatFlag, from2, size2);
	}

	public void creatIndexBySQL(ISPrepareDataType prepare, QueryBean qb, String creatFlag, String from2, String size2) throws Exception {
		makeIndexBySQL(prepare, qb, creatFlag, from2, size2);
	}

	/**
	 * 分页查询并创建索引
	 * 
	 * @param tableName
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public void updateIndexBySQL(String actionID) throws Exception {
		updateIndexBySQL(actionID, EMPTY, EMPTY);
	}

	public void updateIndexBySQL(String actionID, String from2, String size2) throws Exception {
		updateIndexBySQL(myESQuerySentenceFactory.getUpdate(actionID), from2, size2);
	}

	public void updateIndexBySQL(ISPrepareDataType prepare, String actionID, String from2, String size2) throws Exception {
		updateIndexBySQL(prepare, myESQuerySentenceFactory.getUpdate(actionID), from2, size2);
	}

	public void updateIndexBySQL(QueryBean qb, String from2, String size2) throws Exception {
		updateIndexBySQL(null, qb, from2, size2);
	}

	public void updateIndexBySQL(ISPrepareDataType prepare, QueryBean qb, String from2, String size2) throws Exception {
		makeIndexBySQL(prepare, qb, ZERO, from2, size2);
	}

	private void makeIndexBySQL(ISPrepareDataType prepare, QueryBean qb, String creatFlag, String from2, String size2) throws Exception {
		logger.info("makeIndexBySQL=====>>>>>Start....." + qb.getId());
		logger.info("=====>>>>>=====>>>>>Index....." + qb.getIndex());
		// 关闭自动更新配置
		myCacheService.removeKey(SENTENCE_UTD + IndexNameHelper.getUpdateId(qb.getId()));
		try {
			if (EmptyHelper.isNotEmpty(from2))
				from = Integer.parseInt(from2);
			if (EmptyHelper.isNotEmpty(size2))
				size = Integer.parseInt(size2);

			JestClient jestClient = null;
			// 初始化判断
			if (ONE.equals(creatFlag)) {
				try {
					// 获得连接
					jestClient = getClient();
					String index = qb.getIndex();
					// 删除索引
					boolean indexExists = jestClient.execute(new IndicesExists.Builder(index).build()).isSucceeded();
					if (indexExists) {
						JestResult deleteIndexResult = jestClient.execute(new DeleteIndex.Builder(index).build());
						if (deleteIndexResult == null || deleteIndexResult.isSucceeded() == false) {
							throw new RuntimeException(deleteIndexResult.getErrorMessage() + "删除索引失败!，index=" + index);
						}
						logger.debug("deleteIndex===>>>ErrorMessage=" + deleteIndexResult.getErrorMessage() + ",JsonString=" + deleteIndexResult.getJsonString());
					}
					// 创建索引
					JestResult createIndexResult = jestClient.execute(new CreateIndex.Builder(index).build());
					logger.debug("createIndex===>>>ErrorMessage=" + createIndexResult.getErrorMessage() + ",JsonString=" + createIndexResult.getJsonString());
					if (createIndexResult == null || createIndexResult.isSucceeded() == false) {
						throw new RuntimeException(createIndexResult.getErrorMessage() + "创建索引失败!，index=" + index);
					}
					// 设定字段类型
					if (EmptyHelper.isEmpty(qb.getMapping()) == false) {
						JestResult putMappingResult = jestClient.execute(new PutMapping.Builder(index, ES_TYPE, qb.getMapping()).build());
						if (putMappingResult == null || putMappingResult.isSucceeded() == false) {
							throw new RuntimeException(putMappingResult.getErrorMessage() + "删除索引失败!，index=" + index);
						}
					}
				} finally {
					if (jestClient != null)
						jestClient.close();
				}
			}

			Builder bulkIndexBuilder;
			BulkResult result;
			int num = 0;
			int errorNum = 0;
			List<Index> actions = null;
			boolean commit = false;
			for (int c = 0; c <= Integer.MAX_VALUE; c++) {
				Thread.sleep(sleep * 2);
				while (commit == false) {
					try {
						actions = loadDataFromDb(prepare, qb);
						commit = true;
					} catch (Exception e) {
						logger.error("loadDataFromDb==xxxxxxxxxxxxxxxx=>>>", e);
						Thread.sleep(sleep * 2);
						if (errorNum > sleep * 2) {
							throw e;
						} else {
							errorNum = errorNum + 1;
						}
					}
				}
				logger.debug("getData======>>>num=" + ((c + 1) * size) + ",size=" + actions.size());
				// 分批提交数据
				if (actions.size() > 0) {
					commit = false;
					while (commit == false) {
						try {
							jestClient = getClient();
							bulkIndexBuilder = new Bulk.Builder();
							bulkIndexBuilder.addAction(actions);
							result = jestClient.execute(bulkIndexBuilder.build());
							Thread.sleep(sleep);
							commit = true;
							num = num + actions.size();
							logger.debug("addDataIntoIndex===>>>num=" + num + ",ErrorMessage=" + result.getErrorMessage());
							if (EmptyHelper.isNotEmpty(result.getErrorMessage())) {
								List<BulkResultItem> rs = result.getItems();
								for (int i = 0; i < rs.size(); i++) {
									logger.debug(i + "===>>>" + rs.get(i).error);
								}
							}
						} catch (Exception e) {
							logger.error("addDataIntoIndex===>>>", e);
							Thread.sleep(sleep * 2);
							if (errorNum > sleep * 2) {
								throw e;
							} else {
								errorNum = errorNum + 1;
							}
							if (jestClient != null)
								jestClient.close();
						}
					}
					commit = false;
				} else {
					break;
				}
			}
		} finally {
			// 开启自动更新配置
			myCacheService.putObject(SENTENCE_UTD + IndexNameHelper.getUpdateId(qb.getId()), "" + System.currentTimeMillis(), 0, false);
		}
		logger.info("makeIndexBySQL<<<<<=====End....." + qb.getId());
	}

	private List<Index> loadDataFromDb(ISPrepareDataType prepare, QueryBean qb) throws Exception {
		List<Index> actions = new ArrayList<Index>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		ResultSetMetaData metaData = null;

		// 获得数据库对象
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql = qb.getDsl();
			if (EmptyHelper.isEmpty(sql))
				throw new RuntimeException("不存在该索引语句");

			if (EmptyHelper.isEmpty(starttime))
				starttime = "1000-01-01 00:00:00";
			if (EmptyHelper.isEmpty(endtime))
				endtime = "9000-01-01 23:59:59";

			// 初始化运作
			{
				sql = sql.replace("{maxID}", "" + maxID);// 开始数据
				sql = sql.replace("{limit}", "" + size);// 分页限制
				sql = sql.replace("{starttime}", starttime);// 开始时间
				sql = sql.replace("{endtime}", endtime);// 终了时间
			}
			logger.debug("sql===>>>" + sql);
			logger.debug("   start===>>>" + System.currentTimeMillis());
			resultSet = stmt.executeQuery(sql);
			logger.debug("   end===>>>" + System.currentTimeMillis());
			metaData = resultSet.getMetaData();
			JSONObject data;
			// rs.beforeFirst();
			while (resultSet.next()) {
				try {
					String id = "";
					String rriidd = "";
					data = new JSONObject();
					for (int i = 1; i <= metaData.getColumnCount(); i++) {
						String columnName = metaData.getColumnLabel(i);
						Object value = resultSet.getObject(i);
						data.put(columnName.toLowerCase(), value);
					}

					// 数据整理
					if (prepare != null)
						data = prepare.prepareDataType(data);

					if (data.containsKey("rriidd")) {
						id = data.remove("id").toString();// 任意类型
						if (lastID.equals(id)) {
							continue;
						} else {
							lastID = id;
						}
						rriidd = data.get("rriidd").toString();// 数值型

						minID = Long.parseLong(rriidd);
						if (maxID < minID)
							maxID = minID;

						// 加入索引
						actions.add(new Index.Builder(data.toJSONString()).index(qb.getIndex()).id(id).type(ES_TYPE).build());
					} else {
						// 加入索引
						actions.add(new Index.Builder(data.toJSONString()).index(qb.getIndex()).type(ES_TYPE).build());
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.debug("    prepare data fail ===>>>" + e);
				}
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			// 关闭资源
			if (resultSet != null) {
				resultSet.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return actions;
	}

	//////////////////////////////
	public void init() {
		maxID = 0;
		minID = 0;
		lastID = "";

		starttime = "1000-01-01 00:00:00";
		endtime = "9000-01-01 23:59:59";

		from = 0;
		size = 500;
		sleep = 1000;
	}

	private long maxID = 0;
	private long minID = 0;
	private String lastID = "";

	private String starttime = "1000-01-01 00:00:00";
	private String endtime = "3000-01-01 23:59:59";

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	private int from = 0;

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}
}
