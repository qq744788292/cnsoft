package org.zmsoft.framework.security;

import org.springframework.util.DigestUtils;

/**
 * 加密解密工具类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <PasswordHelper>
 */
public class MD5SecurityHelper {

	/**
	 * 加密
	 * 
	 * @param plainText
	 *            需加密内容
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String plainText) throws Exception {
		return encrypt(plainText.getBytes());
	}
	public static String encrypt(byte[] data) throws Exception {
		return DigestUtils.md5DigestAsHex(data);
	}
	public static void main(String[] args) throws Exception {
		System.out.println(encrypt("123456"));//e10adc3949ba59abbe56e057f20f883e
		System.out.println("e672d7e60d9fbee05e8ec62cca78f8e6".equals(encrypt("yvonne1120")));
	}
	
}
