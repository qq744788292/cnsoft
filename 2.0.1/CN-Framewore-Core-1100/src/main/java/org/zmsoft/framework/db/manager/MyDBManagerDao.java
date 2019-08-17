package org.zmsoft.framework.db.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.zmsoft.framework.config.holder.MyTableSQLConfigerHolder;
import org.zmsoft.framework.constants.IDBConstants;
import org.zmsoft.framework.support.MyBeanContextSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 内部初始化数据库模式
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class MyDBManagerDao extends MyDBTableSQLSupport implements IDBConstants {

	@Autowired
	SqlSessionFactory sqlSessionFactory;

	protected Connection getConnection() throws SQLException {
		return SqlSessionUtils.getSqlSession(sqlSessionFactory).getConnection();
	}

	/**
	 * 创建空的数据库环境
	 * 
	 * @throws SQLException
	 */
	public boolean creat(boolean drop, boolean creat) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		// 获得数据库对象
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			logger.debug(MESSAGE_STATEMENT_START);
			// 处理SQL建表文件
			if (EmptyHelper.isEmpty(MyTableSQLConfigerHolder.getMyTableSQLs())) {
				throw new Exception(MESSAGE_SQLLOCATIONS_NULL);
			}
			String sql;

			MyDBTableSQLSupport table;
			for (String sqlBeanName : MyTableSQLConfigerHolder.getMyTableSQLs()) {
				logger.debug("当前处理对象为...{}",sqlBeanName.toString());
				table = (MyDBTableSQLSupport) MyBeanContextSupport.getBean(sqlBeanName);
				if (drop) {
					// 删除当前表
					try {
						sql = DROP_TABLE + table.getTableName();// 获得表名
						logger.info("预处理的建表SQL语句...{}", sql);
						stmt.execute(sql);
					} catch (SQLException e) {
						logger.warn(e.getMessage());
					}
				}
				// 创建表
				if (creat) {
					sql = table.getSQL();
					logger.info("预处理的建表SQL语句...{}", sql);
					try {
						stmt.execute(sql);
					} catch (SQLException e) {
						// 是否继续处理SQL判定
						logger.error("预处理的建表SQL语句异常！！！" + e.getMessage());
						throw e;
					}
				}
			}
			conn.commit();
			logger.debug(MESSAGE_STATEMENT_END);
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			// 关闭资源
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}

	/**
	 * 重构数据库环境
	 * 
	 * @throws SQLException
	 */
	public boolean build() throws Exception {
		if (creat(false, true)) {
			// init();
			// index();
		}

		return true;
	}

}
