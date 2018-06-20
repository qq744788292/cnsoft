package org.zmsoft.jfp.persistent.common.System;

import java.util.List;

import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

/** 系统配置 */
public interface SystemDao extends ISDatabaseSupport<SystemDBO> {

	/**
	 * 获得用户资料
	 * @param _ManagerMenuDBO_
	 * @return
	 */
	List<SystemDBO> doSelectData(SystemDBO _SystemDBO_);
	
	int saveSystem(SystemDBO _SystemDBO_);

}
