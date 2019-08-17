package com.zmsoft.persistent.framework.common.SystemManager;

import java.util.List;

import org.zmsoft.framework.db.ISDatabaseSupport;

/** 系统用户 */
public interface SystemManagerDao extends ISDatabaseSupport<SystemManagerDBO> {

	/**
	 * 获得用户资料
	 * @param _ManagerMenuDBO_
	 * @return
	 */
	List<SystemManagerDBO> doSelectManager(SystemManagerDBO _ManagerMenuDBO_);
	
	int saveLoginLog(SystemManagerDBO _SystemManagerDBO_);

}
