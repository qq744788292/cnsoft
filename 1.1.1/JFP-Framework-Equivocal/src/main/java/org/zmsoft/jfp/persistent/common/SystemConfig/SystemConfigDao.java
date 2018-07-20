package org.zmsoft.jfp.persistent.common.SystemConfig;

import java.util.List;

import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

/** 系统配置 */
public interface SystemConfigDao extends ISDatabaseSupport<SystemConfigDBO> {

	/**
	 * 获得用户资料
	 * @param _ManagerMenuDBO_
	 * @return
	 */
	List<SystemConfigDBO> doSelectData(SystemConfigDBO _SystemDBO_);
	
	int saveSystem(SystemConfigDBO _SystemDBO_);

}
