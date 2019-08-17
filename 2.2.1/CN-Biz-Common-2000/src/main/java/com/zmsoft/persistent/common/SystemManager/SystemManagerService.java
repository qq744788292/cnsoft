package com.zmsoft.persistent.common.SystemManager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统用户 */
@Service("SystemManagerService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemManagerService extends MyDataBaseOperateSupport2<SystemManagerDBO> {

	@Resource
	private SystemManagerDao daoSystemManager;

	public SystemManagerDao getMapper() {
		return daoSystemManager;
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
