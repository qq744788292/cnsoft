package org.cnsoft.framework.db.support;

import java.util.List;

import org.cnsoft.framework.common.ISLoginer;
import org.cnsoft.framework.constants.ICDBConstants;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.db.ISDatabaseSupport;
import org.cnsoft.framework.db.page.PageModel;
import org.cnsoft.framework.log.LogDataHelper;
import org.cnsoft.framework.mybatis.ISDataSourceName;
import org.cnsoft.framework.saas.plugin.MySAASBusinesslogicPlugin;
import org.cnsoft.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据DAO操作超类
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public abstract class ADataBaseOperateSupport<T> implements ISLoginer, ISDataSourceName, ICFrameworkConstants, ICDBConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean dataLock = false;// 是否开启更新乐观锁模式

	public boolean isDataLock() {
		return dataLock;
	}

	public void setDataLock(boolean dataLock) {
		this.dataLock = dataLock;
	}

	/**
	 * 数据库操作语句
	 * 
	 * @return
	 */
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
		return doSelectPage(formParamPageModel, true);
	}

	public PageModel<T> doSelectPage(PageModel<T> formParamPageModel, boolean ddd) {
		// 补充删除标记
		if (ddd)
			((ADataBaseDefaultSupportBean) formParamPageModel.getFormParamBean()).prepareDeleteFlag(1);
		// SAAS模式
		MySAASBusinesslogicPlugin.sqlHandle((ADataBaseDefaultSupportBean) formParamPageModel.getFormParamBean());
		
		// 默认排序
//		if (EmptyHelper.isEmpty(formParamPageModel.getOrderby()))
//			formParamPageModel.setOrderbyIdDESC();
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
	public List<T> doSelectData(ADataBaseDefaultSupportBean formParamBean) {
		return doSelectData(formParamBean, 1);
	}

	/**
	 * 全表查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public List<T> doSelectData(ADataBaseDefaultSupportBean formParamBean, int ddd) {
		// 删除标记
		formParamBean.prepareDeleteFlag(ddd);
		// SAAS模式
		MySAASBusinesslogicPlugin.sqlHandle(formParamBean);
		
		return getMapper().doSelectPage(formParamBean);
	}

	///////////////// 基本操作////带有乐观锁/////////////////////
	/**
	 * 根据主键，逻辑删除一条数据
	 */
	public int toDelete(ADataBaseDefaultSupportBean formParamBean) {
		// 更新者、更新时间
		formParamBean.prepareUpdator(2, dataLock);
		// SAAS模式
		MySAASBusinesslogicPlugin.sqlHandle(formParamBean);

		return getMapper().toDelete(formParamBean);
	}

	/**
	 * 物理删除一条数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doDelete(ADataBaseDefaultSupportBean formParamBean) {
		// SAAS模式
		MySAASBusinesslogicPlugin.sqlHandle(formParamBean);
		
		return getMapper().doDelete(formParamBean);
	}

	/**
	 * 数据存储
	 * 
	 * @param datas
	 * @return
	 */
	public int doInsertOrUpdate(ADataBaseDefaultSupportBean formParamBean) {
		return doInsertOrUpdate(formParamBean, 2, true);
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int doInsertOrUpdate(ADataBaseDefaultSupportBean formParamBean, int intercept) {
		return doInsertOrUpdate(formParamBean, intercept, true);
	}

	/**
	 * 
	 * @param formParamBean
	 * @param intercept
	 * @param simpleCheck
	 *            true:检查主键，false：检查数据
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int doInsertOrUpdate(ADataBaseDefaultSupportBean formParamBean, int intercept, boolean simpleCheck) {
		return doInsertOrUpdate(formParamBean, intercept, simpleCheck, false);
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int doInsertOrUpdate(ADataBaseDefaultSupportBean formParamBean, int intercept, boolean simpleCheck, boolean selective) {
		if (simpleCheck && EmptyHelper.isEmpty(formParamBean.currentPrimarykey())) {
			return doInsert(formParamBean, intercept, selective);
		} else {
			if (EmptyHelper.isEmpty(doRead(formParamBean)))
				return doInsert(formParamBean, intercept, selective);
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
	public int[] doInsertOrUpdate(List<? extends ADataBaseDefaultSupportBean> datas) {
		return doInsertOrUpdate(datas, 2, true);
	}

	/**
	 * 批量数据存储
	 * 
	 * @param datas
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doInsertOrUpdate(List<? extends ADataBaseDefaultSupportBean> datas, int intercept) {
		return doInsertOrUpdate(datas, intercept, true);
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doInsertOrUpdate(List<? extends ADataBaseDefaultSupportBean> datas, int intercept, boolean simpleCheck) {
		if (EmptyHelper.isEmpty(datas))
			return new int[0];
		int[] rs = new int[datas.size()];
		ADataBaseDefaultSupportBean formParamBean;
		for (int i = 0; i < rs.length; i++) {
			formParamBean = datas.get(i);
			rs[i] = doInsertOrUpdate(formParamBean, intercept, false);
		}
		return rs;
	}

	/**
	 * 明细表更新(先删除在插入)
	 * 
	 * @param datas
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doDetailInsert(ADataBaseDefaultSupportBean formParamBean, ADataBaseDefaultSupportBean[] datas, boolean selective) {
		if (EmptyHelper.isEmpty(datas))
			return new int[0];
		// 根据外键删除所有数据
		doDelete(formParamBean);
		// 从新插入数据
		int[] rs = new int[datas.length];
		for (int i = 0; i < rs.length; i++) {
			rs[i] = -1;
			try {
				rs[i] = doInsert(datas[i], 2, selective);
			} catch (Exception e) {
				logger.error(e.getMessage());
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
	public int[] doInsert(ADataBaseDefaultSupportBean[] datas) {
		return doInsert(datas, false);
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doInsert(ADataBaseDefaultSupportBean[] datas, boolean selective) {
		if (EmptyHelper.isEmpty(datas))
			return new int[0];
		int[] rs = new int[datas.length];
		for (int i = 0; i < rs.length; i++) {
			rs[i] = -1;
			try {
				rs[i] = doInsert(datas[i], 2, selective);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return rs;
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doInsert(List<? extends ADataBaseDefaultSupportBean> datas) {
		return doInsert(datas, 2, false);
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doInsert(List<? extends ADataBaseDefaultSupportBean> datas, int intercept, boolean selective) {
		if (EmptyHelper.isEmpty(datas))
			return new int[0];
		int[] rs = new int[datas.size()];
		for (int i = 0; i < rs.length; i++) {
			rs[i] = -1;
			try {
				rs[i] = doInsert(datas.get(i), intercept, selective);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return rs;
	}

	/**
	 * 插入数据（使用空值）
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doInsert(ADataBaseDefaultSupportBean formParamBean) {
		return doInsert(formParamBean, 2, false);
	}

	/**
	 * 插入数据（使用默认值）
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doInsertSelective(ADataBaseDefaultSupportBean formParamBean) {
		return doInsert(formParamBean, 2, true);
	}

	/**
	 * 插入数据
	 * 
	 * @param formParamBean
	 * @param intercept
	 * @param selective
	 *            true:空的时候使用默认数据
	 * @return
	 */
	public int doInsert(ADataBaseDefaultSupportBean formParamBean, int intercept, boolean selective) {
		// 主键补全
		formParamBean.preparePrimarykey();
		// 数字补全
		formParamBean.prepareNumeric();

		// 有效标记、创建者、创建时间、更新者、更新时间
		formParamBean.prepareCreator(intercept);
		formParamBean.prepareUpdator(intercept, true);// 利用锁机制，添加更新时间

		// 补充删除标记
		formParamBean.prepareDeleteFlag(1);
		// SAAS模式
		MySAASBusinesslogicPlugin.sqlHandle(formParamBean);

		// 日志输出
		LogDataHelper.saveLog("MyDataBaseOperateSupport.doInsert=>" + formParamBean.getClass().getSimpleName(), formParamBean);

		if (selective)
			return getMapper().doInsertSelective(formParamBean);
		else
			return getMapper().doInsert(formParamBean);
	}

	/**
	 * 更新数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doUpdate(ADataBaseDefaultSupportBean formParamBean) {
		return doUpdate(formParamBean, 2);
	}

	public int doUpdate(ADataBaseDefaultSupportBean formParamBean, int intercept) {
		return doUpdate(formParamBean, intercept, dataLock);
	}

	public int doUpdate(ADataBaseDefaultSupportBean formParamBean, int intercept, boolean lock) {
		// 更新者、更新时间
		formParamBean.prepareUpdator(intercept, lock);
		// SAAS模式
		MySAASBusinesslogicPlugin.sqlHandle(formParamBean);

		return getMapper().doUpdate(formParamBean);
	}

	public void doUpdateAll(ADataBaseDefaultSupportBean formParamBean) {
		doUpdateAll(formParamBean, 2);
	}

	public void doUpdateAll(ADataBaseDefaultSupportBean formParamBean, int intercept) {
		doUpdateAll(formParamBean, intercept, dataLock);
	}

	public void doUpdateAll(ADataBaseDefaultSupportBean formParamBean, int intercept, boolean lock) {
		// 更新者、更新时间
		formParamBean.prepareUpdator(intercept, lock);
		// SAAS模式
		MySAASBusinesslogicPlugin.sqlHandle(formParamBean);

		getMapper().doUpdateAll(formParamBean);
	}

	/**
	 * 批量更新
	 * 
	 * @param datas
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doUpdate(ADataBaseDefaultSupportBean[] datas) {
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

	public int[] doUpdate(List<? extends ADataBaseDefaultSupportBean> datas) {
		return doUpdate(datas, 2);
	}

	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public int[] doUpdate(List<? extends ADataBaseDefaultSupportBean> datas, int intercept) {
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
	 * 读取数据（全部数据）
	 * 
	 * @param formParamBean
	 * @return
	 */
	public T doRead(ADataBaseDefaultSupportBean formParamBean) {
		return doRead(formParamBean, true);
	}

	/**
	 * 读取数据（有效数据：delflag=1）
	 * 
	 * @param formParamBean
	 * @param ddd
	 * @return
	 */
	public T doRead(ADataBaseDefaultSupportBean formParamBean, boolean ddd) {
		// 补充删除标记
		if (ddd)
			formParamBean.prepareDeleteFlag(1);
		// SAAS模式
		MySAASBusinesslogicPlugin.sqlHandle(formParamBean);
		
		return getMapper().doRead(formParamBean);
	}

	/**
	 * 读取数据
	 * 
	 * @param formParamBean
	 * @param flag
	 * @return
	 */
	public T doRead(ADataBaseDefaultSupportBean formParamBean, int flag) {
		// 补充删除标记
		formParamBean.prepareDeleteFlag(flag);
		// SAAS模式
		MySAASBusinesslogicPlugin.sqlHandle(formParamBean);
		
		return getMapper().doRead(formParamBean);
	}
}
