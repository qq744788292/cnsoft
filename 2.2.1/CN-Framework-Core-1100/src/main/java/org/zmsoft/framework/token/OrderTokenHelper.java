package org.zmsoft.framework.token;

import org.zmsoft.framework.utils.PKHelper;
import org.zmsoft.framework.utils.RandomHelper;

public class OrderTokenHelper {
	public static void main(String[] args) throws Exception {
		System.out.println(PKHelper.creatUUKey());
		System.out.println(loadToken(PKHelper.creatUniqueKey(), PKHelper.creatUUKey()));
	}

	public static String loadToken(String userid, String ordercode) throws Exception {
		// 获得一个随机数
		String code = RandomHelper.getRandomString(ordercode.length() - PKHelper.getKeylength());
		return TokenBusinessHelper.getBizTokenData(ordercode, userid + code);
	}

	public static String loadOrderCode(String ordertoken) throws Exception {
		return TokenBusinessHelper.getBizTokenData(ordertoken, 2)[0];
	}
}