package org.cnsoft.persistent.common.SystemConfig;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.cnsoft.framework.db.ISDatabaseSupport;

/** 系统配置 */
@Mapper
public interface SystemConfigMapper extends ISDatabaseSupport<SystemConfigDBO> {

	/**
	 * 获得用户资料
	 * @param _ManagerMenuDBO_
	 * @return
	 */
	List<SystemConfigDBO> doSelectData(SystemConfigDBO _SystemDBO_);
	
	int saveSystem(SystemConfigDBO _SystemDBO_);

}
