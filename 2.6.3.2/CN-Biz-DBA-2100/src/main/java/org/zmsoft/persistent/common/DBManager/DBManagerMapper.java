package org.zmsoft.persistent.common.DBManager;

import org.apache.ibatis.annotations.Mapper;
import org.zmsoft.framework.db.ISDatabaseSupport;

/** 系统菜单 */
@Mapper
public interface DBManagerMapper extends ISDatabaseSupport<DBManagerDBO> {

	/**
	 * 删除数据表
	 * @param _DBManagerDBO_
	 * @return
	 */
	int dropTable(DBManagerDBO _DBManagerDBO_);

	/**
	 * 创建数据表
	 * @param _DBManagerDBO_
	 * @return
	 */
	int createTable(DBManagerDBO _DBManagerDBO_);

}
