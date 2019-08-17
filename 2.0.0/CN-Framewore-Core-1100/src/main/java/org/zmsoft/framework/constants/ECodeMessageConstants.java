package org.zmsoft.framework.constants;

/**
 * 系统常量
 *
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public enum ECodeMessageConstants {

	/**
	 * 数据库操作
	 */
	MESSAGE_DB_SELECT(0, "1100:数据查询成功"), 
	MESSAGE_DB_INSERT(0, "1110:数据增加成功"), 
	MESSAGE_DB_UPDATE(0, "1120:数据更新成功"), 
	MESSAGE_DB_DELETE(0, "1130:数据删除成功"), 
	MESSAGE_DB_FAIL(1, "1140:数据操作失败"),
	MESSAGE_DB_SAVE(0, "1150:数据保存成功"), 
	MESSAGE_DB_Export_FAIL(1, "1160:数据导出失败！！！"),
	MESSAGE_DB_Export_SUCCESS(0, "1170:数据导出成功！！！"),
	MESSAGE_SMS_SEND(0, "2000:短信发送成功！！！"),
	MESSAGE_SYNC_SUCCESS(0, "3000:数据同步成功！！！"),
	MESSAGE_SYNC_FAIL(1, "3100:数据同步失败！！！");
	///////////////////////////////////////////////////////////////// 4
	private int code;

	private String msg;

	ECodeMessageConstants(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
