package org.zmsoft.persistent.common.DBManager;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统菜单 */
@Repository("DBManagerDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DBManagerDao extends MyDataBaseOperateSupport2<DBManagerDBO> {

	@Resource
	private DBManagerMapper mapperDBManager;

	public DBManagerMapper getMapper() {
		return mapperDBManager;
	}

	/**
	 * 删除数据表
	 * @param _DBManagerDBO_
	 * @return
	 */
	public int dropTable(DBManagerDBO _DBManagerDBO_){
		return getMapper().dropTable(_DBManagerDBO_);
	}

	/**
	 * 创建数据表
	 * @param _DBManagerDBO_
	 * @return
	 */
	public int createTable(DBManagerDBO _DBManagerDBO_){
		return getMapper().createTable(_DBManagerDBO_);
	}
}
