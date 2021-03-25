package org.zmsoft.framework.utils;

import java.util.UUID;

import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 系统唯一识别ID
 *
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class PKHelper implements ICFrameworkConstants {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			// System.out.println(creatTimeKey());
			System.out.println(creatUniqueKey());
		}

	}

	private static String serverId = "10";

	public static String getServerId() {
		return serverId;
	}

	public static void setServerId(String serverId) {
		PKHelper.serverId = serverId;
	}

	private static final long defaultNum = 10000l;

	private static long synchronizedNum = defaultNum;
	private static StringBuffer primaryUniqueKey;

	/**
	 * 获得一个PKID<br>
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatPUKey() {

		// return creatUUKey();

		return creatUniqueKey();
	}

	private static long systemTimeMillis = defaultNum;

	/**
	 * 获得一个数据记录的主键Key<br>
	 * 时间yyMMddHHmmss+序号
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatTimeKey() {
		long currentTimeMillis = DateHelper.currentTime("yyyyMMddHHmmss");
		// 同步判定
		if (currentTimeMillis > systemTimeMillis) {
			synchronizedNum = defaultNum;
			systemTimeMillis = currentTimeMillis;
		} else {
			synchronizedNum++;
		}
		return completion(systemTimeMillis, synchronizedNum);
	}

	private static long systemUniqueMillis = defaultNum;

	/**
	 * 获得一个数据记录的主键Key<br>
	 * 毫秒currentTimeMillis+序号
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatUniqueKey() {
		long currentTimeMillis = System.currentTimeMillis();
		// 同步判定
		if (currentTimeMillis > systemUniqueMillis) {
			synchronizedNum = defaultNum;
			systemUniqueMillis = currentTimeMillis;
		} else {
			synchronizedNum++;
		}
		return completion(systemUniqueMillis, synchronizedNum);
	}

	private static int keylength = 24;

	public static int getKeylength() {
		return keylength;
	}

	private static String completion(long time, long num) {

		// 日期+（小时分钟）+流水号
		primaryUniqueKey = new StringBuffer(keylength);
		primaryUniqueKey.append(serverId);
		primaryUniqueKey.append(time);

		return StringUtil.completion2(keylength, primaryUniqueKey.toString(), "0", ("" + synchronizedNum));
	}

	/**
	 * 获得一个UUID<br>
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatUUKey() {
		return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
	}
}