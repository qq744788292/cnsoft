package org.zmsoft.jfp.framework.key.common;

import org.zmsoft.jfp.framework.constants.pub.IKeyConstants;
import org.zmsoft.jfp.framework.key.DefaultKeyService;

/**
 * 定时任务Key定义
 * 
 * @author zmsoft
 * @version 2.4.1 2015/8/17
 * @since 2.4.1 2015/8/17
 * 
 */
public class BussinessKeyService extends DefaultKeyService implements IKeyConstants {

	/**
	 * 获得日常业务名称(客户数据)
	 * 
	 * @param compId
	 *            医院ID
	 * @return
	 */
	public static String getAppServiceKeyName(String compId, String bizName) {
		return getKeysName(KEY_APP_Service, compId, bizName);
	}

	/**
	 * 获得日常业务名称(客户数据)
	 * 
	 * @param compId
	 *            医院ID
	 * @return
	 */
	public static String getBizServiceKeyName(String compId, String bizName) {
		return getKeysName(KEY_Sys_Bussiness, compId, bizName);
	}

	/**
	 * 获得同步业务名称(客户数据)
	 * 
	 * @param compId
	 *            医院ID
	 * @return
	 */
	public static String getSyncDataKeyName(String compId, String bizName) {
		return getKeysName(KEY_Sync_Data, compId, bizName);
	}

	/**
	 * 获得同步结果
	 * 
	 * @param compId
	 *            医院ID
	 * @return
	 */
	public static String getSyncResultKeyName(String compId, String bizName) {
		return getKeysName(KEY_Sync_Result, compId, bizName);
	}

}
