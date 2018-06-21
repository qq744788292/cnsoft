package org.zmsoft.jfp.framework.key.common;

import java.util.Random;

import org.zmsoft.jfp.framework.constants.pub.IKeyConstants;
import org.zmsoft.jfp.framework.key.DefaultKeyService;

/**
 * 定时任务Key定义
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public class JobKeyService extends DefaultKeyService implements IKeyConstants {

	/**
	 * 获取范围内随机数
	 * 
	 * @return
	 */
	public static long getRandomMS() {
		int max = 10000;
		int min = 1000;
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}

	/**
	 * 获得对接业务名称(客户数据)
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static String getJobKeyName(String companyID, String bizName) {
		return getKeysName(EMPTY, companyID, bizName);
	}

	/**
	 * 获得对接业务名称(客户数据)
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static String getJobKeyName(String companyID, String bizName, String version) {
		// Key = 企业ID:业务名称(英字):对接程序所属版本号
		return getKeysName(version, companyID, bizName);
	}

	/**
	 * 主线程排他锁定
	 * 
	 * @param bizName
	 * @return
	 */
	public static String getJobKey(String bizName) {
		return getKeysName(SYSTEM_NAME, bizName);
	}

}
