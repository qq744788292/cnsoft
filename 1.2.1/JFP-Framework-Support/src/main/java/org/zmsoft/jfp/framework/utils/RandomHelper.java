package org.zmsoft.jfp.framework.utils;

import java.util.Random;

/**
 * 随机数验证码
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class RandomHelper {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			// nextDouble
			System.out.println(getRandomNumerical(1));
	}

	/**
	 * 获取随机字符串(数字加字符)
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	/**
	 * 获取随机字符串(英字小写)
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String getRandomZZ(int length) {
		StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	/**
	 * 获取随机字符串(英数字小写)
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String getRandomStringNN(int length) {
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	/**
	 * 根据长度获得随机数字
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNumerical(int length) {
		Random rd = new Random();
		String code = "" + rd.nextDouble();
		// System.out.print(code+"=");
		if (code.length() <= length + 2)
			return code.substring(2);
		return code.substring(2, length + 2);
	}

}
