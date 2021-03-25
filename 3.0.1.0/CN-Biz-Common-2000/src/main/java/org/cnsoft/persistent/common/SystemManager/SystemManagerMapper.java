package org.cnsoft.persistent.common.SystemManager;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.cnsoft.framework.db.ISDatabaseSupport;

/** 系统用户 */
@Mapper
public interface SystemManagerMapper extends ISDatabaseSupport<SystemManagerDBO> {

	/**
	 * 获得用户资料
	 * @param _ManagerMenuDBO_
	 * @return
	 */
	List<SystemManagerDBO> doSelectManager(SystemManagerDBO _ManagerMenuDBO_);
	
	int saveLoginLog(SystemManagerDBO _SystemManagerDBO_);

}
