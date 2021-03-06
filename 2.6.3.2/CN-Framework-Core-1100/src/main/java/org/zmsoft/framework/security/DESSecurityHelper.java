package org.zmsoft.framework.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * DES加解密算法工具类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <PasswordHelper>
 */
public class DESSecurityHelper implements ICFrameworkConstants {

	/**
	 * 加密
	 * 
	 * @param datasource
	 * @param password
	 * @return
	 */
	public static byte[] encrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes(SYSTEM_CHARSET));
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes(SYSTEM_CHARSET));
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}

	public static void main(String[] args) throws Exception {
		// 待加密内容
		String str = "测试内容12345";
		// 密码，长度要是8的倍数
		String password = MD5SecurityHelper.encrypt("12345678");
		byte[] result = encrypt(str.getBytes(SYSTEM_CHARSET), password);
		System.out.println("加密后内容为：" + new String(result));

		// 直接将如上内容解密
		try {
			byte[] decryResult = decrypt(result, password);
			System.out.println("加密后内容为：" + new String(decryResult));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
