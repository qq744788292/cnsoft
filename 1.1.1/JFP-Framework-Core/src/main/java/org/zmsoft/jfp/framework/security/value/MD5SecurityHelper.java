package org.zmsoft.jfp.framework.security.value;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 加密解密工具类
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
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
		return new Md5PasswordEncoder().encodePassword(plainText, null);
	}
	public static void main(String[] args) throws Exception {
		System.out.println(encrypt("wx038792acead673b41495451332"));
	}
	
}
