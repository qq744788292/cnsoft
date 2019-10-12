package org.zmsoft.persistent.common.SystemManager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统用户 */
@Repository("SystemManagerDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemManagerDao extends MyDataBaseOperateSupport2<SystemManagerDBO> {

	@Resource
	private SystemManagerMapper mapperSystemManager;

	public SystemManagerMapper getMapper() {
		return mapperSystemManager;
	}

	/**
	 * 一览查询
	 * 
	 * @see doSelectData
	 */
	public List<SystemManagerDBO> doSelectManager(SystemManagerDBO _SystemManagerDBO_) {
		return getMapper().doSelectManager(_SystemManagerDBO_);
	}

	/**
	 * 登录日志
	 * 
	 * @param _SystemManagerDBO_
	 */
	public void saveLoginLog(SystemManagerDBO _SystemManagerDBO_) {
		getMapper().saveLoginLog(_SystemManagerDBO_);
	}

}
