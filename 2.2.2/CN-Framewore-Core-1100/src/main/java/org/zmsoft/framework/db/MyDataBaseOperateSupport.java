package org.zmsoft.framework.db;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.IDBConstants;
import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.mybatis.ISDataSourceName;
import org.zmsoft.framework.mybatis.dialect.DefaultDialect;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 数据持久层操作超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MyDataBaseOperateSupport<T> implements ISDataSourceName, IFrameworkConstants, IDBConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 当前使用分页功能
	 */
	@Resource
	protected DefaultDialect defaultDialect;

	public ISDatabaseSupport<T> getMapper() {
		return null;
	}

	/**
	 * 获得当前数据库类别
	 * 
	 * @return
	 */
	public String currentDataSourceName() {
		return DataSource_DEFAULT;
	}

	///////////// 分页查询////////////////
	/**
	 * 分页查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public PageModel<T> doSelectPage(PageModel<T> formParamPageModel) {

		// 设置分页器
		formParamPageModel.setDefaultDialect(defaultDialect);
		// 查询数据
		formParamPageModel.setPageListData(getMapper().doSelectPage(formParamPageModel));

		return formParamPageModel;
	}

	/**
	 * 全表查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public List<T> doSelectData(MyDataBaseObjectSupport formParamBean) {
		return doSelectData(formParamBean, 0);
	}

	/**
	 * 全表查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public List<T> doSelectData(MyDataBaseObjectSupport formParamBean, int ddd) {
		formParamBean.prepareDeleteFlag(ddd);
		return getMapper().doSelectPage(formParamBean);
	}

	///////////////// 基本操作////带有乐观锁/////////////////////
	/**
	 * 根据主键，逻辑删除一条数据
	 */
	public int toDelete(MyDataBaseObjectSupport formParamBean) {
		// 更新者、更新时间
		formParamBean.prepareUpdator(2);

		return getMapper().toDelete(formParamBean);
	}

	/**
	 * 物理删除一条数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doDelete(MyDataBaseObjectSupport formParamBean) {
		return getMapper().doDelete(formParamBean);
	}

	/**
	 * 数据存储
	 * 
	 * @param datas
	 * @return
	 */
	public int doInsertOrUpdate(MyDataBaseObjectSupport formParamBean) {
		return doInsertOrUpdate(formParamBean, 2);
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int doInsertOrUpdate(MyDataBaseObjectSupport formParamBean, int intercept) {
		if (EmptyHelper.isEmpty(formParamBean.currentPrimarykey())) {
			return doInsert(formParamBean, intercept);
		} else {
			if (EmptyHelper.isEmpty(doRead(formParamBean)))
				return doInsert(formParamBean, intercept);
			else
				return doUpdate(formParamBean, intercept);
		}
	}

	/**
	 * 批量数据存储
	 * 
	 * @param datas
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doInsertOrUpdate(List<? extends MyDataBaseObjectSupport> datas) {
		return doInsertOrUpdate(datas, 2);
	}

	/**
	 * 批量数据存储
	 * 
	 * @param datas
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doInsertOrUpdate(List<? extends MyDataBaseObjectSupport> datas, int intercept) {
		if (EmptyHelper.isEmpty(datas))
			return new int[0];
		int[] rs = new int[datas.size()];
		MyDataBaseObjectSupport formParamBean;
		for (int i = 0; i < rs.length; i++) {
			formParamBean = datas.get(i);
			rs[i] = -1;
			if (EmptyHelper.isEmpty(formParamBean.currentPrimarykey())) {
				rs[i] = doInsert(formParamBean, intercept);
			} else {
				if (EmptyHelper.isEmpty(doRead(formParamBean)))
					rs[i] = doInsert(formParamBean, intercept);
				else
					rs[i] = doUpdate(formParamBean, intercept);
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
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doInsert(MyDataBaseObjectSupport[] datas) {
		if (EmptyHelper.isEmpty(datas))
			return new int[0];
		int[] rs = new int[datas.length];
		for (int i = 0; i < rs.length; i++) {
			rs[i] = -1;
			try {
				rs[i] = doInsert(datas[i], 2);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return rs;
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doInsert(List<? extends MyDataBaseObjectSupport> datas) {
		return doInsert(datas, 2);
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doInsert(List<? extends MyDataBaseObjectSupport> datas, int intercept) {
		if (EmptyHelper.isEmpty(datas))
			return new int[0];
		int[] rs = new int[datas.size()];
		for (int i = 0; i < rs.length; i++) {
			rs[i] = -1;
			try {
				rs[i] = doInsert(datas.get(i), intercept);
			} catch (Exception e) {
				logger.error(e.getMessage());
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
		return doInsert(formParamBean, 2);
	}

	public int doInsert(MyDataBaseObjectSupport formParamBean, int intercept) {
		// 补充主键
		formParamBean.preparePrimarykey();
		// 数字不全
		formParamBean.prepareNumeric();

		// 有效标记、创建者、创建时间、更新者、更新时间
		formParamBean.prepareCreator(intercept);
		formParamBean.prepareUpdator(intercept);

		// 补充删除标记
		formParamBean.prepareDeleteFlag(1);

		return getMapper().doInsert(formParamBean);
	}

	/**
	 * 更新数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doUpdate(MyDataBaseObjectSupport formParamBean) {
		return doUpdate(formParamBean, 2);
	}

	public int doUpdate(MyDataBaseObjectSupport formParamBean, int intercept) {
		// 更新者、更新时间
		formParamBean.prepareUpdator(intercept);

		return getMapper().doUpdate(formParamBean);
	}

	public void doUpdateAll(MyDataBaseObjectSupport formParamBean) {
		doUpdateAll(formParamBean, 2);
	}

	public void doUpdateAll(MyDataBaseObjectSupport formParamBean, int intercept) {
		// 更新者、更新时间
		formParamBean.prepareUpdator(intercept);

		getMapper().doUpdateAll(formParamBean);
	}

	/**
	 * 批量更新
	 * 
	 * @param datas
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doUpdate(MyDataBaseObjectSupport[] datas) {
		int[] rs = new int[datas.length];
		for (int i = 0; i < rs.length; i++) {
			rs[i] = -1;
			try {
				rs[i] = doUpdate(datas[i], 2);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return rs;
	}

	public int[] doUpdate(List<? extends MyDataBaseObjectSupport> datas) {
		return doUpdate(datas, 2);
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doUpdate(List<? extends MyDataBaseObjectSupport> datas, int intercept) {
		int[] rs = new int[datas.size()];
		for (int i = 0; i < rs.length; i++) {
			rs[i] = -1;
			try {
				rs[i] = doUpdate(datas.get(i), intercept);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return rs;
	}

	/**
	 * 读取数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public T doRead(MyDataBaseObjectSupport formParamBean) {
		return doRead(formParamBean, true);
	}

	/**
	 * 读取数据
	 * 
	 * @param formParamBean
	 * @param ddd
	 * @return
	 */
	public T doRead(MyDataBaseObjectSupport formParamBean, boolean ddd) {

		// 补充删除标记
		if (ddd)
			formParamBean.prepareDeleteFlag(1);
		return getMapper().doRead(formParamBean);
	}
}
