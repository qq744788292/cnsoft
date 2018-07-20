package org.zmsoft.jfp.persistent.common.SystemManager;

import java.util.List;

import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 系统用户 */
@Service("SystemManagerService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemManagerService extends MyDataBaseOperateSupport2<SystemManagerDBO> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public SystemManagerDao getDao() {
		return getMySqlSession().getMapper(SystemManagerDao.class);
	}

	/**
	 * 一览查询
	 * 
	 * @see doSelectData
	 */
	public List<SystemManagerDBO> doSelectManager(SystemManagerDBO _SystemManagerDBO_) {
		return getDao().doSelectManager(_SystemManagerDBO_);
	}

	/**
	 * 登录日志
	 * 
	 * @param _SystemManagerDBO_
	 */
	public void saveLoginLog(SystemManagerDBO _SystemManagerDBO_) {
		getDao().saveLoginLog(_SystemManagerDBO_);
	}

}
