package org.isotope.jfp.framework.support;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.constants.ISDBConstants;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.2.1 2014/11/05
 * @version 0.1.0 2014/2/8
 */
public class MyDataBaseOperateSupport<T> implements ISFrameworkConstants, ISDBConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 数据库连接
	 */
	private SqlSession mySqlSession;

	public SqlSession getMySqlSession() {
		if (mySqlSession == null)
			mySqlSession = BeanFactoryHelper.getBean("mySqlSession");
		return mySqlSession;
	}

	/**
	 * 设定SQL连接
	 * 
	 * @param mySqlSession
	 */
	public void setMySqlSession(SqlSession mySqlSession) {
		this.mySqlSession = mySqlSession;
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

	/////////////////////////
	/**
	 * 获得数据库操作对象
	 * @deprecated
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IDatabaseSupport<T> getDao() {
		return getMySqlSession().getMapper(IDatabaseSupport.class);
	}

	/**
	 * 数据库分表
	 * 
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data, int dbType) {
		// String companyType = ((XXXXXDBO)data).getT01();
		// 分表处理
		// if (ZERO.equals(companyType)) {
		// data.setTableName("0");
		// } else if (ONE.equals(companyType)) {
		// data.setTableName("1");
		// }
		// if (dbType != DB_SELECT) {
		//
		// }
	}

	///////////// 分页查询////////////////
	/**
	 * 分页查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public PageVOSupport<T> doSelectPage(PageVOSupport<T> formParamPageModel) {
		return doSelectPage(formParamPageModel, true, true);
	}

	/**
	 * 分页查询
	 * 
	 * @param formParamPageModel
	 * @param ppp
	 * @return
	 */
	public PageVOSupport<T> doSelectPage(PageVOSupport<T> formParamPageModel, boolean ppp) {
		return doSelectPage(formParamPageModel, ppp, true);
	}

	/**
	 * 分页查询
	 * 
	 * @param formParamPageModel
	 * @param ppp
	 * @return
	 */
	public PageVOSupport<T> doSelectPage(PageVOSupport<T> formParamPageModel, boolean ppp, boolean ddd) {
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		// 查询数据
		changeTable((MyDataBaseObjectSupport) formParamBean, DB_SELECT);
		formParamPageModel.setPageListData(getDao().doSelectPage(formParamPageModel));

		return formParamPageModel;
	}

	/**
	 * 全表查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public List<T> doSelectData(MyDataBaseObjectSupport formParamBean) {
		return doSelectData(formParamBean, true, true);
	}

	/**
	 * 全表查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public List<T> doSelectData(MyDataBaseObjectSupport formParamBean, boolean ppp) {
		return doSelectData(formParamBean, ppp, true);
	}

	/**
	 * 全表查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public List<T> doSelectData(MyDataBaseObjectSupport formParamBean, boolean ppp, boolean ddd) {
		changeTable((MyDataBaseObjectSupport) formParamBean, DB_SELECT);
		formParamBean.prepareGroup(ppp);
		formParamBean.prepareDeleteFlag(ddd);
		return getDao().doSelectPage(formParamBean);
	}

	///////////////// 基本操作////带有乐观锁/////////////////////
	/**
	 * 根据主键，逻辑删除一条数据
	 */
	public int toDelete(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_DELETE);
		return getDao().toDelete(formParamBean);
	}

	/**
	 * 物理删除一个用户
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doDelete(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_DELETE);
		return getDao().doDelete(formParamBean);
	}

	/**
	 * 批量插入
	 * 
	 * @param datas
	 * @return
	 */
	public int[] doInsertOrUpdate(List<? extends MyDataBaseObjectSupport> datas) {
		int[] rs = new int[datas.size()];
		MyDataBaseObjectSupport formParamBean;
		for (int i = 0; i < rs.length; i++) {
			formParamBean = datas.get(i);
			changeTable(formParamBean, DB_INSERT);
			rs[i] = -1;
			try {
				rs[i] = doInsert(formParamBean);
			} catch (Exception e) {
				// System.err.println(e.getMessage());
				rs[i] = doUpdate(formParamBean);
			}
		}
		return rs;
	}

	/**
	 * 批量插入
	 * 
	 * @param datas
	 * @return
	 */
	public int doInsertOrUpdate(MyDataBaseObjectSupport formParamBean) {
		try {
			return doInsert(formParamBean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return doUpdate(formParamBean);
		}
	}

	/**
	 * 批量插入
	 * 
	 * @param datas
	 * @return
	 */
	public int[] doInsert(List<? extends MyDataBaseObjectSupport> datas) {
		int[] rs = new int[datas.size()];
		MyDataBaseObjectSupport formParamBean;
		for (int i = 0; i < rs.length; i++) {
			formParamBean = datas.get(i);
			rs[i] = -1;
			try {
				rs[i] = doInsert(formParamBean);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return rs;
	}

	/**
	 * 插入数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doInsert(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_INSERT);

		// 有效标记、创建者、创建时间、更新者、更新时间
		formParamBean.prepareCreator();
		formParamBean.prepareUpdator();

		return getDao().doInsert(formParamBean);
	}

	/**
	 * 更新数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doUpdate(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_UPDATE);
		// 更新者、更新时间
		formParamBean.prepareUpdator();
		return getDao().doUpdate(formParamBean);
	}

	public void doUpdateAll(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_UPDATE);
		// 更新者、更新时间
		formParamBean.prepareUpdator();
		getDao().doUpdateAll(formParamBean);
	}

	/**
	 * 读取数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public FrameworkDataBean doRead(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_SELECT);
		return doRead(formParamBean, true);
	}

	/**
	 * 读取数据
	 * 
	 * @param formParamBean
	 * @param ddd
	 * @return
	 */
	public FrameworkDataBean doRead(MyDataBaseObjectSupport formParamBean, boolean ddd) {
		changeTable(formParamBean, DB_SELECT);
		return getDao().doRead(formParamBean);
	}
}
