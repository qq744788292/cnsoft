package org.zmsoft.common.dba.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.zmsoft.common.dba.bean.DBUpdateInfoBean;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.config.holder.MyTableSQLConfigerHolder;
import org.zmsoft.framework.constants.ICDBConstants;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;
import org.zmsoft.framework.db.manager.MyDBTableSQLSupport;
import org.zmsoft.framework.mybatis.MyDynamicSqlSessionFactoryHelper;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.persistent.common.DBManager.DBManagerDBO;
import org.zmsoft.persistent.common.DBManager.DBManagerMapper;

import com.alibaba.fastjson.JSONObject;

/**
 * 数据库迁移升级(数据合并，数据一致保留原始数据)
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Component("DBTransferService")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DBTransferService extends MyDBAServiceSupport {

	private DBUpdateInfoBean updateInfo;
	public void setUpdateInfo(DBUpdateInfoBean updateinfo) throws Exception {
		this.updateInfo = updateinfo;
	}

	public final static String TABLE_DATA = "TABLE_DATA:";

	public void doProcess() {
		try {
			update();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * 创建空的数据库环境
	 * 
	 * @throws SQLException
	 */
	@Async
	public boolean update() throws Exception {
		logger.debug(MESSAGE_STATEMENT_START);
		// 处理SQL建表文件
		if (EmptyHelper.isEmpty(MyTableSQLConfigerHolder.getTableSQLs())) {
			throw new Exception(MESSAGE_SQLLOCATIONS_NULL);
		}
		// 获得数据库对象
		//////////////////////////////////////////////////////////////
		SqlSessionFactory sSqlSessionFactory = MyDynamicSqlSessionFactoryHelper.build(updateInfo.getsServer());
		SqlSession sSqlSession = sSqlSessionFactory.openSession();
		SqlSessionFactory tSqlSessionFactory = MyDynamicSqlSessionFactoryHelper.build(updateInfo.gettServer());
		SqlSession tSqlSession = sSqlSessionFactory.openSession();
		//////////////////////////////////////////////////////////////
		// 获得数据库对象
		try {

			String tmp;
			Object data;
			JSONObject  jcdata;
			MyDBTableSQLSupport table;
			MyDataBaseObjectSupport3 item;
			PageModel<MyDataBaseObjectSupport3> pageModel;
			MyDataBaseOperateSupport2<MyDataBaseObjectSupport3> dao;
			List<MyDataBaseObjectSupport3> pageListData;
			DBManagerMapper sMapper = sSqlSession.getMapper(DBManagerMapper.class);
			DBManagerMapper tMapper = tSqlSession.getMapper(DBManagerMapper.class);
			DBManagerDBO manager = new DBManagerDBO();
			// 获得全部表名称
			for (String sqlBeanName : MyTableSQLConfigerHolder.getTableSQLs()) {
				logger.debug("当前处理对象为...{}", sqlBeanName);
				table = (MyDBTableSQLSupport) MyBeanFactoryHelper.getBean(sqlBeanName);

				//实例化数据驱动
				{
					//转化名字
					tmp = table.getClass().getName().replaceAll("SQL", "Dao");
					dao = MyBeanFactoryHelper.getBean(tmp);
				}
				// 获得当前表数据
				{
					pageModel = new PageModel<>();
					pageModel.setResultCountFlag(true);
					pageModel.setPageLimit(500);

					// TODO 分页获取数据
					{
						pageListData = dao.doSelectPage(pageModel).getPageListData();
						// 保存到缓存中心
						for (MyDataBaseObjectSupport3 sdata : pageListData) {
							myCacheService.addObjectInList(TABLE_DATA + table.getTableName(), sdata);
						}
					}
				}

				// 删除当前表
				{
					manager.setTableName(table.getTableName());// 获得表名
					logger.debug("预处理的建表SQL语句...{}", table.getTableName());
					tMapper.dropTable(manager);
				}
				// 创建表
				{
					manager.setTableName(table.getSQL());// 获得建表语句
					logger.debug("预处理的建表SQL语句...{}", table.getSQL());
					tMapper.createTable(manager);
				}
				// 迁移数据到新表
				{
					// 获取数据
					boolean runFlg = true;
					while (runFlg) {
						data = myCacheService.pollFirstObjectInList(TABLE_DATA + table.getTableName());
						if (EmptyHelper.isEmpty(data)) {
							runFlg = false;
						}
						tmp = table.getClass().getName().replaceAll("SQL", "DBO");
						jcdata = JSONObject.parseObject((String) data);
						item = (MyDataBaseObjectSupport3) jcdata.toJavaObject(Class.forName(tmp));
						//插入数据
						dao.doInsert(item, 1, true);
					}
				}
				logger.debug("对象{}处理成功", sqlBeanName.toString());
			}

			logger.debug(MESSAGE_STATEMENT_END);
		} catch (SQLException e) {
			throw e;
		} finally {
			sSqlSession.close();
			tSqlSession.close();
		}
		return true;
	}
}
