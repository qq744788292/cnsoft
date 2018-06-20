package org.zmsoft.jfp.persistent.common.System;

import java.util.List;

import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 系统配置 */
@Service("SystemService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemService extends MyDataBaseOperateSupport2<SystemDBO> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public SystemDao getDao() {
		return getMySqlSession().getMapper(SystemDao.class);
	}

	/**
	 * 一览查询
	 * 
	 * @see doSelectData
	 */
	public List<SystemDBO> doSelectData(SystemDBO _SystemDBO_) {
		return getDao().doSelectData(_SystemDBO_);
	}

	/**
	 *数据保存
	 * 
	 * @param _SystemDBO_
	 */
	public void saveSystem(SystemDBO _SystemDBO_) {
		getDao().saveSystem(_SystemDBO_);
	}

}
