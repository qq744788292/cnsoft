package org.zmsoft.framework.beans.db;

/**
 * 数据持久层超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MyDataBaseObjectSupport3 extends MyDataBaseDefaultSupport {

	/**
	 * 数据库统一主键
	 */
	private String puk;

	public String getPuk() {
		return puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}

	public String getId() {
		return puk;
	}

	public void setId(String id) {
		this.puk = id;
	}

}
