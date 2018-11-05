package org.zmsoft.framework.beans.db;

import org.zmsoft.framework.beans.common.FrameworkDataBean;
import org.zmsoft.framework.constants.IDBConstants;
import org.zmsoft.framework.db.ISPrimarykey;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.PKHelper;

/**
 * 数据持久层存储超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public abstract class MyDataBaseObjectSupport extends FrameworkDataBean implements IDBConstants, ISPrimarykey {

	/**
	 * 获得数据主键
	 * 
	 * @return
	 */
	public String currentPrimarykey() {
		return getId();
	}

	/**
	 * 创建一个默认的主键
	 */
	public void makePrimarykey() {
		setId(PKHelper.creatPUKey());
	}

	//////////////////////////////////////////////////////////////////

	/**
	 * 表名
	 */
	private String tableName = null;

	public String getTableName() {
		return tableName;
	}

	public void loadTableName() {
		if (EmptyHelper.isEmpty(tableName)) {
			tableName = this.getClass().getSimpleName();
			if (tableName.indexOf("DBO") > 0)
				tableName = tableName.substring(0, tableName.indexOf("DBO"));
		}
	}

	public void changeTableNameToTemp() {
		this.tableName = getTableName() + "_tmp";
	}

	public void setTableName(String tablename) {
		this.tableName = tablename;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 插入场合的数据初始化
	 */
	public void prepareNumeric() {

	}

	/**
	 * 拦截创建信息
	 */
	public void preparePrimarykey() {
		// 主键ID
		if (EmptyHelper.isEmpty(currentPrimarykey()))
			makePrimarykey();
	}

	/**
	 * 拦截创建信息 0无视1默认2拦截
	 */
	public void prepareCreator(int flag) {
	}

	/**
	 * 拦截更新信息 0无视1默认2拦截
	 */
	public void prepareUpdator(int flag) {
	}

	/**
	 * 拦截有效标记信息 0无视1默认2拦截
	 */
	public void prepareDeleteFlag(int flag) {
	}
}
