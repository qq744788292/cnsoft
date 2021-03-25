package org.zmsoft.common.dba.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.zmsoft.common.dba.bean.DriverInfoBean;
import org.zmsoft.config.system.utils.MyConfigHelper;
import org.zmsoft.framework.config.holder.MyDatConfigerHolder;
import org.zmsoft.framework.config.holder.MyTableSQLConfigerHolder;
import org.zmsoft.framework.db.manager.MyDBTableSQLSupport;
import org.zmsoft.framework.mybatis.MyDynamicSqlSessionFactoryHelper;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.persistent.common.DBManager.DBManagerDBO;
import org.zmsoft.persistent.common.DBManager.DBManagerMapper;

/**
 * 内部初始化数据库模式
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Component("DBBuildToolService")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DBBuildService extends MyDBAServiceSupport {

	private DriverInfoBean server;

	public void setServer(DriverInfoBean server) throws Exception {
		this.server = server;
	}

	@Async
	public void doProcess(String type, String drop) {
		try {
			if ("creat".equals(type)) {
				creat(false, true);
				init();
			} else if ("init".equals(type)) {
				init();
			} else if ("build".equals(type)) {
				if (ONE.equals(drop)) {
					creat(false, true);
				} else {
					creat(true, true);
				}
				init();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建空的数据库环境
	 * 
	 * @throws SQLException
	 */
	public boolean creat(boolean drop, boolean creat) throws Exception {
		logger.debug(MESSAGE_STATEMENT_START);
		// 处理SQL建表文件
		if (EmptyHelper.isEmpty(MyTableSQLConfigerHolder.getTableSQLs())) {
			throw new Exception(MESSAGE_SQLLOCATIONS_NULL);
		}
		// 获得数据库对象
		//////////////////////////////////////////////////////////////
		SqlSessionFactory mySqlSessionFactory = MyDynamicSqlSessionFactoryHelper.build(server);
		SqlSession sqlsession = mySqlSessionFactory.openSession();
		//////////////////////////////////////////////////////////////
		try {
			MyDBTableSQLSupport table;
			DBManagerMapper mapper = sqlsession.getMapper(DBManagerMapper.class);
			DBManagerDBO manager = new DBManagerDBO();
			// 获得全部表名称
			for (String sqlBeanName : MyTableSQLConfigerHolder.getTableSQLs()) {
				Thread.sleep(100);
				super.saveLog(String.format("当前处理对象为...%s", sqlBeanName));
				logger.debug("当前处理对象为...{}", sqlBeanName);
				table = (MyDBTableSQLSupport) MyBeanFactoryHelper.getBean(sqlBeanName);
				if (EmptyHelper.isNotEmpty(table)) {
					// 删除当前表
					if (drop) {
						manager.setTableName(table.getTableName());// 获得表名
						super.saveLog(String.format("即将删除表...%s", table.getTableName()));
						logger.debug("即将删除表...{}", table.getTableName());
						mapper.dropTable(manager);
					}
					// 创建表
					if (creat) {
						manager.setSql(table.getSQL());// 获得建表语句
						super.saveLog(String.format("即将创建表...%s", table.getTableName()));
						logger.debug("预处理的建表SQL语句...{}", table.getSQL());
						mapper.createTable(manager);
					}
				}else{
					logger.debug("当前处理对象异常====>>>>>{}", sqlBeanName);
				}
				super.saveLog(String.format("对象%s处理成功!!!", sqlBeanName));
				logger.debug("对象{}处理成功", sqlBeanName.toString());
			}
			logger.debug(MESSAGE_STATEMENT_END);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			sqlsession.close();
			MyConfigHelper.reloadConfig();
		}
		return true;
	}

	public boolean init() throws Exception {
		logger.debug(MESSAGE_STATEMENT_START);
		// 处理SQL建表文件
		if (EmptyHelper.isEmpty(MyTableSQLConfigerHolder.getTableSQLs())) {
			throw new Exception(MESSAGE_SQLLOCATIONS_NULL);
		}
		// 获得数据库对象
		Connection conn = null;
		Statement stmt = null;
		try {
			ResourceLoader resourceLoader = new DefaultResourceLoader();
			Resource dataLocation;
			String sql;
			// 获得数据库对象
			conn = MyDynamicSqlSessionFactoryHelper.getConnection(MyDynamicSqlSessionFactoryHelper.build(server));
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (String sqlDatName : MyDatConfigerHolder.getTableDats()) {
				logger.debug("当前处理对象为...{}", sqlDatName.toString());
				dataLocation = resourceLoader.getResource(sqlDatName);
				// 处理SQL数据文件
				ArrayList<String> sqlList = loadResourceDataToSQL(dataLocation);
				for (int i = 1; i < sqlList.size(); i++) {
					sql = sqlList.get(i);
					if (EmptyHelper.isNotEmpty(sql.trim())) {
						logger.debug("预处理的SQL语句...{}", sql);
						try {
							stmt.execute(sql);
						} catch (SQLException e) {
							// 是否继续处理SQL判定
							logger.error("预处理的建表SQL语句异常！！！" + e.getMessage());
						}
					}
				}
			}
			conn.commit();
			logger.debug(MESSAGE_STATEMENT_END);
		} catch (SQLException e) {
			throw e;
		} finally {
			// 关闭资源
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			MyConfigHelper.reloadConfig();
		}
		return true;
	}

	private ArrayList<String> loadResourceDataToSQL(Resource configLocation) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		StringBuffer buff = new StringBuffer();
		InputStream is = configLocation.getInputStream();
		boolean down = false;
		if (configLocation.getFilename().lastIndexOf(".dat") > 0) {
			down = true;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String data = null;
		String name = null;
		while ((data = br.readLine()) != null) {
			if (name == null) {
				name = data.replace(CREATE_TABLE, EMPTY);
				list.add(name);
			}
			if (down == true)
				list.add(data);
			else
				buff.append(data);
		}
		if (down == false)
			list.add(buff.toString());

		return list;
	}

}
