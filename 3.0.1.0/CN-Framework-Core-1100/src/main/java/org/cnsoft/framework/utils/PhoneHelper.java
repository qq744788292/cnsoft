package org.cnsoft.framework.utils;

/**
 * 隐藏手机号码
 */
public class PhoneHelper {
	public static String getMaskPhone(String phone) throws Exception {
		char[] charArray = phone.toCharArray();
		charArray[2] = '*';
		charArray[4] = '*';
		charArray[6] = '*';
		charArray[8] = '*';
		charArray[10] = '*';
		return new String(charArray);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getMaskPhone("13012345678"));
	}
}
