package org.zmsoft.persistent.common.DBManager;

import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;

public class DBManagerDBO extends MyDataBaseObjectSupport3 {

	/**
	 * 建表语句
	 */
	private String sql = null;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
