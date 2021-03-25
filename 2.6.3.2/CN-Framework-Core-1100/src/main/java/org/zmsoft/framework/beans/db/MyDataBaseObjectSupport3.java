package org.zmsoft.framework.beans.db;

/**
 * 数据持久层超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MyDataBaseObjectSupport3 extends MyDataBaseDefaultSupportBean {

	/**
	 * 数据库统一主键
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		return id;
	}
}
