package org.zmsoft.jfp.framework.search;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;

import io.searchbox.client.JestClient;

public class MySearchSupport implements ISSentenceConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	// 缓存中心
	@Resource
	protected ISCacheService myCacheService;
	
	// 缓存中心
	@Resource
	protected ESQuerySentenceFactory myESQuerySentenceFactory;

	// 检索中心
	@Resource
	protected ESPoolFactory myElasticsearchPool;

	public JestClient getClient() throws Exception {
		if (myElasticsearchPool == null)
			myElasticsearchPool = BeanFactoryHelper.getBean("ElasticsearchPool");
		return myElasticsearchPool.getClient();
	}
	
	/**
	 * 数据库连接
	 */
	@Resource
	protected SqlSession mySqlSession;

	public SqlSession getMySqlSession() {
		if (mySqlSession == null)
			mySqlSession = BeanFactoryHelper.getBean("mySqlSession");
		return mySqlSession;
	}

	/**
	 * 获得用于执行静态 SQL 语句并返回它所生成结果的对象
	 * <p>
	 * 基于事物控制
	 * 
	 * @return Statement
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		SqlSessionTemplate st = (SqlSessionTemplate) getMySqlSession();
		return SqlSessionUtils.getSqlSession(st.getSqlSessionFactory(), st.getExecutorType(), st.getPersistenceExceptionTranslator()).getConnection();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 单次处理数目
	 */
	protected int size = 1000;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	protected int sleep = 1000;

	public int getSleep() {
		return sleep;
	}

	public void setSleep(int sleep) {
		this.sleep = sleep;
	}
}
