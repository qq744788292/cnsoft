package org.zmsoft.framework.token;

import org.zmsoft.framework.security.PasswordHelper;
import org.zmsoft.framework.utils.PKHelper;
import org.zmsoft.framework.utils.RandomHelper;

public class OrderTokenHelper {
	public static void main(String[] args) throws Exception {
		String uuid = PKHelper.creatUniqueKey();
		String ordercode = PKHelper.creatUUKey();
		System.out.println(uuid);
		System.out.println(ordercode);
		System.out.println(loadToken(uuid, ordercode));
		System.out.println(loadOrderData(loadToken(uuid, ordercode))[0]);
		System.out.println(loadOrderData(loadToken(uuid, ordercode))[1]);
	}

	public static String loadToken(String userid, String ordercode) throws Exception {
		// 获得一个随机数(ordercode:UUID)
		int length = 32 - PKHelper.getKeylength();
		String code = RandomHelper.getRandomString(length);
		return TokenBusinessHelper.getBizTokenData(ordercode, userid + code);
	}

	public static String[] loadOrderData(String ordertoken) throws Exception {
		String[] ds = TokenBusinessHelper.getBizTokenData(ordertoken, 2);
		ds[1] = ds[1].substring(0,PKHelper.getKeylength());
		return ds;
	}

	public static String loadOrderCode(String ordertoken) throws Exception {
		return TokenBusinessHelper.getBizTokenData(ordertoken, 2)[0];
	}

	public static String loadOrderUserId(String ordertoken) throws Exception {
		return TokenBusinessHelper.getBizTokenData(ordertoken, 2)[1].substring(0,PKHelper.getKeylength());
	}
	
	public static String loadSafeOrderCode(String ordercode) throws Exception {
		return PasswordHelper.getPassword(ordercode);
	}
}