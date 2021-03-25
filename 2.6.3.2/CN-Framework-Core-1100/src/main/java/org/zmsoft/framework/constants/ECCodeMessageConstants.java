package org.zmsoft.framework.constants;

/**
 * 系统常量
 *
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public enum ECCodeMessageConstants {

	/**
	 * 错误提示
	 */
	SYSTEM_ERROR(1, "4:系统繁忙，请稍后再试"), MESSAGE_USER_ERROR(1, "4:用户不存在"), MESSAGE_DB_ERROR(1, "4:缺少必要条件"), MESSAGE_DB_SELECT(0, "1:数据查询成功"), MESSAGE_DB_INSERT(0, "2:数据增加成功"), MESSAGE_DB_UPDATE(0, "2:数据更新成功"), MESSAGE_DB_DELETE(0, "3:数据删除成功"), MESSAGE_DB_FAIL(1, "4:数据操作失败"), MESSAGE_DB_SAVE(0, "2:数据保存成功"), MESSAGE_DB_Export_FAIL(1, "4:数据导出失败！！！"), MESSAGE_DB_Export_SUCCESS(0, "3:数据导出成功！！！"), MESSAGE_SMS_SEND(0, "2:短信发送成功！！！"), MESSAGE_SYNC_SUCCESS(0, "2:数据同步成功！！！"), MESSAGE_SYNC_FAIL(1, "4:数据同步失败！！！"), MESSAGE_SERVER_NONE(1, "4:服务不存在！！！");
	///////////////////////////////////////////////////////////////// 4
	private int code;

	private String msg;

	ECCodeMessageConstants(int code, String msg) {
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
