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
		return DigestUtils.md5DigestAsHex(plainText.getBytes());
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(encrypt("wx038792acead673b41495451332"));
	}
	
}
